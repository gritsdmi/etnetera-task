package com.etnetera.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etnetera.hr.data.JavaScriptFramework;
import org.springframework.data.jpa.repository.Query;
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


    //    List<JavaScriptFramework> findByNonDeprecated(String dateNow);
    @Query("select f from JavaScriptFramework f where (f.deprecationDate > CURRENT_DATE)")
    List<JavaScriptFramework> findByNonDeprecated();
}
