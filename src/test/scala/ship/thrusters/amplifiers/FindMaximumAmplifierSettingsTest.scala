package ship.thrusters.amplifiers

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram


class FindMaximumAmplifierSettingsTest extends AnyFunSuite with Matchers {
  test("No values of a possible sequence can contain repeating phase settings") {
    FindMaximumAmplifierSettings.possibleAmplifierSettings(5).map(_.distinct.length) should contain only 5
  }

  test("All amplifier settings should be equal to the number of amplifiers") {
    FindMaximumAmplifierSettings.possibleAmplifierSettings(5).map(_.length) should contain only 5
  }

  test("Can find the best possible AmplifierResult for a small program") {
    val smallControlProgram = IntcodeProgram.load(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)

    FindMaximumAmplifierSettings(smallControlProgram) shouldBe AmplifierResult(Seq(4, 3, 2, 1, 0), 43210)
  }

  test("Can find the bets possible AmplifierResult for a medium program") {
    val mediumControlProgram = IntcodeProgram.load(
      3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0
    )

    FindMaximumAmplifierSettings(mediumControlProgram) shouldBe AmplifierResult(Seq(0, 1, 2, 3, 4), 54321)
  }

  test("Can find the bets possible AmplifierResult for a large program") {
    val largeControlProgram = IntcodeProgram.load(
      3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
      1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0
    )

    FindMaximumAmplifierSettings(largeControlProgram) shouldBe AmplifierResult(Seq(1, 0, 4, 3, 2), 65210)
  }
}
