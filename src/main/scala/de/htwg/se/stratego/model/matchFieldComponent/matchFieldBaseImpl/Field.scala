package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import de.htwg.se.stratego.model.matchFieldComponent.FieldInterface

case class Field(isSet:Boolean, character: Option[GameCharacter] = None, colour: Option[Colour.FigureCol] = None) extends FieldInterface {

  override def toString: String = character.fold(" ")("".+)
}
