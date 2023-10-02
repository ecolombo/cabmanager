package com.edesign.cabmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edesign.cabmanager.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	boolean existsByName(String name);

	Category findByName(String name);

}
