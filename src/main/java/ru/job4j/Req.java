package ru.job4j;

/**
 * Класс служит для парсинга входящего запроса.
 *
 * @author yustas
 * @version 1.0
 */
public class Req {
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    /**
     * @param content Входящий запрос.
     * @return Переменные входящего запроса.
     */
    public static Req of(String content) {
        String[] parseContent = content.split(System.lineSeparator());
        String[] request = parseContent[0].split(" |/");
        if (parseContent[0].contains("GET /queue")) {
            return new Req(request[0], request[2], request[3], "");
        }
        if (parseContent[0].contains("GET /topic")) {
            return new Req(request[0], request[2], request[3], request[4]);
        }
        return new Req(request[0], request[2], request[3], parseContent[7]);
    }

    /**
     * @return Тип запроса GET или POST.
     */
    public String getHttpRequestType() {
        return httpRequestType;
    }

    /**
     * @return Режим работы queue или topic.
     */
    public String getPoohMode() {
        return poohMode;
    }

    /**
     * @return Имя очереди или топика.
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * @return Содержимое параметра.
     */
    public String getParam() {
        return param;
    }
}