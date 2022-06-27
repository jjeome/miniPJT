package management;

import java.util.List;

import DAO.MovieDAO;
import VO.Movie;

public class SerchCategory extends Management{
	public MovieDAO movieDAO = MovieDAO.getInstance();
	public SerchCategory() {
		while(true) {
			printMenu();
			int menuNo = menuSelect();
			
			if(menuNo == 1) {
				//장르별 
				serchGenre();
			} else if(menuNo==2) {
				//국가별
				serchNation();
			} else if(menuNo==3) {
				//뒤로가기
				back();
				break;
			} else {
				showInputError();
			}
		}
	}

	private void serchNation() {
		String nation = inputNation();
		
		List<Movie> list = movieDAO.serchNationInfo(nation);
		for(Movie movie : list) {
			System.out.println(movie);
		}
	}

	private String inputNation() {
		System.out.println("국가를 입력하세요 : ");
		return sc.nextLine();
	}

	private void serchGenre() {
		String genre = inputGenre();
		
		List<Movie> list = movieDAO.serchCategoryInfo(genre);
		for(Movie movie : list) {
			System.out.println(movie);
		}
	}

	private String inputGenre() {
		System.out.println("장르를 입력하세요 : ");
		return sc.nextLine();
	}

	private void printMenu() {
		System.out.println("=====================================");
		System.out.println(" 1.장르별 검색 | 2.국가별 검색 | 3.뒤로 가기 ");
		System.out.println("=====================================");
	}

	private void back() {
		System.out.println("뒤로 갑니다.");
	}

}