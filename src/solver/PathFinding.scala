package solver

import maze.{GameMap, GridLocation, MapTile, PhysicsVector}
import scalafx.collections.ObservableMap.{Remove, empty}


object PathFinding {

  def ToID(gridLocation: GridLocation): Double = {
    val x: Int = (2*gridLocation.x) + 1
    val X: String = x.toString
    val y: Int = (2*gridLocation.y) + 1
    val Y: String = y.toString
    val IDString: String = X +"."+ Y
    IDString.toDouble
  }

  def ToGridLocation(ID: Double, pass: PassGraph): GridLocation = {
   pass.Tile.getOrElse(ID, new GridLocation(1,1))
  }

  def storeToPass( map: List[List[MapTile]]): PassGraph = {
    val lenRow: Int = map.length
    val numRow: Int = map.apply(0).length
    var pass: PassGraph = new PassGraph


    for (i <- 0 to lenRow-1){
      val y: Int = i
      for (j <- 0 to numRow - 1){
        val x: Int = j
        val maptile: MapTile =  map.apply(i).apply(j)
        if (maptile.passable){
          val gridLoc: GridLocation = new GridLocation(x,y)
          val ID: Double = ToID(gridLoc)
          pass.addTile(ID,gridLoc)
        }
      }
    }
  pass
  }

  def adjacentChecker(pass: PassGraph, map: List[List[MapTile]]): Unit = {
    val lenRow2: Int = map.length
    val lenRow:Double = 2*lenRow2 +1
    val numRow: Int = map.apply(0).length
    val IDs: List[Double] = pass.Tile.keySet.toList


    for(key <- 0 to IDs.length-1) {
      val loc: GridLocation = ToGridLocation(IDs(key),pass)
      //println(loc)

      //Checking Up in bounds
      if (loc.y < numRow-1){
        val updateLoc: GridLocation = new GridLocation(loc.x, loc.y +1)
        val fakeIDUp: Double = ToID(updateLoc)
        if (IDs.contains(fakeIDUp)){
         val key2: Double =  fakeIDUp
          val key1: Double = PathFinding.ToID(loc)
          pass.addEdge(key1,key2)
        }
      }
      //Checking down in bounds
      if (loc.y >= 3){
        val fakeIDDown: Double = ToID(new GridLocation(loc.x,loc.y - 1))
        if (IDs.contains(fakeIDDown)){
          val key2: Double = fakeIDDown
          val key1: Double = PathFinding.ToID(loc)
          pass.addEdge(key1,key2)
        }
      }
      //Checking right in bounds
      if(loc.x < numRow - 1){
        val fakeIDRight: Double = ToID(new GridLocation(loc.x + 1,loc.y))
        if (IDs.contains(fakeIDRight)){
          val key2: Double = fakeIDRight
          val key1: Double = PathFinding.ToID(loc)
          pass.addEdge(key1,key2)
        }
      }
      //Checking left in bounds
      if(loc.x >= 3){
        val fakeIDLeft: Double = ToID(new GridLocation(loc.x - 1, loc.y))
        if (IDs.contains(fakeIDLeft)){
          val key2: Double = fakeIDLeft
          val key1: Double = PathFinding.ToID(loc)
          pass.addEdge(key1,key2)
        }
      }
    }
  }



  def distinctAdjacency(pass: PassGraph): Unit = {
    val AdjLisInd: List[Double] = pass.adjacencyList.keySet.toList
    var newAdj: Map[Double,List[Double]] = Map()
    for (key <- 0 to AdjLisInd.length-1){
      val currentKey: Double = AdjLisInd(key)
      val adjacentNodes: List[Double] = pass.adjacencyList.getOrElse(currentKey,List(1.1))
      newAdj  = newAdj + (currentKey -> adjacentNodes.distinct)
      println(newAdj)
    }
    pass.adjacencyList = newAdj
  }

