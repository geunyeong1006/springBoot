package com.cyberone.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public ResUsers login(ReqUsers reqUser) {
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
	
	public int updateUser(ReqUsers reqUsers) {
		List<Object> params = new ArrayList<>();
		
		String sql = "UPDATE	users\r\n";
		sql += "	  SET		           \r\n";
		if(reqUsers.getPw() != null && reqUsers.getModr() != "") {
			sql += "	     	pw = ?,\r\n";
			params.add(reqUsers.getPw());
		}
		
		if(reqUsers.getEmail() != null && reqUsers.getModr() != "") {
			sql += "			email = ?,\r\n";
			params.add(reqUsers.getEmail());
		}
		if(reqUsers.getModr() != null && reqUsers.getModr() != "") {
			sql += "			modr = ?,\r\n";
			params.add(reqUsers.getModr());
		}
		
		sql += "   		 	modDtime = NOW()";
		sql += "   			WHERE 	id = ?";
		params.add(reqUsers.getId());
        return jdbcTemplate.update(sql, params.toArray());
	}
	
	public int signup(ReqUsers reqUser) {
		List<Object> params = new ArrayList<>();
		params.add(reqUser.getId());
		params.add(reqUser.getUserName());
		params.add(reqUser.getPw());
		params.add(reqUser.getEmail());
		params.add(reqUser.getRegr());
		String sql = "INSERT INTO users(id, username, pw, email, userTp, userSt, regr)\r\n"
	    		+ "        VALUES (?, ?, ?, ?, 1, 1, ?)";
		return jdbcTemplate.update(sql, params.toArray());
	}
	
	public ResUsers confirmUser(String id) {
		List<ResUsers> results = jdbcTemplate.query(
				"SELECT * FROM users WHERE id = ?",
				new RowMapper<ResUsers>() {
					public ResUsers mapRow(ResultSet rs, int rowNum) throws SQLException {
						ResUsers resUser = new ResUsers();
						resUser.setId(rs.getString("id"));
						resUser.setUserName("username");
						resUser.setPw(rs.getString("pw"));
						resUser.setEmail(rs.getString("email"));
						resUser.setUserTp(rs.getInt("userTp"));;
						resUser.setUserSt(rs.getInt("userSt"));
						return resUser;
					}
				},id);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Map<String, Object>> selectAllUser() {
		List<Map<String, Object>> listMap = new ArrayList<>();
		
		List<Map<String, Object>> results = jdbcTemplate.query(
				"SELECT * FROM users",
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, Object> map = new HashMap<>();
						map.put("id", rs.getString("id"));
						map.put("username", rs.getString("username"));
						map.put("pw", rs.getString("pw"));
						map.put("email", rs.getString("email"));
						map.put("userTp", rs.getInt("userTp"));
						map.put("userSt", rs.getInt("userSt"));
						map.put("regr", rs.getString("regr"));
						map.put("modr", rs.getString("modr"));
						map.put("regDtime", rs.getTimestamp("regDtime").toLocalDateTime());
						map.put("modDtime", rs.getTimestamp("modDtime").toLocalDateTime());
						listMap.add(map);
						return null;
						
					}
				});
		return listMap;
	}
	

}
