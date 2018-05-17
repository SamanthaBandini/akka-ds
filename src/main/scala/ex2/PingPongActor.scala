package ex2

import akka.actor.{Actor}

/**
  * Declaring enum with values that will be used as messages,
  * since static values can also be used in template matching and as messages
  */
object PingPongValue extends Enumeration {
  type PingPongValue = Value
  val ping, pong = Value
}

/**
  * Simple actor showing basic communication functionalities
  *
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
case class PingPongActor() extends Actor{

  //Start communication sending a message to itself
  self ! PingPongValue.ping

  /**
    * Declaring actor behavior
    */
  override def receive = {

    case PingPongValue.ping =>
      println(PingPongValue.ping)
      Thread.sleep(1000)
      self ! PingPongValue.pong

    case PingPongValue.pong =>
      println(PingPongValue.pong)
      Thread.sleep(1000)
      self ! PingPongValue.ping

    case _ => //do nothing
  }

}
