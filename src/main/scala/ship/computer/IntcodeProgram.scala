package ship.computer

import ship.computer.internals.instructions.InstructionSet
import ship.computer.internals._
import ship.computer.internals.instructions.io.Input

import scala.annotation.tailrec


case class IntcodeProgram(
  memory: Seq[Long],
  instructionPointer: Long = 0,
  state: State = State.Initial,
  configuration: Configuration = Configuration.Default)
    extends MemoryManagement
      with ConfigurationManagement
      with StateManagement
      with InstructionControl {

  private lazy val instructionSet: InstructionSet = InstructionSet(configuration)


  final def start(): IntcodeProgram = IntcodeProgram.execute(this)
  def continue(input: Long): IntcodeProgram =
    if(state.isWaiting) stopWaiting.setInput(Input.NextInput(input)).start()
    else this
}

object IntcodeProgram {
  def load(intcodes: Long*): IntcodeProgram = IntcodeProgram(intcodes.toVector)

  @tailrec
  final def execute(program: IntcodeProgram): IntcodeProgram =
    if(program.shouldStop) program
    else execute(program.instructionSet.execute(program))
}
