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

/**
 * 사용자 Dao 클래스입니다.
 * 사용자 관련 기능을 제공합니다.
 */
@Repository
@RequiredArgsConstructor
public class UsersDao {
	
	/**
	 * JdbcTemplate 객체입니다.
	 */
	private final JdbcTemplate jdbcTemplate;
	
	/**
	 * 로그인 메서드입니다.
	 * @return 유저 정보
	 */
	public ResUsers login(ReqUsers reqUser) {
		List<ResUsers> results = jdbcTemplate.query(
				"SELECT * FROM users WHERE id = ? AND pw = ?",
				new RowMapper<>() {
					@Override
					public ResUsers mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						ResUsers resUser = new ResUsers();
						resUser.setId(resultSet.getString("id"));
						resUser.setPw(resultSet.getString("pw"));
						return resUser;
					}
				},reqUser.getId(),reqUser.getPw());
		return results.isEmpty() ? null : results.get(0);
	}
	
	/**
	 * 유저 수정 메서드입니다.
	 * @return 수정 성공 여부
	 */
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
	
	/**
	 * 회원가입 메서드입니다.
	 * @return 회원가입 성공 여부
	 */
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
	
	/**
	 * 유저 정보 조회 메서드입니다.
	 * @return 유저정보
	 */
	public ResUsers selectUser(String id) {
		List<ResUsers> results = jdbcTemplate.query(
				"SELECT * FROM users WHERE id = ?",
				new RowMapper<>() {
					@Override
					public ResUsers mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						ResUsers resUser = new ResUsers();
						resUser.setId(resultSet.getString("id"));
						resUser.setUserName("username");
						resUser.setPw(resultSet.getString("pw"));
						resUser.setEmail(resultSet.getString("email"));
						resUser.setUserTp(resultSet.getInt("userTp"));
						resUser.setUserSt(resultSet.getInt("userSt"));
						return resUser;
					}
				},id);
		return results.isEmpty() ? null : results.get(0);
	}
	
	/**
	 * 모든 유저 정보 조회 메서드입니다.
	 * @return 모든 유저정보
	 */
	public List<Map<String, Object>> selectAllUser() {
		
		List<Map<String, Object>> results = jdbcTemplate.query(
				"SELECT * FROM users",
				new RowMapper<>() {
					@Override
					public Map<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						Map<String, Object> map = new HashMap<>();
						map.put("id", resultSet.getString("id"));
						map.put("username", resultSet.getString("username"));
						map.put("pw", resultSet.getString("pw"));
						map.put("email", resultSet.getString("email"));
						map.put("userTp", resultSet.getInt("userTp"));
						map.put("userSt", resultSet.getInt("userSt"));
						map.put("regr", resultSet.getString("regr"));
						map.put("modr", resultSet.getString("modr"));
						map.put("regDtime", resultSet.getTimestamp("regDtime").toLocalDateTime());
						map.put("modDtime", resultSet.getTimestamp("modDtime").toLocalDateTime());
						return map;
						
					}
				});
		return results;
	}
	

}
