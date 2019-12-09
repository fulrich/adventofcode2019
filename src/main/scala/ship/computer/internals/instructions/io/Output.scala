package ship.computer.internals.instructions.io

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction

import scala.collection.mutable


trait Output extends Instruction {
  override val Opcode = 4
  override val NumberOfParameters = 1

  def trackedOutput: Seq[Long] = Vector.empty
}

object Output {
  case object Console extends Output {
    override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
      println(program.parameterOne.value)
      program
    }
  }

  case class Collection() extends Output {
    private val outputValue: mutable.ArrayBuffer[Long] = new mutable.ArrayBuffer[Long]()

    override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
      outputValue.addOne(program.parameterOne.value)
      program
    }

    override def trackedOutput: Seq[Long] = outputValue.toVector
  }
}
