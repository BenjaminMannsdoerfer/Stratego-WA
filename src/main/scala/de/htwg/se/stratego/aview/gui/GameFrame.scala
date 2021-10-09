package de.htwg.se.stratego.aview.gui

import java.awt.{Color, Font}

import de.htwg.se.stratego.controller.controllerComponent.GameStatus._

import scala.swing.{Color, _}
import scala.swing.event._
import de.htwg.se.stratego.controller.controllerComponent.{ControllerInterface, FieldChanged, GameFinished, GameStatus, NewGame, PlayerSwitch}
import de.htwg.se.stratego.model.playerComponent.Player
import javax.imageio.ImageIO
import javax.swing.{BorderFactory, JOptionPane, WindowConstants}
import javax.swing.border.LineBorder

class GameFrame(controller:ControllerInterface) extends Frame{

  listenTo(controller)

  val matchFieldSize = controller.getSize
  var optionAttack = false //if set to false -> move, else attack
  var fields = Array.ofDim[FieldPanel](matchFieldSize, matchFieldSize)
  var gameStatus: GameStatus = IDLE
  val colRed = new Color(138,41,37)
  val colBlue = new Color(37,39,138)
  val colGreen = new Color(37,138,73)
  val defaultFont = new Font("Calibri", Font.BOLD, 40)
  val defaultColor = new Color(143,138,126)
  val defaultBorder = new LineBorder(java.awt.Color.WHITE,1)
  val grColor = new Color(79,76,70)
  val iconImg = ImageIO.read(getClass.getResource("iconS.png"))
  val playerName: List[Player] = if(controller.playerListBuffer.isEmpty) controller.playerList else controller.playerListBuffer.toList

  title = "Stratego"
  iconImage = iconImg
  peer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  //peer.setLocationRelativeTo(null)
  resizable= false

  def statusString:String = GameStatus.getMessage(gameStatus)

  def matchfieldPanel = new GridPanel(matchFieldSize,matchFieldSize){
    background = colGreen
    for{
      row <- 0 until matchFieldSize
      col <- 0 until matchFieldSize
    }{
      val fieldPanel = new FieldPanel(row, col, controller)
      fields(row)(col) = fieldPanel
      contents += fieldPanel
      listenTo(fieldPanel)
    }
  }

  val upButton = new Button{
    text = "\u2191"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
  }

  val downButton = new Button{
    text = "\u2193"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
  }

  val rightButton = new Button{
    text = "\u2192"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
  }

  val leftButton = new Button{
    text = "\u2190"
    font = defaultFont
    background = defaultColor
    foreground= Color.WHITE
    border = defaultBorder
  }

  def attackOrMove(direction: String, rowD:Int, colD:Int):Unit = {
    fields.foreach(r => for(c<- r){
      if(c.isClicked) {
        if(optionAttack){
          controller.handle("a"+(c.r).toString+(c.c).toString+(c.r+rowD).toString+(c.c+colD).toString)
          gameStatus=ATTACK
          c.isClicked=false
          repaint
        }else{
          controller.handle("m" + direction + c.r.toString+ c.c.toString)
          c.isClicked= false
          repaint
        }
      }
    })
  }

  def lrPanel = new GridPanel(1,2){
    contents += leftButton
    contents += rightButton
  }

  def directionsPanel = new GridPanel(3,1){
    background = new Color(113,126,138)
    contents += upButton
    contents += lrPanel
    contents += downButton

    listenTo(upButton)
    listenTo(downButton)
    listenTo(leftButton)
    listenTo(rightButton)
    reactions += {
      case ButtonClicked(`upButton`) =>
        attackOrMove("u", -1,0)
      case ButtonClicked(`downButton`) =>
        attackOrMove("d",1,0)
      case ButtonClicked(`leftButton`) =>
        attackOrMove("l",0,-1)
      case ButtonClicked(`rightButton`) =>
        attackOrMove("r", 0,1)
    }
  }

  val moveButton = new RadioButton{
    text = "  \uD83C\uDFC3 MOVE"
    selected = true
    font= font.deriveFont(1, 40)
    foreground = defaultColor
    verticalAlignment=Alignment.Center
  }

  val attackButton = new RadioButton{
    text = "  \uD83D\uDC4A ATTACK"
    font= font.deriveFont(1, 40)
    foreground = defaultColor
    verticalAlignment=Alignment.Center
  }

