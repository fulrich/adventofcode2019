package ship.thrusters.amplifiers

import ship.computer.IntcodeProgram

import scala.io.Source
import scala.util.Try


object AmplificationProgram {
    val AmplificationProgram = "ship/thrusters/amplification_program.txt"
    val Separator = ","


    def controllerProgram: Try[IntcodeProgram] = from(AmplificationProgram)

    def from(resourceName: String): Try[IntcodeProgram] =
      raw(resourceName).map(intcode => IntcodeProgram(intcode))

    def raw(resourceName: String): Try[Seq[Long]] =
      Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toLong))
}
