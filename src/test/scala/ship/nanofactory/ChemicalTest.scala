package ship.nanofactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Try}


class ChemicalTest extends AnyFunSuite with Matchers {
  test("Can load chemicals from a string") {
    Chemical.load("7 ZLQW") shouldBe Chemical(name = "ZLQW", quantity = 7)
    Chemical.load("3 BMBT") shouldBe Chemical(name = "BMBT", quantity = 3)
    Chemical.load("26 XMNCP") shouldBe Chemical(name = "XMNCP", quantity = 26)
  }

  test("Can multiply a chemical") {
    val chemical = Chemical(5, "ABC")

    chemical * 2 shouldBe Chemical(10, "ABC")
    chemical * 5 shouldBe Chemical(25, "ABC")
    chemical * 10 shouldBe Chemical(50, "ABC")
  }

  test("Can determine if the chemical is an ore") {
    Chemical(5, "ABC").isOre shouldBe false
    Chemical(5, Chemical.OreName).isOre shouldBe true
  }

  test("Can add two chemicals together if they have the same name otherwise an exception is raised") {
    Chemical(5, "ABC") + Chemical(8, "ABC") shouldBe Chemical(13, "ABC")
    Chemical(10, "XMNCP") + Chemical(20, "XMNCP") shouldBe Chemical(30, "XMNCP")

    Try(Chemical(5, "ABC") + Chemical(8, "XMNCP")) shouldBe a[Failure[_]]
  }

  test("Can subtract two chemicals together if they have the same name otherwise an exception is raised") {
    Chemical(20, "ABC") - Chemical(8, "ABC") shouldBe Chemical(12, "ABC")
    Chemical(50, "XMNCP") - Chemical(20, "XMNCP") shouldBe Chemical(30, "XMNCP")

    Try(Chemical(45, "ABC") - Chemical(8, "XMNCP")) shouldBe a[Failure[_]]
  }

  test("Can compare two chemicals together if they have the same name otherwise an exception is raised") {
    Chemical(20, "ABC") > Chemical(8, "ABC") shouldBe true
    Chemical(50, "XMNCP") > Chemical(20, "XMNCP") shouldBe true
    Try(Chemical(45, "ABC") > Chemical(8, "XMNCP")) shouldBe a[Failure[_]]

    Chemical(5, "ABC") < Chemical(8, "ABC") shouldBe true
    Chemical(2, "XMNCP") < Chemical(20, "XMNCP") shouldBe true
    Try(Chemical(2, "ABC") < Chemical(8, "XMNCP")) shouldBe a[Failure[_]]
  }
}
