package de.htwg.se.stratego

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.stratego.controller.controllerComponent.ControllerInterface
import de.htwg.se.stratego.model.fileIoComponent.FileIOInterface
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldAvancedImpl.MatchField
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.stratego.model.fileIoComponent.fileIoJsonImpl.FileIO

class StrategoModule extends AbstractModule with ScalaModule {

  val defaultSize:Int = 4
  val defaultSet:Boolean = false

  override def configure():Unit = {

    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bindConstant().annotatedWith(Names.named("DefaultSet")).to(defaultSet)
    bind[MatchFieldInterface].to[MatchField]
    bind[ControllerInterface].to[controller.controllerComponent.controllerBaseImpl.Controller]

    bind[MatchFieldInterface].annotatedWithName("tiny").toInstance(new MatchField(defaultSize, defaultSize, defaultSet))
    bind[MatchFieldInterface].annotatedWithName("small").toInstance(new MatchField(6, 6, defaultSet))
    bind[MatchFieldInterface].annotatedWithName("normal").toInstance(new MatchField(10, 10, defaultSet))

    bind[FileIOInterface].to[FileIO]

  }

}
