package com.example.voaenglish.model;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloudResponseBody {
    @SerializedName("result")
    @Expose
    private boolean result;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private JsonElement data;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CloudResponseBody{" +
                "result=" + result +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
