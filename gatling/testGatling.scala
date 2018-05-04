
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://detectportal.firefox.com")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0")

	val headers_0 = Map("Pragma" -> "no-cache")

    val uri1 = "http://detectportal.firefox.com/success.txt"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/success.txt")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/success.txt")
			.headers(headers_0)))
		.pause(1)
		.exec(http("request_2")
			.get("/success.txt")
			.headers(headers_0))
		.pause(2)
		.exec(http("request_3")
			.get("/success.txt")
			.headers(headers_0)
			.resources(http("request_4")
			.get("/success.txt")
			.headers(headers_0)))
		.pause(1)
		.exec(http("request_5")
			.get("/success.txt")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}