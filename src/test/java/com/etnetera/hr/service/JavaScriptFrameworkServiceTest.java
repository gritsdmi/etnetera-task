package com.etnetera.hr.service;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.enums.DeprecatedEnum;
import com.etnetera.hr.data.enums.HypeLevel;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.to.FilterTO;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        var frameList = service.findByName(expected);

        assertNotNull(frameList);
        frameList.forEach(framework -> assertThat(framework.getName(), containsString(expected)));

    }

    @Test
    @Order(4)
    public void testFindNonDeprecated() {
        var actualOnly = service.findNonDeprecated();
        assertThat(actualOnly, is(empty()));
    }

    @Test
    @Order(5)
    public void testFilter() {
        var filterDeprecatedTrue = new FilterTO();
        filterDeprecatedTrue.setDeprecated(DeprecatedEnum.TRUE);
        var filtered = service.getByFilter(filterDeprecatedTrue);
        filtered.forEach(javaScriptFramework -> assertTrue(javaScriptFramework.getDeprecationDate().before(new Date())));

        var newDepDate = service.getById(2);
        newDepDate.setDeprecationDate(
                Date.from(new Date().toInstant().plus(1, ChronoUnit.DAYS)));
        service.save(newDepDate);

        var filterDeprecatedFalse = new FilterTO();
        filterDeprecatedFalse.setDeprecated(DeprecatedEnum.FALSE);
        filtered = service.getByFilter(filterDeprecatedFalse);
        filtered.forEach(javaScriptFramework -> assertTrue(javaScriptFramework.getDeprecationDate().after(new Date())));



        var filterHypeLow = new FilterTO();
        filterHypeLow.setHypeLevels(List.of(HypeLevel.LOW));
        filtered = service.getByFilter(filterHypeLow);
        filtered.forEach(javaScriptFramework -> assertEquals(javaScriptFramework.getHypeLevel(), HypeLevel.LOW));

        var filterHypeHigh = new FilterTO();
        filterHypeHigh.setHypeLevels(List.of(HypeLevel.HIGH));
        filtered = service.getByFilter(filterHypeHigh);
        assertThat(filtered, is(empty()));


        var filterName = new FilterTO();
        filterName.setFrameworkName("test");
        filtered = service.getByFilter(filterName);
        filtered.forEach(javaScriptFramework -> assertTrue(javaScriptFramework.getName().contains(filterName.getFrameworkName())));


    }

    @Test
    @Order(6)
    public void testRemoveFrameWork() {
        var all = service.findAll();
        assertThat(all, is(not(empty())));

        var idToRemove = all.get(0).getId();
        service.remove(idToRemove);

        assertThat(service.findAll(), is(empty()));
    }
}