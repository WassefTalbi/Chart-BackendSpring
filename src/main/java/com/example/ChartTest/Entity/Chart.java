package com.example.ChartTest.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.ORDINAL)
   private Type type;
    private String title;
    private String description;
    private String decsql;
   @ManyToOne
   private Dashboard dashboard ;

   @OneToMany(mappedBy = "chart",cascade = CascadeType.ALL)
   private Collection<Label> labels=new ArrayList<>();

    @OneToMany(mappedBy = "chart",cascade = CascadeType.ALL)
    private Collection<ChartDataset> datasets;
@OneToOne
   private Config config;



}
