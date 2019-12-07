package ship.thrusters.amplifiers

import ship.computer.IntcodeComputer
import ship.computer.internals.IntcodeState

import scala.io.Source
import scala.util.Try


object AmplificationProgram {
    val AmplificationProgram = "ship/thrusters/amplification_program.txt"
    val Separator = ","


    def controllerProgram: Try[IntcodeComputer] = from(AmplificationProgram).map(IntcodeComputer.apply(_))

    def controllerSoftware: Try[IntcodeState] = from(AmplificationProgram)

    def from(resourceName: String): Try[IntcodeState] =
      raw(resourceName).map(intcode => IntcodeState(intcode))

    def raw(resourceName: String): Try[Seq[Int]] =
      Try(Source.fromResource(resourceName).getLines.mkString.split(Separator).map(_.toInt))
}
