package com.example.ChartTest.DAO;

import com.example.ChartTest.Entity.ChartDataset;
import com.example.ChartTest.Entity.DataPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ChartDatasetRepository extends JpaRepository<ChartDataset, Long> {
}
