import asteroidbelt.AsteroidMap
import asteroidbelt.targeting.GiantLaserPrioritization
import jupiter.{EfficientGravitySimulator, JupiterOrbit}
import mars.{RoverPassword, SpaceImageChecksum}
import mercury.{OrbitalMapBuilder, OrbitalPathFinder}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.ShipModules
import ship.arcade.{Cabinet, Tile}
import ship.computer._
import ship.fuel.{FuelCounterUpper, FuelManagement, ModuleFueler}
import ship.hull.{EmergencyPaintingRobot, PaintPrinter}
import ship.nanofactory.Nanofactory
import ship.oxygen.{RepairRobot, ShortestPathFinder}
import ship.panel.WirePanel
import ship.thrusters.amplifiers.FindMaximumAmplifierSettings
import venus.FuelDepot


class ProblemRunner extends AnyFunSuite with Matchers with ComputerTesting with ProblemPrinter {
  val OutputAnswers = false

  test("Day 1 - Problem 1: How much fuel for all modules") {
    val modules = ShipModules.modules.get
    val fueledModules = ModuleFueler.fuelUpModulesIgnoringFuelWeight(modules)
    val requiredFuel = FuelCounterUpper.countTotalFuel(fueledModules)
    requiredFuel shouldBe 3497399

    printAnswer(day = 1, problem = 1, requiredFuel)
  }

  test("Day 1 - Problem 2: How much fuel accounting for fuel weight") {
    val modules = ShipModules.modules.get
    val fueledModules = ModuleFueler.fuelUpModules(modules)
    val requiredFuel = FuelCounterUpper.countTotalFuel(fueledModules)
    requiredFuel shouldBe 5243207

    printAnswer(day = 1, problem = 2, requiredFuel)
  }


  test("Day 2 - Program 1: Fixing the Gravity Assist Program") {
    val gravityAssistProgram = Programs.GravityAssist.get
    val gravityAssistLastState = gravityAssistProgram.set(1, 12).set(2, 2)
    val gravityAssistOutput = gravityAssistLastState.start().address(0)
    gravityAssistOutput shouldBe 5098658

    printAnswer(day = 2, problem = 1, gravityAssistOutput)
  }

  test("Day 2 - Program 2: Fixing the Gravity Assist Program") {
    val foundValue = GravityAssistFinder.find()
    foundValue shouldBe 5064

    printAnswer(day = 2, problem = 2, foundValue)
  }


  test("Day 3 - Problem 1: Fix the Fuel Management System Finding Manhattan Closest") {
    val wirePanel = FuelManagement.Panel.get
    val distanceToCentralPort = wirePanel.minimumDistanceTo(WirePanel.CentralPort) shouldBe 316

    printAnswer(day = 3, problem = 1, distanceToCentralPort)
  }

  test("Day 3 - Problem 2 - Fix the Fuel Management System Finding Minimum Number of Steps") {
    val wirePanel = FuelManagement.Panel.get
    wirePanel.minimalSteps shouldBe 16368

    printAnswer(day = 3, problem = 2, wirePanel.minimalSteps)
  }


  test("Day 4 - Problem 1 - Determine the Password for Venus Fuel Depot") {
    val validPasswords = FuelDepot.validPasswords()
    validPasswords.length shouldBe 966

    printAnswer(day = 4, problem = 1, validPasswords.length)
  }

  test("Day 4 - Problem 2 - Determine the More Comprehensive Password for Venus Fuel Depot") {
    val comprehensiveValidPasswords = FuelDepot.validPasswords(onlyTwoAdjacent = true)
    comprehensiveValidPasswords.length shouldBe 628

    printAnswer(day = 4, problem = 2, comprehensiveValidPasswords.length)
  }


