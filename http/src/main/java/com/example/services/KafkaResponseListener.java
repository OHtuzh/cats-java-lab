package com.example.services;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class KafkaResponseListener {

    private final ConcurrentHashMap<String, CompletableFuture<Object>> futureMap = new ConcurrentHashMap<>();

    public CompletableFuture<Object> getResponse(String topic) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        futureMap.put(topic, future);
        return future;
    }

    @KafkaListener(topics = {
            "cat-create-response", "cat-read-response", "cat-delete-response",
            "cat-filter-response", "cat-add-friend-response",
            "owner-create-response", "owner-read-response",
            "owner-delete-response", "owner-get-current-response"
    })
    public void listenResponse(String response, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        CompletableFuture<Object> future = futureMap.get(topic);
        if (future != null) {
            future.complete(response);
            futureMap.remove(topic);
        }
    }
}
