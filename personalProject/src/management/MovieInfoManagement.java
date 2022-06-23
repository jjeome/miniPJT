package management;

import java.util.Scanner;

public class MovieInfoManagement {
	protected Scanner sc = new Scanner(System.in);
	public MovieInfoManagement() {
		while (true) {
			menuPrint();
			
			int menuNo = selectMenu();
			if(menuNo==1) {
				//영화 정보 등록
			} else if(menuNo==2) {
				//영화 정보 수정
			} else if(menuNo==3) {
				//영화 삭제
			} else if(menuNo==4) {
				//영화 제목 조회
			} else if(menuNo==5) {
				//영화 키워드 조회
			} else if(menuNo==6) {
				//영화 전체 조회
			} else if(menuNo==9) {
				back();
			} else {
				showInputError();
			}
		} 
	}
	
	

	private void back() {
		System.out.println("메인으로 돌아갑니다.");
	}

	private void showInputError() {
		System.out.println("메뉴번호를 입력해주시기 바랍니다.");
	}

	private int selectMenu() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return 0;
	}

	private void menuPrint() {
		System.out.println("===========================================================================================================");
		System.out.println(" 1.영화 정보 등록 | 2.영화 정보 수정 | 3.영화 삭제 | 4.영화 제목 조회 | 5.영화 키워드 조회 | 6.영화 전체 조회 | 9.뒤로 가기 | 0. 종료");
		System.out.println("===========================================================================================================");
	}
}
