package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.stratego.StrategoModule
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, MachtfieldInitialized, NewGame, PlayerChanged, PlayerSwitch}
import de.htwg.se.stratego.controller.controllerComponent.GameStatus._
import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Field, Game, MatchField, Matrix}
import de.htwg.se.stratego.model.playerComponent.Player
import de.htwg.se.stratego.util.UndoManager
import net.codingwell.scalaguice.InjectorExtensions._
import com.google.inject.name.Names

import scala.collection.mutable.ListBuffer
import scala.swing.{Publisher, reflectiveCalls}


class Controller @Inject()(var matchField:MatchFieldInterface) extends ControllerInterface with Publisher {

  val injector = Guice.createInjector(new StrategoModule)
  val fileIO = injector.getInstance(classOf[FileIOInterface])

  val list = CharacterList(matchField.fields.matrixSize)
  val playerBlue = Player("PlayerBlue", list.getCharacterList())
  val playerRed = Player("PlayerRed", list.getCharacterList())
  var game = Game(playerBlue, playerRed, matchField.fields.matrixSize, matchField)
  val playerList = List[Player](playerBlue, playerRed)
  val playerListBuffer: ListBuffer[Player] = ListBuffer.empty

  var gameStatus: GameStatus = IDLE
  var currentPlayerIndex: Int = 0
  private val undoManager = new UndoManager
  var state: ControllerState = EnterPlayer(this)

  def handle(input: String):String = {
    state.handle(input)
  }

  override def createNewMatchfieldSize(size:Int): String = {
    matchField.size match {
      case 4 => matchField = injector.instance[MatchFieldInterface](Names.named("tiny"))
      case 7 => matchField = injector.instance[MatchFieldInterface](Names.named("small"))
      case 10 => matchField = injector.instance[MatchFieldInterface](Names.named("normal"))
      case _ =>
    }
    matchField = matchField.createNewMatchfieldSize(size)
    game = game.copy(Player("PlayerBlue", list.copy(size).getCharacterList()), Player("PlayerRed", list.copy(size).getCharacterList()), size, new MatchField(size, size, false))
    gameStatus=NEW
    state = EnterPlayer(this)
    publish(new NewGame)
    currentPlayerIndex=0
    "created new matchfield\nPlease enter the names like (player1 player2)"
  }

  def welcome():String = {
    "Welcome to STRATEGO!\n" +
      "Please enter first name of Player1 and then of Player2 like (player1 player2)!\n"
  }

  def setPlayers(input: String): String = {
    // allowed size of playerlistbuffer is 2, otherwise clear it
    for (i <- setPlayer(input)) {
      if (playerListBuffer.size != 2)
        playerListBuffer.append(i)
      else {
        playerListBuffer.clear()
        playerListBuffer.append(i)
      }
    }
    nextState
    publish(new PlayerChanged)
    ""
  }

  def setPlayer(input: String): List[Player] = {
    input.split(" ").map(_.trim).toList match{
      case player1 :: player2 :: Nil =>
        val Game = game.copy(playerA = Player(player1, game.bList), playerB = Player(player2, game.rList), size = game.size, matchField = matchField)
        val playerList = List[Player](Game.playerA, Game.playerB)
        playerList
    }
  }

  def initMatchfield(): String = {
    var newMatchField = matchField
    newMatchField = game.init(matchField, 0, 0, 0).matchField
    if (matchField.equals(newMatchField)) {
      ""
    } else {
      game = game.copy(matchField = game.init(matchField, 0, 0, 0).matchField)
      gameStatus=INIT
      nextState
      publish(new MachtfieldInitialized)
      playerListBuffer(currentPlayerIndex) + " it's your turn!"
    }
  }

