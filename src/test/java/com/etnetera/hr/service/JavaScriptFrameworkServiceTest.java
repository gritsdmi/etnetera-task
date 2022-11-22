package com.etnetera.hr.service;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.to.JavaScriptFrameworkTO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavaScriptFrameworkServiceTest extends TestCase {

    @Autowired
    private JavaScriptFrameworkService service;
    @Spy //idk
    private JavaScriptFrameworkRepository repository;
    @Spy
    private ModelMapper modelMapper;


    @Before
    public void setUp() {
        var javaScriptFramework = new JavaScriptFrameworkTO("test setUp");
        var saved = service.createNew(javaScriptFramework);
        System.out.println(saved);
    }

    @Test
    @Order(1)
    public void testCreateNewFromTO() {
        var javaScriptFramework = new JavaScriptFrameworkTO("test testCreateNew");
        var saved = service.createNew(javaScriptFramework);

        assertNotNull(saved);
    }

    @Test
    @Order(2)
    public void testUpdateFramework() {

        var javaScriptFrameworkList = service.findAll();
        JavaScriptFramework saved = null;
        var newFrameName = "testNewName";
        if (!javaScriptFrameworkList.isEmpty()) {
            var frame = javaScriptFrameworkList.get(0);
            frame.setName(newFrameName);
            saved = service.save(frame);
        }

        assertNotNull(saved);
        assertEquals(newFrameName, saved.getName());
    }

    @Test
    @Order(3)
    public void testFindByName() {
        var expected = "testNewName";
        var frameList = service.findByName("qqq");

        assertNotNull(frameList);
        frameList.forEach(framework -> assertThat(framework.getName(), containsString(expected)));

    }

    @Order(4)
    public void testFindNonDeprecated() {


    }

    @Order(5)
    public void testFilter() {
    }

    @Order(6)
    public void testRemoveFrameWork() {

    }
}