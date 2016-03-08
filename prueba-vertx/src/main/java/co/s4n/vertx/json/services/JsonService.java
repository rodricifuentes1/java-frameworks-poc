package co.s4n.vertx.json.services;

import co.s4n.vertx.json.entities.Data;
import rx.Observable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Contains JSON services
 */
public class JsonService {

    /**
     * Get data and return an observable
     */
    public Observable<Data> getData() {
        Future<Data> dataFuture = CompletableFuture.completedFuture(
                new Data(1L, "data", 23, 240000.566)
        );
        return Observable.from(dataFuture).delay(100, TimeUnit.MILLISECONDS);
    }

}
