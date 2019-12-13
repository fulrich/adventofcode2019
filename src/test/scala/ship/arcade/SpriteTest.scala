package ship.arcade

import grids.Screen
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class SpriteTest extends AnyFunSuite with Matchers {

  test("Can load sprites from a series of output values") {
    val output = Seq[Long](1, 2, 3, 6, 5, 4)
    val expected = Seq(Sprite(1, 2, Tile.Paddle), Sprite(6, 5, Tile.Ball))

    Screen.from(output).sprites should contain theSameElementsAs expected
  }

}
