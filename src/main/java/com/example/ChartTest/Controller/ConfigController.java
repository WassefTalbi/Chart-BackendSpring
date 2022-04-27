package com.example.ChartTest.Controller;

import com.example.ChartTest.DAO.*;
import com.example.ChartTest.Entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;


@RestController
@Transactional
@CrossOrigin("*")
public class ConfigController {
@Autowired
private ConfigRepository configRepository ;
@Autowired
private ChartRepository chartRepository;
@Autowired
private LabelRepository labelRepository ;
    @Autowired
    private DataPointRepository dataPointRepository ;

    @Autowired
    private ChartDatasetRepository chartDatasetRepository;
    @CrossOrigin("*")
@PostMapping("/config/{id}")
    public Chart connect_Chart_To_Database(@PathVariable(name ="id") Long id, @RequestBody ConfigDTO configDTO )  {
try{

    Chart chart = chartRepository.findById(id).get();
    Config config =new Config();
    config.setUrl(configDTO.getUrl());
    config.setUsername(configDTO.getUsername());
    config.setPassword(configDTO.getPassword());
    config.setDriverClass(configDTO.getDriverClass());
    configRepository.save(config);
    chart.setConfig(config);
    chartRepository.save(chart);

    String url=configDTO.getUrl();
    String driverClass=configDTO.getDriverClass();
    String username=configDTO.getUsername();
    String password=configDTO.getPassword();
    System.out.println("how to connect to data base next step connect");
    System.out.println(driverClass);
if("mysql".equals(driverClass)){
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con= DriverManager.getConnection(url,username,password);
    System.out.println("connected successfully to mySql Data Base");
    return chart;

}

else if ("oracle".equals(driverClass)){
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con= DriverManager.getConnection(url,username,password);
    System.out.println("connected successfully to oracle Data Base");
    return chart;
}
else{
    return null;
}




}catch (Exception e){
    return null;
}


}


    @CrossOrigin("*")
@PostMapping ("/LoadData/{id_chart}")
public void  LoadData(@PathVariable(name ="id_chart") Long id , @RequestBody SqlDTO sqlDTO )throws ClassNotFoundException, SQLException {
   try {
       Chart chart = chartRepository.findById(id).get();

       Config conf= chart.getConfig();
       String driverClass=conf.getDriverClass();
       String url= conf.getUrl();
       String password=  conf.getPassword();
       String username= conf.getUsername();
       if("mysql".equals(driverClass)){
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con= DriverManager.getConnection(url,username,password);
           Statement st= con.createStatement();
           String query=sqlDTO.getQuery();


           ResultSet rs=st.executeQuery(query);
           ResultSetMetaData resultSetMetaData= rs.getMetaData();
           chart.setDecsql(query);
           int column_count=resultSetMetaData.getColumnCount();
           System.out.println("Number of columns in this query :   "+column_count);

           Collection<Label> labelList =new ArrayList();
           while(rs.next()){
               String label_name= rs.getString(1);
               Label label =new Label();
               label.setDescLabel(label_name);
               labelList.add(label);
               label.setChart(chart);
               labelRepository.save(label);
           }
           chart.setLabels(labelList);
           chartRepository.save(chart);

           Collection<ChartDataset> datasetList=new ArrayList();
           for(int i=2;i<=column_count;i++){
               Statement statement= con.createStatement();
               ResultSet resultSet=statement.executeQuery(query);

               Collection<DataPoint> datapointList =new ArrayList();
               ChartDataset chartDataset =new ChartDataset();
               chartDataset.setLabel("dataset"+(i-1));
               chartDataset.setChart(chart);
               datasetList.add(chartDataset);
               while(resultSet.next()){
                   Integer valeur_dataPoint=resultSet.getInt(i);
                   System.out.println("dapoint valeur is "+valeur_dataPoint);
                   DataPoint dataPoint=new DataPoint();
                   dataPoint.setValeur(valeur_dataPoint);
                   dataPoint.setChartDataset(chartDataset);
                   dataPointRepository.save(dataPoint);
                   datapointList.add(dataPoint);
                   System.out.println(datapointList);

               }
               statement.close();
               con.close();
               chartDataset.setDataPoints(datapointList);

               chartDatasetRepository.save(chartDataset);


               System.out.println(datasetList);
               chart.setDatasets(datasetList);
               chartRepository.save(chart);



           }
          // return ResponseEntity.ok(labelList);
       }

       else if("oracle".equals(driverClass)){

           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con= DriverManager.getConnection(url,username,password);
           Statement st= con.createStatement();
           String query=sqlDTO.getQuery();
           chart.setDecsql(query);

           ResultSet rs=st.executeQuery(query);
           ResultSetMetaData resultSetMetaData= rs.getMetaData();
           int column_count=resultSetMetaData.getColumnCount();
           System.out.println("Number of columns in this query :   "+column_count);

           Collection<Label> labelList =new ArrayList();
           while(rs.next()){
               String label_name= rs.getString(1);
               Label label =new Label();
               label.setDescLabel(label_name);
               labelList.add(label);
               label.setChart(chart);
               labelRepository.save(label);
           }
           chart.setLabels(labelList);
           chartRepository.save(chart);

           Collection<ChartDataset> datasetList=new ArrayList();
           for(int i=2;i<=column_count;i++){
               Statement statement= con.createStatement();
               ResultSet resultSet=statement.executeQuery(query);

               Collection<DataPoint> datapointList =new ArrayList();
               ChartDataset chartDataset =new ChartDataset();
               chartDataset.setLabel("dataset"+(i-1));
               chartDataset.setChart(chart);
               datasetList.add(chartDataset);
               while(resultSet.next()){
                   Integer valeur_dataPoint=resultSet.getInt(i);
                   System.out.println("dapoint valeur is "+valeur_dataPoint);
                   DataPoint dataPoint=new DataPoint();
                   dataPoint.setValeur(valeur_dataPoint);
                   dataPoint.setChartDataset(chartDataset);
                   dataPointRepository.save(dataPoint);
                   datapointList.add(dataPoint);
                   System.out.println(datapointList);

               }
               statement.close();
               con.close();
               chartDataset.setDataPoints(datapointList);

               chartDatasetRepository.save(chartDataset);


               System.out.println(datasetList);
               chart.setDatasets(datasetList);
               chartRepository.save(chart);



           }
         //  return ResponseEntity.ok(labelList);
       }
else{
  //  return ResponseEntity.ok("no data load ");
       }


   }catch (Exception e){
       System.out.println(e.getMessage());
      // return ResponseEntity.badRequest()
              // .body(e.getMessage());


   }






}





//DTO
@Data
@NoArgsConstructor
public static class ConfigDTO{
    private String driverClass;
   private  String username;
   private String password;
   private String url ;
}

    @Data
    @NoArgsConstructor
    public static class SqlDTO{
        private String query ;
    }

}
