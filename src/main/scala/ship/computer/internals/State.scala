package ship.computer.internals

case class State (
  isComplete: Boolean = false,
  isWaiting: Boolean = false,
  relativeBase: Long = 0,
  error: Option[String] = None
)

object State {
  val Initial: State = State()
}