package tests
import org.scalatest._
import maze._
import solver._
import tests.TestingMaps
class TestingValidPaths extends FunSuite {


  //Sample Test Case 1
  //Use TestingMap level 1 (This map contains impassable tiles)
  //Start: (15, 10)
  //End: (15, 4)
  test("Sample Path 1 - with impassable Tiles"){
    val map: List[List[MapTile]] = TestingMaps.apply(1).tiles
    val start: GridLocation = new GridLocation(15,10)
    val end: GridLocation = new GridLocation(15,4)
    print(PathFinding.findPath(start, end, map))
  }


  //Sample Test Case 2
  //Use TestingMap level 1
  //Start: (15, 4)
  //End: (22, 9)

  test("Sample Path 2 - with impassable Tiles"){
    val map: List[List[MapTile]] = TestingMaps.apply(1).tiles
    val start: GridLocation = new GridLocation(15,4)
    val end: GridLocation = new GridLocation(22,9)
    print(PathFinding.findPath(start, end, map))
    val StartID: Double = PathFinding.ToID(start)
    val EndID: Double = PathFinding.ToID(end)
   
  }

  //Sample Test Case 3
  //Use TestingMap level 1
  //Start: (22, 9)
  //End: (28, 6)
  test("Sample Path 3 - with impassable Tiles"){
    val map: List[List[MapTile]] = TestingMaps.apply(1).tiles
    val start: GridLocation = new GridLocation(22,9)
    val end: GridLocation = new GridLocation(28,6)
    print(PathFinding.findPath(start, end, map))
  }

  test(" skipping to goal :/"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val start: GridLocation = GameMap.apply().startingLocation
    val end: GridLocation = new GridLocation(27,11)
    print(PathFinding.findPath(start, end, map))
  }

  test(" Checking that black squares are not in Tiles list"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val grid: Map[Double,GridLocation] = PathFinding.storeToPass(map).Tile
    println(grid.keySet.toList.sorted)
  }
  test("Testing adjacentcy of odds"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    PathFinding.adjacentChecker(pass,map)
    PathFinding.distinctAdjacency(pass)
    println(pass.adjacencyList)

  }

  test("Testing adjacentcy of odds 2"){
    val map: List[List[MapTile]] = TestingMaps.apply(0).tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    PathFinding.adjacentChecker(pass,map)
    println(pass.adjacencyList)
  }
  test("Testing adjacentcy of oddsc"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    val start:GridLocation = new GridLocation(1,10)
    val end: GridLocation = new GridLocation(10,9)

    println(PathFinding.findPath(start,end,map))
  }
  test("Testing circulat paths"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    val start:GridLocation = new GridLocation(9,9)
    val end: GridLocation = new GridLocation(10,9)

    println(PathFinding.findPath(start,end,map))
  }
  test("Testing circular paths"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    val start:GridLocation = new GridLocation(9,9)
    val end: GridLocation = new GridLocation(10,10)

    println(PathFinding.findPath(start,end,map))
  }
  test("Testing circulars paths"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    val start:GridLocation = new GridLocation(9,9)
    val end: GridLocation = new GridLocation(9,9)

    println(PathFinding.findPath(start,end,map))
  }
  test("Testing circulate paths"){
    val map: List[List[MapTile]] = GameMap.apply().tiles
    val pass: PassGraph = PathFinding.storeToPass(map)

    val start:GridLocation = new GridLocation(10,9)
    val end: GridLocation = new GridLocation(10,9)

    println(PathFinding.findPath(start,end,map))
  }


}
