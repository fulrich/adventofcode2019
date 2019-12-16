package ship.oxygen

import grids.Point
import ship.computer.internals.Configuration
import ship.computer.{IntcodeProgram, Programs}

import scala.annotation.tailrec


case class RepairRobot(program: IntcodeProgram, current: Point, lastMove: Movement, area: RepairArea) {
  def continue: RepairRobot = {
    val nextMovement = lastMove.next(current, area)
    val runProgram = program.continue(nextMovement.command)
    val foundTile = Tile(program.output.last)

    val updatedArea = area.registerExplored(nextMovement.move(current), foundTile)
    val newCurrent = if(foundTile.canEnter) nextMovement.move(current) else current
    val newMove = if(foundTile.canEnter) nextMovement else lastMove

    copy(
      program = runProgram,
      current = newCurrent,
      lastMove = newMove,
      area = updatedArea
    )
  }

}

object RepairRobot {
  def explore(): RepairRobot = {
    val startPoint = Point(0, 0)
    val robot = RepairRobot(
      program = Programs.RepairRobot.get.configure(Configuration.singleInput).start(),
      current = startPoint,
      lastMove = Movement.North,
      area = RepairArea.startAt(startPoint)
    )

    explore(robot, robot.area.unexplored.size, startPoint)
  }

  @tailrec
  def explore(robot: RepairRobot, initialUnexplored: Int, start: Point): RepairRobot = {
    val next = robot.continue

    if(next.current == start && next.area.explored.size > initialUnexplored) robot
    else explore(next, initialUnexplored, start)
  }
}