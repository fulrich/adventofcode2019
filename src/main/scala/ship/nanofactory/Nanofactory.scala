package ship.nanofactory

import scala.annotation.tailrec
import scala.io.Source

case class Nanofactory(
  reactions: ReactionSet,
  stillRequired: ChemicalList = ChemicalList.Empty,
  availableExcess: ChemicalList = ChemicalList.Empty) {
  def create(goal: Chemical): Nanofactory = copy(stillRequired = stillRequired + goal)

  def processFuel(quantity: Long = 1): Nanofactory = Nanofactory.process(this.create(Chemical(1, "FUEL")))
  def process: Nanofactory = Nanofactory.process(this)

  def nextRequired: Nanofactory = next match {
    case Some(requiresReaction) => nextRequiredUsingExcess(requiresReaction)
    case None => this
  }

  def nextRequiredUsingExcess(required: Chemical): Nanofactory =
    availableExcess.find(required.equalType) match {
      case Some(availableExcess) => nextRequiredWithExcess(required, availableExcess)
      case None => nextRequiredWithoutExcess(required)
    }

  def nextRequiredWithoutExcess(required: Chemical): Nanofactory = {
    val reaction = reactions.reactionFor(required)
    copy(
      stillRequired = (stillRequired - required) ++ reaction.input,
      availableExcess = availableExcess + (reaction.output - required)
    )
  }

  def nextRequiredWithExcess(required: Chemical, excess: Chemical): Nanofactory =
    if(required <= excess) copy(
      stillRequired = stillRequired - required,
      availableExcess = availableExcess - required
    )
    else {
      val requiredAfterExcess = required - excess
      val reaction = reactions.reactionFor(requiredAfterExcess)
      val excessFromReaction = reaction.output - requiredAfterExcess

      copy(
        stillRequired = (stillRequired - required) ++ reaction.input,
        availableExcess = availableExcess - excess + excessFromReaction
      )
    }

  lazy val ore: Long = stillRequired.find(_.isOre).map(_.quantity).getOrElse(0L)
  lazy val next: Option[Chemical] = stillRequired.find(_.notOre)
  lazy val isComplete: Boolean = next.isEmpty
}

object Nanofactory {
  val ReactionSetResource = "ship/nanofactory/reaction_set.txt"

  def load(): Nanofactory = Nanofactory(ReactionSet.load(Source.fromResource(ReactionSetResource).getLines.toVector))

  @tailrec
  def process(factory: Nanofactory): Nanofactory =
    if(factory.isComplete) factory
    else process(factory.nextRequired)

  @tailrec
  def processUntilNoExcess(factory: Nanofactory, availableOre: Long, count: Long = 0): (Long, Nanofactory) = {
    val nextProcessedFuel = factory.processFuel()

    if(nextProcessedFuel.availableExcess.chemicals.isEmpty) {
      println("Found Run Length")
      (count + 1, nextProcessedFuel)
    }
    else if (nextProcessedFuel.ore > availableOre) {
      println(nextProcessedFuel.ore + " > " + availableOre)
      println("Hit Available Ore Limit")
      (count + 1, nextProcessedFuel)
    }
    else processUntilNoExcess(nextProcessedFuel, availableOre, count + 1)
  }

  @tailrec
  private def fuelForOre(factory: Nanofactory, availableOre: Long, fuelCreated: Long = 0): Long ={
    val nextProcessedFuel = factory.processFuel()

    if(nextProcessedFuel.ore > availableOre) fuelCreated
    else fuelForOre(nextProcessedFuel, availableOre, fuelCreated + 1)
  }

  def findFuelForOre(factory: Nanofactory, availableOre: Long): Long = {
    val resultFromRun = Nanofactory.processUntilNoExcess(factory, availableOre)

    val createdFuelFromRun: Long = resultFromRun._1
    val requiredOreFromRun: Long = resultFromRun._2.ore

    val runsPossible: Long = math.floor(availableOre.toDouble / requiredOreFromRun.toDouble).toLong
    val totalCreatedFuelFromRun: Long = createdFuelFromRun * runsPossible

    val remainingOreAfterRuns = availableOre - (requiredOreFromRun * runsPossible)
    val fuelFromRemainingOre = Nanofactory.fuelForOre(factory, remainingOreAfterRuns)

    totalCreatedFuelFromRun + fuelFromRemainingOre
  }
}

