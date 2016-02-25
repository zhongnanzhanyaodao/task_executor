package com.kingdee.internet.util;

public class Response<T> {
    public final int code;
    public final String message;
    public final T data;

    public static final Response<String> INVALID_PARAMETER = Response.create(500, "invalid parameters!", null);

    private Response(Builder<T> builder) {
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }

    public static <T> Response<T> create(int code, String message, T data) {
        return new Builder<T>().withCode(code).withMessage(message).withDate(data).build();
    }

    public static class Builder<T> implements IBuilder<Response<T>> {
        public int code;
        public String message;
        public T data;

        public Builder<T> withCode(int code) {
            this.code = code;
            return this;
        }

        public Builder<T> withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> withDate(T data) {
            this.data = data;
            return this;
        }

        @Override
        public Response<T> build() {
            return new Response<T>(this);
        }
    }
}
