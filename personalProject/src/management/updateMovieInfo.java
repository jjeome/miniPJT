package management;

import java.util.List;

import VO.Movie;

public class updateMovieInfo extends Management{
	public updateMovieInfo() {
		while(true) {
			printMenu();
			
			int menuNo = menuSelect();
			
			if(menuNo == 1) {
				//영화 이름 수정
				updateName();
			} else if(menuNo == 2) {
				//장르 수정
				updateGenre();
			} else if(menuNo == 3) {
				//국가 수정
				updateNation();
			} else if(menuNo == 4) {
				//키워드 수정
				updateKeyword();
			} else if(menuNo == 5) {
				back();
				break;
			} else {
				showInputError();
			}
		}
	}

	private void back() {
		System.out.println("이전메뉴로 돌아갑니다.");
	}
	
	//제조국 수정
	private void updateNation() {
		List<Movie> list = movieDAO.selectAll();
		for(Movie movie : list) {
			System.out.println("--------------------------------------");
			System.out.println("| 영화 이름 : "+movie.getMovieName());
			System.out.println("| 영화 제조국 : "+movie.getMovieNation());
			System.out.println("--------------------------------------");
		}
		
		Movie movie = movieDAO.selectOne(inputMovieName());
		if(movie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		
		String newNation = inputUpdateNation();
		movieDAO.updateNation(movie, newNation);
	}

	private String inputUpdateNation() {
		System.out.println("수정할 국가 : ");
		return sc.nextLine();
	}

	//영화 장르 수정
	private void updateGenre() {
		List<Movie> list = movieDAO.selectAll();
		for(Movie movie : list) {
			System.out.println("-------------------------------------");
			System.out.println("| 영화 이름 : "+movie.getMovieName());
			System.out.println("| 영화 장르 : "+movie.getMovieGenre());
			System.out.println("-------------------------------------");
		}
		
		System.out.println("영화 이름 : ");
		Movie oldMovie = movieDAO.selectOne(sc.nextLine());
		if(oldMovie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		
		String newGenre = inputUpdateGenre();
		movieDAO.updateGenre(oldMovie, newGenre);
	}

	private String inputUpdateGenre() {
		System.out.println("수정할 장르 : ");
		return sc.nextLine();
	}

	//영화 이름 수정
	private void updateName() {
		List<Movie> list = movieDAO.selectAll();
		for(Movie movie : list) {
			System.out.println("----------------------------------");
			System.out.println("|영화 이름 : "+movie.getMovieName());
			System.out.println("----------------------------------");
		}
		
		System.out.println("수정할 영화 이름 : " );
		
		Movie oldMovie = movieDAO.selectOne(sc.nextLine());
		if(oldMovie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		
		String newMovieName = inputMovieName();
		movieDAO.updateName(oldMovie, newMovieName);
	}


	//영화 키워드 수정
	private void updateKeyword() {
		List<Movie> list = movieDAO.selectAll();
		for(Movie movie : list) {
			System.out.println("-----------------------------------");
			System.out.println("| 영화 이름 : "+movie.getMovieName());
			System.out.println("| 영화 키워드 : "+movie.getMovieKeyword());
			System.out.println("-----------------------------------");
		}
		
		// 영화 정보 검색
		System.out.println("수정할 영화 이름 : " );
		Movie movie = movieDAO.selectOne(sc.nextLine());
		if (movie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		String newKeyWord = inputUpdateKeyword();
		movieDAO.updateKeyword(movie, newKeyWord);
	}
	
	private String inputUpdateKeyword() {
		System.out.println("바꿀 키워드 : ");
		return sc.nextLine();
	}

	private String inputMovieName() {
		System.out.println("새로운 이름 : ");
		return sc.nextLine();
	}

	
	private void printMenu() {
		System.out.println("=================================");
		System.out.println(" 1.이름 수정 | 2.장르 수정 | 3.국가 수정 ");
		System.out.println("       4.키워드 수정 | 5.뒤로 가기     ");
		System.out.println("=================================");
	}
}