  val radioButtons = List(moveButton, attackButton)
  val radioPanel = new GridPanel(2,1) {
    contents ++= radioButtons
    listenTo(moveButton)
    reactions += {
      case ButtonClicked(`moveButton`) =>
        attackButton.selected = false
        optionAttack=false
    }
    listenTo(attackButton)
    reactions += {
      case ButtonClicked(`attackButton`) =>
        moveButton.selected = false
        optionAttack= true
    }
    border = BorderFactory.createEmptyBorder(0,60,0,0)
    xLayoutAlignment= 20
  }

  val status = new TextField(controller.statusString, 20)



  val message = new TextPane {
    text = playerName(controller.currentPlayerIndex) + "! It's your turn!"
    editable = false
    foreground= colBlue
    font = defaultFont
  }

  def optionPanel = new BorderPanel{
    add(radioPanel, BorderPanel.Position.Center)
  }

  def statusPanel = new BorderPanel {
    add(status, BorderPanel.Position.Center)
    border = BorderFactory.createEmptyBorder(15,0,0,0)
  }

  def messagePanel = new BorderPanel{
    add(message, BorderPanel.Position.Center)
  }

  def controllPanel = new GridPanel(3,1){
    contents += messagePanel
    contents += directionsPanel
    contents += optionPanel
    border = BorderFactory.createEmptyBorder(0,15,0,0)
    preferredSize = new Dimension(400, 100)
  }

  val mainPanel = new BorderPanel{
    add(matchfieldPanel, BorderPanel.Position.Center)
    add(controllPanel, BorderPanel.Position.East)
    add(statusPanel, BorderPanel.Position.South)
    //border = BorderFactory.createEmptyBorder(20,20,20,20)
  }

  contents = mainPanel

  visible = true
  redraw

  menuBar = new MenuBar {
    foreground = new Color(73,82,89)
    contents += new Menu("File") {
      foreground = new Color(73,82,89)
      mnemonic = Key.F
      contents += new MenuItem(Action("New Game 4x4") {
        controller.createNewMatchfieldSize(4)
      })
      contents += new MenuItem(Action("New Game 7x7") {
        controller.createNewMatchfieldSize(7)
      })
      contents += new MenuItem(Action("New Game 10x10") {
        controller.createNewMatchfieldSize(10)
      })
      contents += new MenuItem(Action("Quit") {
        foreground = new Color(73,82,89)
        System.exit(0)
      })
    }
    contents += new Menu("Edit"){
      foreground = new Color(73,82,89)
        mnemonic = Key.E
        contents+= new MenuItem(Action("Undo") {
          foreground = new Color(73,82,89)
          controller.undo
          redraw
        })
        contents += new MenuItem(Action("Redo") {
          foreground = new Color(73,82,89)
          controller.redo
          redraw
          foreground = new Color(73,82,89)
        })
    }
    contents += new Menu("Game"){
      foreground = new Color(73,82,89)
      mnemonic = Key.E
      contents+= new MenuItem(Action("Save") {
        foreground = new Color(73,82,89)
        controller.save
        redraw
      })
      contents += new MenuItem(Action("Load") {
        foreground = new Color(73,82,89)
        controller.load
        redraw
      })
    }
  }

  def redraw: Unit = {
    for {
      row <- 0 until matchFieldSize
      column <- 0 until matchFieldSize
    } fields(row)(column).redraw
    status.text = controller.statusString

    message.text = playerName(controller.currentPlayerIndex) + "! It's your turn!"
    if(controller.currentPlayerIndex.equals(1)){
      message.foreground= colRed
    }else{
      message.foreground= colBlue
    }

    repaint
  }

  reactions += {
    case event: FieldChanged     => redraw
    case event: GameFinished     =>
      JOptionPane.showMessageDialog(null,
        if(controller.playerListBuffer.isEmpty)
          controller.playerList(controller.currentPlayerIndex)
        else
          controller.playerListBuffer(controller.currentPlayerIndex)
            + " you have won the game!")
      visible = false
      deafTo(controller)
      close()
      new PlayerFrame(controller)
    case event: NewGame          =>
      deafTo(controller)
      close()
      new PlayerFrame(controller)
    case event: PlayerSwitch =>
      redraw

  }

  pack()

}
