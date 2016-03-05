package co.s4n

import com.typesafe.config.{ Config, ConfigFactory }
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ValueReader

import scala.concurrent.duration.FiniteDuration

case class ScenarioConf( name: String, requestName: String, requestPath: String, pause: FiniteDuration, rampUsers: Int, rampUsersDuration: FiniteDuration )
object ScenarioConf {
  implicit def confReader: ValueReader[ ScenarioConf ] = ValueReader.relative { conf =>
    ScenarioConf(
      conf.as[ String ]( "name" ),
      conf.as[ String ]( "request-name" ),
      conf.as[ String ]( "request-path" ),
      conf.as[ FiniteDuration ]( "pause" ),
      conf.as[ Int ]( "ramp-users" ),
      conf.as[ FiniteDuration ]( "ramp-users-duration" )
    )
  }
}

case class SimulationConf( acceptHeader: String, url: String, port: Int, scenario: ScenarioConf )
object SimulationConf {

  def fromConfig( config: Config = ConfigFactory.load() ): SimulationConf = {
    config.as[ SimulationConf ]( "simulation-conf" )
  }

  implicit def confReader: ValueReader[ SimulationConf ] = ValueReader.relative { conf =>
    SimulationConf(
      conf.as[ String ]( "accept-header" ),
      conf.as[ String ]( "url" ),
      conf.as[ Int ]( "port" ),
      conf.as[ ScenarioConf ]( "scenario" )
    )
  }

}
