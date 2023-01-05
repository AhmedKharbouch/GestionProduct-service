package com.example.Gestion_Productservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   //@JsonProperty("ID")
    Long id;
    //@JsonProperty("LABEL")
    String label;
    float prix_HT;
    float prix_TT;
    double quantite;
    Date createdAt;
    Date modifiedAt;

    @ManyToOne
    Category category;
}
