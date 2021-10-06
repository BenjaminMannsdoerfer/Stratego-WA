package de.htwg.se.stratego.model.fileIoComponent

import de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Game, MatchField}
import de.htwg.se.stratego.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class FileIoSpec extends WordSpec with Matchers {
  "FileIO" when {
    "a new Game created" should {
      val matchField = new MatchField(4, 4, false)
      val characList = CharacterList(4)
      val playerBlue = Player("PlayerBlue", characList.getCharacterList())
      val playerRed = Player("PlayerRed", characList.getCharacterList())
      val game = Game(playerBlue, playerRed, 4, matchField)
      val board = game.init(matchField, 0, 0, 0)
      val board1 = game.moveDown(board.matchField,0,0)
      val controller = new Controller(board1)

      "save and load the Gamestate with Json" in {
        import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.FileIO
        val fileIOJson = new FileIO()
        fileIOJson.save(board.matchField, controller.currentPlayerIndex, List[Player](playerBlue, playerRed))
        val fileIOLoadAsString = fileIOJson.load.toString()
        fileIOLoadAsString should be("(   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n,0,\"PlayerBlue PlayerRed\")")
      }

      "save and load the Gamestate with XML" in {
        import de.htwg.se.stratego.model.fileIoComponent.fileIoXmlImpl.FileIO
        val fileIOXML = new FileIO()
        fileIOXML.save(board.matchField, controller.currentPlayerIndex, List[Player](playerBlue, playerRed))
        val fileIOLoadAsString = fileIOXML.load.toString()
        fileIOLoadAsString should be("(   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n,0,PlayerBlue PlayerRed)")
      }
    }
  }
}