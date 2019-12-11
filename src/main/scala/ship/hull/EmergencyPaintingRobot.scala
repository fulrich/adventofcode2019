package ship.hull

import grids.{Direction, Point}
import ship.computer.internals.Configuration
import ship.computer.{IntcodeProgram, Programs}

import scala.annotation.tailrec


case class EmergencyPaintingRobot(location: Location, program: IntcodeProgram, painted: Seq[PaintedPoint]) {
  lazy val colourUnder: Long = painted.findLast(painted => location.at == painted.point).map(_.colour).getOrElse(Paint.Black)
  lazy val donePainting: Boolean = program.state.isComplete || program.hasFault
  lazy val numberOfPaintedPanels: Int = painted.length


  def paint(): EmergencyPaintingRobot = EmergencyPaintingRobot.paint(this)

  def paintAndTurn(paintColour: Long, turnCommand: Long): EmergencyPaintingRobot = copy(
      painted = painted.filter(_.point != location.at) :+ PaintedPoint(location.at, paintColour),
      location = location.turn(turnCommand).move
  )

  def fault(fault: String): EmergencyPaintingRobot = copy(program = program.fault(fault))

  def clearOutput(): EmergencyPaintingRobot = copy(program = program.configure(Configuration.singleInput))
}


object EmergencyPaintingRobot {
  val PaintingProgram: IntcodeProgram = Programs.HullPainting.get

  def initialize(): EmergencyPaintingRobot = {
    EmergencyPaintingRobot(
      Location(Point.Origin, Direction.Up),
      program = PaintingProgram.configure(Configuration.singleInput).start(),
      painted = Vector.empty
    )
  }

  @tailrec
  def paint(robot: EmergencyPaintingRobot, count: Int = 0): EmergencyPaintingRobot =
    if(robot.donePainting) robot
    else {
      val robotWithOutput = nextOutput(robot)
      val afterPainting = paintAndTurn(robotWithOutput)
      paint(afterPainting, count + 1)
    }

  def paintAndTurn(robot: EmergencyPaintingRobot): EmergencyPaintingRobot =
    robot.program.output match {
      case Seq(paint, turn) => robot.paintAndTurn(paint, turn).clearOutput()
      case output => robot.fault(s"Invalid output from hull painting program: '$output''")
    }

  @tailrec
  def nextOutput(robot: EmergencyPaintingRobot): EmergencyPaintingRobot = {
    val next = robot.copy(program = robot.program.continue(robot.colourUnder))
    if (next.program.output.nonEmpty) next else nextOutput(next)
  }
}