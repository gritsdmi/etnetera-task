package com.etnetera.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etnetera.hr.data.JavaScriptFramework;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 *
 * @author Etnetera
 */
//public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {
@Repository
public interface JavaScriptFrameworkRepository extends JpaRepository<JavaScriptFramework, Long> {

    List<JavaScriptFramework> findByName(String name);

    List<JavaScriptFramework> findByHypeLevel(String hype);
}
