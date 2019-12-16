package ship.computer

import scala.io.Source
import scala.util.Try


object Programs {
  private val GravityAssistProgram = "ship/computer/gravity_assist_program.txt"
  private val DiagnosticsProgram = "ship/computer/diagnostics_program.txt"
  private val BoostProgram = "ship/computer/boost_program.txt"
  private val AmplificationProgram = "ship/thrusters/amplification_program.txt"
  private val HullPaintingProgram = "ship/hull/emergency_painting_robot_program.txt"
  private val ArcadeGameProgram = "ship/arcade/game_program.txt"
  private val RepairRobotProgram = "ship/oxygen/repair_robot.txt"
  private val Separator = ","


  lazy val GravityAssist: Try[IntcodeProgram] = from(GravityAssistProgram)
  lazy val Diagnostics: Try[IntcodeProgram] = from(DiagnosticsProgram)
  lazy val Boost: Try[IntcodeProgram] = from(BoostProgram)
  lazy val ThrusterAmplification: Try[IntcodeProgram] = from(AmplificationProgram)
  lazy val HullPainting: Try[IntcodeProgram] = from(HullPaintingProgram)
  lazy val ArcadeGame: Try[IntcodeProgram] = from(ArcadeGameProgram)
  lazy val RepairRobot: Try[IntcodeProgram] = from(RepairRobotProgram)


  def from(resourceName: String): Try[IntcodeProgram] =
    raw(resourceName).map(intcode => IntcodeProgram(intcode))

  def raw(resourceName: String): Try[Seq[Long]] =
    Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toLong))
}
