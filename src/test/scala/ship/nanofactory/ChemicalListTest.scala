package ship.nanofactory

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Try}


class ChemicalListTest extends AnyFunSuite with Matchers {
  val A: Chemical = Chemical(1, "A")
  val B: Chemical = Chemical(2, "B")
  val C: Chemical = Chemical(5, "C")
  val D: Chemical = Chemical(10, "D")


  test("Can add and accumulate to the chemical list") {
    val chemicalList = ChemicalList.Empty

    val firstPlus = (chemicalList + A)
    firstPlus shouldBe ChemicalList(Vector(A))

    val secondPlus = firstPlus + B + A + B
    secondPlus.chemicals should contain theSameElementsAs Vector(Chemical(2, "A"), Chemical(4, "B"))
  }

  test("Can remove values from the chemical list") {
    val chemicalList = ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))
    val result = chemicalList - C - D

    result.chemicals should contain theSameElementsAs Vector(Chemical(10, "C"), Chemical(10, "D"))
  }

  test("If the type does not exist in the chemical list an exception will be raised for trying to sub") {
    val chemicalList = ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))

    Try(chemicalList - A) shouldBe a[Failure[_]]
  }

  test("Cannot remove more chemical from the list then is available in the list") {
    val chemicalList = ChemicalList(Vector(Chemical(3, "C"), Chemical(20, "D")))

    Try(chemicalList - C) shouldBe a[Failure[_]]
  }

  test("Can find chemicals in the chemical list") {
    val chemicalList = ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))

    chemicalList.findChemical(A) shouldBe None
    chemicalList.findChemical(C) shouldBe Some(Chemical(15, "C"))
  }

  test("Can find chemicals using a boolean function") {
    val chemicalList = ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))

    chemicalList.find(A.equalType) shouldBe None
    chemicalList.find(C.equalType) shouldBe Some(Chemical(15, "C"))
  }

  test("If a value is 0 it should be removed from the list") {
    val chemicalList = ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))

    (chemicalList - Chemical(15, "C")) shouldBe ChemicalList(Vector(Chemical(20, "D")))
  }

  test("Can add a list of other chemicals to a chemical list") {
    val chemicalList = ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))
    val additional = Vector(Chemical(15, "C"), Chemical(5, "A"), Chemical(0, "Q"))
    val expected = Vector(Chemical(5, "A"), Chemical(30, "C"), Chemical(20, "D"))

    (chemicalList ++ additional).chemicals should contain theSameElementsAs expected
  }

  test("Adding a 0 chemical to the list does nothing") {
    val chemicalList = ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))

    (chemicalList + Chemical(0, "QQ")) shouldBe ChemicalList(Vector(Chemical(15, "C"), Chemical(20, "D")))
  }
}
