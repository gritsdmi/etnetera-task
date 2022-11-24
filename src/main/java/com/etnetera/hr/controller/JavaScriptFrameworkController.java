package com.etnetera.hr.controller;

import com.etnetera.hr.service.JavaScriptFrameworkService;
import com.etnetera.hr.to.FilterTO;
import com.etnetera.hr.to.JavaScriptFrameworkTO;
import com.etnetera.hr.to.NewVersionTO;
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

    private final JavaScriptFrameworkService service;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<JavaScriptFramework> getAllFrameworks() {
        return service.findAll();
    }

    @PostMapping
    public JavaScriptFramework update(@RequestBody JavaScriptFramework framework) {
        return service.save(framework);
    }

    @PostMapping("/new")
    public JavaScriptFramework create(@RequestBody JavaScriptFrameworkTO frameworkTO) {
        return service.createNew(frameworkTO);
    }

    @GetMapping("/actual")
    public Iterable<JavaScriptFramework> getActualFrameworks() {
        return service.findNonDeprecated();
    }

    @PostMapping("/newVersion")
    public JavaScriptFramework addNewVersion(@RequestBody NewVersionTO newVersionTO) {
        return service.addNewVersion(newVersionTO);
    }

    @GetMapping("/byName/{pattern}")
    public Iterable<JavaScriptFramework> findByName(@PathVariable String pattern) {
        return service.findByName(pattern);
    }

    @GetMapping("/filter")
    public Iterable<JavaScriptFramework> getByFilter(@RequestBody FilterTO filterTO) {
        return service.getByFilter(filterTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.remove(id);
    }
}
