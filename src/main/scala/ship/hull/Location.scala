package ship.hull

import grids.{Direction, Point}


case class Location(at: Point, facing: Direction) {
  def facing(newDirection: Direction): Location = copy(facing = newDirection)
  def move: Location = copy(at = facing.move(at, 1))

  def turn(turnCommand: Long): Location = turnCommand match {
    case Turn.Left => facing(facing.turnLeft)
    case Turn.Right => facing(facing.turnRight)
  }
}
