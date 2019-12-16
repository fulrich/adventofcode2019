package ship.oxygen

import grids.Point

import scala.annotation.tailrec
import scala.collection.Set

case class ShortestPathFinder(goal: Point, searching: Set[Point], available: Set[Point], visited: Set[Point], depth: Int) {
  lazy val isFound: Boolean = searching.contains(goal)
  lazy val allVisited: Boolean = available.diff(visited).isEmpty

  def search(condition: ShortestPathFinder => Boolean): Int = ShortestPathFinder.search(this, condition)

  def nextDepth: ShortestPathFinder =
    copy(
      searching = searching.flatMap(surroundingPoints).intersect(available),
      visited = visited ++ searching,
      depth = depth + 1
    )

  private def surroundingPoints(point: Point): Set[Point] = Set(
    point.up(1),
    point.down(1),
    point.left(1),
    point.right(1)
  )
}

object ShortestPathFinder {
  def apply(area: RepairArea, goal: Point = Point(0, 0)): ShortestPathFinder = ShortestPathFinder(
    goal = goal,
    searching = Set(area.explored.find(_._2 == Tile.OxygenSystem).map(_._1).get),
    available = Set.from(area.explored.toVector.filter(_._2 == Tile.Open).map(_._1)),
    visited = Set.empty[Point],
    depth = 0
  )

  @tailrec
  def search(finder: ShortestPathFinder, condition: ShortestPathFinder => Boolean): Int = {
    if(condition(finder)) finder.depth
    else search(finder.nextDepth, condition)
  }
}
