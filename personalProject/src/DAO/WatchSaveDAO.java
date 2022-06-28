package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VO.Member;
import VO.Movie;
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
	

	//담은 영화 전체조회
	public List<String> saveMovie(String memberId){
		List<String> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT movie_name FROM wanted WHERE member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rs.getString("movie_name"));
				
				list.add(movie.getMovieName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//시청 내역 조회
	public List<String> watchedMovie(String memberId){
		List<String> list = new ArrayList<>();
		
		try {
			connect();
			String sql = "SELECT movie_name FROM watched WHERE member_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rs.getString("movie_name"));
				list.add(movie.getMovieName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//보고싶은 영화 담기
	public void insertWantMovie(Movie movie, Member member) {
		try {
			connect();
			String sql = "INSERT INTO wanted (movie_name, member_id) VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovieName());
			pstmt.setString(2, member.getMemberId());
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("영화가 담아졌습니다.");
			} else {
				System.out.println("영화 담기에 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//시청 내역 담기
		public void insertWatchedMovie(Movie movie, Member member) {
			try {
				connect();
				String sql = "INSERT INTO watched (movie_name, member_id) VALUES (?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, movie.getMovieName());
				pstmt.setString(2, member.getMemberId());
				
				int result = pstmt.executeUpdate();
				
				if(result>0) {
					System.out.println("영화 시청을 시작합니다.");
				} else {
					System.out.println("영화 시청에 실패했습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
