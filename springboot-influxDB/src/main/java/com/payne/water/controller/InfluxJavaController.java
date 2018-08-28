package com.payne.water.controller;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class InfluxJavaController {
	
	@Value("${spring.influx.url}")
	private String infulxUrl;
	
	@GetMapping("/query/{table}")
	public Object query(@PathVariable String table){
		InfluxDB influxDB = InfluxDBFactory.connect(infulxUrl);
		Query query = new Query("SELECT * FROM "+table, "NOAA_water_database");
        return influxDB.query(query);
    }

}
