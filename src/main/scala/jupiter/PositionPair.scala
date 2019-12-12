package jupiter


case class PositionPair(position: Int, velocity: Int) {
  def applyGravity(other: PositionPair): PositionPair = copy(velocity = velocity + other.position.compare(position))
  def applyVelocity: PositionPair = copy(position = position + velocity)
}

