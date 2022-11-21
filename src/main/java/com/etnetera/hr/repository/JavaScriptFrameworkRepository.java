package com.etnetera.hr.repository;

import com.etnetera.hr.data.JavaScriptFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 *
 * @author Etnetera
 */
@Repository
public interface JavaScriptFrameworkRepository extends JpaRepository<JavaScriptFramework, Long> {

    @Query("select frame from JavaScriptFramework frame where " +
            "lower(frame.name) like lower(concat('%', :pattern,'%'))")
    List<JavaScriptFramework> findByName(@Param("pattern") String pattern);

    @Query("select f from JavaScriptFramework f where (f.deprecationDate > CURRENT_DATE)")
    List<JavaScriptFramework> findByNonDeprecated();
}
