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

	private void registerIn() {
		Member member = inputMember();
		memberDAO.insertInfo(member);
	}
	
	private Member inputMember() {
		Member member = new Member();
		System.out.println("회원가입을 시작합니다.");
		System.out.println("ID를 입력하세요 : ");
		member.setMemberId(sc.nextLine());
		System.out.println("PASSWORD를 입력하세요 : ");
		member.setMemberPassword(sc.nextInt());
		return member;
	}

	private void back() {
		System.out.println("메인으로 돌아갑니다.");
	}

	private void logIn() {
		//아이디, 비밀번호 입력받기
		Member inputInfo= inputId();
		//로그인시도
		LoginInfo = MemberDAO.getInstance().selectOne(inputInfo);
		//실패할경우, 메소드종료
		if(LoginInfo == null) {
			return;
		}
		//성공할 경우 프로그램 실행
		System.out.println("로그인 되어 영화메뉴를 실행합니다.");
		new MovieInfoManagement();

	}
	private Member inputId() {
		Member member = new Member();
		System.out.println("ID : ");
		member.setMemberId(sc.nextLine());
		System.out.println("PASSWORD : ");
		member.setMemberPassword(sc.nextInt());
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
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}
}