  test("Day 5 - Problem 1 - Run Diagnostic Tests on the Thermal Environment Supervision Terminal") {
    val diagnosticProgram = Programs.Diagnostics.get

    diagnosticProgram.testInput(1).testOutput { diagnosticCodeList =>
      diagnosticCodeList.last shouldBe 6731945
      printAnswer(day = 5, problem = 1, diagnosticCodeList.last)
    }
  }

  test("Day 5 - Problem 2 - Run Diagnostic Tests on the Thermal Radiator Controller") {
    val diagnosticProgram = Programs.Diagnostics.get

    diagnosticProgram.testInput(5).testOutput { diagnosticCodeList =>
      diagnosticCodeList.last shouldBe 9571668
      printAnswer(day = 5, problem = 2, diagnosticCodeList.last)
    }
  }


  test("Day 6 - Problem 1 - Determine the total number of orbits in the Orbital Map") {
    val orbitalMap = OrbitalMapBuilder.build.get
    val totalOrbits = orbitalMap.orbits
    totalOrbits shouldBe 186597

    printAnswer(day = 6, problem = 1, totalOrbits)
  }

  test("Day 6 - Problem 2 - Determine the number of jumps required to reach Santa") {
    val orbitalMap = OrbitalMapBuilder.build.get
    val transfersToSanta = OrbitalPathFinder.minimumTransfersBetween(orbitalMap)("YOU", "SAN")
    transfersToSanta shouldBe 412

    printAnswer(day = 6, problem = 2, transfersToSanta)
  }


  test("Day 7 - Problem 1 - Find the amplifier signal to give the highest thruster boost") {
    val amplifierResult = FindMaximumAmplifierSettings()
    amplifierResult.lastOutput shouldBe 24405

    printAnswer(day = 7, problem = 1, amplifierResult.lastOutput)
  }

  test("Day 7 - Problem 2 - Find the amplifier signal to give the highest thruster boost using a looped configuration") {
    val amplifierResult = FindMaximumAmplifierSettings.looped()
    amplifierResult.lastOutput shouldBe 8271623

    printAnswer(day = 7, problem = 2, amplifierResult.lastOutput)
  }


  test("Day 8 - Problem 1 - Find the checksum of the space image for the Mars Rover") {
    val spaceImage = RoverPassword.Image.get
    val checksum = SpaceImageChecksum(spaceImage)

    printAnswer(day = 8, problem = 1, checksum)
  }

  test("Day 8 - Problem 2 - Find the overlay of all layers and print it to view the password") {
    val spaceImage = RoverPassword.Image.get

    printAnswer(day = 8, problem = 2) {
      spaceImage.overlay.print()
    }
  }


  test("Day 9 - Problem 1 - Should output a single value from the BOOST program in test mode") {
    val boostProgram = Programs.Boost.get

    boostProgram.testInput(1).testExecute { (program, boostTestResult) =>
      program.state.isComplete shouldBe true
      boostTestResult.last shouldBe 2671328082L
      printAnswer(day = 9, problem = 1, boostTestResult.last)
    }
  }

  test("Day 9 - Problem 2 - Using the BOOST program can find the coordinates of the distress signal") {
    val boostProgram = Programs.Boost.get

    boostProgram.testInput(2).testExecute { (program, boostTestResult) =>
      program.state.isComplete shouldBe true
      boostTestResult.last shouldBe 59095
      printAnswer(day = 9, problem = 2, boostTestResult.last)
    }
  }


  test("Day 10 - Problem 1 - Find the best location for the new Ceres Monitoring Station") {
    val asteroidMap = AsteroidMap.Ceres.get

    val bestAsteroid = asteroidMap.asteroidWithMostVisibleAsteroids
    val visibleAsteroids = asteroidMap.visibleAsteroidsFor(bestAsteroid)

    visibleAsteroids shouldBe 344
    printAnswer(day = 10, problem = 1, visibleAsteroids)
  }

