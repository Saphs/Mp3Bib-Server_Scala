import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{CommonFilters, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter

object ServerMain extends Mp3BibServer

class Mp3BibServer extends HttpServer {

  override protected def warmup(): Unit = {
    MongoStartup.startOnStaticPath()
  }

  override def configureHttp(router: HttpRouter) {

    router
      .filter[LoggingMDCFilter[Request, Response]]
      .filter[TraceIdMDCFilter[Request, Response]]
      .filter[CommonFilters]
      .add[Mp3BibController]
  }
}