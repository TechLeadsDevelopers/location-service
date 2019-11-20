package com.location.app.service;

import java.util.List;

import com.location.app.model.Location;

public interface LocationService {
	public Location save(Location location) throws Exception;
	public Location findById(Long id) throws Exception;
	public Location findByName(String name) throws Exception;
	public List<Location> findAll() throws Exception;
	public Location updateById(Long id,Location location) throws Exception;
	public List<Location> deleteById(Long id) throws Exception;
}
