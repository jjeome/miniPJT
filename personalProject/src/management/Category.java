package management;

import java.util.List;

import VO.Movie;

public class Category extends Management {
	public Category() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 장르별 검색
				serchJenre();
			} else if (menuNo == 2) {
				// 국가별 검색
				serchNation();
			} else if (menuNo == 9) {
				// 뒤로 가기
				back();
				break;
			} else {
				// 오류메세지
				showInputError();
			}
		}
	}

	private void back() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}

	//국가 검색
	private void serchNation() {
		List<Movie> list = movieDAO.serchNationInfo(inputNation());
		if (list.size() == 0) {
			System.out.println("등록되지 않은 국가입니다.");
		} else {
			for (Movie movie : list) {
				System.out.print(movie);
			}
		}
	}

	private String inputNation() {
		System.out.println("국가 입력 :");
		return sc.nextLine();
	}

	//장르 검색
	private void serchJenre() {
		List<Movie> list = movieDAO.serchCategoryInfo(inputJenre());
		if (list.size() == 0) {
			System.out.println("등록되지 않은 장르입니다.");
		} else {
			for (Movie movie : list) {
				System.out.print(movie);
			}
		}
	}

	private String inputJenre() {
		System.out.println("장르 입력 : ");
		return sc.nextLine();
	}

	private void menuPrint() {
		System.out.println("======================================");
		System.out.println("  1.장르별 검색 | 2.국가별 검색 | 9.뒤로 가기  ");
		System.out.println("======================================");
	}
}
