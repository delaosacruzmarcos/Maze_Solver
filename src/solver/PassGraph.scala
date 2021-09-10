package solver

import maze.GridLocation

class PassGraph {

  var Tile: Map[Double,GridLocation] = Map()
  var adjacencyList: Map[Double, List[Double]] = Map()

  //Used to create the tiles list
  def addTile(index: Double, a: GridLocation): Unit = {
    Tile += index -> a
    adjacencyList += index -> List()
  }


  // addEdge function from the examples repo
  def addEdge(index1: Double, index2: Double): Unit = {
    adjacencyList += index1 -> (index2 :: adjacencyList(index1))
    adjacencyList += index2 -> (index1 :: adjacencyList(index2))
  }

  // areConnected function from the LQ
  def areConnected(index1: Double, index2: Double): Boolean = {
    var explored: Set[Double] = Set(index1)
    val toExplore: Queue[Double] = new Queue()
    toExplore.enqueue(index1)
    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- adjacencyList(nodeToExplore)) {
        if (!explored.contains(node)) {
          println(node)
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }
  if (explored.contains(index2)) {
    true
  }
  else {
    false
  }
}


  // Distance function from the LQs
  def distance(index1: Double, index2: Double): Double = {
    val dist: Double = 0.0
    var explored: Set[Double] = Set(index1)
    var distance: Map[Double, Double] = Map()
    distance += index1 -> dist
    val toExplore: Queue[Double] = new Queue()
    toExplore.enqueue(index1)
    while (!toExplore.empty()) {
      val nodeToExplore = toExplore.dequeue()
      for (node <- this.adjacencyList(nodeToExplore)) {
        if(!explored.contains(node)){
          val newD: Double = distance.getOrElse(nodeToExplore,0.0) + 1
          distance += node -> newD
          toExplore.enqueue(node)
          explored = explored + node
        }
      }
    }
    distance.getOrElse(index2,0.0)
  }
}
