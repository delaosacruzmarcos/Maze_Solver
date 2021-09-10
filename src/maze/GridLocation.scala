package maze

class GridLocation(val x: Int, val y: Int){

  // optional state variable
  override def toString = s"($x, $y)"


  // When testing this switches the == so it works. gridlocation1 == gridlocation2
  override def equals(that: Any): Boolean = {
    that match {
      case other: GridLocation =>
        this.x == other.x && this.y == other.y
      case _ => false
    }
  }
}
