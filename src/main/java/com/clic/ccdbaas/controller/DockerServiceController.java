package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.service.DockerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dockerService")
public class DockerServiceController extends CloudBaseController {
    @Autowired
    private DockerServiceService dockerService;


    @GetMapping("/dealDockerServiceInfo")
    public void dealDockerServiceInfo(){
        dockerService.saveAllDockerService();
    }




}
