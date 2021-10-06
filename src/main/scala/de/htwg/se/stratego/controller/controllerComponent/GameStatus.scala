package de.htwg.se.stratego.controller.controllerComponent
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Field, Matrix}
import de.htwg.se.stratego.model.playerComponent.Player

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, UNDO, REDO, NEW, INIT, ATTACK = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    UNDO -> "Undo last step",
    REDO -> "Redo last step",
    NEW -> "Created new Game",
    INIT -> "matchfield initialized\nMove Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\n",
    ATTACK -> "enemy attacked"
  )

  def getMessage(actualStatus: GameStatus) = {
    map(actualStatus)
  }
}
