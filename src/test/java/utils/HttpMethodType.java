package utils;

public enum HttpMethodType {

    GET("get"), POST("post"), PUT("put"), DELETE("delete");

    private String methodType;

    private HttpMethodType(String name) {
        this.methodType = name;
    }

}
