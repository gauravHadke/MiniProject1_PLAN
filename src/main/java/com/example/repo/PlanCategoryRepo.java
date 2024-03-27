package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

//no need to write implementation of repo interface JPA will implemented internally  automatically 
import org.springframework.stereotype.Repository;

import com.example.entity.PlanCategory;

@Repository
public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{

}
