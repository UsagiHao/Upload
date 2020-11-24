package com.example.upload.controller;

import com.example.upload.service.StartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class StartController {
    private final StartService startService;

    public StartController(StartService startService){
        this.startService = startService;
    }

    @GetMapping("/start")
    public void startUploadData(){
        System.out.println("start");
        startService.startUpload();
    }
}
