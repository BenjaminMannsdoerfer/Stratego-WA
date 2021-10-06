package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField
import de.htwg.se.stratego.util.Command

class MoveCommand(dir: Char, matchField: MatchFieldInterface, row: Int, col: Int, currentPlayerIndex: Int, controller: Controller) extends Command {

  override def doStep: Unit = {
    controller.game = controller.game.copy(matchField = controller.game.move(dir, matchField, row, col, currentPlayerIndex))
  }

  override def undoStep: Unit = {
    controller.game = controller.game.copy(matchField =
      dir match {
        case 'u' => controller.game.move('d', matchField, row, col, currentPlayerIndex)
        case 'd' => controller.game.move('u', matchField, row, col, currentPlayerIndex)
        case 'r' => controller.game.move('l', matchField, row, col, currentPlayerIndex)
        case 'l' => controller.game.move('r', matchField, row, col, currentPlayerIndex)
      })
  }

  override def redoStep: Unit = {
    controller.game = controller.game.copy(matchField = controller.game.move(dir, matchField, row, col, currentPlayerIndex))
  }

}
