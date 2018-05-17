package ex3.sol

import java.io.File

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory

/**
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
object Main2 extends App{

  //Declaring the Actor System with the given configuration
  val config = ConfigFactory.parseFile(new File("src/main/scala/ex3/app2.conf"))
  val actorSystem = ActorSystem("helloworld", config)
  //Retrieve the actors of the existing Actor System
  val selection1 = actorSystem.actorSelection("akka.tcp://helloworld@127.0.0.1:2552/user/actor1")
  val selection2 = actorSystem.actorSelection("akka.tcp://helloworld@127.0.0.1:2552/user/actor2")
  //Send them an HelloMessage passing as content their name
  selection1 ! HelloMessage("actor1")
  selection2 ! HelloMessage("actor2")

}
