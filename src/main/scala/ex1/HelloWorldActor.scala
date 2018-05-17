package ex1
import akka.actor.{Actor, ActorRef, Props}

/**
  * Companion object of the class below
  */
object HelloWorldActor{
  val ActorName : String = "[HELLOWORLD]"
  val HelloWorldTxt = "Hello world!"
  def props(printer: ActorRef): Props = Props(HelloWorldActor(printer))
}

/**
  * Simple actor who sends printable messages to a printer actor and
  * it must stop when it receives a message of type Out
  * @param printer indicates the ActorRef of the printer actor
  *
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
case class HelloWorldActor(printer: ActorRef) extends Actor{

  //Start communication sending a message to Printer actor
  ??? //TODO

  /**
    * Declaring actor behavior
    */
  override def receive : Receive = {

    ??? //TODO

  }
}
