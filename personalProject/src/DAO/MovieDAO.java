
package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VO.Movie;
import common.DAO;

public class MovieDAO extends DAO {
	//싱글톤
	private static MovieDAO movieDAO = null;
	private MovieDAO() {}
	public static MovieDAO getInstance() {
		if(movieDAO == null) {
			movieDAO = new MovieDAO();
		}
		return movieDAO;
	}
	
	//등록
	public void insert(Movie movie) {
		try {
			connect();
			String sql = "INSERT INTO movies (movie_no, movie_name, movie_date, movie_genre, movie_nation, movie_keyword)"
						+ " VALUES(seq_movie_no.nextval, ?, ?, ? ,? ,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovieName());
			pstmt.setString(2, movie.getMovieDate());
			pstmt.setString(3, movie.getMovieGenre());
			pstmt.setString(4, movie.getMovieNation());
			pstmt.setString(5, movie.getMovieKeyword());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("영화 등록 완료.");
			} else {
				System.out.println("다시 영화를 등록해주십시오.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//수정
	public void update(Movie movie) {
		try {
			connect();
			String sql = "UPDATE movies SET movie_keyword = ? WHERE movie_name = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movie.getMovieKeyword());
			pstmt.setString(2, movie.getMovieName());
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("키워드가 수정되었습니다.");
			} else {
				System.out.println("다시 수정해주십시오.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//장르별검색
	public List<Movie> serchCategoryInfo(String movieGenre) {
		List<Movie> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT movie_name, movie_date, movie_genre, movie_nation, movie_keyword FROM movies WHERE movie_genre LIKE '%"+movieGenre+"%' AND abled = 0";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Movie movie = new Movie();
				
				movie.setMovieName(rs.getString("movie_name"));
				movie.setMovieDate(rs.getString("movie_date"));
				movie.setMovieGenre(rs.getString("movie_genre"));
				movie.setMovieNation(rs.getString("movie_nation"));
				movie.setMovieKeyword(rs.getString("movie_keyword"));
				
				list.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//국가별 검색
	public List<Movie> serchNationInfo(String movieNation) {
		List<Movie> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT movie_name, movie_date, movie_genre, movie_nation, movie_keyword FROM movies WHERE movie_nation LIKE '%"+movieNation+"%' AND abled = 0";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rs.getString("movie_name"));
				movie.setMovieDate(rs.getString("movie_date"));
				movie.setMovieGenre(rs.getString("movie_genre"));
				movie.setMovieNation(rs.getString("movie_nation"));
				movie.setMovieKeyword(rs.getString("movie_keyword"));
				
				list.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
	//삭제
	public void delete(String movieName) {
		try {
			connect();
			String sql = "UPDATE movies SET abled = 1 WHERE movie_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, movieName);
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println(movieName+"이/가 삭제되었습니다.");
			} else {
				System.out.println("다시 삭제해주십시오.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	// 이름조회
	public Movie selectOne(String movieName) {
		Movie movie = null;
		try {
			connect();
			String sql = "SELECT movie_name, movie_date, movie_genre, movie_nation, movie_keyword FROM movies WHERE movie_name = '"+movieName +"' AND abled = 0";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				movie = new Movie();
				movie.setMovieName(rs.getString("movie_name"));
				movie.setMovieDate(rs.getString("movie_date"));
				movie.setMovieGenre(rs.getString("movie_genre"));
				movie.setMovieNation(rs.getString("movie_nation"));
				movie.setMovieKeyword(rs.getString("movie_keyword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
 		} finally {
			disconnect();
		}
		return movie;
	}
	
	//단건조회 - 이름존재 유무
	public boolean checkName(String movieName) {
		boolean isSelected = false;
		try {
			connect();
			String sql = "SELECT COUNT(*) AS count FROM movies WHERE movie_name = '"+movieName +"'";
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				if(rs.getInt("count")>0) {
					isSelected = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
 		} finally {
			disconnect();
		}
		return isSelected;
	}
	
	
	// 키워드 조회
	public List<Movie> serchKeyword(String movieKeyword){
		List<Movie> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT movie_name, movie_date, movie_genre, movie_nation, movie_keyword FROM movies WHERE movie_keyword LIKE '%"+movieKeyword+"%' AND abled = 0";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rs.getString("movie_name"));
				movie.setMovieDate(rs.getString("movie_date"));
				movie.setMovieGenre(rs.getString("movie_genre"));
				movie.setMovieNation(rs.getString("movie_nation"));
				movie.setMovieKeyword(rs.getString("movie_keyword"));
			
				list.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	// 전체조회
	public List<Movie> selectAll(){
		List<Movie> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT movie_name, movie_date, movie_genre, movie_nation, movie_keyword FROM movies WHERE abled = 0 ORDER BY movie_no";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setMovieName(rs.getString("movie_name"));
				movie.setMovieDate(rs.getString("movie_date"));
				movie.setMovieGenre(rs.getString("movie_genre"));
				movie.setMovieNation(rs.getString("movie_nation"));
				movie.setMovieKeyword(rs.getString("movie_keyword"));
			
				list.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}