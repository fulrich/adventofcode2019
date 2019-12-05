package ship.fuel

import ship.Module


object FuelCounterUpper {
  def totalFuelFor(modules: Seq[Module]): Int = modules.map(_.fuel).sum
}
