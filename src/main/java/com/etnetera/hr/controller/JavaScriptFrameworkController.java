package com.etnetera.hr.controller;

import com.etnetera.hr.service.JavaScriptFrameworkService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.etnetera.hr.data.JavaScriptFramework;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
@Log
@RequestMapping(value = "/frameworks", produces = {"application/json; charset=UTF-8"})
public class JavaScriptFrameworkController {

    //    private final JavaScriptFrameworkRepository repository;
    private final JavaScriptFrameworkService service;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<JavaScriptFramework> frameworks() {
        return service.findAll();
    }

    @PostMapping
    public JavaScriptFramework save(@RequestBody JavaScriptFramework framework) {
        return service.save(framework);
    }

    @PostMapping("/new")
    public JavaScriptFramework create() {
        return service.createNew();
    }

    @GetMapping("/actual")
    public Iterable<JavaScriptFramework> getActualFrameworks() {
        return service.findNonDeprecated();
    }

//    @GetMapping("/byDate") //todo filterTO
//    public Iterable<JavaScriptFramework> getActualFrameworks() {
//        return service.findNonDeprecated();
//    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.remove(id);
    }
}
