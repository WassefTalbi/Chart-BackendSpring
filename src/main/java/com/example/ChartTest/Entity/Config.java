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
public class Config implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String driverClass;
    private String url;
    private String username;
    private String password;
    @OneToOne(mappedBy ="config",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private Chart chart;


}
