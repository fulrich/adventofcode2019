package ship.nanofactory


case class Reaction(input: Seq[Chemical], output: Chemical) {
  def * (multiple: Long): Reaction = Reaction(input.map(_ * multiple), output * multiple)

  lazy val isOreReaction: Boolean = input.forall(_.isOre)
  lazy val ore: Option[Long] =
    if(isOreReaction) Some(input.foldLeft(0L){ case(totalOre, input) => totalOre + input.quantity })
    else None

  override def toString: String = s"${input.map(_.toString).mkString(" ")} => $output"
}

object Reaction {
  val ReactionSeparators = "(=>|,)"

  def load(reaction: String): Reaction =
    reaction.split(ReactionSeparators).toVector match {
      case input :+ output => Reaction(input.map(Chemical.load), Chemical.load(output))
      case _ => throw new Exception(s"Unable to parse reaction for Nanofactory: $reaction")
    }
}
