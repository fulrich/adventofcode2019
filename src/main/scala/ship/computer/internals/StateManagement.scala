package ship.computer.internals

import ship.computer.IntcodeProgram


trait StateManagement { self: IntcodeProgram =>
  def isComplete: Boolean = self.state.isComplete

  def state(updated: State => State): IntcodeProgram = copy(state = updated(state))

  def completed: IntcodeProgram = state(_.copy(isComplete = true))
}
