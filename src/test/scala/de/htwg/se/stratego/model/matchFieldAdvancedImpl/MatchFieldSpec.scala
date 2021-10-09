package de.htwg.se.stratego.model.matchFieldAdvancedImpl
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.MatchField
import org.scalatest.{Matchers, WordSpec}

class MatchFieldSpec extends WordSpec with Matchers{
  "A MatchField" should {
    val matchField = new MatchField(4, 4, false)
    "have beend created new" in {
      matchField.createNewMatchfieldSize(4).toString should be("   0     1     2     3  \n+-----+-----+-----+-----+\n|" +
        "     |     |     |     | 0\n+-----+-----+-----+-----+\n|     |     |     |     | 1\n+-----+-----+-----" +
        "+-----+\n|     |     |     |     | 2\n+-----+-----+-----+-----+\n|     |     |     |     | 3\n+-----+-" +
        "----+-----+-----+\n**********  STRATEGO  **********\n\nn:   create a new empty machtfield\nz:   undo\ny:" +
        "   redo\nq:   quit the programm\n")
    }
  }
}
