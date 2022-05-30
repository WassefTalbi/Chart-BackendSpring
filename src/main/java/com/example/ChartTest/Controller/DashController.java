package com.example.ChartTest.Controller;

import com.example.ChartTest.DAO.DashboardRepository;
import com.example.ChartTest.DTO.ChartDashboard;
import com.example.ChartTest.DTO.DatasetDTO;
import com.example.ChartTest.Entity.Chart;
import com.example.ChartTest.Entity.Dashboard;
import com.example.ChartTest.Entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@Transactional
@CrossOrigin("*")
public class DashController {
    @Autowired
    private DashboardRepository dashboardRepository;
    @CrossOrigin("*")
    @GetMapping("/dash/{id}")
    public List<ChartDashboard>chartList(@PathVariable(name ="id") Long id){
        Dashboard dashboard= dashboardRepository.findById(id).get();
        Collection<Chart> charts = dashboard.getCharts();
        List<ChartDashboard> chartDashbord_List=new ArrayList<>();
        charts.forEach(c->{
            ChartDashboard chartDashboard=new ChartDashboard();
           String title= c.getTitle();
           Type type= c.getType();
           Long id_chart=c.getId();
           List<String> labels_Name=new ArrayList<>();
           c.getLabels().forEach(label->{
              String lab= label.getDescLabel();
              labels_Name.add(lab);
           });
               List<DatasetDTO> dataset_List=new ArrayList<>();
           c.getDatasets().forEach(ds->{
               DatasetDTO datasetDTO=new DatasetDTO();
          String  label_Dataset =  ds.getLabel();
          Long id_dataset=ds.getId();
         String backgroundColor= ds.getBackgroundColor();
               List<Integer> valeurs =new ArrayList<>();
            ds.getDataPoints().forEach(dataPoint -> {
                Integer val= dataPoint.getValeur();
                valeurs.add(val);
            });
            datasetDTO.setId(id_dataset);
            datasetDTO.setName(label_Dataset);
            datasetDTO.setBackgroundColor(backgroundColor);
            datasetDTO.setDataPoints(valeurs);
            dataset_List.add(datasetDTO);
           });


           chartDashboard.setLabels(labels_Name);
           chartDashboard.setTitle(title);
           chartDashboard.setId(id_chart);
           chartDashboard.setType(type);
           chartDashboard.setValeurs(dataset_List);
           chartDashbord_List.add(chartDashboard);
        });

        return chartDashbord_List;

    }
}
