package ex1.sol
import akka.actor.{Actor, ActorRef, Props}

/**
  * Companion object of the class below
  */
object PrinterActor{
  val ActorName : String = "[PRINTER]"
  def props(n: Int): Props = Props(PrinterActor(n))
}

/**
  * Simple Actor who prints the content of a received message and
  * reply with an ACK until it runs out of the maximum number of prints
  * @param n indicates the maximum number of prints available
  *
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
case class PrinterActor(n: Int) extends Actor{

  //It counts the messages received
  var msgReceived : Int = 0

  /**
    * Declaring actor behavior
    */
  override def receive : Receive = {
    case ContentMessage(content) =>
      println(PrinterActor.ActorName + " received a message with content: " + content)
      sender ! AckMessage
      msgReceived = msgReceived + 1
      if (msgReceived == n) {
        println(PrinterActor.ActorName + " reached the maximum limit of prints")
        context.become({
          case _ =>
            sender ! OutMessage
        })
      }
    case _ => //do nothing

  }
}
