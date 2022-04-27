package com.example.ChartTest.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataPoint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int valeur;
    @ManyToOne
    private ChartDataset chartDataset;

    @Override
    public String toString() {
        return "DataPoint{" +
                "id=" + id +
                ", valeur=" + valeur +
                ", chartDataset=" + chartDataset +
                '}';
    }


}
