package day1


object FuelCounterUpper {
  def totalFuelFor(modules: Seq[Module]): Int = modules.map(_.fuel).sum
}
