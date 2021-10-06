import de.htwg.se.stratego.model.matchFieldComponent.matchFieldAvancedImpl.MatchField
import de.htwg.se.stratego.model.fileIoComponent.fileIoXmlImpl.FileIO
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Colour, Figure, GameCharacter}


object ioWorksheet{
  1 + 2
  val size = 4

  def toXml = {
    <matchField size={size.toString}>
    </matchField>
  }

  println(toXml)


  /*
  val matchField = new MatchField(4,4,false)
  val filledMatchField = matchField.addChar(0,0, new GameCharacter(Figure.Flag), Colour.BLUE)
  println(filledMatchField.toString)

  val fileIO = new FileIO(filledMatchField)
  val xml = fileIO.matchFieldToXml

   */

}