package com.etnetera.hr;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Class used for Spring Boot/MVC based tests.
 *
 * @author Etnetera
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkTests {

    @Autowired
    private JavaScriptFrameworkService service;
    @MockBean
    private JavaScriptFrameworkRepository repository;

    @Before
    public void setUp() {
        var framework = new JavaScriptFramework("alex");

        Mockito.when(repository.findByName(framework.getName()))
                .thenReturn((List.of(framework)));
    }

    @Test
    public void findByNameTest() {
        String name = "ale";
        var all = service.findAll();
        List<JavaScriptFramework> foundL = service.findByName(name);
        if(!foundL.isEmpty()){
            var found = foundL.get(0);
            assertThat(found.getName()).isEqualTo(name);
        }
    }
}
