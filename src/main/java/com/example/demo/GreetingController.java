package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import java.sql.SQLException;


@RestController
@RequestMapping
public class GreetingController {

    @GetMapping(value = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get(@RequestParam(value = "login") String login_name) throws SQLException {
        return ResponseEntity.ok(ConnectionData.doSelect(login_name));
    }

    @ExceptionHandler(ExceptionService.class)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> post(@RequestBody User user) throws ExceptionService {
        ConnectionData connectionData = new ConnectionData();

        if (user.getLogin() == null | user.getPassword() == null | user.getEmail() == null) {

            throw new ExceptionService(HttpStatus.BAD_REQUEST);

        } else if (user.getLogin().isEmpty() | user.getPassword().isEmpty() | user.getEmail().isEmpty()) {

            throw new ExceptionService(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(connectionData.doInsert(user));
    }

    // Для
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<User> exception(HttpMessageNotReadableException ex) {
        throw new ExceptionService(HttpStatus.BAD_REQUEST);
    }
}