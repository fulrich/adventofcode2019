package ship.nanofactory


case class Chemical(quantity: Long, name: String) {
  def isOre: Boolean = name == Chemical.OreName
  def notOre: Boolean = !isOre

  def * (multiple: Long): Chemical = copy(quantity * multiple)

  def + (other: Chemical): Chemical = operationWith(other) { Chemical(quantity + other.quantity, name) }
  def - (other: Chemical): Chemical = operationWith(other) { Chemical(quantity - other.quantity, name) }

  def > (other: Chemical): Boolean = operationWith(other) { quantity > other.quantity }
  def < (other: Chemical): Boolean = operationWith(other) { quantity < other.quantity }
  def <= (other: Chemical): Boolean = operationWith(other) { quantity <= other.quantity }
  def >= (other: Chemical): Boolean = operationWith(other) { quantity >= other.quantity }
  def == (other: Chemical): Boolean = operationWith(other) { quantity == other.quantity }

  def equalType(other: Chemical): Boolean = name == other.name

  private def operationWith[A](other: Chemical)(doOperation: A): A =
    if(equalType(other)) doOperation
    else throw new Exception("Cannot run operations on chemicals that have different names.")

  override def toString: String = s"$quantity $name"
}

object Chemical {
  val OreName = "ORE"

  def load(chemical: String): Chemical =
    chemical.trim.split(' ').toVector match {
      case Seq(quantity, name) => Chemical(quantity.toLong, name)
      case _ => throw new Exception(s"Unable to parse Chemical for Nanofactory: $chemical")
    }

  def reduce(chemicals: Seq[Chemical]): Seq[Chemical] =
    chemicals.groupBy(_.name).values.toVector.map {
      case chemical :: Nil => chemical
      case chemicals => chemicals.reduce(_ + _)
    }
}
