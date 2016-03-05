package co.s4n

import com.typesafe.scalalogging.LazyLogging
import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object Boot extends App with LazyLogging {

  // This sets the class for the simulation we want to run.
  val simulationClass = classOf[ ConcurrencySimulation ].getName

  val props = new GatlingPropertiesBuilder()
  props.simulationClass( simulationClass )
  props.binariesDirectory( "./target/scala-2.11/classes" )
  props.sourcesDirectory( "./src/main/scala" )
  props.outputDirectoryBaseName( "simulation" )

  Gatling.fromMap( props.build )

}

