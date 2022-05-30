package com.example.ChartTest.DTO;


import com.example.ChartTest.Entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data @NoArgsConstructor @AllArgsConstructor
public class ChartDashboard {
   private String title;
   private Long id;
   private Type type;
   private Collection<DatasetDTO>valeurs;
   private Collection<String>labels;

}
