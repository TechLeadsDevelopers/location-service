package com.location.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.location.app.model.Location;
import com.location.app.repository.LocationDao;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationDao locationDao;

	@Override
	public Location save(Location location) throws Exception {
		int count=locationDao.save(location);
		if(count>0) {
		return findById(location.getId());
		}
		return new Location();
	}

	@Override
	public Location findById(Long id) throws Exception {
		try {
			Location location = locationDao.findById(id);

			return location;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Location findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> findAll() throws Exception {
		try {
			List<Location> locations = locationDao.findAll();
			return locations;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Location updateById(Long id, Location location) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
