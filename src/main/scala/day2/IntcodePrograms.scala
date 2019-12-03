package day2

import scala.io.Source
import scala.util.Try


object IntcodePrograms {
  val GravityAssistProgram = "day2/gravity_assist_program.txt"
  val Separator = ","


  def gravityAssist: Try[IntcodeState] = from(GravityAssistProgram)

  def from(resourceName: String): Try[IntcodeState] =
    raw(resourceName).map(intcode => IntcodeState(intcode))

  def raw(resourceName: String): Try[Seq[Int]] =
    Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toInt))
}
