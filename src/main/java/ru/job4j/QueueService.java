package ru.job4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Отправитель посылает запрос на добавление данных с указанием очереди и значением параметра.
 * Сообщение помещается в конец очереди. Если очереди нет в сервисе, то нужно создать новую и поместить в нее сообщение.
 * Получатель посылает запрос на получение данных с указанием очереди. Сообщение забирается из начала очереди и
 * удаляется. Если в очередь приходят несколько получателей, то они поочередно получают сообщения из очереди. Каждое
 * сообщение в очереди может быть получено только одним получателем.
 *
 * @author yustas
 * @version 1.0
 */
public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        if ("POST".equals(req.getHttpRequestType())) {
            queue.putIfAbsent(req.getSourceName(), new ConcurrentLinkedQueue<>());
            queue.get(req.getSourceName()).add(req.getParam());
            return new Resp(req.getParam(), "201");
        } else if ("GET".equals(req.getHttpRequestType())) {
            var concurrentLinkedQueue = queue.get(req.getSourceName());
            return new Resp(concurrentLinkedQueue.poll(), "200");
        } else {
            return new Resp(null, "204");
        }
    }
}