package ship.computer.internals

case class State (
  isComplete: Boolean = false
)

object State {
  val Initial: State = State()
}