package com.location.app.repository;

import java.util.List;

import com.location.app.model.Location;

public interface LocationDao {
	public int save(Location location) throws Exception;
	public Location findById(Long id) throws Exception;
	public Location findByName(String name) throws Exception;
	public List<Location> findAll() throws Exception;
	public int updateById(Long id,Location location) throws Exception;
	public int deleteById(Long id) throws Exception;
	public Location getCSMTRByMaxId() throws Exception;
}
