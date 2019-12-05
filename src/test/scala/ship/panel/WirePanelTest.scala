package ship.panel

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.panel.parts.{Point, Wire}


class WirePanelTest extends AnyFunSuite with Matchers {
  test("Find the minimum number of steps between two short wires") {
    val wirePanel = WirePanel(
      Wire.fromRaw("R8,U5,L5,D3"),
      Wire.fromRaw("U7,R6,D4,L4")
    )

    wirePanel.intersectionWithMinimalSteps shouldBe Point(6, 5)
    wirePanel.minimalSteps shouldBe 30
  }

  test("Find the minimum number of steps between two medium wires") {
    val wirePanel = WirePanel(
      Wire.fromRaw("R75,D30,R83,U83,L12,D49,R71,U7,L72"),
      Wire.fromRaw("U62,R66,U55,R34,D71,R55,D58,R83")
    )

    wirePanel.intersectionWithMinimalSteps shouldBe Point(158, -12)
    wirePanel.minimalSteps shouldBe 610
  }

  test("Find the minimum number of steps between two long wires") {
    val wirePanel = WirePanel(
      Wire.fromRaw("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"),
      Wire.fromRaw("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
    )

    wirePanel.intersectionWithMinimalSteps shouldBe Point(107, 47)
    wirePanel.minimalSteps shouldBe 410
  }


  test("Can find the closest intersection and distance to a specific point of medium wires") {
    val wirePanel = WirePanel(
      Wire.fromRaw("R75,D30,R83,U83,L12,D49,R71,U7,L72"),
      Wire.fromRaw("U62,R66,U55,R34,D71,R55,D58,R83")
    )

    wirePanel.intersectionWithMinimumDistanceTo(Point.CentralPort) shouldBe Point(155, 4)
    wirePanel.minimumDistanceTo(Point.CentralPort) shouldBe 159
  }

  test("Can find the closest intersection and distance to a specific point of long wires") {
    val wirePanel = WirePanel(
      Wire.fromRaw("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"),
      Wire.fromRaw("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7")
    )

    wirePanel.intersectionWithMinimumDistanceTo(Point.CentralPort) shouldBe Point(124, 11)
    wirePanel.minimumDistanceTo(Point.CentralPort) shouldBe 135
  }
}
