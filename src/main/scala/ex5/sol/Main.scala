package ex5.sol

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
    val systemName1 = "cluster1"
    val config1 = ConfigFactory.parseFile(new File("src/main/scala/ex5/sol/cluster1.conf"))

    //Declaring the first Actor System with the given name and congifuration
    val system1 = ActorSystem(systemName1, config1)
    //Joining the cluster
    val joinAddress1 = Cluster(system1).selfAddress
    Cluster(system1).join(joinAddress1)
    //Creating a Subscriber actor
    system1.actorOf(Props[Subscriber], "subscriber1")

    val systemName2 = "cluster2"
    val config2 = ConfigFactory.parseFile(new File("src/main/scala/ex5/sol/cluster2.conf"))
    val system2 = ActorSystem(systemName2, config2)
    val joinAddress2 = Cluster(system2).selfAddress
    Cluster(system2).join(joinAddress2)
    //Creating a Subscriber and a Publisher actor
    system2.actorOf(Props[Subscriber], "subscriber2")
    val publisher = system2.actorOf(Props[Publisher], "publisher1")

    Thread.sleep(5000)

    //Publish a message
    publisher ! "hello"

}
