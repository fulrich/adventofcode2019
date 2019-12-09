package ship.computer

import scala.io.Source
import scala.util.Try


object Programs {
  val GravityAssistProgram = "ship/computer/gravity_assist_program.txt"
  val DiagnosticsProgram = "ship/computer/diagnostics_program.txt"
  val BoostProgram = "ship/computer/boost_program.txt"
  val Separator = ","


  def gravityAssist: Try[IntcodeProgram] = from(GravityAssistProgram)
  def diagnostics: Try[IntcodeProgram] = from(DiagnosticsProgram)
  def boost: Try[IntcodeProgram] = from(BoostProgram)



  def from(resourceName: String): Try[IntcodeProgram] =
    raw(resourceName).map(intcode => IntcodeProgram(intcode))

  def raw(resourceName: String): Try[Seq[Long]] =
    Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toLong))
}
