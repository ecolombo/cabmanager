package com.edesign.cabmanager.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Use Pseudo-Mercator in [m] for Simplicity 
// https://epsg.io/transform#s_srs=4326&t_srs=3857&x=NaN&y=NaN
// Firenze Piazza del Duomo 43.773108810883976, 11.255494171775753
// 4872800.183266161, 1261093.2739237915
// Firenze Palazzo Pitti 43.765168736815504, 11.250083202646069
// 4871916.298263998 1260479.1209865198
// Firenze Piazza della Signoria 43.769801988083465, 11.255866047394946
// 4872432.069435865 1261135.482776169

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
@SequenceGenerator(name="location_seq", sequenceName = "location_seq", initialValue =1, allocationSize = 1 )
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
	
	@Column(name="location_id")
	private int locationId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="xcoord")
	private Double xcoord;
	
	@Column(name="ycoord")
	private Double ycoord;
	

}
