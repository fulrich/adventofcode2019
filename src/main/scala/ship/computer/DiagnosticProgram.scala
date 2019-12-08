package ship.computer

import scala.io.Source
import scala.util.Try


object DiagnosticProgram {
  val TestTerminalProgram = "ship/computer/diagnostics_program.txt"
  val Separator = ","


  def diagnosticTests: Try[IntcodeProgram] = from(TestTerminalProgram)

  def from(resourceName: String): Try[IntcodeProgram] =
    raw(resourceName).map(intcode => IntcodeProgram(intcode))

  def raw(resourceName: String): Try[Seq[Int]] =
    Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toInt))
}
