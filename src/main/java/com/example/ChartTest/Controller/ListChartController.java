package com.example.ChartTest.Controller;

import com.example.ChartTest.DAO.ChartDatasetRepository;
import com.example.ChartTest.DAO.ChartRepository;
import com.example.ChartTest.DTO.ChartDashboard;
import com.example.ChartTest.DTO.DatasetDTO;
import com.example.ChartTest.Entity.Chart;
import com.example.ChartTest.Entity.ChartDataset;
import com.example.ChartTest.Entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ListChartController {
    @Autowired
    private ChartRepository chartRepository;
    @Autowired
    private ChartDatasetRepository chartDatasetRepository;
    @CrossOrigin("*")
    @GetMapping("/listChart")
    public List<ChartDashboard> listChart(){
        Collection<Chart> charts= chartRepository.findAll();
        List<ChartDashboard> chartDashbord_List=new ArrayList<>();
        charts.forEach(c->{
            ChartDashboard chartDashboard=new ChartDashboard();
            String title= c.getTitle();
            Type type= c.getType();
            Long id=c.getId();
            List<String> labels_Name=new ArrayList<>();
            c.getLabels().forEach(label->{
                String lab= label.getDescLabel();
                labels_Name.add(lab);
            });
            List<DatasetDTO> dataset_List=new ArrayList<>();
            c.getDatasets().forEach(ds->{
                DatasetDTO datasetDTO=new DatasetDTO();
                String  label_Dataset=  ds.getLabel();
                Long id_dataset=ds.getId();
                String backgroundColor= ds.getBackgroundColor();
                List<Integer> valeurs =new ArrayList<>();
                ds.getDataPoints().forEach(dataPoint -> {
                    Integer val= dataPoint.getValeur();
                    valeurs.add(val);
                });
                datasetDTO.setName(label_Dataset);
                datasetDTO.setId(id_dataset);
                datasetDTO.setBackgroundColor(backgroundColor);
                datasetDTO.setDataPoints(valeurs);
                dataset_List.add(datasetDTO);
            });


            chartDashboard.setLabels(labels_Name);
            chartDashboard.setTitle(title);
            chartDashboard.setType(type);
            chartDashboard.setId(id);
            chartDashboard.setValeurs(dataset_List);
            chartDashbord_List.add(chartDashboard);
        });

        return chartDashbord_List;

    }
    @GetMapping("/specChart/{id}")
    public ChartDashboard getChart(@PathVariable(name = "id")Long id) {
        ChartDashboard chartDTO = new ChartDashboard();
        Chart chart = chartRepository.findById(id).get();
        String title = chart.getTitle();
        Type type = chart.getType();
        List<String> labels_Name = new ArrayList<>();
        chart.getLabels().forEach(label -> {
            String lab = label.getDescLabel();
            labels_Name.add(lab);

        });

        List<DatasetDTO> dataset_List = new ArrayList<>();
        chart.getDatasets().forEach(ds -> {
            DatasetDTO datasetDTO = new DatasetDTO();
            Long id_dataset=ds.getId();
            String label_Dataset = ds.getLabel();
            String backgroundColor = ds.getBackgroundColor();
            List<Integer> valeurs = new ArrayList<>();
            ds.getDataPoints().forEach(dataPoint -> {
                Integer val = dataPoint.getValeur();
                valeurs.add(val);
            });
            datasetDTO.setId(id_dataset);
            datasetDTO.setName(label_Dataset);
            datasetDTO.setBackgroundColor(backgroundColor);
            datasetDTO.setDataPoints(valeurs);
            dataset_List.add(datasetDTO);
        });
        chartDTO.setLabels(labels_Name);
        chartDTO.setTitle(title);
        chartDTO.setType(type);
        chartDTO.setId(id);
        chartDTO.setValeurs(dataset_List);
        return chartDTO;

    }
@CrossOrigin("*")
@PostMapping ("/updateChart/{id}")
    public void update_chart(@PathVariable(name ="id") Long id,@RequestBody OptionDTO optionDTO){
    Chart chart = chartRepository.findById(id).get();
 System.out.println(id);
 chart.setTitle(optionDTO.getTitle());
ChartDataset dataset= chartDatasetRepository.findById(optionDTO.getId_dataset()).get();
 dataset.setBackgroundColor(optionDTO.getBackgroundColor());
 dataset.setLabel(optionDTO.getName());
 chartDatasetRepository.save(dataset);
 chartRepository.save(chart);
}

    @Data
    @NoArgsConstructor
    public static class OptionDTO{

        private Long id_dataset;
        private String title;
        private String backgroundColor ;
        private String name;


    }

}