  test("Day 10 - Problem 2 - Find out which was the 200th asteroid vaporized by the laser") {
    val asteroidMap = AsteroidMap.Ceres.get
    val bestAsteroid = asteroidMap.asteroidWithMostVisibleAsteroids
    val targetList = new GiantLaserPrioritization(bestAsteroid).prioritize(asteroidMap)


    val twoHundredthTarget = targetList.target(200)
    val result = twoHundredthTarget.asteroid.x * 100 + twoHundredthTarget.asteroid.y

    result shouldBe 2732
    printAnswer(day = 10, problem = 2, result)
  }


  test("Day 11 - Problem 1 - How many tiles does the emergency painting robot paint?") {
    val robot = EmergencyPaintingRobot.initialize()
    val result = robot.paint().numberOfPaintedPanels

    result shouldBe 2415
    printAnswer(day = 11, problem = 1, result)
  }

  test("Day 11 - Problem 2 - Determine the registration identifier to avoid Space Jail") {
    val robot = EmergencyPaintingRobot.initializeOnWhite()
    val painted = robot.paint().painted

    printAnswer(day = 11, problem = 1) {
      PaintPrinter(painted)
    }
  }


  test("Day 12 - Problem 1 - Calculate the total energy of the moons in Jupiter's orbit") {
    val jupiterOrbit = JupiterOrbit.Scan
    val jupiterOrbitResult = jupiterOrbit.orbitAfter(1000)

    jupiterOrbitResult.totalEnergy shouldBe 10198
    printAnswer(day = 12, problem = 1, answer = jupiterOrbitResult.totalEnergy)
  }

  test("Day 12 - Problem 2 - Calculate how long it will take the moons to reach their initial state") {
    val jupiterOrbit = JupiterOrbit.Scan
    val numberOfStepsToInitial = EfficientGravitySimulator.stepsToInitial(jupiterOrbit)

    numberOfStepsToInitial shouldBe 271442326847376L
    printAnswer(day = 12, problem = 2, answer = numberOfStepsToInitial)
  }


  test("Day 13 - Problem 1 - Count how many block tiles are on the arcade game screen") {
    val game = Cabinet.start()
    val numberOfWallTiles = game.numberOf(Tile.Block)

    numberOfWallTiles shouldBe 260
    printAnswer(day = 12, problem = 2, answer = numberOfWallTiles)
  }

  test("Day 13 - Problem 2 - What is the score after beating the arcade game") {
    val game = Cabinet.start(quarters = 2)
    val result = Cabinet.play(game)

    result.screen.score shouldBe 12952
    printAnswer(day = 13, problem = 2, answer = result.screen.score)
  }


  test("Day 14 - Problem 1 - Determine how much ore is needed to create 1 FUEL") {
    val factory = Nanofactory.load()
    val requiredOre = factory.processFuel().ore

    requiredOre shouldBe 397771
    printAnswer(day = 14, problem = 1, answer = requiredOre)
  }

  test("Day 14 - Problem 2 - Determine how much ore is needed to create 1 FUEL") {
    val AvailableOre: Long = 1_000_000_000_000L
    val factory = Nanofactory.load()
    val createdFuel = Nanofactory.findFuelForOre(factory, AvailableOre)

    createdFuel shouldBe 3126714
    printAnswer(day = 14, problem = 1, answer = createdFuel)
  }


  test("Day 15 - Problem 1 - Determine how long the shortest path to the Oxygen System is") {
    val exploredRobot = RepairRobot.explore()
    val shortestPathLength = ShortestPathFinder(exploredRobot.area).search(_.isFound)

    shortestPathLength shouldBe 424
    printAnswer(day = 15, problem = 1, answer = shortestPathLength)
  }

  test("Day 15 - Problem 2 - Determine how long until the entire corridor is filled") {
    val exploredRobot = RepairRobot.explore()
    val minutesUntilFilled = ShortestPathFinder(exploredRobot.area).search(_.allVisited)

    (minutesUntilFilled - 1) shouldBe 446
    printAnswer(day = 15, problem = 2, answer = minutesUntilFilled)
  }
}
