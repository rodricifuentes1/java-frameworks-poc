package co.s4n.vertx;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;

public class Prueba extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route(HttpMethod.GET, "/observableAsync")
                .produces("application/json")
                .handler(routingContext -> {
                    String jsonData = Json.encode(new Data(1L, "data", 23, 240000.566));
                    routingContext
                            .response()
                            .putHeader("Content-Length", Integer.toString(jsonData.length()))
                            .write(jsonData)
                            .end();
                });

        HttpServer server = vertx.createHttpServer();
        server.requestStream()
                .toObservable()
                .subscribe(router::accept);
        server.listen(8080);
    }

}
