**Clustering with DistributedPubSub and Remote**

_TODO_

Modify `scala/ex4` in order to have two Actor Systems on different Clusters that communicate through Remote.

Key points
* create two configuration files, one for each cluster (as in `scala/ex4`) adding an appropriate configuration for Remote (as in `scala/ex3`)
* inside `Main` object create two cluster
    * _cluster1_ with one `Subscriber`
    * _cluster2_ with one `Subscriber` and one `Publisher`
* subscribers have to subscribe to the same topic (e.g. `content`)
* the publisher has to publish for the same topic above on the `mediator` of all the clusters