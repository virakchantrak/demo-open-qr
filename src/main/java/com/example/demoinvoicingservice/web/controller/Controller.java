package com.example.demoinvoicingservice.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoice")
public class Controller {


    @GetMapping("/hi")
    public String hi() {
        return "HI";
    }
}
