

import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Game, MatchField}
import de.htwg.se.stratego.model.playerComponent.Player

import scala.swing.{Button}


val bu = new Button{
  font.deriveFont(2,21)
}


def up(a: Int, b: Int): Unit ={
  println("up")
  println(a)
  println(b)
}
def down(a: Int, b: Int): Unit ={
  println("down")
  println(a)
  println(b)
}

val input = "hallo hei"
input.split(" ").map(_.trim).toList match{
  case name1 :: name2 :: Nil => println(name1 + name2)
  case _ => println("ne")
}


val matchField = new MatchField(4, 4, false)
val characList = CharacterList(4)
val playerBlue = Player("PlayerBlue", characList.getCharacterList())
val playerRed = Player("PlayerRed", characList.getCharacterList())
val game = Game(playerBlue, playerRed, 4, matchField)



