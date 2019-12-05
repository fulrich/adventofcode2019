package ship.computer

import ship.computer.internals.instructions.io.{InputSource, OutputSource}
import ship.computer.internals.{ComputerConfiguration, IntcodeState}

import scala.collection.mutable


trait ComputerTesting {
  class TestOutputSource extends OutputSource {
    private val outputValue: mutable.ArrayBuffer[Int] = new mutable.ArrayBuffer[Int]()

    override def output(value: Int): Unit = outputValue.addOne(value)
    def outputValues: Seq[Int] = outputValue.toVector
  }

  class TestInputSource(inputs: Seq[Int]) extends InputSource {
    private val inputValues: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer.from(inputs)

    override def get(): Int = inputValues.remove(0)
  }

  implicit def toComputerTestExecution(computer: IntcodeComputer): ComputerTestExecution = ComputerTestExecution(computer)

  case class ComputerTestExecution(computer: IntcodeComputer, input: Seq[Int] = Vector.empty) {
    def testInput(value: Int*): ComputerTestExecution = copy(input = value.toVector)

    def testOutput(testFunction: Seq[Int] => Unit): Unit = testExecute { (_, output) => testFunction(output) }

    def testExecute(testFunction: (IntcodeState, Seq[Int]) => Unit): Unit = {
      val testOutputSource = new TestOutputSource()
      val testConfiguration = ComputerConfiguration(new TestInputSource(input), testOutputSource)
      val testComputer = computer.copy(configuration = testConfiguration)
      
      testFunction(testComputer.execute(), testOutputSource.outputValues)
    }
  }
}
