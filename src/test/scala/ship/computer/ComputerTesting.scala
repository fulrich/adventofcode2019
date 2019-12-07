package ship.computer

import ship.computer.internals.instructions.io.InputSource.ListInputSource
import ship.computer.internals.instructions.io.OutputSource.ListOutputSource
import ship.computer.internals.{ComputerConfiguration, IntcodeState}
import language.implicitConversions


trait ComputerTesting {
  implicit def toComputerTestExecution(computer: IntcodeComputer): ComputerTestExecution = ComputerTestExecution(computer)

  case class ComputerTestExecution(computer: IntcodeComputer, input: Seq[Int] = Vector.empty) {
    def testInput(value: Int*): ComputerTestExecution = copy(input = value.toVector)

    def testOutput(testFunction: Seq[Int] => Unit): Unit = testExecute { (_, output) => testFunction(output) }

    def testExecute(testFunction: (IntcodeState, Seq[Int]) => Unit): Unit = {
      val testOutputSource = new ListOutputSource()
      val testConfiguration = ComputerConfiguration(new ListInputSource(input), testOutputSource)
      val testComputer = computer.copy(configuration = testConfiguration)
      
      testFunction(testComputer.execute(), testOutputSource.output())
    }
  }
}
