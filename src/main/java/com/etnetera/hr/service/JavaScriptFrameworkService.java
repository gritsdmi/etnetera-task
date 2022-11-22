package com.etnetera.hr.service;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.Version;
import com.etnetera.hr.data.enums.DeprecatedEnum;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.to.FilterTO;
import com.etnetera.hr.to.JavaScriptFrameworkTO;
import com.etnetera.hr.to.NewVersionTO;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log
public class JavaScriptFrameworkService {

    private final JavaScriptFrameworkRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public JavaScriptFrameworkService(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }

    public JavaScriptFramework createNew(JavaScriptFrameworkTO frameTO) {
        var frame = modelMapper.map(frameTO, JavaScriptFramework.class);
        return repository.save(frame);
    }

    public List<JavaScriptFramework> findAll() {
        return repository.findAll();
    }

    public JavaScriptFramework save(JavaScriptFramework framework) {
        var curFramework = getById(framework.getId());
        if (curFramework != null) {
            return repository.save(framework);
        }
        return null;
    }

    public JavaScriptFramework getById(long frameId) {
        var opt = repository.findById(frameId);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            log.severe("Framework with id: " + frameId + " does not exist");
        }
        return null; //this is bad
    }

    public JavaScriptFramework addNewVersion(NewVersionTO versionTO) {
        var framework = getById(versionTO.getFrameworkId());
        if (framework != null) {
            var version = modelMapper.map(versionTO.getVersion(), Version.class);
            framework.getVersion().add(version);
            return repository.save(framework);
        }
        return null; //this is bad
    }

    public List<JavaScriptFramework> findByName(String name) {
        return repository.findByName(name);
    }

    public List<JavaScriptFramework> findNonDeprecated() {
        return repository.findByNonDeprecated();
    }

    public List<JavaScriptFramework> filter(FilterTO filterTO) {
        var allFrameworks = findAll();
        var filterName = filterTO.getFrameworkName();
        var hypeLevels = filterTO.getHypeLevels();
        var deprecated = filterTO.getDeprecated();

        if (filterName != null && !filterName.isEmpty()) {
            allFrameworks = allFrameworks.stream()
                    .filter(framework -> framework.getName().contains(filterTO.getFrameworkName()))
                    .collect(Collectors.toList());
        }

        if (hypeLevels != null && !hypeLevels.isEmpty()) {
            allFrameworks = allFrameworks.stream()
                    .filter(framework -> hypeLevels.contains(framework.getHypeLevel()))
                    .collect(Collectors.toList());
        }

        if (deprecated != null) {
            if (deprecated == DeprecatedEnum.TRUE) {
                allFrameworks = allFrameworks.stream()
                        .filter(framework -> framework.getDeprecationDate().before(new Date()))
                        .collect(Collectors.toList());
            } else if (deprecated == DeprecatedEnum.FALSE) {
                allFrameworks = allFrameworks.stream()
                        .filter(framework -> framework.getDeprecationDate().after(new Date()))
                        .collect(Collectors.toList());
            }
        }

        return allFrameworks;
    }

    public void remove(Long frameId) {
        var frame = getById(frameId);
        if (frame != null) {
            repository.deleteById(frameId);
        } else {
            log.severe("No framework entity with id " + frameId + " exists!");
        }
    }

    public void remove(JavaScriptFramework framework) {
        remove(framework.getId());
    }

    public void remove(List<JavaScriptFramework> javaScriptFrameworks) {
        repository.deleteInBatch(javaScriptFrameworks);
    }
}
