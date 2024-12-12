package com.Aire;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") 

public class Api {
    @RequestMapping("/data")
    public String getData() {
        return "Hello from the backend!";
    }

    }
