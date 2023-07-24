package com.example.demo;


import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;


@RestController
@RequestMapping
public class GreetingController {
    private String template;


    public String getTemplate() {
        return template;
    }
    public void setTemplate(String template) {
        this.template = template;
    }
    public GreetingController() {
    }

    @GetMapping(value = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingController get() {
        GreetingController gC = new GreetingController();
        gC.setTemplate("Hello world");
        return gC;
    }
    @ExceptionHandler(ExceptionService.class)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody Login login) throws ExceptionService {

        if (login.getLogin().isEmpty() | login.getPassword().isEmpty()) {
            throw new ExceptionService(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(login.toString());
    }
}