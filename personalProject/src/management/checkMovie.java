package management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import VO.Movie;

public class checkMovie extends Management{
	private Scanner sc = new Scanner(System.in);

	public checkMovie() {
		while (true) {
			printMenu();
			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 영화 이름검색
				serchMovieName();
			} else if (menuNo == 2) {
				// 영화 카테고리 검색
				new Category();
			} else if (menuNo == 3) {
				// 영화 키워드 조회
				serchKeyword();
			} else if (menuNo == 4) {
				// 영화 전체 조회
				showAllMovie();
			} else if (menuNo == 5) {
				// 뒤로가기
				back();
				break;
			} else {
				// 입력 오류
				showInputError();
			}
		}

	}

	private void serchKeyword() {
		String keyword = inputKeyword();
		
		List<Movie> list = movieDAO.serchKeyword(keyword);
		
		for(Movie movie : list) {
			System.out.println(movie);
		}
		
	}

	private String inputKeyword() {
		System.out.println("키워드를 검색하세요 : ");
		return sc.nextLine();
	}

	private void back() {
		System.out.println("이전메뉴로 돌아갑니다.");
	}

	private void showAllMovie() {
		List<Movie> list = movieDAO.selectAll();
		
		for(Movie movie : list) {
			System.out.println(movie);
		}
	}
	
	
	private String inputMovieName() {
		System.out.println("영화 이름 : ");
		return sc.nextLine();
	}
	
	private void serchMovieName() {
		String movieName = inputMovieName();
		Movie movie = movieDAO.selectOne(movieName);
		if(movie ==null) {
			System.out.println("등록된 영화가 아닙니다.");
			return;
		} 
		System.out.println(movie);
		
	}
	
	private void printMenu() {
		System.out.println("======================================================");
		System.out.println(" 1.영화 이름 검색 | 2. 영화 카테고리 검색 | 3. 영화 키워드 조회 ");
		System.out.println("               | 4.영화 전체 조회 | 5. 뒤로가기           ");
		System.out.println("======================================================");
	}

}
