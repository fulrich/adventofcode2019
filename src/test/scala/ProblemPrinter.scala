trait ProblemPrinter {
  def OutputAnswers: Boolean

  def printAnswer[A](day: Int, problem: Int, answer: A): Unit = {
    if(OutputAnswers) {
      println(s"Day $day - Problem $problem Answer: $answer")
    }
  }

  def printAnswer(day: Int, problem: Int)(bigAnswer: => Unit): Unit = {
    if(OutputAnswers) {
      printAnswer(day, problem, "")
      bigAnswer
    }
  }
}
