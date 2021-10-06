package de.htwg.se.stratego.aview

import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField
import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{

  "A Stratego Tui" should {
    val controller = new Controller(new MatchField(4, 4, false))
    val tui = new Tui(controller)

    "create and empty Matchfield on input 'n'" in {
      tui.processInputLine("n")
      controller.game.matchField should be(new MatchField(4, 4, false))
    }
    "quit" in {
      tui.processInputLine("q")
    }
    "undo" in {
      tui.processInputLine("z")
    }
    "redo" in {
      tui.processInputLine("y")
    }
    "save" in {
      tui.processInputLine("s")
    }
    "load" in {
      tui.processInputLine("l")
    }
  }
}