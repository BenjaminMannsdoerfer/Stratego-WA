package de.htwg.se.stratego.model.playerComponent

import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.GameCharacter

import scala.collection.mutable.ListBuffer

case class Player(name: String, characterList: ListBuffer[GameCharacter]) {
  override def toString: String = name
}
