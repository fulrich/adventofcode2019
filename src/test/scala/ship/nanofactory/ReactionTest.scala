package ship.nanofactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class ReactionTest extends AnyFunSuite with Matchers {
  test("Can load a reaction from a string") {
    Reaction.load("7 A, 1 B => 1 C") shouldBe Reaction(
      input = Vector(Chemical(7, "A"), Chemical(1, "B")),
      output = Chemical(1, "C")
    )
  }

  test("Can load a complex reaction from a string") {
    val reactionString = "2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG"
    val expected = Reaction(
      input = Seq(Chemical(2, "VPVL"), Chemical(7, "FWMGM"), Chemical(2, "CXFTF"), Chemical(11, "MNCFX")),
      output = Chemical(1, "STKFG")
    )

    Reaction.load(reactionString) shouldBe expected
  }

  test("Can multiply a reaction which will multiply its input and output chemicals") {
    val reaction = Reaction.load("2 VPVL, 7 FWMGM, 2 CXFTF, 11 MNCFX => 1 STKFG")
    val expected = Reaction(
      input = Seq(Chemical(20, "VPVL"), Chemical(70, "FWMGM"), Chemical(20, "CXFTF"), Chemical(110, "MNCFX")),
      output = Chemical(10, "STKFG")
    )

    reaction * 10 shouldBe expected
  }

  test("Can determine how much ore is required for a reaction") {
    val multiple = Reaction(input = Seq(Chemical(50, "ORE"), Chemical(10, "ORE")), output = Chemical(10, "STKFG"))
    val single = Reaction(input = Seq(Chemical(50, "ORE")), output = Chemical(10, "STKFG"))
    val none = Reaction(input = Seq(Chemical(10, "OTHER")), output = Chemical(10, "STKFG"))


    multiple.ore shouldBe Some(60)
    single.ore shouldBe Some(50)
    none.ore shouldBe None
  }
}
