package com.example.ChartTest.DAO;

import com.example.ChartTest.Entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ConfigRepository extends JpaRepository<Config,Long> {
}
