package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

case class GameState(controller: Controller) extends ControllerState {

  override def handle(input: String): String = fixInput(input)

  def fixInput(input: String): String = {
    input.toList.filter(c => c != ' ') match {
      case 'm' :: dir :: row :: col :: Nil => controller.move(dir, row.toString.toInt, col.toString.toInt)
      case 'a' :: rowA :: colA :: rowD :: colD :: Nil => controller.attack(rowA.toString.toInt, colA.toString.toInt,
        rowD.toString.toInt, colD.toString.toInt)
      case _ => "move a figure with (m row col) or attack a figure with (a row col row col)"
    }
  }

  override def nextState(): ControllerState = EnterPlayer(controller)
}
