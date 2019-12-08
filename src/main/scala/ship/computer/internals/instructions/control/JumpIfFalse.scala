package ship.computer.internals.instructions.control

import ship.computer.IntcodeProgram
import ship.computer.internals.instructions.Instruction

object JumpIfFalse extends Instruction {
  override val Opcode: Int = 6
  override val NumberOfParameters: Int = 2

  override def execute(program: IntcodeProgram): IntcodeProgram =
    if(program.parameterOne.value == 0) program.setInstructionPointer(program.parameterTwo.value)
    else program.incrementInstructionPointer
}
