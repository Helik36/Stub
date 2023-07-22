package com.example.demo;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionService extends ResponseStatusException {
    public ExceptionService(HttpStatusCode status) {
        super(status);
    }
}
