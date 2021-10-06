package de.htwg.se.stratego.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import de.htwg.se.stratego.StrategoModule
import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Colour, Figure, Game, GameCharacter}
import de.htwg.se.stratego.model.playerComponent.Player

import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface{
  override def load: (MatchFieldInterface,Int,String) = {
    val file = scala.xml.XML.loadFile("matchField.xml")
    val currentPlayerIndex = (file \\ "matchField" \ "@currentPlayerIndex").text.toInt
    val playerS = (file \\ "matchField" \ "@players").text
    val injector = Guice.createInjector(new StrategoModule)
    var matchField = injector.getInstance(classOf[MatchFieldInterface])

    val fieldNodes = (file \\ "field")
    for(field <- fieldNodes){
      val row: Int = (field \ "@row").text.toInt
      val col: Int = (field\ "@col").text.toInt
      val figName: String = (field\ "@figName").text
      val figValue: Int = (field\ "@figValue").text.toInt
      val colour:Int = (field\ "@colour").text.toInt
      matchField = matchField.addChar(row, col, GameCharacter(Figure.FigureVal(figName,figValue)),
        Colour.FigureCol(colour))
    }
    (matchField, currentPlayerIndex,playerS)
  }

  def cellToXml(matchField: MatchFieldInterface, row: Int, col: Int) = {
    if(matchField.fields.field(row,col).isSet){
      <field row={row.toString} col={col.toString} figName={matchField.fields.field(row,col).character.get.figure.name}
             figValue={matchField.fields.field(row,col).character.get.figure.value.toString}
             colour={matchField.fields.field(row,col).colour.get.value.toString}>
      </field>
    }
  }

  def matchFieldToXml(matchField: MatchFieldInterface, currentPlayerIndex: Int, playerS: String) ={
    <matchField  currentPlayerIndex={ currentPlayerIndex.toString} players={playerS}>
      {
      for{
        row <- 0 until matchField.fields.matrixSize
        col <- 0 until matchField.fields.matrixSize
      } yield cellToXml(matchField, row, col)
      }
    </matchField>

  }

  def saveString(matchField: MatchFieldInterface, currentPlayerIndex:Int, players: List[Player]): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("matchField.xml"))
    val prettyPrinter = new PrettyPrinter(120,4)
    val playerS = players(0) + " " + players(1)
    val xml = prettyPrinter.format(matchFieldToXml(matchField, currentPlayerIndex, playerS))
    pw.write(xml)
    pw.close
  }

  override def save(matchField: MatchFieldInterface, currentPlayerIndex: Int, players: List[Player]): Unit = saveString(matchField,currentPlayerIndex,players)
}
