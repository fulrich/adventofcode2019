package ship.computer.internals.instructions.io

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction

import scala.collection.mutable
import scala.io.StdIn


trait Input extends Instruction {
  override val Opcode: Int = 3
  override val NumberOfParameters: Int = 1
}

object Input {
  object Console extends Input {
    override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
      program.set(program.parameterOne, StdIn.readInt)
    }
  }

  case class Static(inputs: Seq[Int]) extends Input {
    private val inputValues: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer.from(inputs)

    override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
      program.set(program.parameterOne, inputValues.remove(0))
    }
  }


  case object NeedInput extends Input {
    override def execute(program: IntcodeProgram): IntcodeProgram = program.startWaiting
  }

  case class NextInput(input: Int) extends Input {
    override def execute(program: IntcodeProgram): IntcodeProgram = withInstructionIncrement {
      program
        .setInput(NeedInput)
        .set(program.parameterOne, input)
    }
  }
}
