package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.util.HashMap;

@RestController
public class LoginController {
    LocalDate currentDate = LocalDate.now();

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>login(@RequestBody Login login){
        HashMap<String, Object> map = new HashMap<>();
        map.put("login", login.getUsername());
        map.put("currentDay", currentDate);
        return ResponseEntity.ok(map);
    }
}


