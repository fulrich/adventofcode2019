package mars.spaceimage

object Pixel {
  val Black = 0
  val White = 1
  val Transparent = 2

  def toString(pixel: Int): String = pixel match {
    case Black => Console.BLACK + "X"
    case White => Console.RED + "X"
    case Transparent => " "
  }
}
