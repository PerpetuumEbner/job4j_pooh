package ru.job4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    @Override
    public Resp process(Req req) {
        Resp resp = new Resp();
        ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();
        if (!req.getSourceName().equals(queue.get(req.getSourceName()))) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            queue.get(req.getSourceName()).add(req.getParam());
        }
        if (queue.isEmpty()) {
            resp.setStatus("204");
        } else {
            resp.setStatus("200");
        }
        if (req.getSourceName().equals(queue.get(req.getSourceName()))) {
            queue.get(req.getSourceName()).poll();
        } else {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
        }
        return resp;
    }
}