package com.example.Gestion_Productservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String rules;
    private double support;
    private double confidence;
    private double coverage;
    private double lift;
    private int count;

}
