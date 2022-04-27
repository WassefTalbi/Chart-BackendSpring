package com.example.ChartTest.DAO;

import com.example.ChartTest.Entity.Chart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface ChartRepository extends JpaRepository<Chart, Long> {

    @Query(value = "SELECT id FROM Chart  WHERE title =:x",nativeQuery = true)
    public Long chercherChartByTitle(@Param("x") String title);


}
