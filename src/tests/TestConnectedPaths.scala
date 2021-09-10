package tests
import maze.GridLocation
import org.scalatest._
import solver._
import tests.TestingMaps
class TestConnectedPaths extends FunSuite {

  test("Testing Store to Pass"){
    val map = TestingMaps(0).tiles
    val pass =  PathFinding.storeToPass(map)
    print(pass)
    print(pass.Tile.keySet.toList.sorted)
  }
  test(" Testing Conversion Functions"){
    val map = TestingMaps(0).tiles
    val pass = PathFinding.storeToPass(map)
    println(PathFinding.ToGridLocation(3.12,pass))

    val loc: GridLocation = pass.Tile.getOrElse(7.15,new GridLocation(4,12))
    println(PathFinding.ToID(loc))
  }
  test("Testing edge Set up"){
    val map = TestingMaps(0).tiles
    val pass = PathFinding.storeToPass(map)
    val edges = PathFinding.adjacentChecker(pass,map)
    println(pass.adjacencyList)
    PathFinding.distinctAdjacency(pass)
    println(pass.adjacencyList)
  }

  test("Sample test 1"){
    val map = TestingMaps(0).tiles
    val starts: GridLocation = new GridLocation(3,12)
    val ends: GridLocation = new GridLocation(3,8)
    println(PathFinding.findPath(starts,ends,map))
  }

  //Sample Test Case 2
  //Use TestingMap level 0
  //Start: (5,5)
  //End: (8,8)
test("Sample Test Case 2"){
  val map = TestingMaps(0).tiles
  val starts: GridLocation = new GridLocation(5,5)
  val ends: GridLocation = new GridLocation(8,8)
  println(PathFinding.findPath(starts,ends,map))
}


}
