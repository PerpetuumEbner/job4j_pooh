package ru.job4j;

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

    public String getHttpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}