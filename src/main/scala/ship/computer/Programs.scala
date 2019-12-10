package ship.computer

import scala.io.Source
import scala.util.Try


object Programs {
  val GravityAssistProgram = "ship/computer/gravity_assist_program.txt"
  val DiagnosticsProgram = "ship/computer/diagnostics_program.txt"
  val BoostProgram = "ship/computer/boost_program.txt"
  val AmplificationProgram = "ship/thrusters/amplification_program.txt"
  val Separator = ","


  lazy val GravityAssist: Try[IntcodeProgram] = from(GravityAssistProgram)
  lazy val Diagnostics: Try[IntcodeProgram] = from(DiagnosticsProgram)
  lazy val Boost: Try[IntcodeProgram] = from(BoostProgram)
  lazy val ThrusterAmplification: Try[IntcodeProgram] = from(AmplificationProgram)


  def from(resourceName: String): Try[IntcodeProgram] =
    raw(resourceName).map(intcode => IntcodeProgram(intcode))

  def raw(resourceName: String): Try[Seq[Long]] =
    Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toLong))
}
