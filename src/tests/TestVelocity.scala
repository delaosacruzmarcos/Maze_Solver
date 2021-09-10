package tests

import maze.{GridLocation, PhysicsVector}
import org.scalatest.FunSuite
import solver._
import maze._
class TestVelocity extends FunSuite {

  val EPSILON: Double = 0.01

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  def equalsVectors(v1: PhysicsVector, v2: PhysicsVector): Boolean = {
    equalDoubles(v1.x, v2.x) && equalDoubles(v1.y, v2.y) && equalDoubles(v1.z, v2.z)
  }

  //A path of (1,2) -> (2,2) -> (3,2)
  //A location of (1.5, 2.5), which is the center of the first tile in the path
  test("test path to Velocity 1") {
    val path: List[GridLocation] = List(new GridLocation(1,2), new GridLocation(2,2), new GridLocation(3,2))
    val currentLocation: PhysicsVector = new PhysicsVector(1.5,2.5,0.0)
    val correctLocation: PhysicsVector = new PhysicsVector(1.0,0.0,0.0).normal2d()
    correctLocation.x = correctLocation.x * 5
    correctLocation.y = correctLocation.y * 5
    val computed: PhysicsVector = PathFinding.getVelocity(path,currentLocation)
    assert(equalsVectors(computed,correctLocation))
    println("computed  " + computed + "  Correct  "+ correctLocation)
  }

  //Sample Test Case 2
  //A path of (11,12) -> (10,12) -> (9,12) -> (9,13) -> (9,14) -> (10,14)
  //A location of (9.55, 14.1)
  test("test path to Velocity 2") {
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13), new GridLocation(9,14), new GridLocation(10,14))
    val currentLocation: PhysicsVector = new PhysicsVector(9.55,14.1,0.0)
    val correctLocation: PhysicsVector = new PhysicsVector(0.95,0.4,0.0)
    val computed: PhysicsVector = PathFinding.getVelocity(path,currentLocation)
//    assert(equalsVectors(computed,correctLocation))
    println("computed  " + computed + "  Correct  "+ correctLocation)
  }
  test("test path to Velocity 22") {
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13), new GridLocation(9,14), new GridLocation(10,14))
    val currentLocation: PhysicsVector = new PhysicsVector(9.50,14.5,0.0)
    val correctLocation: PhysicsVector = new PhysicsVector(5.0,5.0,0.0)
    val computed: PhysicsVector = PathFinding.getVelocity(path,currentLocation)
    //    assert(equalsVectors(computed,correctLocation))
    println("computed  " + computed + "  Correct  "+ correctLocation)
  }

  //Sample Test Case 3
  //A path of (11,12) -> (10,12) -> (9,12) -> (9,13) -> (9,14) -> (10,14)
  //A location of (10.48, 14.49)
  test("test path to Velocity 3") {
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13), new GridLocation(9,14), new GridLocation(10,14))
    val currentLocation: PhysicsVector = new PhysicsVector(10.48,14.49,0.0)
    val correctLocation: PhysicsVector = new PhysicsVector(0.02,0.01,0.0)
    val computed: PhysicsVector = PathFinding.getVelocity(path,currentLocation)
    assert(equalsVectors(computed,correctLocation))
    println("computed  " + computed + "  Correct  "+ correctLocation)
  }


  //Sample Test Case 4
  //A path of (11,12) -> (10,12) -> (9,12) -> (9,13) -> (9,14) -> (10,14)
  //A location of (10.9, 14.52)
  test("test path to Velocity 4") {
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13), new GridLocation(9,14), new GridLocation(10,14))
    val currentLocation: PhysicsVector = new PhysicsVector(10.9,14.52,0.0)
    val correctLocation: PhysicsVector = new PhysicsVector(-0.4,-0.02,0.0)
    val computed: PhysicsVector = PathFinding.getVelocity(path,currentLocation)
  //  assert(equalsVectors(computed,correctLocation))
    println("computed  " + computed + "  Correct  "+ correctLocation)
  }
  test("Where am I? when on a path at start" ){
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13))
    val currentLocation : PhysicsVector = new PhysicsVector(11.5,12.5,0.0)
    val correctLocation: GridLocation = new GridLocation(11,12)
    println(PathFinding.whereAmI(path,currentLocation))
    assert(PathFinding.whereAmI(path,currentLocation) == correctLocation, " Should return gridlocation (11,12")
  }

  test("Where am I? when on a path at second node but close to first" ){
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13))
    val currentLocation : PhysicsVector = new PhysicsVector(10.3,12.5,0.0)
    val correctLocation: GridLocation = new GridLocation(10,12)
    println(PathFinding.whereAmI(path,currentLocation))
    assert(PathFinding.whereAmI(path,currentLocation) == correctLocation, " Should return gridlocation (10,12)")
  }

  test("Where am I? when on a path at last node" ){
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13))
    val currentLocation : PhysicsVector = new PhysicsVector(9.5,13.5,0.0)
    val correctLocation: GridLocation = new GridLocation(9,13)
    println(PathFinding.whereAmI(path,currentLocation))
    assert(PathFinding.whereAmI(path,currentLocation) == correctLocation, " Should return gridlocation (9,13)")
  }

  test("determinenext Tile ~ when its the first tile" ){
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13))
    val currentLocation : PhysicsVector = new PhysicsVector(11.5,12.5,0.0)
    val correctLocation: GridLocation = new GridLocation(10,12)
    println(PathFinding.determineNextTile(path,currentLocation))
    assert(PathFinding.determineNextTile(path,currentLocation) == correctLocation, " Should return gridlocation (10,12)")
  }
  test("determinenext Tile ~ last tile" ){
    val path: List[GridLocation] = List(new GridLocation(11,12), new GridLocation(10,12), new GridLocation(9,12), new GridLocation(9,13))
    val currentLocation : PhysicsVector = new PhysicsVector(9.5,13.5,0.0)
    val correctLocation: GridLocation = new GridLocation(9,13)
    println(PathFinding.determineNextTile(path,currentLocation))
    assert(PathFinding.determineNextTile(path,currentLocation) == correctLocation, " Should return gridlocation (9,13)")
  }
  test("Testing not a valid tile"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    val start:GridLocation = new GridLocation(1,10)
    val end: GridLocation = new GridLocation(27,10)

    println(PathFinding.findPath(start,end,map))
  }




}
