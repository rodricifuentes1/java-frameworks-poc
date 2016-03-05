package co.s4n

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.config.HttpProtocolBuilder

class ConcurrencySimulation extends Simulation {

  /** Configuration settings **/
  val simulationConf: SimulationConf = SimulationConf.fromConfig()

  /** Gatling http configuration **/
  def httpConf: HttpProtocolBuilder = http
    .baseURL( s"${simulationConf.url}:${simulationConf.port}" )
    .acceptHeader( simulationConf.acceptHeader )

  /** Gatling scenario builder **/
  def simulationScenario: ScenarioBuilder = scenario( simulationConf.scenario.name )
    .exec( http( simulationConf.scenario.requestName ).get( simulationConf.scenario.requestPath ) )
    .pause( simulationConf.scenario.pause )

  /** Simulation setup **/
  setUp(
    simulationScenario.inject(
      rampUsers( simulationConf.scenario.rampUsers ).over( simulationConf.scenario.rampUsersDuration )
    )
  ).protocols( httpConf )
}
