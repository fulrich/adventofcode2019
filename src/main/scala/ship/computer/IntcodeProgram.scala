package ship.computer

import ship.computer.internals.instructions.InstructionSet
import ship.computer.internals._

import scala.annotation.tailrec


case class IntcodeProgram(
  memory: Seq[Int],
  instructionPointer: Int = 0,
  state: State = State.Initial,
  configuration: Configuration = Configuration.Default)
    extends MemoryManagement
      with ConfigurationManagement
      with StateManagement
      with InstructionControl {

  private lazy val instructionSet: InstructionSet = InstructionSet(configuration)

  @tailrec
  final def execute(program: IntcodeProgram = this): IntcodeProgram =
    if(program.isComplete) program
    else execute(instructionSet.execute(program))

  def configure(configuration: Configuration): IntcodeProgram = copy(configuration = configuration)
}

object IntcodeProgram {
  def load(intcodes: Int*): IntcodeProgram = IntcodeProgram(intcodes.toVector)
}