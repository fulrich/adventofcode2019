package ship.thrusters.amplifiers

import ship.computer.IntcodeComputer
import ship.computer.internals.ComputerConfiguration
import ship.computer.internals.instructions.io.InputSource.ListInputSource
import ship.computer.internals.instructions.io.OutputSource.ListOutputSource


case class Amplifier(phaseSetting: Int, amplificationProgram: IntcodeComputer) {
  def run(input: Int = 0): Int = {
    val inputSource = new ListInputSource(Vector(phaseSetting, input))
    val outputSource = new ListOutputSource()
    val configuration = ComputerConfiguration(inputSource, outputSource)

    amplificationProgram.configure(configuration).execute()
//    println(s"${inputSource} => ${outputSource.output()}")
    outputSource.output().last
  }
}
