package de.htwg.se.stratego.controller

import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.{Controller, GameState}
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField
import de.htwg.se.stratego.util.{Observable, Observer}
import org.scalatest.{Matchers, WordSpec}

class GameStateSpec extends WordSpec with Matchers {
  "A InGameState" when {
    val matchField = new MatchField(4, 4, false)
    val controller = new Controller(matchField)
    val state = GameState(controller)
    "created" should {
      "handle" in {
        state.handle("m d 0 0") should be("")
        state.handle("a 0 0 0 1") should be("")
        state.handle("bullshit") should be("move a figure with (m row col) or attack a figure with (a row col row col)")
      }
      "next State" in {
        state.nextState() should be (state.nextState())
      }
    }

  }
}
