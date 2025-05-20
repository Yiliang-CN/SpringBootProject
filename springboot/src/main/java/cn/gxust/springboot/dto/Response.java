package cn.gxust.springboot.dto;

public class Response<T> {
    private static final int SUCCESS_CODE = 200;
    private static final String SUCCESS_MESSAGE = "Success";

    private int code;
    private String message;
    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setCode(response.SUCCESS_CODE);
        response.setMessage(response.SUCCESS_MESSAGE);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(int code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> badRequest(String message) {
        return fail(400, message);
    }

    public static <T> Response<T> notFound(String message) {
        return fail(404, message);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
