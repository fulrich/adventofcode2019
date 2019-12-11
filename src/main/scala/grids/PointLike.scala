package grids


trait PointLike {
  def x: Int
  def y: Int

  def distanceTo(point: PointLike): Int =
    Math.abs((Math.abs(x) - Math.abs(point.x)) + (Math.abs(y) - Math.abs(point.y)))
}
