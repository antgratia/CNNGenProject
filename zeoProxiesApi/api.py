import flask 
#import numpy as np
from flask import request, jsonify
from flask_cors import CORS

#from zero_shot_proxies.compute_zen_score_torch import compute_zen_score
from zero_shot_proxies.compute_zen_score_tf import compute_zen_score

app = flask.Flask(__name__)
cors = CORS(app, resources={r"/api/v1/*": {"origins": "*"}})
    
@app.route('/', methods=['GET'])
def hello():
    return jsonify('bjr')

@app.route('/api/v1/zenScore', methods=['POST'])
def zenScore():

    print(request.json)
    architecture_data = request.json

    info = compute_zen_score(
        architecture_data['architecture'].split(), 
        architecture_data['stride'].split(), 
        architecture_data['filename'],
        16,
        28,
        1e-2,
        32)
    print(info)
    return jsonify(info)

app.run()