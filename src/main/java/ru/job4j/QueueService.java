package ru.job4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    @Override
    public Resp process(Req req) {
        Resp resp = new Resp();
        ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();
        queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
        if (queue.isEmpty()) {
            resp.setStatus("204");
        } else {
            resp.setStatus("200");
        }
        queue.get(req.getSourceName()).add(req.getParam());
        queue.get(req.getSourceName()).poll();
        return resp;
    }
}