package ship.computer

import ship.computer.internals.instructions.io.InputSource.ListInputSource
import ship.computer.internals.instructions.io.OutputSource.ListOutputSource
import ship.computer.internals.{Configuration, MemoryManagement}
import language.implicitConversions


trait ComputerTesting {
  implicit def toComputerTestExecution(computer: IntcodeProgram): ComputerTestExecution = ComputerTestExecution(computer)

  case class ComputerTestExecution(computer: IntcodeProgram, input: Seq[Int] = Vector.empty) {
    def testInput(value: Int*): ComputerTestExecution = copy(input = value.toVector)

    def testOutput(testFunction: Seq[Int] => Unit): Unit = testExecute { (_, output) => testFunction(output) }

    def testExecute(testFunction: (IntcodeProgram, Seq[Int]) => Unit): Unit = {
      val testOutputSource = ListOutputSource()
      val testConfiguration = Configuration(ListInputSource(input), testOutputSource)
      val testComputer = computer.copy(configuration = testConfiguration)
      val postTestComputer = testComputer.execute()

      testFunction(postTestComputer, testOutputSource.output())
    }
  }
}
