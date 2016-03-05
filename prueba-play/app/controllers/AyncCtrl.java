package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import entities.Data;
import play.libs.Json;
import play.mvc.*;

/**
 * Created by rodrigo on 4/03/16.
 */
public class AyncCtrl extends Controller {

    public Result async() {
        JsonNode jsonData = Json.toJson(new Data(1L, "data", 23, 240000.566));
        return ok(jsonData);
    }

}
