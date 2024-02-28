package com.jwtauthentication.jwtauth.demo;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-api")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> sayHello(){

        return ResponseEntity.ok("This is secured services");
    }

}
