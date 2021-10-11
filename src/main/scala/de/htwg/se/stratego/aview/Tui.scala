package de.htwg.se.stratego.aview

import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, MachtfieldInitialized, NewGame, PlayerChanged, PlayerSwitch}

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {
  listenTo(controller)

  def processInputLine(input: String):String = {
    input match {
      case "q" => controller.exit
      case "4" => controller.createNewMatchfieldSize(4)
      case "7" => controller.createNewMatchfieldSize(7)
      case "10" => controller.createNewMatchfieldSize(10)
      case "z" => controller.undo
      case "y" => controller.redo
      case "s" => controller.save
      case "l" => controller.load
      case _ => controller.handle(input)
    }
  }

  reactions +={
    case event: FieldChanged => printTui
    case event: PlayerChanged => println("Hello " + controller.playerListBuffer(0) + " and "+ controller.playerListBuffer(1) + "!")
    case event: MachtfieldInitialized => println("Matchfield initialized")
    case event: NewGame =>
      println("Created new matchfield\nPlease enter the names like (player1 player2)")
      printTui
    case event: GameFinished => println("Game finished! " + controller.playerListBuffer(controller.currentPlayerIndex) + " has won the game!")
    case event: PlayerSwitch => println(controller.playerListBuffer(controller.currentPlayerIndex) + " it's youre turn!")
  }

  def printTui: Unit = {
    println(controller.matchFieldToString)
    println(GameStatus.getMessage(controller.gameStatus))
  }

}
