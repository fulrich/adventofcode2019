package ship.computer.internals

import ship.computer.IntcodeProgram


trait StateManagement { self: IntcodeProgram =>
  def state(updated: State => State): IntcodeProgram = copy(state = updated(state))

  def completed: IntcodeProgram = state(_.copy(isComplete = true))

  def startWaiting: IntcodeProgram = state(_.copy(isWaiting = true))
  def stopWaiting: IntcodeProgram = state(_.copy(isWaiting = false))

  def shouldContinue: Boolean = !shouldStop
  def shouldStop: Boolean = state.isComplete || state.isWaiting
}
