import torch
from torch import nn
import numpy as np
import time


def network_weight_gaussian_init(net: nn.Module):
    with torch.no_grad():
        for m in net.modules():
            if isinstance(m, nn.Conv2d):
                nn.init.normal_(m.weight)
                if hasattr(m, 'bias') and m.bias is not None:
                    nn.init.zeros_(m.bias)
            elif isinstance(m, (nn.BatchNorm2d, nn.GroupNorm)):
                nn.init.ones_(m.weight)
                nn.init.zeros_(m.bias)
            elif isinstance(m, nn.Linear):
                nn.init.normal_(m.weight)
                if hasattr(m, 'bias') and m.bias is not None:
                    nn.init.zeros_(m.bias)
            else:
                continue

    return net


def compute_zen_score(list_layer , filename, batch_size, resolution, mixup_gamma):
    info = {}
    nas_score_list = []
    time_start = time.time()

    with torch.no_grad():
        for repeat_count in range(2):
            model, dropout, pooling, nb_layer = create_model(list_layer)
            network_weight_gaussian_init(model)
            input = torch.randn(size=[batch_size, 3, resolution, resolution])
            input2 = torch.randn(size=[batch_size, 3, resolution, resolution])
            mixup_input = input + mixup_gamma * input2
            output = model(input)
            mixup_output = model(mixup_input)

            nas_score = torch.sum(torch.abs(output - mixup_output), dim=[1, 2, 3])
            nas_score = torch.mean(nas_score)

            # compute BN scaling
            log_bn_scaling_factor = 0.0
            for m in model.modules():
                if isinstance(m, nn.BatchNorm2d):
                    bn_scaling_factor = torch.sqrt(torch.mean(m.running_var))
                    log_bn_scaling_factor += torch.log(bn_scaling_factor)
                pass
            pass
            nas_score = torch.log(nas_score) + log_bn_scaling_factor
            nas_score_list.append(float(nas_score))


    std_nas_score = np.std(nas_score_list)
    avg_precision = 1.96 * std_nas_score / np.sqrt(len(nas_score_list))
    avg_nas_score = np.mean(nas_score_list)


    time_glo = (time.time() - time_start)  
    time_cost = time_glo/32
    info['filename'] = filename
    info['pooling'] = pooling
    info['dropout'] = dropout
    info['nb_layer'] = nb_layer
    info['nb_layer_tot'] = nb_layer + pooling + dropout
    info['time_glo'] = time_glo
    info['time_cost'] = time_cost
    info['avg_nas_score'] = float(avg_nas_score)
    info['std_nas_score'] = float(std_nas_score)
    info['avg_precision'] = float(avg_precision)

    return info

def create_model(list_model):
    module = []
    for i in list_model:
         if (i == "conv"):
             module.append(nn.Conv2d(16,16,2,2,1))

    return nn.Sequential(*module),0,0,5