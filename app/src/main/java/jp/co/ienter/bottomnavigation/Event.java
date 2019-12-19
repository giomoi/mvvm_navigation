package jp.co.ienter.bottomnavigation;

//
// Created by  on 2019-12-02.
//
public class Event<T> {
    private T data;
    public Event(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
