package ex1

import akka.actor.{ActorSystem}

/**
  *
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
object Main extends App {

  //Set the maximum number of prints available to 3
  val nPrint = 3
  //Declaring the Actor System
  val actorSystem = ActorSystem("system1")
  //Creating the Printer actor
  val printerActor = actorSystem.actorOf(PrinterActor.props(nPrint),"printer-actor")
  //Creating the HelloWorld actor
  val helloActor = actorSystem.actorOf(HelloWorldActor.props(printerActor),"hello-actor")

}
