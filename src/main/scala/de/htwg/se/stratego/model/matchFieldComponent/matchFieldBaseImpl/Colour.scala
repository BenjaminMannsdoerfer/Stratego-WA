package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

object Colour extends Enumeration {
  case class FigureCol(value:Int) {
    override def toString: String = value.toString
  }

  val BLUE = FigureCol(0)
  val RED = FigureCol(1)
}
