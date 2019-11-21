package com.location.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<Location> findLocationById(@PathVariable("id") Long id) {
		Location location = new Location();
		try {
			location = locationService.findById(id);
			if (location.getId() == 0) {
				return new ResponseEntity<Location>(location, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Location>(location, HttpStatus.OK);
	}

	@RequestMapping(value = "/locations", method = RequestMethod.POST)
	public ResponseEntity<Location> saveLocation(@RequestBody Location location) {
		try {
			location = locationService.save(location);
			if (location.getId() == 0) {
				return new ResponseEntity<Location>(location, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Location>(location, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> findAllLocations() throws Exception {
		List<Location> locations = new ArrayList<Location>();
		try {
			locations = locationService.findAll();
			if (locations.size() == 0) {
				return new ResponseEntity<List<Location>>(locations, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			throw e;
		}
		return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
	}

	@RequestMapping(value = "/locations/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Location> updateById(@PathVariable("id") Long id, @RequestBody Location location) {
		try {
			location = locationService.updateById(id, location);
			if (location.getId() == 0) {
				return new ResponseEntity<Location>(new Location(), HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Location>(location, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/locations/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Location>> deleteLocationById(@PathVariable("id") Long id) {
		List<Location> locations = new ArrayList<Location>();
		try {
			locations = locationService.deleteById(id);
			if (locations.size() == 0) {
				return new ResponseEntity<List<Location>>(locations, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
	}
	

}
