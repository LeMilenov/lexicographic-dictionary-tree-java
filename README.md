# lexicographic-dictionary-tree-java
Manipulate a list of words with a binary tree in java
# launch the project
1- download the project files
2- run the main file (lexicographic-dictionary-tree-java/src/main/java/lexicoTree/Main.java)
# options
1- you can Load/ Save a list of word with this format : word & definition. 
   (there is a sample file called dictio.txt in the project.)
2- you can add words by clicking on the "ajouter/modifier" button. To add a word you will need to type it and give it a definition
3- you can search for words by writing letter by letter in the search box
4- you can modify a current word`s definition by adding text to it and clicking on the "ajouter/modifier" button
5- the far right panel shows all the possible words

# technology

-this project was made in Java and is using a node tree (data structure where each node references it`s own childs)  
-When the app loads a valid txt file, it take each word, splits in letters and adds each letter in the tree and then adds the definition in the last node added.
- the letters are added following this logic : we start at the root node -> we check his childrens and go to the position where the current`s node value is the current letter that we want to add. if this children doesn`t exist, we add a new children that has the corresponding value and we repeat the logic for the next letter.

