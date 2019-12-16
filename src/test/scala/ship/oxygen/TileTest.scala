package ship.oxygen

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class TileTest extends AnyFunSuite with Matchers {
  test("Can construct a tile based on the output value of the repair robot") {
    Tile(0) shouldBe Tile.Wall
    Tile(1) shouldBe Tile.Open
    Tile(2) shouldBe Tile.OxygenSystem
  }

  test("Only a wall tile blocks movement") {
    Tile.Wall.isBlocked shouldBe true
    Tile.Unexplored.isBlocked shouldBe false
    Tile.Open.isBlocked shouldBe false
    Tile.OxygenSystem.isBlocked shouldBe false

    Tile.Wall.canEnter shouldBe false
    Tile.Unexplored.canEnter shouldBe true
    Tile.Open.canEnter shouldBe true
    Tile.OxygenSystem.canEnter shouldBe true
  }
}
