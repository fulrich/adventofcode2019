package jupiter


case class Moon(position: Position3d, velocity: Position3d = Position3d.Origin) {
  lazy val potentialEnergy: Int = energyOf(position)
  lazy val kineticEnergy: Int = energyOf(velocity)
  lazy val totalEnergy: Int = potentialEnergy * kineticEnergy

  lazy val noVelocity: Boolean = velocity.x == 0 && velocity.y == 0 && velocity.z == 0

  def applyGravity(other: Moon): Moon = copy(
     velocity = Position3d(
       x = velocityChange(other)(_.x),
       y = velocityChange(other)(_.y),
       z = velocityChange(other)(_.z)
     )
  )

  def applyVelocity: Moon = copy(position = position + velocity)

  private def velocityChange(other: Moon)(coordinate: Position3d => Int): Int =
    coordinate(velocity) + coordinate(other.position).compare(coordinate(position))

  private def energyOf(position: Position3d): Int =
    math.abs(position.x) + math.abs(position.y) + math.abs(position.z)
}

object Moon {
  def at(x: Int, y: Int, z: Int): Moon = Moon(position = Position3d(x, y, z))
}
