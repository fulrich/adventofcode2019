package ship.computer.internals.instructions.io


trait OutputSource {
  def output(value: Int): Unit
}

object OutputSource {
  val StandardOut: OutputSource = (value: Int) => println(value)
}
