package management;

import java.util.List;

import VO.Movie;

public class SerchMovie extends Management{
	public SerchMovie () {
		while(true) {
			printMenu();
			
			int menuNo = menuSelect();
			if(menuNo==1) {
				//전체 조회 
				selectAllMovie();
			} else if(menuNo==2) {
				//영화 이름 조회
				selectNameMovie();
			} else if(menuNo==3) {
				//뒤로가기
				back();
				break;
			} else {
				showInputError();
			}
		}
	}

	private void selectNameMovie() {
		String movieName = inputMovieName();
		Movie movie = movieDAO.selectOne(movieName);
		
		if(movie==null){
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		System.out.println(movie);
	}

	private String inputMovieName() {
		System.out.println("영화이름 : ");	
		return sc.nextLine();
	}

	private void selectAllMovie() {
		List<Movie> list = movieDAO.selectAll();
		for(Movie movie : list) {
			System.out.println(movie);
		}
		
	}

	private void printMenu() {
		System.out.println("====================================");
		System.out.println(" 1. 전체 조회 | 2. 이름 조회 | 3. 뒤로 가기 ");
		System.out.println("====================================");
	}

	private void back() {
		System.out.println("뒤로 갑니다.");
	}
	
	
	
	
	
	
}
