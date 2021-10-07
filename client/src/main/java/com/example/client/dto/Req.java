package com.example.client.dto;

public class Req<T> {

    private Header header;
    private T resBody; //body의 내용(예를 들어 body 안의 book,page가 Name,age로 바뀌는 경우)은 계속 바뀌니까 제너릭 타입 <T>

    public static class Header {

        private String responseCode;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getResBody() {
        return resBody;
    }

    public void setResBody(T resBody) {
        this.resBody = resBody;
    }

    @Override
    public String toString() {
        return "Req{" +
                "header=" + header +
                ", body=" + resBody +
                '}';
    }
}
