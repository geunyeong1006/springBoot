package com.cyberone.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cyberone.demo.model.request.ReqUsers;
import com.cyberone.demo.model.response.ResUsers;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsersDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public ResUsers getUsers(ReqUsers reqUser) {
		List<ResUsers> results = jdbcTemplate.query(
				"SELECT * FROM users WHERE id = ? AND pw = ?",
				new RowMapper<ResUsers>() {
					public ResUsers mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResUsers resLogin = new ResUsers();
						resLogin.setId(rs.getString("id"));
						resLogin.setPw(rs.getString("pw"));
						return resLogin;
					}
				},reqUser.getId(),reqUser.getPw());
		return results.isEmpty() ? null : results.get(0);
	}
	
	public int signup(ReqUsers reqUser) {
		List<Object> params = new ArrayList<>();
		params.add(reqUser.getId());
		params.add(reqUser.getUserName());
		params.add(reqUser.getPw());
		params.add(reqUser.getEmail());
		String sql = "INSERT INTO users(id, username, pw, email, userTp, userSt)\r\n"
	    		+ "        VALUES (?,,? ?, ?, 1, 1)";
		return jdbcTemplate.update(sql, params.toArray());
	}
	
	public ResUsers confirmUser(String id) {
		List<ResUsers> results = jdbcTemplate.query(
				"SELECT * FROM users WHERE id = ?",
				new RowMapper<ResUsers>() {
					public ResUsers mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResUsers resUser = new ResUsers();
						resUser.setId(rs.getString("id"));
						resUser.setUserName(null);
						resUser.setPw(rs.getString("pw"));
						resUser.setEmail(rs.getString("email"));
						resUser.setUserTp(rs.getInt("userTp"));;
						resUser.setUserSt(rs.getInt("userSt"));
						return resUser;
					}
				},id);
		return results.isEmpty() ? null : results.get(0);
	}
	

}
