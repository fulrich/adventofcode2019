package ship.computer

import ship.computer.internals.Configuration

import scala.language.implicitConversions


trait ComputerTesting {
  implicit def toComputerTestExecution(computer: IntcodeProgram): ComputerTestExecution = ComputerTestExecution(computer)

  case class ComputerTestExecution(computer: IntcodeProgram, input: Seq[Long] = Vector.empty) {
    def testInput(value: Long*): ComputerTestExecution = copy(input = value.toVector)

    def testOutput(testFunction: Seq[Long] => Unit): Unit = testExecute { (_, output) => testFunction(output) }

    def testExecute(testFunction: (IntcodeProgram, Seq[Long]) => Unit): Unit = {
      val testConfiguration = Configuration.static(input)
      val testComputer = computer.copy(configuration = testConfiguration)
      val postTestComputer = testComputer.start()

      testFunction(postTestComputer, testConfiguration.output.trackedOutput)
    }
  }
}
