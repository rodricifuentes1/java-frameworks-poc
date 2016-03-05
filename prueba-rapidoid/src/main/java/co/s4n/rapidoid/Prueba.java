package co.s4n.rapidoid;

import org.rapidoid.data.JSON;
import org.rapidoid.http.Req;
import org.rapidoid.http.fast.On;
import rx.Observable;
import rx.util.async.Async;

public class Prueba {

    public Prueba(String host, int port) {
        On.address(host).port(port).listen();
        On.get("/nativeAsync").json(this::NativeAsyncRequest);
        On.get("/observableAsync").json(this::ObservableRequest);
    }

    public static void main(String[] args) {
        new Prueba("0.0.0.0", 8080);
    }

    public Req NativeAsyncRequest(Req request) {
        try {
            request.async();
            Data data = new Data(1L, "data", 23, 240000.566);
            request.response().json(JSON.stringify(data));
        } finally {
            request.done();
        }
        return request;
    }

    public Req ObservableRequest(Req request) {
        request.async();
        Async.start(() -> new Data(1L, "data", 23, 240000.566))
                .map(data -> new Data(2L, "datamod", 24, 150.10))
                .subscribe(data -> {
                    request.response().json(JSON.stringify(data));
                    request.done();
                });
        return request;
    }

}
