package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.service.DockerStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dockerStack")
public class DockerStackController extends CloudBaseController {
    @Autowired
    private DockerStackService dockerService;


    @GetMapping("/dealDockerStackInfo")
    public void dealDockerStackInfo(){
        dockerService.saveAllDockerStack();
    }




}
