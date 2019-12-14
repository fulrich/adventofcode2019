package ship.nanofactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.{Success, Try}


class NanofactoryTest extends AnyFunSuite with Matchers {
  test("Can load the full Nanofactory without errors") {
    Try(Nanofactory.load()) shouldBe a[Success[_]]
  }
//
//  test("If the only required chemicals are ORE it means we are successful") {
//    val factory = Nanofactory(ReactionSet.Empty, requires = Vector(Chemical(500, Chemical.OreName)))
//    factory.isComplete shouldBe true
//  }
//
//  test("If there are any requires chemicals other than ORE we are not yet successful") {
//    val factory = Nanofactory(ReactionSet.Empty, requires = Vector(Chemical(500, Chemical.OreName), Chemical(2, "OT")))
//    factory.isComplete shouldBe false
//  }
//
//  test("Can reduce the requires chemicals if any are duplicates") {
//    val factory = Nanofactory(
//      ReactionSet.Empty,
//      requires = Vector(Chemical(10 ,"AB"), Chemical(20 ,"AB"), Chemical(5 ,"QQ"))
//    )
//    val expected = Vector(Chemical(30 ,"AB"), Chemical(5 ,"QQ"))
//
//    factory.reduceChemicals.requires should contain theSameElementsAs expected
//  }
//
//  test("Can determine the chemicals that still need to be created") {
//    val factory = Nanofactory(
//      ReactionSet.Empty,
//      requires = Vector(Chemical(30 ,"AB"), Chemical(5 ,"QQ"), Chemical(10, "ORE"))
//    )
//    val expected = Vector(Chemical(30 ,"AB"), Chemical(5 ,"QQ"))
//
//    factory.missingChemicals should contain theSameElementsAs expected
//  }
//
//  test("Given a required chemicals the Nanofactory will determine the next set of required chemicals") {
//    val factory = Nanofactory(
//      reactions = ReactionSet(Vector(Reaction(input = Vector(Chemical(50, "ORE")), output = Chemical(1, "QQ")))),
//      requires = Vector(Chemical(5 ,"QQ"), Chemical(10, "ORE"))
//    )
//
//    val expected = Vector(Chemical(260, "ORE"))
//
//    factory.findInputsForRequired.requires shouldBe expected
//  }
//
//  test("Given a required chemicals the Nanofactory will determine the next all of the required chemicals") {
//    val factory = Nanofactory(
//      reactions = ReactionSet(Vector(
//        Reaction(input = Vector(Chemical(50, "ORE")), output = Chemical(1, "QQ")),
//        Reaction(input = Vector(Chemical(10, "QQ")), output = Chemical(1, "AB"))
//      )),
//      requires = Vector(Chemical(2 ,"AB"), Chemical(5 ,"QQ"), Chemical(10, "ORE"))
//    )
//
//    val expected = Vector(Chemical(20, "QQ"), Chemical(260, "ORE"))
//
//    factory.findInputsForRequired.requires should contain theSameElementsAs expected
//  }
}
