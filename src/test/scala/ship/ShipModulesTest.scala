package ship

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success}


class ShipModulesTest extends AnyFunSuite with Matchers {
  val TotalModules = 100


  test("Returns a list of all modules if the resource exists") {
    ShipModules.modules.get.length shouldBe TotalModules
  }

  test("Returns a failure if the given resource does not exist") {
    ShipModules.modules("invalidResource") shouldBe a[Failure[_]]
  }

  test("Creates a list of modules from a list of masses") {
    val masses = Vector("12", "14", "1969", "100756")
    val expected = Vector(Module(12), Module(14), Module(1969), Module(100756))

    ShipModules.modules(masses) shouldBe Success(expected)
  }

  test("Returns a failure if an invalid mass is in the list of masses") {
    val masses = Vector("12", "NOT_A_MASS", "1969", "100756")

    ShipModules.modules(masses) shouldBe a[Failure[_]]
  }
}
