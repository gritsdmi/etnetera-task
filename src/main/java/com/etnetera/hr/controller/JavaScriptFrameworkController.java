package com.etnetera.hr.controller;

import com.etnetera.hr.service.JavaScriptFrameworkService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etnetera.hr.data.JavaScriptFramework;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
@Log
public class JavaScriptFrameworkController {

//    private final JavaScriptFrameworkRepository repository;
    private final JavaScriptFrameworkService service;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkService service) {
        this.service = service;
    }

    @GetMapping
    public String emptyGet() {
        return "get works";
    }

    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> frameworks() {
        return service.findAll();
    }

    @PostMapping()
    public JavaScriptFramework create(@PathVariable JavaScriptFramework framework) {
        return service.save(framework);
    }

    @PostMapping("/new")
    public JavaScriptFramework create() {
        return service.createNew();
    }
}
