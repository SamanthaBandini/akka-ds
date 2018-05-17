package ex4

import java.io.File

import akka.actor.{ActorSystem, Props}
import akka.cluster.Cluster
import com.typesafe.config.ConfigFactory

/**
  * @author samantha.bandini3@studio.unibo.it
  * @author annagiulia.leoni@studio.unibo.it
  */
object Main extends App {

    //Declaring Actor System name and configuration
    val systemName = "pub-sub"
    val config = ConfigFactory.parseFile(new File("src/main/scala/ex4/application.conf"))

    //Declaring the first Actor System with the given name and congifuration
    val system1 = ActorSystem(systemName, config)
    //Joining the cluster
    val joinAddress = Cluster(system1).selfAddress
    Cluster(system1).join(joinAddress)
    //Creating a Subscriber actor
    system1.actorOf(Props[Subscriber], "Subscriber1")

    Thread.sleep(5000)

    val system2 = ActorSystem(systemName, config)
    Cluster(system2).join(joinAddress)
    //Creating a Subscriber and a Publisher actor
    system2.actorOf(Props[Subscriber], "Subscriber2")
    val publisher = system2.actorOf(Props[Publisher], "Publisher1")

    Thread.sleep(5000)

    //Publish a message
    publisher ! "hello"

}
