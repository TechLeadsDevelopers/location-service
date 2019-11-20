package com.location.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.location.app.model.Location;
import com.location.app.service.LocationService;

@RestController
public class LocationController {
	@Autowired
	private LocationService locationService;

	@RequestMapping(value = "/locations/{id}", method = RequestMethod.GET)
	public ResponseEntity<Location> findLocationById(@PathVariable("id") Long id) throws Exception {
		try {
			Location location = locationService.findById(id);
			if (location.getId() == 0) {
				return new ResponseEntity<Location>(location, HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<Location>(location, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}
}
