package management;

import java.sql.Date;
import java.util.Scanner;

import DAO.MovieDAO;
import VO.Movie;

public class MovieInfoManagement extends Management {
	protected Scanner sc = new Scanner(System.in);
	int menuNo = 0;
	private int role = MovieMemberManagement.LoginInfo.getMemberRole();

	public MovieInfoManagement() {
		while (true) {
		
			menuPrint();
			
			menuNo = selectMenu();

			if(menuNo==1) {
				//영화 조회
				new checkMovie();	
			}else if(menuNo==2) {
				//영화 시청
				new OnlyMember();
			} else if(menuNo==3 && role == 0) {
				//영화 정보 등록
				insertMovieInfo();
			} else if(menuNo==4 && role == 0) {
				//영화 삭제
				deleteMovie();
			} else if(menuNo==5 && role == 0) {
				//영화 정보 수정(키워드수정)
				updateMovie();
			} else if(menuNo==9){
				//뒤로가기
				back();
				break;	
			} else {
				showInputError();
			}
		} 
	}

	private void deleteMovie() {
		String movieName = inputMovieName();

		// 영화 정보 검색
		// -1 movies 테이블에서 정보 가져오기
		Movie movie = movieDAO.selectOne(movieName);

		if (movie == null) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}

		// -2 영화 이름을 이용해 영화 있는지 확인하기
		boolean isSelected = movieDAO.checkName(movie.getMovieName());
		// DB에서 삭제
		if (isSelected) {
			movieDAO.delete(movie.getMovieName());
		} else {
			System.out.println("영화가 존재하지 않습니다.");
		}
	}

	private void updateMovie() {
		// 영화이름 입력 받기
		String movieName = inputMovieName();
		// 영화 정보 검색
		Movie movie = movieDAO.selectOne(movieName);
		if (movie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		// 수정할 정보 입력 -> 기존의 정보를 넘겨주고 새로운 정보를 반환
		movie = inputUpdateKeyword(movie);
		// DB에 업데이트
		movieDAO.update(movie);
	}

	private String inputMovieName() {
		System.out.println("영화 이름 : ");
		return sc.nextLine();
	}

	private Movie inputUpdateKeyword(Movie movie) {
		System.out.println("영화 키워드 : " + movie.getMovieKeyword());
		System.out.println("수정할 키워드 : ");
		String key = sc.nextLine();
		if (!key.equals("0")) {
			movie.setMovieKeyword(key);
		}
		return movie;

	}

	private void insertMovieInfo() {
		
		// 영화 정보 입력
		Movie movie = inputMovie();
		// DB에 저장
		movieDAO.insert(movie);
	}

	private Movie inputMovie() {
		Movie movie = new Movie();
		System.out.println("영화 제목 : ");
		movie.setMovieName(sc.nextLine());
		System.out.println("영화 개봉일 : ");
		movie.setMovieDate(sc.nextLine());
		System.out.println("영화 장르 : ");
		movie.setMovieGenre(sc.nextLine());
		System.out.println("국가 : ");
		movie.setMovieNation(sc.nextLine());
		System.out.println("키워드 : ");
		movie.setMovieKeyword(sc.nextLine());
		return movie;
	}

	protected void back() {
		System.out.println("메인으로 돌아갑니다.");
	}

	protected void showInputError() {
		System.out.println("권한이 없습니다.");
		System.out.println("메뉴번호를 다시 입력해주시기 바랍니다.");
	}

	protected int selectMenu() {
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}

	private void menuPrint() {
		System.out.println("========================================");
		System.out.println("| 1.영화 조회 | 2.영화 시청 | 3.영화 정보 등록 |");
		System.out.println("| 4.영화 삭제 | 5.영화 정보 수정 | 9.뒤로 가기 |");
		System.out.println("========================================");
	}

}