package ship.thrusters.amplifiers

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram

class AmplifierTestSeries extends AnyFunSuite with Matchers {
  test("The amplifier series correctly determines the value from the amplifiers") {
    val controlProgram = IntcodeProgram.load(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)
    val amplifierSeries = AmplifierSeries(Seq(4, 3, 2, 1, 0), controlProgram)
  }
}
