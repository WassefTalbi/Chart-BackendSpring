package com.example.ChartTest.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChartDataset implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @OneToMany(mappedBy = "chartDataset" ,cascade = CascadeType.ALL)
    protected Collection<DataPoint> dataPoints;
    @ManyToOne
    private Chart chart ;


}
