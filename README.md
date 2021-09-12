# Maze_Solver
*Maze Solver is a click and follow type game. The blue dot will navigate the maze to the player’s click location. This project utilizes the breath first search algorithm along with graphs, queues,and linked list data structures.*


The approach I took was to have the blue dot travel along a plane identical to the tiles on the map. This means that the blue dot refers to the tiles to navigate but the actual physics representation of the blue dot it tied to a plane above the grid

  

This plane can be best described in the PassGraph class. Where a variety of methods are used to determine the path and location of the blue dot.

**Disclaimer I did not write all the code in this repository!**
More on accreditation in the acknowledgment section.

## Linked Lists
The linkedListNode class is a shined up version of a linkedList. It contains functionality to add to itself along with being able to add and subtract elements.

## Queue
The Queue class utilizes the LinkedListNode to build a standard first in, first out queue. This is used in BFS later in the PathFinding class.

## PathFinding
PathFinding is one of the bigger files in this project. This is due to the fact that it preforms most of the work.

 - The storeToPass, adjacencyChecker, distinctAdjacency are utilized to create the pane on which the blue dot travels. This prevents the blue dot from ever traveling on a wall tile.
 - The next section of code is used to determine the shortest path for the blue dot to take. Admittedly this could be cleaner, however it utilizes recursive elements in a bfs like manner.
 - The retVelocity method tells the blue dot what direction to move based upon the physics constraints in the Physics vector class. This is called repeatedly until the goal tile is met.
## Acknowledgment section
I made this project as part of UB’s CSE 116 curriculum. I utilized office hours and course notes to create the code I wrote. I am only posting this thanks to Dr. Hartloff leniency towards past students. Any other code not explicitly stated was written by him and the CSE 116 staff as part of the handout. That being said, I have a good understanding of how the code they wrote works. Since it’s comprehension was necessary for completion of the assignment.

## I endorse test driven development
Please feel free to take a look at my unit testing. I believe that test driven development is a critical part of software engineering. Therefore I write test cases for all of my functions. I found that this is helpful for incremental development. It’s a critical part of the way I develop code.

## Author's note
I coded this project rather early on in my software development career. Therefore my choice of data structures and algorithms may not have been the most efficient. I have since improved my development skills. I may periodically revise this project to update it with more efficient algorithms and structures.

 - Marcos De La Osa Cruz
