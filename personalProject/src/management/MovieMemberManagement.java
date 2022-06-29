package management;

import java.util.Scanner;

import DAO.MemberDAO;
import VO.Member;

public class MovieMemberManagement extends Management{
	protected Scanner sc = new Scanner(System.in);

	public static Member LoginInfo = null;

	//한번 로그인을 로그인 하는동안 로그인을 유지해야함
	public static Member getLoginInfo() {
		return LoginInfo;
	}

	public MovieMemberManagement() {
		while (true) {
			menuPrint();

			int menuNo = menuSelect();
			if (menuNo == 1) {
				// 로그인
				logIn();
			} else if (menuNo == 2) {
				// 회원가입
				registerIn();
			} else if (menuNo == 3) {
				// 뒤로가기
				back();
				break;
			} else {
				// 입력 오류
				showInputError();
			}
		}
	}
	
	//회원가입
	private void registerIn() {
		Member member = inputMember();
		if(memberDAO.loginReturn(member) == true) {
			memberDAO.insertInfo(member); 			
		} else {
			System.out.println(" ======이미 가입된 회원입니다.======");
			return;
		}
	}
	
	//회원가입 정보 입력 받기
	private Member inputMember() {		
		Member member = new Member();
			System.out.println("===================");
			System.out.println(" 회원가입을 시작합니다. ");
			System.out.println("===================");
			System.out.println("ID를 입력하세요 : ");
			member.setMemberId(sc.nextLine());
			
		while(true) {
			try {
				System.out.println("======= 숫자로 =======");
				System.out.println("PASSWORD를 입력하세요 : ");
				member.setMemberPassword(Integer.parseInt(sc.nextLine()));
				break;	
			} catch (NumberFormatException e) {
				System.out.println("숫자형태로 다시 입력해 주십시오");
				continue;
			}
		
		}
		return member;
	}
	private void back() {
		System.out.println("======메인으로 돌아갑니다.======");
	}

	//로그인
	private void logIn() {
		//아이디, 비밀번호 입력받기
		Member inputInfo= inputId();
		//로그인시도
		LoginInfo = MemberDAO.getInstance().selectOne(inputInfo);
		//실패할경우, 메소드종료
		if(LoginInfo == null) {
			return;
		} else {
			//성공할 경우 프로그램 실행
			if(LoginInfo.getMemberRole()==0) {
				System.out.println("=============== 관리자로 로그인 ==============");
				System.out.println("                MOVIE-MENU               ");
				new MovieInfoManagement();
				
			} else {
				System.out.println("=============== 회원으로 로그인 ==============");
				System.out.println("                MOVIE-MENU               ");
				new MovieInfoManagement();
			}
		}
		
	}
	
	//로그인 정보 입력받기
	private Member inputId() {
		Member member = new Member();
		System.out.println("ID : ");
		member.setMemberId(sc.nextLine());
		System.out.println("PASSWORD : ");
		member.setMemberPassword(Integer.parseInt(sc.nextLine()));
		return member;
	}
	
	protected int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력하세요");
		}
		return menuNo;
	}

	private void menuPrint() {
		System.out.println("=============================");
		System.out.println(" 1.로그인 | 2.회원가입 | 3.뒤로가기 ");
		System.out.println("=============================");
	}

	protected void showInputError() {
		System.out.println("=====메뉴에서 입력해주시기 바랍니다.=====");
	}
}
