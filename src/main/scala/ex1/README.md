**Hello World with Akka**

The system provided includes 2 actors:
* The `PrinterActor` who prints the content of a received message and reply with an ACK until it runs out of the maximum number of prints
* The `HelloWorldActor`, who sends printable messages (with the `HelloWorldTxt` as content) to the `PrinterActor` (whose reference is passed to `HelloWorldActor` constructor) and it must stop when it receives an OUT message
  
_TODO_

Implement missing pieces of code (see `???`):
* Complete `HelloWorldActor` starting the communication and implementing the actor behavior as described above.
* Complete `PrinterActor` so that he starts replying to any message with an `OutMessage` when he runs out of the maximum number of prints.
* Verify that the output matches the one shown in the slides 

