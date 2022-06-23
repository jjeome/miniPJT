package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VO.WatchSave;
import common.DAO;

public class WatchSaveDAO extends DAO{
	//싱글톤
	private static WatchSaveDAO watchsaveDAO = null;
	private WatchSaveDAO() {}
	public static WatchSaveDAO getInstance() {
		if(watchsaveDAO == null) {
			watchsaveDAO = new WatchSaveDAO();
		}
		return watchsaveDAO;
	}

	//시청했던 영화 전체조회
	public List<WatchSave> selectWatchedMovie(){
		List<WatchSave> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT b.member_id, m.movie_name FROM members b JOIN movies m ON(b.movie_name = m.movie_name)";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				WatchSave ws = new WatchSave();
				ws.setMemberId(rs.getString("member_id"));
				ws.setMovieName(rs.getString("member_name"));
				
				list.add(ws);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//보고싶은 영화 전체조회
	public List<WatchSave> wantMovie(){
		List<WatchSave> list = new ArrayList<>();
		
		try {
			connect();
			String sql = "SELECT b.member_id, m.movie_name FROM members b JOIN movies m ON (b.movie_name = m.movie_name)";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				WatchSave ws = new WatchSave();
				ws.setMemberId(rs.getString("member_id"));
				ws.setMovieName(rs.getString("member_name"));
				
				list.add(ws);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
