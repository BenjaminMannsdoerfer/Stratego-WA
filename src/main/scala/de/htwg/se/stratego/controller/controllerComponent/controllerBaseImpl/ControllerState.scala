package de.htwg.se.stratego.controller.controllerComponent.controllerBaseImpl

abstract class ControllerState {

  def handle(input: String): String
  def nextState(): ControllerState

}