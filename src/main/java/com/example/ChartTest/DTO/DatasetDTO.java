package com.example.ChartTest.DTO;

import com.example.ChartTest.Entity.DataPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Collection;
@Data @AllArgsConstructor @NoArgsConstructor
public class DatasetDTO {
    private Long id;
private String name;
private String backgroundColor;
private Collection<Integer> dataPoints;

}
