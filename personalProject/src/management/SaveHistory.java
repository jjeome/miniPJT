package management;

import java.util.List;

import DAO.WatchSaveDAO;
import VO.Member;
import VO.Movie;

public class SaveHistory extends Management{
	public SaveHistory() {
		while(true) {
			printMenu();
			
			int menuNo = menuSelect();
			
			if(menuNo==1) {
				//담은 영화 내역
				saveHistory();
			} else if(menuNo == 2) {
				//담은 영화 시청하기
				savedMovieWatch();
			} else if(menuNo == 3) {
				//뒤로가기
				back();
				break;
			} else {
				showInputError();
			}
		}
	}
	
	//담은 내역 조회
	private void saveHistory() {
		List<String> list = watchsaveDAO.saveMovie(MovieMemberManagement.LoginInfo.getMemberId());
		
		int i =1;
		for(String movie : list) {
			System.out.println("-------------------------------");
			System.out.println(" "+i+"| "+ movie);
			i++;
		}
	}
	
	private void savedMovieWatch() {
		List<String> list = watchsaveDAO.saveMovie(MovieMemberManagement.LoginInfo.getMemberId());
		int i =1;
		for(String movie : list) {
			System.out.println(" "+i+"| "+ movie);
			System.out.println("=============================");
			i++;
		}
		
		Movie movie = new Movie();
		movie.setMovieName(inputSaveMovieName());
		
		Member member = new Member();
		member.setMemberId(MovieMemberManagement.LoginInfo.getMemberId());
		
		WatchSaveDAO.getInstance().insertWatchedMovie(movie, member);
	}
	
	
	private String inputSaveMovieName() {
		System.out.println("시청하실 영화를 선택하세요 : ");
		return sc.nextLine();
	}


	private void back() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}


	private void printMenu() {
		System.out.println("========================================");
		System.out.println(" 1.담은 내역 조회 | 2.담은 영화 시청 | 3.뒤로 가기 ");
		System.out.println("========================================");
	}
	
}
