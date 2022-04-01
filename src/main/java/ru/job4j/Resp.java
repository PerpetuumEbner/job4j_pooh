package ru.job4j;

public class Resp {
    private String text;
    private String status;

    public Resp(String text, String status) {
        this.text = text;
        this.status = status;
    }

    public Resp() {
    }

    public String text() {
        return text;
    }

    public String status() {
        return status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}