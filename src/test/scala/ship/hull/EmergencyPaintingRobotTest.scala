package ship.hull

import grids.{Direction, Point}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import ship.computer.internals.Configuration
import ship.computer.internals.instructions.io.Output

class EmergencyPaintingRobotTest extends AnyFunSuite with Matchers {
  val robot: EmergencyPaintingRobot = EmergencyPaintingRobot.initialize()

  test("What happens?") {
    val robot = EmergencyPaintingRobot.initialize()
    println(robot.paint().numberOfPaintedPanels)
  }

  test("If the area hasn't been colour yet the colour is always black") {
    robot.painted shouldBe empty
    robot.colourUnder shouldBe Paint.Black
  }

  test("Can detect what colour is currently under the robot") {
    val robotHasPainted = robot.copy(painted = Seq(PaintedPoint(Point(0, 0), Paint.White)))
    robotHasPainted.colourUnder shouldBe Paint.White
  }

  test("The robot can paint and turn and move forward") {
    val robotHasPainted = robot.paintAndTurn(paintColour = Paint.White, turnCommand = Turn.Left)
    robotHasPainted.location shouldBe Location(Point(-1, 0), Direction.Left)
    robotHasPainted.painted should contain only PaintedPoint(Point(0, 0), Paint.White)
    robotHasPainted.colourUnder shouldBe Paint.Black

    val robotHasPainted2 = robot.paintAndTurn(paintColour = Paint.White, turnCommand = Turn.Right)
    robotHasPainted2.location shouldBe Location(Point(1, 0), Direction.Right)
    robotHasPainted2.painted should contain only PaintedPoint(Point(0, 0), Paint.White)
    robotHasPainted2.colourUnder shouldBe Paint.Black
  }

  test("When a robot has output it can pant and turn and move forward") {
    val configuration = Configuration.singleInput.copy(
      output = Output.Collection(Vector(Paint.White, Turn.Left))
    )

    val initial = EmergencyPaintingRobot.initialize()
    val withOutput = initial.copy(program = initial.program.configure(configuration))

    val robotPainted = EmergencyPaintingRobot.paintAndTurn(withOutput)

    robotPainted.painted should contain only PaintedPoint(Point.Origin, Paint.White)
    robotPainted.location shouldBe Location(Point.Origin, Direction.Up).turn(Turn.Left).move
    robotPainted.program.output shouldBe empty
  }
}
