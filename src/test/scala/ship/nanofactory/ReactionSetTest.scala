package ship.nanofactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Try}


class ReactionSetTest extends AnyFunSuite with Matchers {
  val reactions = Vector(
    "10 ORE => 10 A",
    "1 ORE => 1 B",
    "7 A, 1 B => 1 C",
    "7 A, 1 C => 1 D",
    "7 A, 1 D => 1 E",
    "7 A, 1 E => 1 FUEL"
  )
  val reactionSet: ReactionSet = ReactionSet.load(reactions)

  test("Can load a set of reactions") {
    reactionSet shouldBe ReactionSet(Vector(
      Reaction(Vector(Chemical(10, "ORE")), Chemical(10, "A")),
      Reaction(Vector(Chemical(1, "ORE")), Chemical(1, "B")),
      Reaction(Vector(Chemical(7, "A"), Chemical(1, "B")), Chemical(1, "C")),
      Reaction(Vector(Chemical(7, "A"), Chemical(1, "C")), Chemical(1, "D")),
      Reaction(Vector(Chemical(7, "A"), Chemical(1, "D")), Chemical(1, "E")),
      Reaction(Vector(Chemical(7, "A"), Chemical(1, "E")), Chemical(1, "FUEL"))
    ))
  }

  test("Can find the reaction needed to create a chemical") {
    val expected = Reaction(Vector(Chemical(70, "A"), Chemical(10, "E")), Chemical(10, "FUEL"))

    reactionSet.reactionFor(Chemical(10, "FUEL")) shouldBe expected
  }

  test("When finding a reaction it will round up the output to ensure there is enough created by the reaction") {
    val reactions = ReactionSet(Vector(
      Reaction(Vector(Chemical(7, "A"), Chemical(1, "E")), Chemical(3, "FUEL"))
    ))
    val expected = Reaction(Vector(Chemical(28, "A"), Chemical(4, "E")), Chemical(12, "FUEL"))

    reactions.reactionFor(Chemical(10, "FUEL")) shouldBe expected
  }

  test("If a reaction for the requested goal cannot be found an exception will be raised") {
    Try(reactionSet.reactionFor(Chemical(10, "MISSING"))) shouldBe a[Failure[_]]
  }
}
