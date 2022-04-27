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
public class Label implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descLabel;
    @ManyToOne
    private Chart chart;

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", descLabel='" + descLabel + '\'' +
                ", chart=" + chart +
                '}';
    }


}
