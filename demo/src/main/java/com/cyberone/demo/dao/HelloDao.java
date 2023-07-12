package com.cyberone.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cyberone.demo.model.request.ReqUsers;
import com.cyberone.demo.model.response.ResUsers;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HelloDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public ResUsers getUsers(ReqUsers reqLogin) {
		List<ResUsers> results = jdbcTemplate.query(
				"SELECT * FROM users WHERE id = ? AND pw = ?",
				new RowMapper<ResUsers>() {
					public ResUsers mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResUsers resLogin = new ResUsers();
						resLogin.setId(rs.getString("id"));
						resLogin.setPw(rs.getString("pw"));
						return resLogin;
					}
				},reqLogin.getId(),reqLogin.getPw());
		return results.isEmpty() ? null : results.get(0);
	}
}
