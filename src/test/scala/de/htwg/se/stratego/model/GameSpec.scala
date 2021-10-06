package de.htwg.se.stratego.model
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Colour, Game, MatchField}
import de.htwg.se.stratego.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class GameSpec extends WordSpec with Matchers {
  "A Game" when {
    val matchField = new MatchField(4, 4, false)
    val characList = CharacterList(4)
    val playerBlue = Player("PlayerBlue", characList.getCharacterList())
    val playerRed = Player("PlayerRed", characList.getCharacterList())
    val game = Game(playerBlue, playerRed, 4, matchField)

    val matchField4 = new MatchField(4, 4, false)
    val characList4 = CharacterList(4)
    val playerBlue4 = Player("PlayerBlue", characList4.getCharacterList())
    val playerRed4 = Player("PlayerRed", characList4.getCharacterList())
    val game4 = Game(playerBlue4, playerRed4, 4, matchField4)

    val matchField6 = new MatchField(6, 6, false)
    val characList6 = CharacterList(6)
    val playerBlue6 = Player("PlayerBlue", characList6.getCharacterList())
    val playerRed6 = Player("PlayerRed", characList6.getCharacterList())
    val game6 = Game(playerBlue6, playerRed6, 6, matchField6)

    val matchField61 = new MatchField(6, 6, false)
    val characList61 = CharacterList(6)
    val playerBlue61 = Player("PlayerBlue", characList61.getCharacterList())
    val playerRed61 = Player("PlayerRed", characList61.getCharacterList())
    val game61 = Game(playerBlue61, playerRed61, 6, matchField61)

    val matchField8 = new MatchField(8, 8, false)
    val characList8 = CharacterList(8)
    val playerBlue8 = Player("PlayerBlue", characList8.getCharacterList())
    val playerRed8 = Player("PlayerRed", characList8.getCharacterList())
    val game8 = Game(playerBlue8, playerRed8, 8, matchField8)

    val matchField10 = new MatchField(10, 10, false)
    val characList10 = CharacterList(10)
    val playerBlue10 = Player("PlayerBlue", characList10.getCharacterList())
    val playerRed10 = Player("PlayerRed", characList10.getCharacterList())
    val game10 = matchFieldBaseImpl.Game(playerBlue10, playerRed10, 10, matchField10)
    "created with two Players and a empty Matchfield" should {
      "fill the Matchfield with Characters" in {
        game.init(matchField, 0, 0, 0).toString should be("Game(PlayerBlue,PlayerRed,4,   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n)")
      }
    }

    "get a matchfield with only bombs and flags" should {
      "be true" in {
        game.onlyBombAndFlag(matchField, 0, 0, 0) should be(true)
      }
    }

    "gets a matchfield with blue and red fields" should {
      "set with characters" in {
        game6.set(0, 0, 0, "9").toString should be("Game(PlayerBlue,PlayerRed,6,   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |     |     |     |     |     | 0\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 3\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 4\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n)")
        game4.set(1, 3, 0, "6").toString should be("Game(PlayerBlue,PlayerRed,4,   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m6\u001B[0m  |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n)")
      }
    }

    "gets a matchfield with blue Fields" should {
      "set a character" in {
        game6.setCharacter(playerBlue6.characterList, Colour.FigureCol(0))(1,0,"F").toString should be("Game(PlayerBlue,PlayerRed,6,   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 0\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34mF\u001B[0m  |     |     |     |     |     | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 3\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 4\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n)")
      }
    }

    "gets a matchfield with red Fields" should {
      "set a character" in {
        game6.setCharacter(playerRed6.characterList, Colour.FigureCol(1))(5,0,"F").toString should be("Game(PlayerBlue,PlayerRed,6,   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 0\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 3\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 4\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31mF\u001B[0m  |     |     |     |     |     | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n)")
        game4.setCharacter(playerRed4.characterList, Colour.FigureCol(1))(0,0,"6").toString should be("Game(PlayerBlue,PlayerRed,4,   0     1     2     3  \n+-----+-----+-----+-----+\n|     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n)")
      }
    }



    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init(matchField, 0, 0, 0)
      val board1 = game.moveDown(board.matchField,0,0)
      val board2 = game.moveDown(board1,1,0)
      "and now one character moves down" in {
        game.moveDown(board.matchField, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveDown(board.matchField,3,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveDown(board.matchField,0,3).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveDown(board2,2,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|     |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init(matchField, 0, 0, 0)
      val board1 = game.moveUp(board.matchField,3,0)
      val board2 = game.moveUp(board1,2,0)
      "and now one character moves up" in {
        game.moveUp(board.matchField, 3, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |     |     |     | 2\n+-----+-----+-----+-----+\n|     |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveUp(board.matchField,3,3).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveUp(board.matchField,0,3).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveUp(board2,1,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init(matchField, 0, 0, 0)
      "and now one character moves to the left" in {
        game.moveLeft(board.matchField, 0, 1).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init(matchField, 0, 0, 0)
      "and now one character moves to the right" in {
        game.moveRight(board.matchField, 0, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.moveRight(board.matchField, 3, 0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with a red and a blue List each with filled characters" should {
      val board = game.init(matchField, 0, 0, 0)
      "and now one character moves to the right out of bounds" in {
        game.moveRight(board.matchField, 3, 3).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with characters in it" should {
      "convert the characters to values" in {
        game.characValue("1") should be(1)
        game.characValue("F") should be(0)
        game.characValue("B") should be(11)
        game.characValue("M") should be(10)
      }
    }

    "gets a matchfield and " should {
      "have some blue character" in {
        game.isColorChar(characList.getCharacterList())( "A") should be(false)
      }
    }

    "gets a matchfield with blue fields" should {

      "only set characters in blue fields" in {
        game.isRedOrBlueField(1,Colour.BLUE.value) should be(false)
        game6.isRedOrBlueField(2,Colour.RED.value) should be(false)
        game8.isRedOrBlueField(3,Colour.RED.value) should be(false)
        game10.isRedOrBlueField(4,Colour.RED.value) should be(false)
      }
    }

    "gets a matchfield with red fields" should {
      "only set characters in red fields" in {
        game.isRedOrBlueField(2,Colour.RED.value) should be(false)
        game6.isRedOrBlueField(3, Colour.RED.value) should be(false)
        game8.isRedOrBlueField(4,Colour.RED.value) should be(false)
        game10.isRedOrBlueField(5,Colour.RED.value) should be(false)
      }
    }

    "gets a matchfield with characters" should {
      val board = game.init(matchField, 0, 0, 0)
      "that are moving in directions" in {
        game.move('u',board.matchField,3,0,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }

    "gets a matchfield with characters" should {
      val board = game.init(matchField, 0, 0, 0)
      "that has values" in {
        game.figureHasValue(board.matchField,0,0) should be(9)
      }
    }

    "gets a matchfield with characters" should {
      val board = game.init(matchField, 0, 0, 0)
      val board1 = game.moveUp(board.matchField,3,2)
      val board2 = game.moveUp(board1,2,2)
      val board3 = game.moveRight(board2,1,2)
      val board4 = game61.init(matchField6, 0, 0, 0)
      val board5 = game61.moveDown(board4.matchField,1,0)
      val board6 = game61.moveDown(board5,2,0)
      val board7 = game61.moveDown(board6,1,4)
      val board8 = game61.moveUp(board7,4,5)
      val board9 = game61.moveUp(board8,3,5)
      val board10 = game61.moveDown(board9,2,4)
      val board11 = game61.moveLeft(board10,2,5)
      val board12 = game61.moveUp(board11,2,4)
      val board13 = game61.Context.attack(board12,1,4,0,4,1)
      "attack each others" in {
        game.Context.attack(board.matchField,0,0,0,1,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        //game.Context.attack(board.matchField,1,1,2,1,1).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.Context.attack(board.matchField,0,1,0,2,1).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        //game.Context.attack(board.matchField,3,0,1,0,1).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game.Context.attack(board3, 0,3,1,3,0).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m6\u001B[0m  |     | 0\n+-----+-----+-----+-----+\n|     |     |     |  \u001B[0;31m6\u001B[0m  | 1\n+-----+-----+-----+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |     |  \u001B[0;31mF\u001B[0m  | 3\n+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game6.Context.attack(board6,3,0,4,0,0).toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34mB\u001B[0m  |  \u001B[0;34m3\u001B[0m  |  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m7\u001B[0m  |  \u001B[0;34m6\u001B[0m  | 0\n+-----+-----+-----+-----+-----+-----+\n|     |  \u001B[0;34m4\u001B[0m  |  \u001B[0;34mM\u001B[0m  |  \u001B[0;34m2\u001B[0m  |  \u001B[0;34m1\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 3\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31mB\u001B[0m  |  \u001B[0;31m3\u001B[0m  |  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m7\u001B[0m  |  \u001B[0;31m6\u001B[0m  | 4\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31m5\u001B[0m  |  \u001B[0;31m4\u001B[0m  |  \u001B[0;31mM\u001B[0m  |  \u001B[0;31m2\u001B[0m  |  \u001B[0;31m1\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game6.Context.attack(board12,1,4,0,4,1).toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34mB\u001B[0m  |  \u001B[0;34m3\u001B[0m  |  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m7\u001B[0m  |  \u001B[0;34m6\u001B[0m  | 0\n+-----+-----+-----+-----+-----+-----+\n|     |  \u001B[0;34m4\u001B[0m  |  \u001B[0;34mM\u001B[0m  |  \u001B[0;34m2\u001B[0m  |     |  \u001B[0;34mF\u001B[0m  | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34m5\u001B[0m  |     |     |     |  \u001B[0;34m1\u001B[0m  |     | 3\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31mB\u001B[0m  |  \u001B[0;31m3\u001B[0m  |  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m7\u001B[0m  |     | 4\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31m5\u001B[0m  |  \u001B[0;31m4\u001B[0m  |  \u001B[0;31mM\u001B[0m  |  \u001B[0;31m2\u001B[0m  |  \u001B[0;31m1\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game6.Context.attack(board12,0,4,1,4,0).toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34mB\u001B[0m  |  \u001B[0;34m3\u001B[0m  |  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |     |  \u001B[0;34m6\u001B[0m  | 0\n+-----+-----+-----+-----+-----+-----+\n|     |  \u001B[0;34m4\u001B[0m  |  \u001B[0;34mM\u001B[0m  |  \u001B[0;34m2\u001B[0m  |  \u001B[0;34m7\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34m5\u001B[0m  |     |     |     |  \u001B[0;34m1\u001B[0m  |     | 3\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31mB\u001B[0m  |  \u001B[0;31m3\u001B[0m  |  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m7\u001B[0m  |     | 4\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31m5\u001B[0m  |  \u001B[0;31m4\u001B[0m  |  \u001B[0;31mM\u001B[0m  |  \u001B[0;31m2\u001B[0m  |  \u001B[0;31m1\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game6.Context.attack(board12,1,4,0,4,0).toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34mB\u001B[0m  |  \u001B[0;34m3\u001B[0m  |  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m7\u001B[0m  |  \u001B[0;34m6\u001B[0m  | 0\n+-----+-----+-----+-----+-----+-----+\n|     |  \u001B[0;34m4\u001B[0m  |  \u001B[0;34mM\u001B[0m  |  \u001B[0;34m2\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34m5\u001B[0m  |     |     |     |  \u001B[0;34m1\u001B[0m  |     | 3\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31mB\u001B[0m  |  \u001B[0;31m3\u001B[0m  |  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m7\u001B[0m  |     | 4\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31m5\u001B[0m  |  \u001B[0;31m4\u001B[0m  |  \u001B[0;31mM\u001B[0m  |  \u001B[0;31m2\u001B[0m  |  \u001B[0;31m1\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game6.Context.attack(board12,3,4,4,4,0).toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34mB\u001B[0m  |  \u001B[0;34m3\u001B[0m  |  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m7\u001B[0m  |  \u001B[0;34m6\u001B[0m  | 0\n+-----+-----+-----+-----+-----+-----+\n|     |  \u001B[0;34m4\u001B[0m  |  \u001B[0;34mM\u001B[0m  |  \u001B[0;34m2\u001B[0m  |  \u001B[0;31m6\u001B[0m  |  \u001B[0;34mF\u001B[0m  | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34m5\u001B[0m  |     |     |     |     |     | 3\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31mB\u001B[0m  |  \u001B[0;31m3\u001B[0m  |  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m7\u001B[0m  |     | 4\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31m5\u001B[0m  |  \u001B[0;31m4\u001B[0m  |  \u001B[0;31mM\u001B[0m  |  \u001B[0;31m2\u001B[0m  |  \u001B[0;31m1\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
        game6.Context.attack(board13,0,4,0,5,1).toString should be("   0     1     2     3     4     5  \n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34mB\u001B[0m  |  \u001B[0;34m3\u001B[0m  |  \u001B[0;34m9\u001B[0m  |  \u001B[0;34m8\u001B[0m  |  \u001B[0;34m7\u001B[0m  |  \u001B[0;34m6\u001B[0m  | 0\n+-----+-----+-----+-----+-----+-----+\n|     |  \u001B[0;34m4\u001B[0m  |  \u001B[0;34mM\u001B[0m  |  \u001B[0;34m2\u001B[0m  |     |  \u001B[0;34mF\u001B[0m  | 1\n+-----+-----+-----+-----+-----+-----+\n|     |     |     |     |     |     | 2\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;34m5\u001B[0m  |     |     |     |  \u001B[0;34m1\u001B[0m  |     | 3\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31mB\u001B[0m  |  \u001B[0;31m3\u001B[0m  |  \u001B[0;31m9\u001B[0m  |  \u001B[0;31m8\u001B[0m  |  \u001B[0;31m7\u001B[0m  |     | 4\n+-----+-----+-----+-----+-----+-----+\n|  \u001B[0;31m5\u001B[0m  |  \u001B[0;31m4\u001B[0m  |  \u001B[0;31mM\u001B[0m  |  \u001B[0;31m2\u001B[0m  |  \u001B[0;31m1\u001B[0m  |  \u001B[0;31mF\u001B[0m  | 5\n+-----+-----+-----+-----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:   redo\nq:   quit the programm\n")
      }
    }
  }
}
