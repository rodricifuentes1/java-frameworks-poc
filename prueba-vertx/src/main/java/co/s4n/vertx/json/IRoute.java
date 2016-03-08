package co.s4n.vertx.json;

import io.vertx.core.json.Json;
import io.vertx.rxjava.core.http.HttpServerResponse;
import io.vertx.rxjava.ext.web.RoutingContext;

public interface IRoute {

    default public void respondWithJson(RoutingContext ctx, int statusCode, Object data) {
        respondWithJson(ctx.response(), statusCode, data);
    }

    default public void respondWithJson(HttpServerResponse response, int statusCode, Object data) {
        String json = Json.encode(data);
        response.setStatusCode(statusCode)
                .putHeader("Content-Length", Integer.toString(json.length()))
                .putHeader("Content-Type", "application/json")
                .write(json)
                .end();
    }

}
