package management;

import java.util.List;

import DAO.WatchSaveDAO;
import VO.Member;
import VO.Movie;

public class OnlyMember extends Management {
	public OnlyMember() {
		while (true) {
			printMenu();

			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 시청
				watchMovie();
			} else if (menuNo == 2) {
				// 담기
				saveMovie();
			} else if (menuNo == 3) {
				//시청 내역 조회
				movieHistory();
			} else if (menuNo == 4) {
				//담은 내역 조회
				saveHistory();
			} else if (menuNo == 5) {
				// 뒤로가기
				back();
				break;
			} else {
				// 오류메세지
				showInputError();
			}

		}
	}

	private void saveHistory() {
		String member = inputMember();
		List<String> list = watchsaveDAO.saveMovie(member);
		
		for(String movie : list) {
			System.out.println(movie);
		}
	}



	private void movieHistory() {
		String member = inputMember();
		
		List<String> list = watchsaveDAO.watchedMovie(member);
		
		for(String movie : list) {
			System.out.println(movie);
		}
	}


	private String inputMember() {
		System.out.println("회원 아이디 : ");
		return sc.nextLine();
	}



	private void watchMovie() {
		List<Movie> list = movieDAO.selectAll();
		
		for(Movie movie : list) {
			System.out.println(movie);
		}
		
		Member member = new Member();
		member.setMemberId(inputId());
		
		Movie movie = new Movie();
		movie.setMovieName(inputMovieName());
		
		WatchSaveDAO.getInstance().insertWatchedMovie(movie, member);
	}


	private String inputMovieName() {
		System.out.println("시청할 영화를 선택하세요 : ");
		return sc.nextLine();
	}


	private void saveMovie() {
		List<Movie> list = movieDAO.selectAll();
		
		for(Movie movie : list) {
			System.out.println(movie);
		}
		
		Member member = new Member();
		member.setMemberId(inputId());
		
		Movie movie = new Movie();
		movie.setMovieName(inputMovie());
		
		WatchSaveDAO.getInstance().insertWantMovie(movie, member);
	}


	private String inputMovie() {
		System.out.println("담을 영화이름을 입력 하세요 : ");
		return sc.nextLine();
	}


	private String inputId() {
		System.out.println("회원 아이디를 입력하세요 : ");
		return sc.nextLine();
	}


	private void printMenu() {
		System.out.println("=============================================================");
		System.out.println(" 1.영화 시청 | 2.영화 담기 | 3.시청 내역 | 4.담은 내역 조회 | 5.뒤로가기 ");
		System.out.println("=============================================================");
	}

	private void back() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}
}
