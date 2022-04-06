package ru.job4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Отправитель посылает запрос на добавление данных с указанием топика и значением параметра. Сообщение помещается
 * в конец каждой индивидуальной очереди получателей. Если топика нет в сервисе, то данные игнорируются. Получатель
 * посылает запрос на получение данных с указанием топика. Если топик отсутствует, то создается новый.
 * А если топик присутствует, то сообщение забирается из начала индивидуальной очереди получателя и удаляется.
 * Когда получатель впервые получает данные из топика – для него создается индивидуальная пустая очередь.
 * Все последующие сообщения от отправителей с данными для этого топика помещаются в эту очередь тоже.
 *
 * @author yustas
 * @version 1.0
 */
public class TopicService implements Service {
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> queue
            = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if ("POST".equals(req.getHttpRequestType())) {
            var topic = queue.get(req.getSourceName());
            for (var linkedQueue : topic.values()) {
                linkedQueue.add((req.getParam()));
            }
            return new Resp(req.getParam(), "200");
        } else if ("GET".equals(req.getHttpRequestType())) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentHashMap<>());
            queue.get(req.getSourceName()).putIfAbsent(req.getParam(), new ConcurrentLinkedQueue<>());
            String text = queue.get(req.getSourceName()).get(req.getParam()).poll();
            return text == null ? new Resp("", "204") : new Resp(text, "201");
        } else {
            return new Resp(null, "204");
        }
    }
}