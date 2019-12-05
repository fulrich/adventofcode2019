package ship

case class Module(mass: Int, fuel: Int = 0) {
  def fuelUp(addedFuel: Int): Module = copy(fuel = fuel + addedFuel)
}
