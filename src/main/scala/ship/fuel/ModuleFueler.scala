package ship.fuel

import ship.Module


object ModuleFueler {
  def fuelUpModules(modules: Seq[Module]): Seq[Module] = modules.map(fuelUpModule)

  def fuelUpModule(module: Module): Module = module.copy(fuel = FuelCounterUpper.fuelForModule(module))


  def fuelUpModulesIgnoringFuelWeight(modules: Seq[Module]): Seq[Module] = modules.map(fuelUpModuleIgnoringFuelWeight)

  def fuelUpModuleIgnoringFuelWeight(module: Module): Module = module.fuelUp(FuelCounterUpper.fuelForMass(module.mass))
}
