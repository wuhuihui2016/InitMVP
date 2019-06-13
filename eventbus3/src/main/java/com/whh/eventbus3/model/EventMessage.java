package com.whh.eventbus3.model;

/**
 * Created by wuhuihui on 2019/5/18.
 */

public class EventMessage {
    private int type; //为了区别消息的类型，相当于handler里的what
    private String message;

    public EventMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}



