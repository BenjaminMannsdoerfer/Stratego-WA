package de.htwg.se.stratego.model

import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Field, Matrix}
import org.scalatest.{Matchers, WordSpec}

class MatrixSpec extends WordSpec with Matchers {
  "A Matrix" when {
    "created with rowSize and colSize" should {
    val field = new Field(true)
    val matrix = new Matrix(4,4,field)
    val fieldN = new Field(false)
    "has a size"  in {
      matrix.matrixSize should be(4)
    }
    "can give us a field"  in {
      matrix.field(0,0) should be(field)
    }
    "can update a field" in {
      matrix.updateField(0,0,fieldN) should be (matrix.updateField(0,0,fieldN))
    }
  }}
}
