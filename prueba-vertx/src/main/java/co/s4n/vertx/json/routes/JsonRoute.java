package co.s4n.vertx.json.routes;

import co.s4n.vertx.json.IRoute;
import co.s4n.vertx.json.entities.Data;
import co.s4n.vertx.json.services.JsonService;
import io.vertx.core.http.HttpMethod;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

/**
 * Contains JSON routes
 */
public class JsonRoute implements IRoute {

    private JsonService jsonService;

    public JsonRoute(Router router) {
        // Services initialization
        jsonService = new JsonService();

        // Routes initialization
        router.route(HttpMethod.GET, "/data").handler(this::getData);
    }

    public void getData(RoutingContext ctx) {
        jsonService.getData()
                .map(data -> Data.nameLens.set("Modified name").f(data))
                .subscribe(data -> respondWithJson(ctx, 200, data));
    }

}
