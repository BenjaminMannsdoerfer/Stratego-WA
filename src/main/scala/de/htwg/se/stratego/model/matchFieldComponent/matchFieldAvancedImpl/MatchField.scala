package de.htwg.se.stratego.model.matchFieldComponent.matchFieldAvancedImpl

import com.google.inject.name.Named
import de.htwg.se.stratego.model.matchFieldComponent.MatchFieldInterface
import javax.inject.Inject
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{MatchField => BaseMatchField}

class MatchField @Inject() (@Named("DefaultSize") sizeR:Int, @Named("DefaultSize") sizeC:Int,
                            @Named("DefaultSet")isSet:Boolean) extends BaseMatchField(sizeR,sizeC,isSet ){
  override def createNewMatchField: MatchFieldInterface = new MatchField(sizeR,sizeC,isSet)

}
