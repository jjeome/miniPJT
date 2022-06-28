package management;

import java.util.List;

import DAO.WatchSaveDAO;
import VO.Member;
import VO.Movie;

public class OnlyMember extends Management {
	private int role = MovieMemberManagement.LoginInfo.getMemberRole();
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
				if(role == 0) {
					//관리자
					seeMemberHistory();
				} else {
					//회원
					movieHistory();					
				}
			} else if (menuNo == 4) {
				//담은 내역 조회
				if(role == 0) {
					//관리자
					seeMemberSaveHistory();
				} else {
					//회원
					saveHistory();					
				}
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

	//관리자 - 회원담은 내역 조회
	private void seeMemberSaveHistory() {
		List<String> list = watchsaveDAO.saveMovie(inputId());
		if(list.size() == 0) {
			System.out.println("등록된 아이디가 없습니다.");
		} else {
			for(String movie : list) {
				System.out.println(movie);
			}			
		}	}
	
	//관리자 - 회원시청 내역 조회
	private void seeMemberHistory() {
		List<String> list = watchsaveDAO.watchedMovie(inputId());
		if(list.size() == 0) {
			System.out.println("등록된 아이디가 없습니다.");
		} else {
			for(String movie : list) {
				System.out.println(movie);
			}			
		}
	}

	//담은 내역 조회
	private void saveHistory() {
		List<String> list = watchsaveDAO.saveMovie(MovieMemberManagement.LoginInfo.getMemberId());
		
		for(String movie : list) {
			System.out.println(movie);
		}
	}

	//시청 내역 조회
	private void movieHistory() {
		List<String> list = watchsaveDAO.watchedMovie(MovieMemberManagement.LoginInfo.getMemberId());
		
		for(String movie : list) {
			System.out.println(movie);
		}
	}

	//영화 시청
	private void watchMovie() {
		//영화 전체 목록 보여주기
		List<Movie> list = movieDAO.selectAll();
		
		for(Movie movie : list) {
			System.out.println(movie);
		}
		
		//영화 시청
		Member member = new Member();
		member.setMemberId(MovieMemberManagement.LoginInfo.getMemberId());
		
		Movie movie = new Movie();
		movie.setMovieName(inputMovieName());
		
		WatchSaveDAO.getInstance().insertWatchedMovie(movie, member);
	}


	private String inputMovieName() {
		System.out.println("시청할 영화를 선택하세요 : ");
		return sc.nextLine();
	}

	//영화 담기
	private void saveMovie() {
		List<Movie> list = movieDAO.selectAll();
		
		for(Movie movie : list) {
			System.out.println(movie);
		}
		
		Member member = new Member();
		member.setMemberId(MovieMemberManagement.LoginInfo.getMemberId());
		
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
		System.out.println("=========================================================");
		System.out.println(" 1.영화 시청 | 2.영화 담기 | 3.시청 내역 | 4.담은 내역 | 5.뒤로가기 ");
		System.out.println("==========================================================");
	}

	private void back() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}
}
