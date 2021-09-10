package maze

object MapTile {
// Use this to create new rows
  def generateRow(row: String): List[MapTile] = {
    row.map((ch: Char) => MapTile(ch.toString)).toList
  }
// used to generate the map tile class
  def apply(tileType: String): MapTile = {
    tileType match {
      case "-" => new MapTile("ground", true)
      case "G" => new MapTile("goal", true)
      case "O" => new MapTile("wall", false)
    }
  }

}

class  MapTile(val tileType: String, val passable: Boolean) {

}
