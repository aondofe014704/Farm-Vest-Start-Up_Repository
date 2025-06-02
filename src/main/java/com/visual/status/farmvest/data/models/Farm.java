package com.visual.status.farmvest.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userId;
    @Column(nullable = false)
    private String farmName;
    @Column(nullable = false)
    private String cropType;
    @Column(nullable = false)
    private String livestockType;
    @Column(nullable = false)
    private double farmSize;
    @Column(nullable = false)
    private  String description;
}