  def attack(rowA: Int, colA: Int, rowD:Int, colD:Int): String ={
    if(game.onlyBombAndFlag(game.matchField,currentPlayerIndex,0,0) && game.matchField.fieldIsSet(rowA,colA) &&
      game.matchField.fieldColor(rowA,colA) == currentPlayerIndex) {
      currentPlayerIndex = nextPlayer
      publish(new GameFinished)
      currentPlayerIndex=1
      nextState
      createNewMatchfieldSize(game.matchField.fields.matrixSize)
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    if(game.flagFound(rowA, colA, rowD, colD, currentPlayerIndex)) {
      publish(new GameFinished)
      currentPlayerIndex=1
      nextState
      createNewMatchfieldSize(matchField.fields.matrixSize)
      return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
        "Game finished! Play new Game with (n)!"
    }
    if (game.attackValid(rowD, colD, rowA, colA, currentPlayerIndex)) {
      game = game.copy(matchField = game.Context.attack(game.matchField, rowA, colA, rowD, colD,currentPlayerIndex))
      gameStatus = ATTACK
      currentPlayerIndex= nextPlayer
      publish(new PlayerSwitch)
      publish(new FieldChanged)
    }
    ""
  }

  def set(row:Int, col:Int, charac:String): String = {
    currentPlayerIndex match {
      case 0 =>
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
        if(game.bList.size == 0){
          currentPlayerIndex=nextPlayer
          publish(new PlayerSwitch)
        }
      case 1 =>
        undoManager.doStep(new SetCommand(currentPlayerIndex, row, col, charac, this))
        if(game.rList.size == 0){
          currentPlayerIndex=nextPlayer
          nextState
          publish(new MachtfieldInitialized)
        }
    }
    publish(new FieldChanged)
    if(game.rList.size == 0){
        return "Move Figures with (m direction[u,d,r,l] row col) or attack with (a row col row col)\n" +
        playerList(currentPlayerIndex) + " it's your turn!"
    }
    if(game.bList.size == 0){
      return ""
    }
    ""
  }

  def move(dir: Char, row:Int, col:Int): String = {
    if (game.matchField.fieldIsSet(row,col) && game.matchField.fieldColor(row,col) == currentPlayerIndex) {
      if(game.onlyBombAndFlag(game.matchField,currentPlayerIndex,0,0)) {
        currentPlayerIndex = nextPlayer
        publish(new GameFinished)
        currentPlayerIndex=1
        nextState
        createNewMatchfieldSize(game.matchField.size)
        return "Congratulations " + playerList(currentPlayerIndex) +"! You're the winner!\n" +
          "Game finished! Play new Game with (n)!"
      }
      undoManager.doStep(new MoveCommand(dir, game.matchField, row, col, currentPlayerIndex, this))
      if (!game.matchField.fieldIsSet(row,col)) {
        currentPlayerIndex = nextPlayer
        publish(new FieldChanged)
        publish(new PlayerSwitch)
      }
    }
    ""
  }

  def matchFieldToString: String = game.matchField.toString

  def undo: String = {
    currentPlayerIndex= nextPlayer
    undoManager.undoStep
    gameStatus = UNDO
    publish(new FieldChanged)
    publish(new PlayerSwitch)
    "undo"
  }

  def redo: String = {
    currentPlayerIndex=nextPlayer
    undoManager.redoStep
    gameStatus = REDO
    publish(new FieldChanged)
    publish(new PlayerSwitch)
    "redo"
  }

  def nextState: Unit = {
    state = state.nextState()
    publish(new FieldChanged)
  }

  def statusString:String = GameStatus.getMessage(gameStatus)

  def nextPlayer: Int = if (currentPlayerIndex == 0) 1 else 0

  override def getSize: Int = game.matchField.fields.matrixSize

  override def getField: Matrix[Field] = game.matchField.fields

  override def exit: String = {
    System.exit(0)
    "Bye bye!"
  }

  override def load: String = {
    val (newmatchField, newPlayerIndex, newPlayers) = fileIO.load
    game = game.copy(matchField = newmatchField)
    currentPlayerIndex = newPlayerIndex
    for (i <- setPlayer(newPlayers))
      playerListBuffer.append(i)
    state = GameState(this)
    publish(new FieldChanged)
    "load"
  }

  override def save: String = {
    val players = if (playerListBuffer.isEmpty) playerList else playerListBuffer.toList
    fileIO.save(game.matchField, currentPlayerIndex, players)
    publish(new FieldChanged)
    "save"
  }
}
