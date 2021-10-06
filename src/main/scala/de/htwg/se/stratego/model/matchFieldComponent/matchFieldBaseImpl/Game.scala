package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.playerComponent.Player
import scala.collection.mutable.ListBuffer

case class Game(playerA: Player, playerB: Player, size: Int, matchField: MatchFieldInterface) {
  val bList: ListBuffer[GameCharacter] = playerA.characterList
  val rList: ListBuffer[GameCharacter] = playerB.characterList

  def init(currentMatchField: MatchFieldInterface, row: Int, col: Int, idx: Int): Game = {
    if (row < size) {
      if (matchField.fields.field(row, col).isSet) return copy(matchField = currentMatchField)
      if (isRedOrBlueField(row, Colour.BLUE.value)) return initColor(bList, Colour.FigureCol(0))(currentMatchField, row, col, idx)
      else if (isRedOrBlueField(row, Colour.RED.value)) return initColor(rList, Colour.FigureCol(1))(currentMatchField, row, col, idx)
      else return init(currentMatchField, row + 1, col, 0)
    }
    copy(matchField = currentMatchField)
  }

  def initColor(list: ListBuffer[GameCharacter], color: Colour.FigureCol)(currentField: MatchFieldInterface, row: Int, col: Int, idx: Int): Game = {
    var field: MatchFieldInterface = currentField
      field = field.addChar(row, col, list(idx), color)
      if (col < size - 1) init(field, row, col + 1, idx + 1)
      else init(field, row + 1, 0, idx+1)
  }

  def characValue(charac: String): Int = {
    if (charac.matches("[1-9]")) return charac.toInt
    charac match {
      case "B" => 11
      case "M" => 10
      case "F" => 0
    }
  }

  def isRedOrBlueField(row: Int, blueOrRed: Int): Boolean = {
    if (blueOrRed == 0) {
      matchField.fields.matrixSize match {
        case 4 | 5 => if (row > 0) false else true
        case 6 | 7 => if (row > 1) false else true
        case 8 | 9 => if (row > 2) false else true
        case 10 => if (row > 3) false else true
      }
    } else {
      matchField.fields.matrixSize match {
        case 4 => if (row < 3) false else true
        case 5 | 6 => if (row < 4) false else true
        case 7 | 8 => if (row < 5) false else true
        case 9 | 10 => if (row < 6) false else true
      }
    }
  }


  def set(player: Int, row: Int, col: Int, charac: String): Game = {
    player match {
      case 0 => return setCharacter(bList, Colour.FigureCol(0))(row, col, charac)
      case 1 => return setCharacter(rList, Colour.FigureCol(1))(row, col, charac)
    }
    copy(matchField = matchField)
  }

  def setCharacter(list: ListBuffer[GameCharacter], Color: Colour.FigureCol)(row: Int, col: Int, charac: String): Game = {
    var field: MatchFieldInterface = matchField
    if (isColorChar(list)(charac) && isRedOrBlueField(row, Color.value) && !field.fields.field(row, col).isSet) {
      val idx = list.indexOf(GameCharacter(Figure.FigureVal(charac, characValue(charac))))
      field = matchField.addChar(row, col, list(idx), Color)
      list.remove(idx)
      return copy(matchField = field)
    }
    copy(matchField = field)
  }

  def isColorChar(listBuffer: ListBuffer[GameCharacter])(charac: String): Boolean = {
    listBuffer.map(GameCharacter => if (GameCharacter.figure.name.equals(charac)) return true else false)
    false
  }

  def onlyBombAndFlag(board: MatchFieldInterface, currentPlayerIndex: Int, row:Int, col:Int): Boolean = {
    if(row < size) {
        if (board.fields.field(row, col).isSet && board.fields.field(row, col).colour.get.value == currentPlayerIndex) {
          if (board.fields.field(row, col).character.get.figure.value == 1 ||
            board.fields.field(row, col).character.get.figure.value == 2 ||
            board.fields.field(row, col).character.get.figure.value == 3 ||
            board.fields.field(row, col).character.get.figure.value == 4 ||
            board.fields.field(row, col).character.get.figure.value == 5 ||
            board.fields.field(row, col).character.get.figure.value == 6 ||
            board.fields.field(row, col).character.get.figure.value == 7 ||
            board.fields.field(row, col).character.get.figure.value == 8 ||
            board.fields.field(row, col).character.get.figure.value == 9 ||
            board.fields.field(row, col).character.get.figure.value == 10) {
              return false
          }
      }
      if (col < size-1) return onlyBombAndFlag(board, currentPlayerIndex, row, col + 1)
      else return onlyBombAndFlag(board, currentPlayerIndex, row + 1, 0)
    }
    true
  }

  def fieldIsInMatchfield(rowD:Int, colD:Int): Boolean = rowD <= this.matchField.size - 1 && rowD >= 0 && colD >= 0 && colD <= this.matchField.size - 1

  def flagFound(rowD: Int, colD: Int, rowA: Int, colA: Int, currentPlayerIndex: Int): Boolean = {
    if (fieldIsInMatchfield(rowD, colD) && this.matchField.fieldIsSet(rowA, colA).equals(true) && this.matchField.fieldIsSet(rowD, colD).equals(true)
      && this.matchField.fieldColor(rowD, colD) != currentPlayerIndex &&
      this.matchField.figureVal(rowD, colD) == 0) true else false
  }

  def attackValid(rowD: Int, colD: Int, rowA: Int, colA: Int, currentPlayerIndex: Int): Boolean = {
    if (fieldIsInMatchfield(rowD, colD) && !matchField.fields.isWater(rowD, colD) && !matchField.fields.isWater(rowA, colA) &&
      this.matchField.fieldIsSet(rowA, colA) && this.matchField.fieldColor(rowA, colA) == currentPlayerIndex
      && this.matchField.fieldIsSet(rowD, colD) && this.matchField.fieldColor(rowD, colD) != currentPlayerIndex
      && !isFlagOrBomb(matchField, rowA, colA))
      true else false
  }

  def move(direction: Char, matchField: MatchFieldInterface, row: Int, col: Int, currentPlayerIndex: Int): MatchFieldInterface = {
    if (matchField.fieldIsSet(row, col).equals(true) && matchField.fieldColor(row, col) == currentPlayerIndex) {
      direction match {
        case 'u' => return moveUp(matchField, row, col) // moveUp
        case 'd' => return moveDown(matchField, row, col) // moveDown
        case 'r' => return moveRight(matchField, row, col) // moveRight
        case 'l' => return moveLeft(matchField, row, col) // moveLeft
      }
    }
    matchField
  }

  def moveDirection(rowCol:Int, eqRowCol:Int, rowPos:Int, colPos:Int, newRowPos: Int, newColPos:Int)(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = {
    if (matchField.fields.isWater(rowPos, colPos).equals(true) || rowCol == eqRowCol || matchField.fields.field(rowPos, colPos).isSet.equals(true) || isFlagOrBomb(matchField, row, col)) matchField
    else matchField.removeChar(row, col).addChar(newRowPos, newColPos, matchField.fields.field(row, col).character.get, matchField.fields.field(row, col).colour.get)
  }

  def moveDown(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = moveDirection(row, size-1, row+1, col, row+1, col)(matchField, row, col)

  def moveUp(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = moveDirection(row, 0, row-1, col, row-1, col)(matchField, row, col)

  def moveRight(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = moveDirection(col, size-1, row, col+1, row, col+1)(matchField, row,col)

  def moveLeft(matchField: MatchFieldInterface, row: Int, col: Int): MatchFieldInterface = moveDirection(col, 0, row, col-1, row, col-1)(matchField, row, col)

  def figureHasValue(matchF: MatchFieldInterface, row: Int, col: Int): Int = matchF.fields.field(row, col).character.get.figure.value

  def isFlagOrBomb(matchField: MatchFieldInterface, row: Int, col: Int): Boolean = if (matchField.fields.field(row, col).character.get.figure.value == 0 ||
    matchField.fields.field(row, col).character.get.figure.value == 11) true else false

  object Context extends Game(playerA: Player, playerB: Player, size: Int, matchField: MatchFieldInterface) {
    def attack(matchField: MatchFieldInterface, rowA: Int, colA: Int, rowD: Int, colD: Int, currentPlayerIndex: Int): MatchFieldInterface = {
      def strategy1: MatchFieldInterface = matchField
      def strategy3: MatchFieldInterface = matchField.removeChar(rowD, colD).addChar(rowD, colD,
        matchField.fields.field(rowA, colA).character.get, matchField.fields.field(rowA, colA).colour.get).removeChar(rowA, colA)
      def strategy6: MatchFieldInterface = matchField.removeChar(rowD, colD)
      def strategy7: MatchFieldInterface = matchField.removeChar(rowA, colA)
      def strategy8: MatchFieldInterface = matchField.removeChar(rowA, colA).removeChar(rowD, colD)
      val fieldIsNotSet = matchField.fields.field(rowA, colA).isSet.equals(false) || matchField.fields.field(rowD, colD).isSet.equals(false)
      val attackOwnFigures = matchField.fields.field(rowD, colD).colour.get.value == currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value == currentPlayerIndex
      val enemyAttack = matchField.fields.field(rowD, colD).colour.get.value != currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value != currentPlayerIndex
      val wrongPlayerAttack = matchField.fields.field(rowD, colD).colour.get.value == currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value != currentPlayerIndex
      val attackToFarAway = ((Math.abs(rowA - rowD) > 1) || (Math.abs(colA - colD) > 1)) || ((Math.abs(rowA - rowD) == 1) && (Math.abs(colA - colD) == 1))
      val minerAttackBomb = figureHasValue(matchField, rowA, colA) == 3 && figureHasValue(matchField, rowD, colD) == 11
      val spyAttackMarshal = (figureHasValue(matchField, rowA, colA) == 1) && (figureHasValue(matchField, rowD, colD) == 10)
      val defenseIsStronger = figureHasValue(matchField, rowA, colA) < figureHasValue(matchField, rowD, colD)
      val attackIsStronger = figureHasValue(matchField, rowA, colA) > figureHasValue(matchField, rowD, colD)
      val attackEqualsDefense = figureHasValue(matchField, rowA, colA) == figureHasValue(matchField, rowD, colD)
      true match {
        case true if fieldIsNotSet | attackOwnFigures | enemyAttack | wrongPlayerAttack | attackToFarAway => return strategy1
        case true if minerAttackBomb => return strategy6
        case true if spyAttackMarshal | attackIsStronger => return strategy3
        case true if defenseIsStronger => return strategy7
        case true if attackEqualsDefense => return strategy8
      }
      matchField

      // TODO match statt if
      // is field set
      /*if (matchField.fields.field(rowA, colA).isSet.equals(false) || matchField.fields.field(rowD, colD).isSet.equals(false)) return strategy1
      // is attack valid
      if (matchField.fields.field(rowD, colD).colour.get.value == currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value == currentPlayerIndex) return strategy1
      // is enemy attack invalid
      if (matchField.fields.field(rowD, colD).colour.get.value != currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value != currentPlayerIndex) return strategy1
      // is player attack wrong
      if (matchField.fields.field(rowD, colD).colour.get.value == currentPlayerIndex &&
        matchField.fields.field(rowA, colA).colour.get.value != currentPlayerIndex) return strategy1
      // is attack far away
      if (((Math.abs(rowA - rowD) > 1) || (Math.abs(colA - colD) > 1)) || ((Math.abs(rowA - rowD) == 1) && (Math.abs(colA - colD) == 1))) return strategy1
      // does the miner attack bomb
      if (figureHasValue(matchField, rowA, colA) == 3 && figureHasValue(matchField, rowD, colD) == 11) return strategy6
      // does the spy attack marshal
      if ((figureHasValue(matchField, rowA, colA) == 1) && (figureHasValue(matchField, rowD, colD) == 10)) return strategy3
      // is defense stronger
      if (figureHasValue(matchField, rowA, colA) < figureHasValue(matchField, rowD, colD)) return strategy7
      // is attack stronger
      if (figureHasValue(matchField, rowA, colA) > figureHasValue(matchField, rowD, colD)) return strategy3
      // does attack equal defense
      if (figureHasValue(matchField, rowA, colA) == figureHasValue(matchField, rowD, colD)) return strategy8
      matchField*/
    }
  }

}
