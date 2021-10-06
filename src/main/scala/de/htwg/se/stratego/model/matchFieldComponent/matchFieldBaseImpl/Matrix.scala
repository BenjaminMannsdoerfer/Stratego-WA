package de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl

case class Matrix[Field] (rows: Vector[Vector[Field]]) {

  def this(rowSize: Int, colSize: Int, field: Field) = this(Vector.tabulate(rowSize, colSize) { (row, col) => field })

  def field(row: Int, col: Int): Field = rows(row)(col)

  def matrixSize: Int = rows.size

  def updateField(row: Int, col: Int, field: Field): Matrix[Field] = copy(rows.updated(row, rows(row).updated(col, field)))

  def isWater(row: Int, col: Int): Boolean = {
    matrixSize match {
      case 4 => false
      case 5 => false
      case 6 => false
      case 7 => if ((row == 2 || row == 3 || row == 4) && (col == 1 || col == 5)) true else false
      case 8 => if ((row == 3 || row == 4) && (col == 1 || col == 2 || col == 5 || col == 6)) true else false
      case 9 => if ((row == 3 || row == 4 || row == 5) && (col == 1 || col == 2 || col == 6 || col == 7)) true else false
      case 10 => if ((row == 4 || row == 5) && (col == 2 || col == 3 || col == 6 || col == 7)) true else false
    }
  }
}
