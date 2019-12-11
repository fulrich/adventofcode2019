package asteroidbelt.targeting


case class TargetList(targets: Seq[Target]) {
  def target(targetNumber: Int): Target = targets(targetNumber - 1)
}
