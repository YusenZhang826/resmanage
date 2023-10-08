package com.clic.ccdbaas.controller;

import com.clic.ccdbaas.CloudBaseController;
import com.clic.ccdbaas.service.DockerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dockerProject")
public class DockerProjectController extends CloudBaseController {
    @Autowired
    private DockerProjectService dockerService;


    @GetMapping("/dealDockerProjectInfo")
    public void dealDockerProjectInfo(){
        dockerService.saveAllDockerProject();
    }




}
