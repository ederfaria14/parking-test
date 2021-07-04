package com.ederfaria.parking.api.response;

/**
 *
 * @author eder
 */
public class JResponse<T> {

    private T data;
    private String error;

    public JResponse() {
    }

    public JResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
