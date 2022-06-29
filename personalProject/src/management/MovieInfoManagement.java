package management;


import java.util.Scanner;
import VO.Movie;

public class MovieInfoManagement extends Management {
	protected Scanner sc = new Scanner(System.in);
	int menuNo = 0;
	private int role;

	// 비회원 막기
	public MovieInfoManagement() {
		if (MovieMemberManagement.getLoginInfo() == null) {
			System.out.println("===== 로그인부터 해주십시오 =====");
			new Management();
		} else {
			role = MovieMemberManagement.LoginInfo.getMemberRole();
			while (true) {
				menuPrint();

				menuNo = selectMenu();

				if (menuNo == 1) {
					// 영화 조회
					new checkMovie();
				} else if (menuNo == 2) {
					// 영화 시청
					new OnlyMember();
				} else if (menuNo == 3 && role == 0) {
					// 영화 정보 등록
					insertMovieInfo();
				} else if (menuNo == 4 && role == 0) {
					// 영화 삭제
					deleteMovie();
				} else if (menuNo == 5 && role == 0) {
					// 영화 정보 수정
					new updateMovieInfo();
				} else if (menuNo == 9) {
					// 뒤로가기
					back();
					break;
				} else {
					showInputError();
				}

			}
		}

	}
	
	//영화 삭제
	private void deleteMovie() {
		String movieName = inputMovieName();

		// 영화 정보 검색
		// -1 movies 테이블에서 정보 가져오기
		Movie movie = movieDAO.selectOne(movieName);
		
		
		if (movie == null) {
			System.out.println("-- 등록된 정보가 없거나 이미 삭제된 영화입니다.");
			return;
		} else {
			// -2 영화 이름을 이용해 영화 있는지 확인하기
			boolean isSelected = movieDAO.checkName(movie.getMovieName());
			// DB에서 삭제
			if (isSelected) {
				movieDAO.delete(movie.getMovieName());
			}
		}
		
	}

	private String inputMovieName() {
		System.out.println("영화 이름 : ");
		return sc.nextLine();
	}


	private void insertMovieInfo() {
		// 영화 정보 입력
		Movie movie = inputMovie();
		// DB에 저장
		movieDAO.insert(movie);
	}
	
	//영화 등록
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
		System.out.println("--------- 권한이 없습니다 ---------");
		System.out.println("  메뉴번호를 다시 입력해주시기 바랍니다  ");
	}

	protected int selectMenu() {
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("----- 숫자를 입력해주시기 바랍니다 -----");
		}
		return menuNo;
	}

	private void menuPrint() {
		System.out.println("=========================================");
		System.out.println("   1.영화 조회 | 2.영화 시청 | 3.영화 정보 등록   ");
		System.out.println("   4.영화 삭제 | 5.영화 정보 수정 | 9.뒤로 가기   ");
		System.out.println("=========================================");
	}
}
