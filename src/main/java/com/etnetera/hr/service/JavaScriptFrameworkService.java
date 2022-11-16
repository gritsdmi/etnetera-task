package com.etnetera.hr.service;

import com.etnetera.hr.data.HypeLevel;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.Version;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Log
public class JavaScriptFrameworkService {

    private final JavaScriptFrameworkRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final VersionService versionService;

    @Autowired
    public JavaScriptFrameworkService(JavaScriptFrameworkRepository repository, VersionService versionService) {
        this.repository = repository;
        this.versionService = versionService;
    }

    public JavaScriptFramework createNew() {
        var frame = new JavaScriptFramework();
        frame.setName("newFramework");
        frame.setDeprecationDate(LocalDateTime.now());
        frame.setHypeLevel(HypeLevel.FIRE);

        var version = new Version("v 001");
        version.setEstablishedDate(LocalDateTime.now());
        frame.setVersion(List.of(version));

        return repository.save(frame);
    }

    public Iterable<JavaScriptFramework> findAll() {
        return repository.findAll();
    }

    public JavaScriptFramework save(JavaScriptFramework framework) {
        return repository.save(framework);
    }

    public JavaScriptFramework update(JavaScriptFramework framework) {
        var currFrameOpt = repository.findById(framework.getId());
        if (currFrameOpt.isEmpty()) {
            log.severe("Framework with id: " + framework.getId() + " doesn't exist");
            return framework;
        }

        var currFrame = currFrameOpt.get();
        currFrame.setName(framework.getName());
        currFrame.setDeprecationDate(framework.getDeprecationDate());
        currFrame.setHypeLevel(framework.getHypeLevel());
        currFrame.setDeprecationDate(framework.getDeprecationDate());
        //todo solve versions

        //use mapper
        var newFrame = modelMapper.map(framework, JavaScriptFramework.class);
        var newVersion = modelMapper.map(framework.getVersion(), Version.class);
        ///////////

        return repository.save(framework);
    }

//    public JavaScriptFramework updateHype(long frameId, String newHype) {
//        switch (newHype) {
////            case Hype
//        }
//
//    }

    public JavaScriptFramework getById(long frameId) {
        return repository.getOne(frameId);
    }

    public JavaScriptFramework addNewVersion(long frameId, Version version) {
        var curFrame = getById(frameId);
        curFrame.getVersion().add(version);
        return repository.save(curFrame);
    }

    public JavaScriptFramework addNewVersion(long frameId, String version) {
        var framework = getById(frameId);
        var newVersion = versionService.save(new Version(version));
        framework.getVersion().add(newVersion);
        return repository.save(framework);
    }

    //finding
    public Iterable<JavaScriptFramework> findByName(String name) {
        return repository.findByName(name);
    }

    public Iterable<JavaScriptFramework> findByHypeLevel(String hypeLevel) {
        return repository.findByHypeLevel(hypeLevel);
    }

//    public Iterable<JavaScriptFramework> findByNonDeprecated(String hypeLevel) {
//
//    }

    public void remove(long frameId) {
        repository.deleteById(frameId); //todo test if version removes
    }

    public void remove(JavaScriptFramework framework) {
        remove(framework.getId());
    }
}