  def shortest(startID: Double, IDs: List[Double], pass: PassGraph): Double ={
    var short: Double = 100000000.0
    var ret: Double = 0.0
    for (id <- 0 to IDs.length - 1){
      if (pass.distance(startID,IDs(id)) < short){
        short = pass.distance(startID,IDs(id))
        ret = IDs(id)
      }
    }
    ret
  }

  def findPathHelper(startID: Double,endID: Double,map: List[List[MapTile]],retLis: List[GridLocation], pass: PassGraph): List[GridLocation] = {
    if (pass.distance(startID,endID) == 0.0){
      retLis
    }
    else{
      val adjLis: List[Double] = pass.adjacencyList.getOrElse(endID,List())
      val adjLis2: List[Double] = adjLis.distinct
      val nextNode: Double = shortest(startID,adjLis2,pass)
      val nextLoc: GridLocation = ToGridLocation(nextNode,pass)
      val RetLis: List[GridLocation] = nextLoc :: retLis
      findPathHelper(startID,nextNode,map,RetLis,pass)
    }
  }

  def findPath(start: GridLocation, end: GridLocation, map: List[List[MapTile]]): List[GridLocation] = {
    val startID: Double = ToID(start)
    val endID: Double = ToID (end)
    var retLis: List[GridLocation] = List(end)
    val pass: PassGraph = storeToPass(map)
    if (pass.Tile.contains(endID)){
    adjacentChecker(pass, map)
    findPathHelper(startID,endID,map,retLis,pass)
    }
    else{
      List(start)
    }

  }

  def whereAmI(path: List[GridLocation], currentLocation: PhysicsVector): GridLocation = {

    var distance: Double = 23.3
    var nodeOnPath: GridLocation = GameMap.apply().startingLocation
    for ( gridIn <- 0 to path.length-1){
      val currx: Double = currentLocation.x - 0.5
      val curry: Double = currentLocation.y - 0.5
      val dis: Double = math.abs( currx - path(gridIn).x) + math.abs(curry - path(gridIn).y)
      if (dis < distance){
        nodeOnPath = path(gridIn)
        distance = dis
      }
    }
    nodeOnPath
  }



  def determineNextTile(path: List[GridLocation], currentLocation: PhysicsVector): GridLocation = {
    val currentLoc: GridLocation = whereAmI(path, currentLocation)
    val indexOfCurrent: Int = path.indexOf(currentLoc)
    if (!(indexOfCurrent == path.length-1)){

      path(indexOfCurrent+1)
    }
    else{
      currentLoc
    }
  }

  def retVelocity(path: List[GridLocation], currentLocation: PhysicsVector, nextLocation: GridLocation): PhysicsVector = {
  val nextLocationVector: PhysicsVector = new PhysicsVector(nextLocation.x + 0.5,nextLocation.y+ 0.5,0.0)
  var velocity: PhysicsVector = new PhysicsVector(0.0,0.0,0.0)
  if (nextLocationVector.distance2d(currentLocation) > 0.1){
    val newVelocityX: Double = nextLocationVector.x - currentLocation.x
    val newVelocityY: Double = nextLocationVector.y - currentLocation.y
    var nonUnitvelocity: PhysicsVector = new PhysicsVector(newVelocityX,newVelocityY,0.0)
    val Unitvelocity: PhysicsVector = nonUnitvelocity.normal2d()
    val FinalX: Double = Unitvelocity.x * 5
    val FinalY: Double = Unitvelocity.y * 5
    velocity = new PhysicsVector(FinalX,FinalY,0.0)
   }
    velocity
  }

  def getVelocity(path: List[GridLocation], currentLocation: PhysicsVector): PhysicsVector = {
    var velocity: PhysicsVector = new PhysicsVector(0.0,0.0,0.0)
    val currentLoc: GridLocation = whereAmI(path, currentLocation)
    val nextLocation: GridLocation = determineNextTile(path,currentLocation)
    val retVector: PhysicsVector = retVelocity(path, currentLocation, nextLocation)
    retVector
  }

}
