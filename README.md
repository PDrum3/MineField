# MineField
This is a program that messes around with genetic creation of objects in order to solve a goal.
In this case, the goal is to get through the "minefield", a 2d array of 1s and 0s where 1s are mines are 0s are safe
Each object has a String with 'u's, 'd's, 'l's, and 'r's, and these stand for the four cardinal directions
An object is tested on how well it performs in the minefield by taking each of the characters from the string 1 by 1 and decoding them to mean "move up/down/left/right" in the minefield
Each object is assigned a score depending on how well it does. The top two scoring objects are then mashed together a few times to create a new group of objects that try to beat the scores of the "generation" before them.
If the algorithm ever gets stuck in the same spot for 100s of generations, it will restart from the beginning.

This sort of works in some cases, but I still need to figure out what makes up how an object is scored better. At the present the score is generated by how many the object moves forwards plus one half the number of moves it takes, but it refuses to go down because it would mean a big hit to its score.
