package com.edesign.cabmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edesign.cabmanager.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

	boolean existsByName(String name);

	Location findByName(String name);

}
