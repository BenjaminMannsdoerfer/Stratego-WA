package de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.stratego.StrategoModule
import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{Colour, Figure, GameCharacter}
import de.htwg.se.stratego.model.playerComponent.Player
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import play.api.libs.json.JsValue.jsValueToJsLookup
import play.api.libs.json.{JsArray, JsNumber, JsObject, JsValue, Json}

import javax.swing.JOptionPane
import scala.io.Source
import scala.util.control.Breaks.break
import scala.util.{Failure, Success, Try}



class FileIO extends FileIOInterface{

  def fileNotFound(filename: String): Try[String] = {
    Try(Source.fromFile(filename).getLines().mkString)
  }

  override def load: (MatchFieldInterface,Int,String) = {
    fileNotFound("matchField.json") match {
      case Success(v) => println("File Found")
      case Failure(v) => JOptionPane.showMessageDialog(null, "Es ist kein Spielstand vorhanden!")
        break
    }
    val source:String = Source.fromFile("matchField.json").getLines().mkString
    val json: JsValue = Json.parse(source)
    val sizeOfMatchfield: Int = (json \ "matchField").as[JsArray].value.size
    val injector = Guice.createInjector(new StrategoModule)
    var matchField = injector.getInstance(classOf[MatchFieldInterface])
    sizeOfMatchfield match {
      case 16 => matchField = injector.instance[MatchFieldInterface](Names.named("tiny"))
      case 49 => matchField = injector.instance[MatchFieldInterface](Names.named("small"))
      case 100 => matchField = injector.instance[MatchFieldInterface](Names.named("normal"))
      case _ =>
    }
    val currentPlayerIndex = (json \ "currentPlayerIndex").get.toString().toInt
    val playerS = (json \ "players").get.toString()
    for(index <- 0 until sizeOfMatchfield){
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      if(((json \ "matchField")(index) \\ "water").nonEmpty) {
        matchField = matchField.addWater(row,col)
      }
      if(((json \ "matchField")(index) \\ "figName").nonEmpty) {
        val figName = ((json \ "matchField")(index) \ "figName").as[String]
        val figValue = ((json \ "matchField")(index) \ "figValue").as[Int]
        val colour = ((json \ "matchField")(index) \ "colour").as[Int]
        matchField = matchField.addChar(row, col, GameCharacter(Figure.FigureVal(figName, figValue)), Colour.FigureCol(colour))
      }
    }
    (matchField,currentPlayerIndex, playerS)
  }


  def matchFieldToJson(matchField: MatchFieldInterface, currentPlayerIndex: Int, players: String): JsObject = {
    Json.obj(
      "currentPlayerIndex" -> JsNumber(currentPlayerIndex),
      "players" -> players,
      "matchField"-> Json.toJson(
          for{
            row <- 0 until matchField.fields.matrixSize
            col <- 0 until matchField.fields.matrixSize
          } yield {
              var obj = Json.obj(
                "row" -> row,
                "col" -> col
              )
            if (matchField.fields.isWater(row,col)) {
              obj = obj.++(Json.obj(
                "water" -> "~"))
            }
              if(matchField.fields.field(row,col).isSet) {
                  obj = obj.++(Json.obj(
                  "figName" -> matchField.fields.field(row, col).character.get.figure.name,
                  "figValue" -> matchField.fields.field(row, col).character.get.figure.value,
                  "colour" -> matchField.fields.field(row, col).colour.get.value
                  )
                  )
                }

            obj
          }
        )
    )
  }

  override def save(matchField: MatchFieldInterface, currentPlayerIndex: Int, players: List[Player]): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("matchField.json"))
    val playerS = players(0) + " " + players(1)
    pw.write(Json.prettyPrint(matchFieldToJson(matchField, currentPlayerIndex, playerS)))
    pw.close()
  }
}






