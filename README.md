# lagom-sbt-standalone

A basic example of building a Lagom project to run outside of the Lightbend Production Suite (aka ConductR).

This was initially created from the `lagom-java` template (`sbt new lagom/lagom-java.g8`), with the following changes:

* Added `ConfigurationServiceLocator` to Guice modules in each implementation project, as described in the [Lagom documentation](http://www.lagomframework.com/documentation/1.3.x/java/ProductionOverview.html)
* Added static port bindings to each service (`hello-impl`: 8001, `hello-stream-impl`: 8002)
* Added a static service location to `hello-impl` for `cas_native` (the Cassandra server)
* Added a static service location to `hello-stream-impl` for `hello`

To test this:

 1. Open a terminal and clone the repository to your workstation (`git clone https://github.com/TimMoore/lagom-sbt-standalone.git`)
 2. `cd lagom-sbt-standalone`
 3. `sbt` to enter the sbt console
 4. `dist` (from the sbt prompt) to create distribution packages
 5. `lagomCassandraStart` (from the sbt prompt) to run a Cassandra server on port 4000
 6. Leave sbt running in that terminal window and open two more terminals in the same directory
 7. In one terminal:
    1. `cd hello-impl/target/universal`
    2. `unzip hello-impl-1.0-SNAPSHOT.zip` (or, if you don't have the `unzip` command, use any tool to unzip the file into the same directory)
    3. `cd hello-impl-1.0-SNAPSHOT`
    4. `bash bin/hello-impl -Dlagom.cluster.join-self=on` (or `bin/hello-impl.bat -Dlagom.cluster.join-self=on` on Windows)
 8. In the other terminal:
    1. `cd hello-stream-impl/target/universal`
    2. `unzip hello-stream-impl-1.0-SNAPSHOT.zip`
    3. `cd hello-stream-impl-1.0-SNAPSHOT`
    4. `bash bin/hello-stream-impl -Dlagom.cluster.join-self=on` (or `bin/hello-stream-impl.bat -Dlagom.cluster.join-self=on` on Windows)

This runs each of the services on the configured port, as single-node clusters.

You can press control-C to exit the services, and run `lagomCassandraStop` in the initial sbt console to stop Cassandra (or simply exit sbt).

## Steps for lagom-scala project

For those looking for a basic example of building a Lagom project, in Scala, to run outside of the Lightbend Production Suite (aka ConductR) follow this link - [lagom-scala-sbt-standalone](https://github.com/knoldus/lagom-scala-sbt-standalone).

## Caveats

This configuration is not suitable for production deployments, it's just intended as a starting point. Here are some of the limitations to be aware of.

* Static service locator configuration is difficult to manage in practice, as it requires configuration to be duplicated across all services, and configuring additional services requires a restart.
* The provided `ConfigurationServiceLocator` only supports a single endpoint per service, which reduces resilience and elasticity.
* Using `-Dlagom.cluster.join-self=on` restricts each service to run on a single node. Creating a multi-node cluster requires configuring seed nodes as described in the [Lagom Cluster documentation](http://www.lagomframework.com/documentation/1.3.x/java/Cluster.html)
* The provided launcher scripts do not attempt to handle process supervision or health monitoring


[Lightbend Production Suite (ConductR)](https://www.lightbend.com/platform/production) provides easy-to-use solutions to these problems and others. 
