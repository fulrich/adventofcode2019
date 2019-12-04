package day3


object IntersectionClosestTo {
  def apply(point: Point)(wire1: Wire, wire2: Wire): Point = {
    val intersections = wire1.intersectionPoints(wire2)
    intersections.min(Ordering.by(point => point.manhattanDistanceTo(point)))
  }
}
