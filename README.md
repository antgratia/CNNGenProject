# Topological Architecture Exploration and Neuroevolution for Energy-aware Neural Architecture Search
Deep learning has become an extremely important technology in numerous domains such as computer vision, natural language processing, and autonomous systems. As neural networks grow in size and complexity to meet the demands of these applications, the cost of designing and training efficient models continues to rise in computation and energy consumption. Neural Architecture Search (NAS) has emerged as a promising solution to automate the design of performant neural networks. However, conventional NAS methods often require evaluating thousands of architectures, making them extremely resource-intensive and environmentally costly.
This thesis introduces a novel, energy-aware NAS pipeline that operates at the intersection of Software Engineering and Machine Learning. We present CNNGen, a domain-specific generator for convolutional architectures, combined with performance and energy predictors to drastically reduce the number of architectures that need full training. These predictors are integrated into a multi-objective genetic algorithm (NSGA-II), enabling an efficient search for architectures that balance accuracy and energy consumption.
Our approach explores a variety of prediction strategies, including sequence-based models, image-based representations, and deep metric learning, to estimate model quality from partial or symbolic representations. We validate our framework across three benchmark datasets, CIFAR-10, CIFAR-100, and Fashion-MNIST, demonstrating that it can produce results comparable to state-of-the-art architectures with significantly lower computational cost. By reducing the environmental footprint of NAS while maintaining high performance, this work contributes to the growing field of Green AI and highlights the value of predictive modelling in scalable and sustainable deep learning workflows.

# CNN Description Language

# Librairies
- eclipse
- xtext/xtend
- lombok
- Jmetal 6.2.2

# Tree Description

- Folders ide, ui, ui.tests auto generate by text, no change was apport

- Folder utils contains 3 files 
    - FonctionStringPy contains all functions to translate the grammar to Python
    - GestionHppNeo4j contains all the functions to manage the Hyperparameters using Neo4j
    - GestionWay is used for the management of Merge word in grammar

- Folder view contains the file that makes the bridge between Neo4j project and the xtext project

- Folder Xtext contains all the generated files from xtext 
    - SML.xtext is the file that contains the grammar
    - SMLGenerator.xtend is the file that generates 1 instance of the grammar to Python file


- Folder DBManager contains the main source files and test files.
    - controllers contains an interface for each service, & controllersImpl the implementation of those interfaces
    - Services contain interfaces for access to the database for each domain class & serviceImpl is the implementation of those services
    - domain contains each specific node for neo4j and annotation for neo4j OGM

- Folder generator contains two files 
    - main file that can be executed to generate N architectures
    - GenerotorUtils that contains the logic for the generations
    - The folder output contains 5 folders
        - architecture_csv : some results about the training of architectures
        - architecture_img : img of the architecture (keras plot)
        - architecture_log : history of the training or errors
        - architecture_py : python code
        - architecture_tb : tensorboard information
        - architecture_sml : description of the architecture (grammar)
        - architecture_emissions : emission informations
     
- Folder genetic contains all the logics with NSGA-II
    - GenerotorGeneticUtils that contains the logic for the generations
    - Folder operation, which contains the implementation of Crossover, mutation and evaluation
    - Folder genetic contains how the problem will be solved
    - main file that can be executed to execute the Genetic Algorithm.
 
