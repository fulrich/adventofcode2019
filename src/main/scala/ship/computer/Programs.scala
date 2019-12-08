package ship.computer

import scala.io.Source
import scala.util.Try


object Programs {
  val GravityAssistProgram = "ship/computer/gravity_assist_program.txt"
  val Separator = ","


  def gravityAssist: Try[IntcodeProgram] = from(GravityAssistProgram)

  def from(resourceName: String): Try[IntcodeProgram] =
    raw(resourceName).map(intcode => IntcodeProgram(intcode))

  def raw(resourceName: String): Try[Seq[Int]] =
    Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toInt))
}
