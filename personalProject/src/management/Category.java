package management;

import java.sql.Date;
import java.util.List;

import javax.print.attribute.PrintServiceAttribute;

import VO.Movie;

public class Category extends Management{
	public Category() {
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			
			if(menuNo==1) {
				//장르별 검색
				serchJenre();
			} else if(menuNo==2) {
				//국가별 검색
				serchNation();
			} else if(menuNo==9) {
				//뒤로 가기
				//back();
				break;
			} else { 
				//오류메세지
				showInputError();
			}
		}
	}
	

	private void serchNation() {
		String nation = inputNation();
		if(nation == null) {
			System.out.println("국가를 찾을 수 없습니다.");
		}
		
		List<Movie> list = movieDAO.serchNationInfo(nation);
		for(Movie movie : list) {
			System.out.print(movie);
		}
	}

	private String inputNation() {
		System.out.println("국가 입력 :");
		return sc.nextLine();
	}


	private void serchJenre() {
		String jenre = inputJenre();
		if(jenre == null) {
			System.out.println("장르를 찾을 수 없습니다.");
		}
		
		List<Movie> list = movieDAO.serchCategoryInfo(jenre);
		for(Movie movie : list) {
			System.out.print(movie);
		}
		
	}

	private String inputJenre() {
		System.out.println("장르 입력 : ");
		return sc.nextLine();
	}

	private void menuPrint() {
		System.out.println("======================================");
		System.out.println(" 1.장르별 검색 | 2.국가별 검색 | 9.뒤로 가기 ");
		System.out.println("======================================");
	}
}
