package com.example.ChartTest.Controller;

import com.example.ChartTest.DAO.ChartDatasetRepository;
import com.example.ChartTest.DAO.ChartRepository;
import com.example.ChartTest.DAO.DashboardRepository;
import com.example.ChartTest.Entity.Chart;
import com.example.ChartTest.Entity.ChartDataset;
import com.example.ChartTest.Entity.Dashboard;
import com.example.ChartTest.Entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;

@RestController
@CrossOrigin("*")
public class ChartController {
    @Autowired
    private ChartRepository chartRepository;
    @Autowired
    private ChartDatasetRepository chartDatasetRepository;
    @Autowired
    private DashboardRepository dashboardRepository;

    @PostMapping("/createChart")
    public ResponseEntity<Chart> create_chart(@RequestBody ChartDTO chartDTO ){
        Chart chart =new Chart();
        chart.setType(chartDTO.getType());
        chart.setTitle(chartDTO.getTitle());
        chart.setDescription(chartDTO.getDescription());
        chartRepository.save(chart);
        return ResponseEntity.ok(chart);

    }


@PostMapping("/parameterDataset/{id_Dataset}")
    public ResponseEntity<String> parameter_chart(@PathVariable(name ="id_Dataset") Long id,@RequestBody ParametrageDTO parametrageDTO){

        try {


            ChartDataset chartDataset =chartDatasetRepository.findById(id).get();
            chartDataset.setLabel(parametrageDTO.getLabel());
            chartDataset.setXAxisID(parametrageDTO.getXAxisID());
            chartDataset.setYAxisID(parametrageDTO.getYAxisID());
            chartDataset.setStack(parametrageDTO.getStack());
            chartDataset.setIndexAxis(parametrageDTO.getIndexAxis());
            chartDataset.setBorderRadius(parametrageDTO.getBorderRadius());
            chartDataset.setBase(parametrageDTO.getBase());
            chartDataset.setBackgroundColor(parametrageDTO.getBackgroundColor());
            chartDataset.setBorderColor(parametrageDTO.getBorderColor());
            chartDataset.setBorderWidth(parametrageDTO.getBorderWidth());

            Chart chart = new Chart();
            chart.setDescription(parametrageDTO.getDescription());
            chart.setTitle(parametrageDTO.getTitle());
            chartRepository.save(chart);
            chartDataset.setChart(chart);
            chartDatasetRepository.save(chartDataset);

            return ResponseEntity.ok("modification de parametrage avec succes");

        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }


    }



@PostMapping("/insertchart/{id_chart}")
public ResponseEntity<String> insert_chart_into_dashboard(@PathVariable(name="id_chart")Long id, @RequestBody InsertDashboardDTO insertDashboardDTO){
    Chart chart=chartRepository.findById(id).get();
    String title=insertDashboardDTO.getTitle();
    Long dashboard_id= dashboardRepository.chercherDashboardByTitle(title);
    Dashboard dashboard =dashboardRepository.findById(dashboard_id).get();
    chart.setDashboard(dashboard);
    chartRepository.save(chart);
return ResponseEntity.ok("add successfully");

}


    @Data
    @NoArgsConstructor
    public static class ChartDTO{
        private Type type;
        private String title ;
        private String description;
    }
    @Data
    @NoArgsConstructor
    public static class InsertDashboardDTO{
        private String title ;

    }
    @Data
    @NoArgsConstructor
    public static class ParametrageDTO{
        private String description;
        private String title;
        private String label;
        private String xAxisID;
        private String yAxisID;
        private String stack;
        private String indexAxis;
        private int borderRadius ;
        private int base;
        private Color backgroundColor ;
        private Color borderColor ;
        private int borderWidth ;

    }
}
