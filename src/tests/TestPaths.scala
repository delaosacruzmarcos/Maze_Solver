package tests

import maze.GridLocation
import org.scalatest._
import tests.TestingMaps
import solver.PathFinding
class TestPaths extends FunSuite {


  test("test path 1") {
    val level: Int = 0
    TestingMaps(level).tiles
  }

  test("Sample Test case 1"){
    //Use the level zero no impassable tiles
    val level: Int = 0
    val map = TestingMaps(level).tiles
    TestingMaps(level).startingLocation = new GridLocation(3,12)
    // Ending location
    val Ending: GridLocation = new GridLocation(3,8)
    // Path given by function findPath
    val found: List[GridLocation] = PathFinding.findPath(TestingMaps(level).startingLocation, Ending, map)
    assert(found.length == 4, "The distance from 12 to 8 should be 4")
  }

  test("Sample Test case 2"){
    //Use the level zero no impassable tiles
    val level: Int = 0
    val map = TestingMaps(level).tiles
    TestingMaps(level).startingLocation = new GridLocation(5,5)
    // Ending location
    val Ending: GridLocation = new GridLocation(8,8)
    // Path given by function findPath
    val found: List[GridLocation] = PathFinding.findPath(TestingMaps(level).startingLocation, Ending, map)
    assert(found.length == 4, "The distance from (5,5) to (8,8) should be 4")
  }


}