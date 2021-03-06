package ru.job4j;

/**
 * Модель ответа от сервиса.
 *
 * @author yustas
 * @version 1.0
 */
public class Resp {
    private final String text;
    private final String status;

    public Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public String status() {
        return status;
    }
}