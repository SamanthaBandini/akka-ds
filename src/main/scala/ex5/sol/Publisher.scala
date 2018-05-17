package ex5.sol

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef}
import akka.cluster.pubsub.DistributedPubSub
import akka.util.Timeout
import akka.cluster.pubsub.DistributedPubSubMediator.Publish

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await

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
  //Get the Actor System mediator of the *remote* cluster
  val actorSelection = context.system.actorSelection("akka.tcp://cluster1@127.0.0.1:2552/system/distributedPubSubMediator")

  /**
    * Declaring actor behavior
    */
  override def receive: Receive = {

    case in: String =>
      val out = in.toUpperCase
      //Publish a message to the *local* mediator
      mediator ! Publish("content",out)
      //Publish a message to the *remote* mediator
      actorSelection ! Publish("content", out)

    case _ => //do nothing

  }

}