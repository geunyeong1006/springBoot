package com.cyberone.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cyberone.demo.model.request.rss.ReqRss;
import com.cyberone.demo.model.request.rss.ReqRssAddr;
import com.cyberone.demo.model.request.rss.ReqRssAddrList;
import com.cyberone.demo.model.response.ResUsers;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RssNewsDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	public int insertRss(ReqRss reqRss) {
	    String sql = "INSERT INTO rss(rssSrc, rssLink, clippingYn)\r\n"
	    		+ "        VALUES (?, ?, ?)";
	    Object[] params = new Object[] {reqRss.getRssSrc(), reqRss.getRssLink(),reqRss.getClippingYn()};
		return jdbcTemplate.update(sql, params);
		
	};


	public List<Map<String, Object>> selectRssAddrList(ReqRssAddrList reqRssAddrList){
		String sql = "SELECT\r\n"
				+ "			rssId, rssSrc, rssAddr, guidParam, guidLast,\r\n"
				+ "			(SELECT username FROM users C WHERE C.id = A.regr) regr,\r\n"
				+ "			(SELECT username FROM users C WHERE C.id = A.modr) modr,\r\n"
				+ "			DATE_format(regDtime, '%Y-%m-%d %H:%i:%s') as regDtime,\r\n"
				+ "			DATE_format(modDtime, '%Y-%m-%d %H:%i:%s') as modDtime\r\n"
				+ "		FROM rssaddrmgr A\r\n"
				+ "		LIMIT ?, ?"
				;
		List<Map<String, Object>> results = jdbcTemplate.query(
				sql,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, Object> map = new HashMap<>();
						map.put("rssId", rs.getString("rssId"));
						map.put("rssSrc", rs.getString("rssSrc"));
						map.put("rssAddr", rs.getString("rssAddr"));
						map.put("guidParam", rs.getString("guidParam"));
						map.put("guidLast", rs.getString("guidLast"));
						map.put("regr", rs.getString("regr"));
						map.put("modr", rs.getString("modr"));
						map.put("regDtime", rs.getTimestamp("regDtime").toLocalDateTime());
						map.put("modDtime", rs.getTimestamp("modDtime").toLocalDateTime());
						return map;
					}
				},reqRssAddrList.getLimit(),reqRssAddrList.getRows());
		return results.isEmpty() ? null : results;
	};
	
	public int updateRssAddr(ReqRssAddr reqRssAddr) {
		List<Object> params = new ArrayList<>();
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		String sql = "UPDATE	rssaddrmgr\r\n";
		sql += "	  SET		           \r\n";
		if(reqRssAddr.getRssSrc() != null) {
			sql += "	     	rssSrc = ?,\r\n";
			params.add(reqRssAddr.getRssSrc());
		}
		
		if(reqRssAddr.getRssAddr() != null) {
			sql += "			rssAddr = ?,\r\n";
			params.add(reqRssAddr.getRssAddr());
		}
		
		if(reqRssAddr.getGuidLast() != null) {
			sql += "			guidLast = ?,\r\n";
			params.add(reqRssAddr.getGuidLast());
		}
		
		if(reqRssAddr.getGuidParam() != null) {
			sql += "			guidLast = ?,\r\n";
			params.add(reqRssAddr.getGuidParam());
		}

		
		if(reqRssAddr.getModr() != null) {
			sql += "			modr = ?,\r\n";
			params.add(reqRssAddr.getModr());
		}
		
		if(reqRssAddr.getLastCollectCnt() > 0) {
			sql += "			lastCollectDtime = ?,\r\n";
			params.add(reqRssAddr.getLastCollectDtime());
		}
		sql += "   		 	rssId = ?";
		params.add(reqRssAddr.getRssId());
		sql += "   WHERE 	rssId = ?";
		params.add(reqRssAddr.getRssId());
//		Object[] params = new Object[] {reqRssAddr.getRssSrc(), reqRssAddr.getRssAddr(),
//				reqRssAddr.getGuidLast(),reqRssAddr.getGuidParam(),reqRssAddr.getModr(),
//				reqRssAddr.getLastCollectCnt(),reqRssAddr.getLastCollectCnt(),reqRssAddr.getRssId()};
        return jdbcTemplate.update(sql, params.toArray());
	};
	
	public int insertBbsBase(Map<String, Object> paramMap) {
		String sql = "		INSERT INTO bbsbase ( \r\n"
				+ "				bbsTit, bbsCont, qryCnt, bbsSct,   \r\n"
				+ "				bbsFileYn,\r\n"
				+ "				mailSendYn,\r\n"
				+ "				procSt,\r\n"
				+ "				notiDtime,\r\n"
				+ "				regr, modr, regDtime, modDtime)\r\n"
				+ "			VALUES\r\n"
				+ "				( ?, ?, 0, ?,\r\n"
				+ "				?,\r\n"
				+ "				?,\r\n"
				+ "				?,\r\n"
				+ "				?,\r\n"
				+ "				?, ?, NOW(), NOW())";
		Object[] params = new Object[] {paramMap.get("bbsTit"), paramMap.get("bbsCont"),
				paramMap.get("bbsSct"),paramMap.get("bbsFileYn"),paramMap.get("mailSendYn"),
				paramMap.get("procSt"),paramMap.get("notiDtime"),paramMap.get("regr"), paramMap.get("modr")};
		return jdbcTemplate.update(sql, params);
	};
	
	public List<Map<String, Object>> selectRssList(){
		List<Map<String, Object>> listMap = new ArrayList<>();
		String sql = "SELECT\r\n"
				+ "			A.bbsId\r\n"
				+ "			, bbsTit\r\n"
				+ "			, bbsCont\r\n"
				+ "			, qryCnt\r\n"
				+ "			, bbsSct\r\n"
				+ "			, bbsFileYn\r\n"
				+ "			, regr\r\n"
				+ "			, DATE_FORMAT(regDtime, '%Y%m%d') AS regDtime\r\n"
				+ "			, (select acctNm from users where acctId = A.modr) as modr\r\n"
				+ "			, DATE_FORMAT(modDtime, '%Y-%m-%d %H:%i:%s') AS modDtime\r\n"
				+ "			, notiDtime\r\n"
				+ "			, mailSendYn\r\n"
				+ "			, procSt\r\n"
				+ "			, rssSrc\r\n"
				+ "			, rssLink\r\n"
				+ "			, clippingYn\r\n"
				+ "			, rsvYn\r\n"
				+ "			, monthYn\r\n"
				+ "		FROM BbsBaseTbl A, RssTbl B";
		
		List<Map<String, Object>> results = jdbcTemplate.query(
				sql,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, Object> map = new HashMap<>();
						map.put("bbsId", rs.getInt("bbsId"));
						map.put("bbsTit", rs.getString("bbsTit"));
						map.put("bbsCont", rs.getString("bbsCont"));
						map.put("qryCnt", rs.getInt("qryCnt"));
						map.put("bbsSct", rs.getString("bbsSct"));
						map.put("bbsFileYn", rs.getString("bbsFileYn"));
						map.put("regr", rs.getString("regr"));
						map.put("regDtime", rs.getDate("regDtime"));
						map.put("modr", rs.getString("modr"));
						map.put("modDtime", rs.getDate("modDtime"));
						map.put("mailSendYn", rs.getString("mailSendYn"));
						map.put("procSt", rs.getString("procSt"));
						map.put("rssSrc", rs.getString("rssSrc"));
						map.put("rssLink", rs.getString("rssLink"));
						map.put("clippingYn", rs.getString("clippingYn"));
						map.put("rsvYn", rs.getString("rsvYn"));
						map.put("monthYn", rs.getString("monthYn"));
						listMap.add(map);
						return null;
						
					}
				});
		return listMap;
		
	}

}
