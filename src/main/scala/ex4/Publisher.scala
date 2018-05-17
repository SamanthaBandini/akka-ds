package ex4

import akka.actor.Actor
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.Publish

/**
  * Simple actor which publishes incoming messages to the
  * topic "content" after turning them into uppercase
  *
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
case class Publisher() extends Actor {

  //Get the Actor System mediator
  val mediator = DistributedPubSub(context.system).mediator

  /**
    * Declaring actor behavior
    */
  override def receive: Receive = {

    case in: String =>
      val out = in.toUpperCase
      mediator ! Publish("content", out)

    case _ => //do nothing

  }

}