package ship.computer

import ship.computer.internals.IntcodeState

import scala.io.Source
import scala.util.Try


object DiagnosticProgram {
  val TestTerminalProgram = "ship/computer/diagnostics_program.txt"
  val Separator = ","


  def diagnosticTests: Try[IntcodeState] = from(TestTerminalProgram)

  def from(resourceName: String): Try[IntcodeState] =
    raw(resourceName).map(intcode => IntcodeState(intcode))

  def raw(resourceName: String): Try[Seq[Int]] =
    Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toInt))
}
