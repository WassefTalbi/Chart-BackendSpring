package com.example.ChartTest.DAO;

import com.example.ChartTest.Entity.Dashboard;
import com.example.ChartTest.Entity.DataPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface DashboardRepository extends JpaRepository<Dashboard, Long> {
    @Query(value = "SELECT id FROM Dashboard  WHERE title =:x",nativeQuery = true)
    public Long chercherDashboardByTitle(@Param("x") String title);
}
