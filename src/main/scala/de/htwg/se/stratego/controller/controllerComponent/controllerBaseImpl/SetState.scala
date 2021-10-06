package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

case class SetState(controller: Controller) extends ControllerState {

  override def handle(input: String): String = fixInput(input)

  def fixInput(input: String): String = {
    input.toList.filter(c => c != ' ') match {
      case 'i' :: Nil => controller.initMatchfield()
      case 's' :: row :: col :: charac :: Nil => controller.set(row.toString.toInt, col.toString.toInt, charac.toString)
      case _ =>"initialize matchfield with (i) or set a figure with (s row col figure) "
    }
  }

  override def nextState(): ControllerState = GameState(controller)
}
