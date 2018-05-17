**PingPong with Jade**

To launch Jade from IntelliJ create a new Application configuration
* type a Name for your configuration (e.g. Jade launch)
* set `jade.Boot` as Main class
* set `-gui` as Program arguments
* set Use classpath of module to `akka-ds_main`

After that create inside the `MainContainer` a `PingPongAgent` and send a new message setting
* the agent itself as Sender
* `propose` as Communicative act
* `ping` as Content
* `ping-pong` as Ontology

The code related to `PingPongAgent` can be found within `java\ex2` package.

**PingPong with Akka**

The code related to `PingPongActor` can be found in this package.

**Comparison**

Focus on
* agent and actor creation
* where agent's and actor's behavior is specified
* how actor and agent send/receive messages
