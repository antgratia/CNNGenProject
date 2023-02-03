# stateMachineLanguage

# StateMachineLanguage

# Librairies
- eclipse
- xtext/xtend
- lombok

# Tree Description

- Folders ide, ui, ui.tests auto generate by xtext no change apport

- Folder utils contains 3 files 
    - FonctionStringPy contains all functions to translate grammar to python
    - GestionHppNeo4j contains all the functions to manage the Hyperparameters using neo4j
    - GestionWay is used for the management of Merge word in grammar

- Folder view contains the file that make the bridge between Neo4j project and the xtext project

- Folder Xtext contains all the genereted file from xtex 
    - SML.xtext is the file that contains the grammar
    - SMLGenerator.xtend is the file that generate 1 instance of the grammar to python file


- Folder DBManager contains main source files and test files.
    - controllers contains interface for each service & controllersImpl the implementation of those interface
    - services contains interface for the access to the db for each domain class & serviceImpl the implementation of those services
    - domain contains each specific nodes for neo4j and anotate for neo4j OGM

- Folder generator contains two files 
    - main file that can be execute to generate N architectures
    - generotorUtils that contains the logic in the generations
    - Folder output contains 5 folders
        - architecture_csv : some results about training of architectures
        - architecture_img : img of the architecture (keras plot)
        - architecture_log : history of the training or errors
        - architecture_py : python code
        - architecture_tb : tensorboard information
        - architecture_sml : description of the architecture (grammar)
        - emissions : emission informations
