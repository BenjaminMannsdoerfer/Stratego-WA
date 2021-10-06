package de.htwg.se.stratego.controller

import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.{Controller, SetCommand}
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.Figure.FigureVal
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField
import de.htwg.se.stratego.util.{Observable, Observer}
import org.scalatest.{Matchers, WordSpec}

class SetCommandSpec extends WordSpec with Matchers {
  "A SetCommandSpec" when {
    val matchField = new MatchField(4, 4, false)
    val controller = new Controller(matchField)
    controller.initMatchfield()
    val command = new SetCommand(0, 1,0,"9",controller)


    "created" should {
      "undoStep" in {
        command.undoStep should be()
        command.redoStep should be()
      }
    }
  }
}
