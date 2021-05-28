package com.ederfaria.parking.api.response;

/**
 *
 * @author eder
 */
public class JResponseStatus<T> {

    private T data;

    public JResponseStatus() {
    }

    public JResponseStatus(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
