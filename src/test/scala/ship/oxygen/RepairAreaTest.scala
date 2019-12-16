package ship.oxygen

import grids.Point
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class RepairAreaTest extends AnyFunSuite with Matchers {
  test("Can register an area as explored which will add it's surrounding areas as unexplored") {
    val repairArea = RepairArea.Empty
    val registered = repairArea.registerExplored(Point(1, 1), Tile.Wall)

    registered.explored shouldBe Map(Point(1, 1) -> Tile.Wall)
    registered.unexplored shouldBe empty
  }

  test("Can register an open or oxygen system point which consider all surrounding areas unexplored") {
    val point = Point(1, 1)
    val repairArea = RepairArea.Empty.registerExplored(point, Tile.Open)
    val expectedUnexplored = Vector(point.up(1), point.down(1), point.left(1), point.right(1))
    val expectedExplored = Map(point -> Tile.Open)

    repairArea.explored shouldBe expectedExplored
    repairArea.unexplored should contain theSameElementsAs expectedUnexplored
  }

  test("If a point is already explored it is not added to the unexplored list") {
    val point = Point(1, 1)
    val repairArea = RepairArea(unexplored = Vector.empty, explored = Map(point.up(1) -> Tile.Open))
    val registeredRepairedArea = repairArea.registerExplored(point, Tile.Open)

    val expectedUnexplored = Vector(point.down(1), point.left(1), point.right(1))
    val expectedExplored = Map(point -> Tile.Open, point.up(1) -> Tile.Open)

    registeredRepairedArea.explored shouldBe expectedExplored
    registeredRepairedArea.unexplored should contain theSameElementsAs expectedUnexplored
  }

}
