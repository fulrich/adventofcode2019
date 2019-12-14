package ship.nanofactory


case class ChemicalList(chemicals: Seq[Chemical]) {
  def ++(addChemicals: Seq[Chemical]): ChemicalList =
    addChemicals.foldLeft(this) {
      case (list, add) if add.quantity > 0 => list + add
      case (list, add) if add.quantity <= 0 => list
    }

  def + (add: Chemical): ChemicalList = chemicals.find(add.equalType) match {
    case Some(previous) => copy(chemicals.filterNot(add.equalType) :+ (previous + add))
    case None => if(add.quantity == 0) this else copy(chemicals :+ add)
  }


  def - (subtract: Chemical): ChemicalList = chemicals.find(subtract.equalType) match {
    case Some(previous) => subtraction(previous = previous, subtract = subtract)
    case None => throw new Exception("Can't remove a chemical that isn't a part of the chemical list.")
  }

  def findChemical(search: Chemical): Option[Chemical] = chemicals.find(search.equalType)
  def find(search: Chemical => Boolean): Option[Chemical] = chemicals.find(search)

  private def subtraction(previous: Chemical, subtract: Chemical): ChemicalList = {
    if(subtract > previous) throw new Exception("Can't subtract more from a chemical list than is available.")
    else if(subtract == previous) copy(chemicals.filterNot(subtract.equalType))
    else copy(chemicals.filterNot(subtract.equalType) :+ (previous - subtract))
  }
}

object ChemicalList {
  val Empty: ChemicalList = ChemicalList(Vector.empty)
}