package ship.panel

import ship.panel.parts.{Point, Wire}


case class WirePanel(wire1: Wire, wire2: Wire) {
  lazy val IntersectionPoints: Seq[Point] = wire1.intersectionPoints(wire2)

  private val OrderBySteps: Ordering[Point] =
    Ordering.by((intersection: Point) => wire1.stepsTo(intersection) + wire2.stepsTo(intersection))

  private def orderByDistanceTo(otherPoint: Point): Ordering[Point] =
    Ordering.by((point: Point) => point.manhattanDistanceTo(otherPoint))


  lazy val intersectionWithMinimalSteps: Point = IntersectionPoints.min(OrderBySteps)

  lazy val minimalSteps: Int = wire1.stepsTo(intersectionWithMinimalSteps) + wire2.stepsTo(intersectionWithMinimalSteps)


  def intersectionWithMinimumDistanceTo(point: Point): Point = IntersectionPoints.min(orderByDistanceTo(point))

  def minimumDistanceTo(point: Point): Int = intersectionWithMinimumDistanceTo(point).manhattanDistanceTo(point)
}
