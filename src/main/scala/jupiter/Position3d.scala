package jupiter


case class Position3d(x: Int, y: Int, z: Int) {
  def + (other: Position3d) = Position3d(x = x + other.x, y = y + other.y, z = z + other.z)
}

object Position3d {
  val Origin: Position3d = Position3d(x = 0, y = 0, z = 0)
}
