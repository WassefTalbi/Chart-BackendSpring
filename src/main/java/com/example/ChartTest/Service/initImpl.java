package com.example.ChartTest.Service;

import com.example.ChartTest.DAO.ChartRepository;
import com.example.ChartTest.DAO.DashboardRepository;
import com.example.ChartTest.DAO.LabelRepository;
import com.example.ChartTest.Entity.Chart;
import com.example.ChartTest.Entity.Dashboard;
import com.example.ChartTest.Entity.Label;
import com.example.ChartTest.Entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Stream;
@Service
@Transactional
public class initImpl implements init{

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private ChartRepository chartRepository;
    @Autowired
    private LabelRepository labelRepository;
    @Override
    public void initDashboard() {
        Stream.of("finance","football","handball").forEach(d->{
            Dashboard dashboard=new Dashboard();
            dashboard.setDescription(d);
            dashboardRepository.save(dashboard);
        });
    }

    @Override
    public void initChart() {
 dashboardRepository.findAll().forEach(d->{
    Chart chart =new Chart();
    chart.setDashboard(d);
    chart.setType(Type.bar);
    chartRepository.save(chart);


});
    }

    @Override
    public void initLabel() {
        chartRepository.findAll().forEach(c->{
            Stream.of("Tunis","Ariana","Medenine","Sfax").forEach(l->{
                Label label=new Label();
                label.setDescLabel(l);
                label.setChart(c);
                labelRepository.save(label);

            });
        });


    }

    @Override
    public void initDataset() {

    }
}
