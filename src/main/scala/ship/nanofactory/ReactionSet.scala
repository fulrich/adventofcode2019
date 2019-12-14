package ship.nanofactory


case class ReactionSet(reactions: Seq[Reaction]) {
  def reactionFor(goal: Chemical): Reaction = reactions.find(_.output.name == goal.name) match {
    case Some(reaction) =>  calculateRequiredReaction(goal, reaction)
    case None => throw new Exception(s"No reaction for creating: $goal")
  }

  def simpleReactionFor(goal: Chemical): Reaction = reactions.find(_.output.name == goal.name) match {
    case Some(reaction) => reaction
    case None => throw new Exception(s"No reaction for creating: $goal")
  }


  private def calculateRequiredReaction(goal: Chemical, reaction: Reaction): Reaction =
    reaction * math.ceil(goal.quantity.toDouble / reaction.output.quantity.toDouble).toLong
}

object ReactionSet {
  val Empty: ReactionSet = ReactionSet(reactions = Vector.empty)

  def load(reactions: Seq[String]): ReactionSet = ReactionSet(reactions.map(Reaction.load))
}
