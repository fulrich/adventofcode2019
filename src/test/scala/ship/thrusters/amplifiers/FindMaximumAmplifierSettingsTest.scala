package ship.thrusters.amplifiers

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.IntcodeProgram


class FindMaximumAmplifierSettingsTest extends AnyFunSuite with Matchers {
  test("Can find the best possible AmplifierResult for a small program") {
    val program = IntcodeProgram.load(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)

    val result = FindMaximumAmplifierSettings(program)
    result.phaseSettings should contain theSameElementsInOrderAs Seq(4, 3, 2, 1, 0)
    result.lastOutput shouldBe 43210
  }

  test("Can find the best possible AmplifierResult for a medium program") {
    val program = IntcodeProgram.load(
      3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23, 101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0
    )

    val result = FindMaximumAmplifierSettings(program)
    result.phaseSettings should contain theSameElementsInOrderAs Seq(0, 1, 2, 3, 4)
    result.lastOutput shouldBe 54321
  }

  test("Can find the best possible AmplifierResult for a large program") {
    val program = IntcodeProgram.load(
      3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
      1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0
    )

    val result = FindMaximumAmplifierSettings(program)
    result.phaseSettings should contain theSameElementsInOrderAs Seq(1, 0, 4, 3, 2)
    result.lastOutput shouldBe 65210
  }


  test("Can find best possible AmplifierResult in a small looped program") {
    val program = IntcodeProgram.load(
      3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
      27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5
    )

    val result = FindMaximumAmplifierSettings.looped(program)
    result.phaseSettings should contain theSameElementsInOrderAs Seq(9, 8, 7, 6, 5)
    result.lastOutput shouldBe 139629729
  }

  test("Can find the best possible Amplifier Result in a longer looped program") {
    val program = IntcodeProgram.load(
      3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54,
      -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4,
      53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10
    )

    val result = FindMaximumAmplifierSettings.looped(program)
    result.phaseSettings should contain theSameElementsInOrderAs Seq(9, 7, 8, 5, 6)
    result.lastOutput shouldBe 18216
  }
}
