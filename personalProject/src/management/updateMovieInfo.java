package management;

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

	private void updateNation() {
		Movie movie = movieDAO.selectOne(inputMovieName());
		if(movie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		movie = inputUpdateNation(movie);
		movieDAO.updateNation(movie);
	}

	private Movie inputUpdateNation(Movie movie) {
		System.out.println("영화 이름 : " + movie.getMovieName());
		System.out.println("수정할 국가 : ");
		String nation = sc.nextLine();
		if(!nation.equals("0")) {
			movie.setMovieNation(nation);
		}
		return movie;
	}

	private void updateGenre() {
		Movie movie = movieDAO.selectOne(inputMovieName());
		if(movie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		movie = inputUpdateGenre(movie);
		movieDAO.updateGenre(movie);
	}

	private Movie inputUpdateGenre(Movie movie) {
		System.out.println("영화 이름 : " + movie.getMovieName());
		System.out.println("수정할 장르 : ");
		String genre = sc.nextLine();
		if(!genre.equals("0")) {
			movie.setMovieGenre(genre);
		}
		return movie;
	}

	//영화 이름 수정
	private void updateName() {
		Movie movie = movieDAO.selectOne(inputMovieName());
		if(movie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		movie = inputUpdateName(movie);
		movieDAO.updateName(movie);
	}

	/************고치기************/
	//이름 수정 입력
	private Movie inputUpdateName(Movie movie, Movie movie2) {
		System.out.println("영화 이름 : " + movie.getMovieName());
		System.out.println("수정할 영화 이름 : ");
		String name = sc.nextLine();
		if(!name.equals("0")) {
			movie.setMovieName(name);			
		}
		return movie;
	}


	//영화 키워드 수정
	private void updateKeyword() {
		// 영화 정보 검색
		Movie movie = movieDAO.selectOne(inputMovieName());
		if (movie == null) {
			System.out.println("등록된 영화가 없습니다.");
			return;
		}
		// 수정할 정보 입력 -> 기존의 정보를 넘겨주고 새로운 정보를 반환
		movie = inputUpdateKeyword(movie);
		// DB에 업데이트
		movieDAO.updateKeyword(movie);
	}
	
	private String inputMovieName() {
		System.out.println("수정 할 영화 이름 : ");
		return sc.nextLine();
	}

	//키워드 수정 입력
	private Movie inputUpdateKeyword(Movie movie) {
		System.out.println("영화 키워드 : " + movie.getMovieKeyword());
		System.out.println("수정할 키워드 : ");
		String key = sc.nextLine();
		if (!key.equals("0")) {
			movie.setMovieKeyword(key);
		}
		return movie;
	}
	
	private void printMenu() {
		System.out.println("  1.이름 수정 | 2.장르 수정 ");
		System.out.println(" 3.국가 수정 | 4.키워드 수정 ");
	}
}
