package management;

import java.util.Scanner;

import DAO.MemberDAO;
import DAO.MovieDAO;
import DAO.WatchSaveDAO;

public class Management {
	//필드
	protected Scanner sc = new Scanner(System.in);
	//공유되는 테이블 2개
	protected MovieDAO movieDAO = MovieDAO.getInstance();
	protected MemberDAO memberDAO = MemberDAO.getInstance();
	protected WatchSaveDAO wsDAO = WatchSaveDAO.getInstance();
	
	public void run() {
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo==1) {
				//회원 정보 관리
				new MovieMemberManagement();
			} else if(menuNo==2) {
				//영화 정보 관리
				new MovieInfoManagement();
			} else if(menuNo==9) {
				//프로그램 종료
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	

	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}

	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	protected int menuSelect() {
		int menuNo = 0;
		try {
			 menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}

	private void menuPrint() {
		System.out.println("========================");
		System.out.println(" 1.로그인 | 2.영화 | 9.종료 ");
		System.out.println("========================");
	}
	
	
	
	
	
	
}
