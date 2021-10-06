package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import com.google.inject.{Inject}
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface

case class MatchField @Inject() (fields: Matrix[Field]) extends MatchFieldInterface {

  def this (rowSize: Int, colSize: Int, isSet: Boolean) = this(new Matrix[Field](rowSize, colSize, Field(isSet)))

  def size: Int = fields.matrixSize

  def addChar(row: Int, col: Int, char: GameCharacter, colour: Colour.FigureCol): MatchField = copy(fields.updateField(row, col, Field(true, Some(char), Some(colour))))

  def removeChar(row: Int, col: Int): MatchField = copy(fields.updateField(row, col, Field(false,None,None)))

  def fieldIsSet (row: Int, col: Int): Boolean = fields.field(row, col).isSet

  def fieldColor(row: Int, col: Int): Int = fields.field(row, col).colour.get.value

  def figureVal(row: Int, col: Int): Int = fields.field(row, col).character.get.figure.value

  def legend():String = {
    val welcome = "**********  STRATEGO  **********\n\n"
    val n = "n:   create a new empty machtfield\n"
    val i = "n:   set all figures automatically\n"
    val s = "s:   set one figure with the format \"s row col figure\"\n"
    val z = "z:   undo\n"
    val y = "y:   redo\n"
    val q = "q:   quit the programm\n"
    welcome + n + i + s + z + y + q
  }

  def frame(row:Int): String = {
    val plus = "+"
    val line = "-"
    val combine = (plus + line * 5) * row + plus
    combine
  }

  def createNewMatchField: MatchFieldInterface = new MatchField(fields.matrixSize,fields.matrixSize,false)

  override def toString:String = {
    val pipe = "|"
    val new_line = "\n"
    var matchField = ""

    for(rowNumbers <- 0 until size) matchField += "   " + rowNumbers + "  "
    matchField += new_line + frame(size) + new_line
    for { row <- 0 until size
          col <- 0 until size }
    {
      if (fields.field(row, col).isSet) {
        if (fields.field(row,col).colour.get.value==0) {
          matchField += "|  " + "\033[0;34m" + fields.field(row,col) + Console.RESET + "  "
        } else {
          matchField += "|  " + "\033[0;31m" + fields.field(row,col) + Console.RESET + "  "
        }
      } else if (fields.isWater(row, col)){
        matchField += "|  " + "~" + "  "
      } else {
        matchField += "|     "
      }
      if (col == size - 1) {
        matchField += pipe + " " + row + new_line
        matchField += frame(size) + new_line
      }
    }
    matchField += legend()
    matchField
  }

}
