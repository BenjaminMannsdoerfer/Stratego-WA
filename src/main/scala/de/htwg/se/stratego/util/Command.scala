package de.htwg.se.stratego.util

trait Command {

  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit

}