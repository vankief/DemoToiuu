package com.example.hashmap_stringinteger.model;

import java.io.Serializable;
import java.util.List;

public class DataResponse implements Serializable {
    private String status;
    private String message;
    private int statusCode;
    private String reasonStatusCode;
    private List<DataResult> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonStatusCode() {
        return reasonStatusCode;
    }

    public void setReasonStatusCode(String reasonStatusCode) {
        this.reasonStatusCode = reasonStatusCode;
    }

    public List<DataResult> getData() {
        return data;
    }

    public void setData(List<DataResult> data) {
        this.data = data;
    }
}
