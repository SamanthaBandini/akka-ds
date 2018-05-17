package ex2

import akka.actor.{ActorSystem, Props}

/**
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
object Main extends App{

    //Declaring the Actor System
    val actorSystem = ActorSystem("ping-pong")
    //Creating the ping pong actor
    val pingpongActor = actorSystem.actorOf(Props(PingPongActor()),"pingpong-actor")

}
