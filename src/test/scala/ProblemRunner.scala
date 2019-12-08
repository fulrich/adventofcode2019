import mars.{RoverPassword, SpaceImageChecksum}
import mercury.{OrbitalMapBuilder, OrbitalPathFinder}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.ShipModules
import ship.computer._
import ship.fuel.{FuelCounterUpper, FuelManagement, ModuleFueler}
import ship.panel.parts.Point
import ship.thrusters.amplifiers.FindMaximumAmplifierSettings
import venus.FuelDepot


class ProblemRunner extends AnyFunSuite with Matchers with ComputerTesting {
  test("Day 1 - Problem 1: How much fuel for all modules") {
    val modules = ShipModules.modules.get
    val fueledModules = ModuleFueler.fuelUpModulesIgnoringFuelWeight(modules)
    val requiredFuel = FuelCounterUpper.countTotalFuel(fueledModules)
    requiredFuel shouldBe 3497399

    println(s"Day 1 - Problem 1 Answer: ${requiredFuel}")
  }

  test("Day 1 - Problem 2: How much fuel accounting for fuel weight") {
    val modules = ShipModules.modules.get
    val fueledModules = ModuleFueler.fuelUpModules(modules)
    val requiredFuel = FuelCounterUpper.countTotalFuel(fueledModules)
    requiredFuel shouldBe 5243207

    println(s"Day 1 - Problem 2 Answer: ${requiredFuel}")
  }


  test("Day 2 - Program 1: Fixing the Gravity Assist Program") {
    val gravityAssistProgram = Programs.gravityAssist.get
    val gravityAssistLastState = gravityAssistProgram.set(1, 12).set(2, 2)
    val gravityAssistOutput = gravityAssistLastState.start().address(0)
    gravityAssistOutput shouldBe 5098658

    println(s"Day 2 - Problem 1 Answer: ${gravityAssistOutput}")
  }

  test("Day 2 - Program 2: Fixing the Gravity Assist Program") {
    val foundValue = GravityAssistFinder.find()
    foundValue shouldBe 5064

    println(s"Day 2 - Problem 1 Answer: ${GravityAssistFinder.find()}")
  }


  test("Day 3 - Problem 1: Fix the Fuel Management System Finding Manhattan Closest") {
    val wirePanel = FuelManagement.Panel.get
    val distanceToCentralPort = wirePanel.minimumDistanceTo(Point.CentralPort) shouldBe 316

    println(s"Day 3 - Problem 1 Answer: $distanceToCentralPort")
  }

  test("Day 3 - Problem 2 - Fix the Fuel Management System Finding Minimum Number of Steps") {
    val wirePanel = FuelManagement.Panel.get
    wirePanel.minimalSteps shouldBe 16368

    println(s"Day 3 - Problem 2 Answer: ${wirePanel.minimalSteps}")
  }


  test("Day 4 - Problem 1 - Determine the Password for Venus Fuel Depot") {
    val validPasswords = FuelDepot.validPasswords()
    validPasswords.length shouldBe 966

    println(s"Day 4 - Problem 1 Answer: ${validPasswords.length}")
  }

  test("Day 4 - Problem 2 - Determine the More Comprehensive Password for Venus Fuel Depot") {
    val comprehensiveValidPasswords = FuelDepot.validPasswords(onlyTwoAdjacent = true)
    comprehensiveValidPasswords.length shouldBe 628

    println(s"Day 4 - Problem 2 Answer: ${comprehensiveValidPasswords.length}")
  }


  test("Day 5 - Problem 1 - Run Diagnostic Tests on the Thermal Environment Supervision Terminal") {
    val diagnosticProgram = DiagnosticProgram.diagnosticTests.get

    diagnosticProgram.testInput(1).testOutput { diagnosticCodeList =>
      diagnosticCodeList.last shouldBe 6731945
      println(s"Day 5 - Problem 1 Answer: ${diagnosticCodeList.last}")
    }
  }

  test("Day 5 - Problem 2 - Run Diagnostic Tests on the Thermal Radiator Controller") {
    val diagnosticProgram = DiagnosticProgram.diagnosticTests.get

    diagnosticProgram.testInput(5).testOutput { diagnosticCodeList =>
      diagnosticCodeList.last shouldBe 9571668
      println(s"Day 5 - Problem 1 Answer: ${diagnosticCodeList.last}")
    }
  }


  test("Day 6 - Problem 1 - Determine the total number of orbits in the Orbital Map") {
    val orbitalMap = OrbitalMapBuilder.build.get
    val totalOrbits = orbitalMap.orbits
    totalOrbits shouldBe 186597

    println(s"Day 6 - Problem 1 Answer: $totalOrbits")
  }

  test("Day 6 - Problem 2 - Determine the number of jumps required to reach Santa") {
    val orbitalMap = OrbitalMapBuilder.build.get
    val transfersToSanta = OrbitalPathFinder.minimumTransfersBetween(orbitalMap)("YOU", "SAN")
    transfersToSanta shouldBe 412

    println(s"Day 6 - Problem 2 Answer: ${transfersToSanta}")
  }


  test("Day 7 - Problem 1 - Find the amplifier signal to give the highest thruster boost") {
    val amplifierResult = FindMaximumAmplifierSettings()
    amplifierResult.result shouldBe 24405

    println(s"Day 7 - Problem 1 Answer: ${amplifierResult.result}")
  }

  test("Day 7 - Problem 2 - Find the amplifier signal to give the highest thruster boost using a looped configuration") {
    val amplifierResult = FindMaximumAmplifierSettings.looped()
    amplifierResult.result shouldBe 8271623

    println(s"Day 7 - Problem 2 Answer: ${amplifierResult.result}")
  }


  test("Day 8 - Problem 1 - Find the checksum of the space image for the Mars Rover") {
    val spaceImage = RoverPassword.Image.get
    val checksum = SpaceImageChecksum(spaceImage)


    println(s"Day 8 - Problem 1 Answer: $checksum")
  }
}
