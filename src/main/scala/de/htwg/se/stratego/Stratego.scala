package de.htwg.se.stratego

import com.google.inject.Guice
import de.htwg.se.stratego.aview.Tui
import de.htwg.se.stratego.aview.gui.{PlayerFrame}
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface}

import scala.io.StdIn.readLine

object Stratego {

  val injector = Guice.createInjector(new StrategoModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  val gui = new PlayerFrame(controller)

  def main(args: Array[String]): Unit = {
    var input = ""
    println("Enter the player names in the format \"Player1 Player2\"")
    do {
      input = readLine()
      println(tui.processInputLine(input))
    } while (!input.equals("q"))
  }
}
