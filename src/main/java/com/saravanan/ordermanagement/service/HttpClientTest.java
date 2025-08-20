package com.saravanan.ordermanagement.service;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Sarav on 17 Jul 2025
 * @project govtech
 * @package com.saravanan.ordermanagement.service
 * @class HttpClientTest
 */
public class HttpClientTest {
    public void makeHttpRequests() {


        // Blocks the calling thread;  (Blocking, Synchronous)
        // Uses HTTP/1.1

        RestTemplate rt = new RestTemplate();
        String body = rt.getForObject("https://api.example.com", String.class);
        System.out.println(body);


        // Java 11 HttpClient (Async, Non-blocking)
        // Uses HTTP/2

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);





    }

//    @PostMapping("/fx/trades")
//    public ResponseEntity<Trade> executeTrade(
//            @RequestBody TradeRequest request,
//            @RequestHeader("Idempotency-Key") String idempotencyKey) {
//
//        // 1. Check Redis for existing idempotencyKey
//        // 2. If not exists, process trade and store result in Redis
//        // 3. Return 409 Conflict if key exists
//    }

}
//public class TradingCache {
//    private final ConcurrentHashMap<String, AtomicReference<MarketData>> cache = new ConcurrentHashMap<>();
//
//    public void update(String symbol, MarketData data) {
//        cache.compute(symbol, (k, v) -> {
//            if (v == null) v = new AtomicReference<>();
//            v.set(data);
//            return v;
//        });
//    }
//
//    public MarketData get(String symbol) {
//        return cache.getOrDefault(symbol, new AtomicReference<>()).get();
//    }
//}

