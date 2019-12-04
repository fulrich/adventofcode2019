package day3

object StepsClosestTo {
  def apply(point: Point)(wire1: Wire, wire2: Wire): Int = {
    val intersections = wire1.intersectionPoints(wire2)
    val stepsTo = intersections.map(intersection => wire1.stepsTo(intersection) + wire2.stepsTo(intersection))

    stepsTo.min
  }
}
