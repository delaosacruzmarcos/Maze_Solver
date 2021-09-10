package tests

import maze.{GridLocation, MapTile}
import play.api.libs.json.{JsValue, Json}


object TestingMaps {

  def apply(number: Int): TestingMaps ={
    if(number == 0){
      new TestingMaps{
        tiles = List(
          MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"),//0
          MapTile.generateRow("O----------------------------O"),//1
          MapTile.generateRow("O----------------------------O"),//2
          MapTile.generateRow("O----------------------------O"),//3
          MapTile.generateRow("O----------------------------O"),//4
          MapTile.generateRow("O----------------------------O"),//5
          MapTile.generateRow("O----------------------------O"),//6
          MapTile.generateRow("O----------------------------O"),//7
          MapTile.generateRow("O----------------------------O"),//8
          MapTile.generateRow("O----------------------------O"),//9
          MapTile.generateRow("O----------------------------O"),//10
          MapTile.generateRow("O----------------------------O"),//11
          MapTile.generateRow("O----------------------------O"),//12
          MapTile.generateRow("O----------------------------O"),//13
          MapTile.generateRow("O----------------------------O"),//14
          MapTile.generateRow("O----------------------------O"),//15
          MapTile.generateRow("O----------------------------O"),//16
          MapTile.generateRow("O----------------------------O"),//17
          MapTile.generateRow("O----------------------------O"),//18
          MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")//19
        )
        startingLocation = new GridLocation(1, 3)
      }
    }else if(number == 1) {
      new TestingMaps{
        tiles = List(
          MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"),//0 there are 30 columns and 20 rows
          MapTile.generateRow("O----------------------------O"),//1
          MapTile.generateRow("O----------------------------O"),//2
          MapTile.generateRow("O----------------------------O"),//3
          MapTile.generateRow("O----------------------------O"),//4
          MapTile.generateRow("O-------------OOOO-----------O"),//5
          MapTile.generateRow("O---------OOOOOOOOOOO--------O"),//6
          MapTile.generateRow("O--------OOO------OOOOOO-----O"),//7
          MapTile.generateRow("O-------OO----------OOO------O"),//8
          MapTile.generateRow("O-------OOO----------O-------O"),//9
          MapTile.generateRow("O--------OO---------OOO------O"),//10
          MapTile.generateRow("O---------OOO-------OOO------O"),//11
          MapTile.generateRow("O--------------OOOOOOOOOOOO--O"),//12
          MapTile.generateRow("O---------OOOOOOOO-----------O"),//13
          MapTile.generateRow("O---------------OOOOO--------O"),//14
          MapTile.generateRow("O---------------OOOOO--------O"),//15
          MapTile.generateRow("O----------------------------O"),//16
          MapTile.generateRow("O----------------------------O"),//17
          MapTile.generateRow("O----------------------------O"),//18
          MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")//19
        )

        startingLocation = new GridLocation(15, 10)

      }
    }else if(number == 2) {
      new TestingMaps{
        tiles = List(
          MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"),//0 there are 30 columns and 20 rows
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//1
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//2
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//3
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//4
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//5
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//6
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//7
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//8
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//9
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//10
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//11
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//12
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//13
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//14
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//15
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//16
          MapTile.generateRow("O-O-O-O-O-O-O-O-O-O-O-O-O-O--O"),//17
          MapTile.generateRow("O----------------------------O"),//18
          MapTile.generateRow("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")//19
        )

        startingLocation = new GridLocation(1, 10)

      }
    }else{
      new TestingMaps
    }
  }

}
class TestingMaps {
  var tiles:List[List[MapTile]] = List()
  var startingLocation = new GridLocation(0, 0)

  def tilesJSON(): JsValue = {
    Json.toJson(tiles.map((row: List[MapTile]) => row.map((tile: MapTile) => Json.toJson(Map("type" -> Json.toJson(tile.tileType), "passable" -> Json.toJson(tile.passable))))))
  }

}
