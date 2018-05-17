package ex3

import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
object Main extends App {

  //Declaring the Actor System with the given configuration
  val config = ConfigFactory.parseFile(new File("src/main/scala/ex3/app1.conf"))
  val actorSystem = ActorSystem("helloworld", config)
  //Creating two HelloActor
  val actor1 = actorSystem.actorOf(Props(HelloActor()),"actor1")
  val actor2 = actorSystem.actorOf(Props(HelloActor()),"actor2")
  //println(actor1.path)
  //println(actor2.path)

}
