package grids

sealed trait Direction {
  def turnLeft: Direction
  def turnRight: Direction
  def turnAround: Direction

  def move(point: Point, steps: Int): Point
}

object Direction {
  case object Up extends Direction {
    override val turnLeft: Direction = Left
    override val turnRight: Direction = Right
    override val turnAround: Direction = Down

    def move(point: Point, steps: Int): Point = point.copy(y = point.y - steps)
  }

  case object Down extends Direction {
    override val turnLeft: Direction = Right
    override val turnRight: Direction = Left
    override val turnAround: Direction = Up

    def move(point: Point, steps: Int): Point = point.copy(y = point.y + steps)
  }
  
  case object Left extends Direction {
    override val turnLeft: Direction = Down
    override val turnRight: Direction = Up
    override val turnAround: Direction = Right

    def move(point: Point, steps: Int): Point = point.copy(x = point.x - steps)
  }
  
  case object Right extends Direction {
    override val turnLeft: Direction = Up
    override val turnRight: Direction = Down
    override val turnAround: Direction = Left

    def move(point: Point, steps: Int): Point = point.copy(x = point.x + steps)
  }
}
