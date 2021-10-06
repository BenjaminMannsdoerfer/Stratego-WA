package de.htwg.se.stratego.model
import de.htwg.se.stratego.model.matchFieldComponent.matchFieldBaseImpl.{CharacterList, Figure, GameCharacter}
import org.scalatest.{Matchers, WordSpec}

class CharacterListSpec extends WordSpec with Matchers {
  "A CharacterList" when { "new with size 4" should {
    val characterlist = CharacterList(4)
    "have a list" in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Flag)))
    }
    "can get the CharacterList" in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be(4)
    }

  }
  "new with size 5" should {
    val characterlist = CharacterList(5)
    "have a list"  in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (5)
    }


  }
  "new with size 6" should {
    val characterlist = CharacterList(6)
    "have a list"  in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (6)
    }
  }
  "new with size 7" should {
    val characterlist = CharacterList(7)
    "have a list"  in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (7)
    }

  }
  "new with size 8" should {
    val characterlist = CharacterList(8)
    "have a list"  in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (8)
    }

  }
  "new with size 9" should {
    val characterlist = CharacterList(9)
    "have a list"  in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be (Seq(GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (9)
    }

  }
  "new with size 10" should {
    val characterlist = CharacterList(10)
    "have a list"  in {
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.Scout),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "can get the CharacterList" in{
      characterlist.getCharacterList() should be(Seq(GameCharacter(Figure.Scout),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.General),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Major),
        GameCharacter(Figure.Lieutenant),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Marshal),
        GameCharacter(Figure.Sergeant),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Bomb),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Miner),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Captain),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Colonel),
        GameCharacter(Figure.Scout),
        GameCharacter(Figure.Spy),
        GameCharacter(Figure.Flag)))
    }
    "has a size" in {
      characterlist.size should be (10)
    }

  }

}}
