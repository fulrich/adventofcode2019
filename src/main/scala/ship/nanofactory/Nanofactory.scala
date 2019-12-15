package ship.nanofactory

import scala.annotation.tailrec
import scala.io.Source

case class Nanofactory(
  reactions: ReactionSet,
  stillRequired: ChemicalList = ChemicalList.Empty,
  availableExcess: ChemicalList = ChemicalList.Empty) {
  def create(goal: Chemical): Nanofactory = copy(stillRequired = stillRequired + goal)

  def processFuel(quantity: Long = 1): Nanofactory = Nanofactory.process(this.create(Chemical(quantity, "FUEL")))
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
  def findFuelForOre(factory: Nanofactory, availableOre: Long, minimum: Long = 0, maximum: Long = 100_000_000): Long = {
    val middleValue: Long = (maximum + minimum) / 2;
    val factoryResult = factory.processFuel(middleValue)

    if (minimum > maximum) { maximum }
    else {
      if (factoryResult.ore > availableOre) findFuelForOre(factory, availableOre, minimum, middleValue - 1)
      else if (factoryResult.ore < availableOre) findFuelForOre(factory, availableOre, middleValue + 1, maximum)
      else middleValue
    }
  }
}

