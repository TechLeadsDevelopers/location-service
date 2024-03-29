package com.location.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.location.app.commons.DBQueries;
import com.location.app.model.Location;

@Repository
public class LocationDaoImpl implements LocationDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int save(Location location) throws Exception {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("name", location.getName())
					.addValue("description", location.getDescription())
					.addValue("createdate", new Timestamp(new Date().getTime()))
					.addValue("createduser", location.getCreatedUser())
					.addValue("lastmodified", new Timestamp(new Date().getTime()))
					.addValue("updateduser", location.getUpdatedUser());

			int count = namedParameterJdbcTemplate.update(DBQueries.INSERT_LOCTN, paramSource, keyHolder,
					new String[] { "ID" });
			long num = keyHolder.getKey().longValue();
			location.setId(num);
			return count;
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Location findById(Long id) throws Exception {
		try {
			Location location = new Location();
			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("id", id);
			namedParameterJdbcTemplate.query(DBQueries.SELECT_LOCTN_BY_ID, paramSource, new ResultSetExtractor<Location>() {

				@Override
				public Location extractData(ResultSet rs) throws SQLException, DataAccessException {
					while (rs.next()) {
						location.setId(rs.getLong("ID"));
						location.setName(rs.getString("NAME"));
						location.setDescription(rs.getString("DESCRIPTION"));
						location.setCreatedate(rs.getTimestamp("CREATEDATE"));
						location.setCreatedUser(rs.getString("CREATEDUSER"));
						location.setLastmodified(rs.getTimestamp("LASTMODIFIED"));
						location.setUpdatedUser(rs.getString("UPDATEDUSER"));

					}
					return location;
				}
			});
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
			SqlParameterSource paramSource = new MapSqlParameterSource();
			List<Location> locations=new ArrayList<Location>();
			namedParameterJdbcTemplate.query(DBQueries.SELECT_ALL_LOCTN, paramSource, new ResultSetExtractor<List<Location>>() {

				@Override
				public List<Location> extractData(ResultSet rs) throws SQLException, DataAccessException {
					while(rs.next()) {
						Location location=new Location();
						location.setId(rs.getLong("ID"));
						location.setName(rs.getString("NAME"));
						location.setDescription(rs.getString("DESCRIPTION"));
						location.setCreatedate(rs.getTimestamp("CREATEDATE"));
						location.setCreatedUser(rs.getString("CREATEDUSER"));
						location.setLastmodified(rs.getTimestamp("LASTMODIFIED"));
						location.setUpdatedUser(rs.getString("UPDATEDUSER"));
						locations.add(location);
					}
					return locations;
				}
			});
			return locations;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int updateById(Long id, Location location) throws Exception {

		try {
			SqlParameterSource paramSource = new MapSqlParameterSource().addValue("name", location.getName())
					.addValue("description", location.getDescription())
					.addValue("lastmodified", new Timestamp(new Date().getTime()))
					.addValue("updateduser", location.getUpdatedUser())
					.addValue("id", id);
			int count = namedParameterJdbcTemplate.update(DBQueries.UPDTE_LOCTN_BY_ID, paramSource);
			return count;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int deleteById(Long id) throws Exception {
		try {
			SqlParameterSource paramSource=new MapSqlParameterSource()
					.addValue("id", id);
			
			int count=namedParameterJdbcTemplate.update(DBQueries.DELTE_LOCTN_BY_ID, paramSource);
			return count;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Location getCSMTRByMaxId() throws Exception {
		return null;
	}

}
