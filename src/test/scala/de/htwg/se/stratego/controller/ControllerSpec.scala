package de.htwg.se.stratego.controller

import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Figure, Game, GameCharacter, MatchField}
import de.htwg.se.stratego.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable.ListBuffer

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when {
    "created" should {
      val matchField = new MatchField(4, 4, false)
      val controller = new Controller(matchField)
      val controller1 = new Controller(matchField)
      val controller2 = new Controller(matchField)
      val controller3 = new Controller(matchField)
      controller3.initMatchfield()


      val characterList = new CharacterList(4)
      val playerBlue = new Player("PlayerBlue",characterList.getCharacterList())
      val playerRed = new Player("PlayerRed", characterList.getCharacterList())
      val game = new Game(playerBlue,playerRed,4,matchField)

      val newCharacterList = ListBuffer[GameCharacter](GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Flag),
        GameCharacter(Figure.Spy))
      val playerBlue1 = new Player("PlayerBlue",newCharacterList)
      val playerRed1 = new Player("PlayerRed", newCharacterList)
      val game1 = new Game(playerBlue,playerRed,4,matchField)

      "can handle" in {
        controller.handle("player1 player2") should be ("")
      }
      "can welcome" in {
        controller.welcome() should be (
          "Welcome to STRATEGO! " +
          "Please enter first name of Player1 and then of Player2 like (player1 player2)!")
      }
      "can set figures" in {
        controller.set(0,0,"F") should be ("")
        controller.set(0,1,"9") should be ("")
        controller.set(0,2,"8") should be ("")
        controller.set(0,3,"6") should be ("")
        controller.set(0,3,"F") should be ("")

        controller.set(3,3,"6") should be ("")
        controller.set(3,0,"F") should be ("")
        controller.set(3,1,"9") should be ("")
        controller.set(3,2,"8") should be ("Move Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\nPlayerBlue it's your turn!")

        controller2.currentPlayerIndex = 0
        controller2.set(3,3,"6") should be ("")
        controller2.set(3,0,"F") should be ("")
        controller2.set(3,1,"9") should be ("")
        controller2.set(3,3,"9") should be ("")

        controller2.set(0,0,"F") should be ("")
        controller2.set(0,1,"9") should be ("")
        controller2.set(0,2,"8") should be ("")
        controller2.set(0,3,"6") should be ("")

      }
      "can move" in {
        controller.move('d', 0 , 0 ) should be ("")
        controller.move ('d', 1, 1) should be ("")
        controller3.move('d', 0 , 0)
        controller3.move('u',3,0) should be ("")
        controller3.attack(1,0,2,0) should be("")
        controller3.move('u',3,2) should be("")
        controller3.move('u',2,2) should be("")
        controller3.move('r',1,2) should be("")
        controller3.attack(1,3,0,3) should be("")
      }
      "can attack" in {
        controller3.move('d', 0 , 0)
        controller3.move('u',3,0) should be ("")
        controller3.attack(1,0,2,0) should be("")
        controller3.move('u',3,1) should be ("")
        controller3.move('d', 0 , 1)
        controller3.attack(2,1,1,1) should be("")
        controller3.move('d', 0 , 2)
        controller3.move('u',3,2) should be ("")
        controller3.attack(1,2,2,2) should be("")
        controller3.attack(0,3,1,3) should be("")
        controller3.attack(3,3,2,3) should be("")
      }
      "can get matchfield as matrix" in {
        controller.getField.toString should be ("Matrix(Vector(Vector(F, 9, 8, 6), Vector( ,  ,  ,  ), Vector( ,  ,  ,  ), Vector(F, 9, 8, 6)))")
      }
      "can get status as string" in {
        controller.statusString should be ("")
      }
      "can get size" in {
        controller.getSize should be (4)
      }
      "can save the game" in {
        controller.save should be ("save")
      }

    }
  }
}
