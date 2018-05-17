package ex5.sol

import akka.actor.Actor
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.{Subscribe, SubscribeAck}

/**
  * Simple actor which subscribe itself to the topic
  * "content" and waits for messages addressed to it
  *
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
case class Subscriber() extends Actor {

  //Get the Actor System mediator
  val mediator = DistributedPubSub(context.system).mediator

  //Subscribe to the topic named "content"
  mediator ! Subscribe("content", self)
  println("Mediator path: " + mediator.path)

  /**
    * Declaring actor behavior
    */
  override def receive: Receive = {

    case in: String =>
      println(self.toString + ": got " + in + " from " + sender.toString)

    case SubscribeAck(Subscribe("content", None, `self`)) =>
      println("Subscribed " + self.toString)

    case _ => //do nothing
  }

}