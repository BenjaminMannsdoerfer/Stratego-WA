package de.htwg.se.stratego.model.fileIoComponent

import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.playerComponent.Player

trait FileIOInterface {
  def load: (MatchFieldInterface, Int, String)
  def save(matchField: MatchFieldInterface, currentPlayerIndex: Int, players:List[Player]): Unit
}
