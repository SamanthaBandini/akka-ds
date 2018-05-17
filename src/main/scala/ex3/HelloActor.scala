package ex3

import akka.actor.{Actor}

/**
  * Simple actor printing a string part of which
  * comes from remote
  *
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
case class HelloActor() extends Actor{

  /**
    * Declaring actor behavior
    */
  override def receive: Receive = {

    case HelloMessage(content) =>
      println("Hello " + content + " from Remote!")

  }

}
