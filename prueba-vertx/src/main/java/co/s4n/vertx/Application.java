package co.s4n.vertx;

import co.s4n.vertx.json.routes.JsonRoute;
import io.vertx.core.Future;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entrypoint verticle
 */
public class Application extends AbstractVerticle {

    /** Logger instance **/
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private HttpServer server;
    private Router router;
    private JsonRoute jsonRoute;

    @Override
    public void start(Future<Void> startFuture) {
        // Router initialization
        router = Router.router(vertx);
        jsonRoute = new JsonRoute(router);

        // Server initialization
        server = vertx.createHttpServer();
        server.requestStream().toObservable().subscribe(router::accept);
        server.listen(8080, "0.0.0.0", bindingResult -> {
            if (bindingResult.succeeded()) {
                logger.info("Application service is now up, listening on address 0.0.0.0 and port 8080" );
                startFuture.complete();
            } else {
                startFuture.fail(bindingResult.cause());
            }
        });
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {
        server.close(closingResult -> {
            if(closingResult.succeeded()) {
                logger.info("Application service was shut down successfully" );
                stopFuture.complete();
            } else {
                stopFuture.fail(closingResult.cause());
            }
        });
    }

    /**
     * Method that retrieves application http server
     * @return Initialized http server
     */
    public HttpServer getServer() {
        return server;
    }

    /**
     * Method that retrieves application router
     * @return Initialized router
     */
    public Router getRouter() {
        return router;
    }
}
