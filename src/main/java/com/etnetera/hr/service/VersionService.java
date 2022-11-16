package com.etnetera.hr.service;

import com.etnetera.hr.data.Version;
import com.etnetera.hr.repository.VersionRepository;
import com.etnetera.hr.to.VersionTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Log
public class VersionService {

    private final VersionRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    public VersionService(VersionRepository repository) {
        this.repository = repository;
    }

    public Version getById(long id) {
        return repository.getOne(id);
    }

    public Version update(VersionTO version) {
        var newVersion = mapper.map(version, Version.class);
        return repository.save(newVersion);
    }

    public Version save(Version version) {
       return repository.save(version);
    }
}
