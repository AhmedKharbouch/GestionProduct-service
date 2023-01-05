package com.example.Gestion_Productservice.repositories;

import com.example.Gestion_Productservice.entities.Category;
import com.example.Gestion_Productservice.entities.Rules;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RulesRepository extends JpaRepository<Rules,Long> {


}
