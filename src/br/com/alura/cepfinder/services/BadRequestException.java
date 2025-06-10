package br.com.alura.cepfinder.services;

public class BadRequestException extends RuntimeException {
    private String msg;

    public BadRequestException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
