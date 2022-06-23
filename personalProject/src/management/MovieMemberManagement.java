package management;

import java.util.Scanner;

import DAO.MemberDAO;
import VO.Member;

public class MovieMemberManagement extends Management{
	protected Scanner sc = new Scanner(System.in);
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

	private void back() {
		System.out.println("메인으로 돌아갑니다.");
	}

	private void registerIn() {
		Member member = inputInfo();
		//회원 가입 입력
		memberDAO.insertInfo(member);
	}

	private Member inputInfo() {
		Member member = new Member();
		System.out.println("회원가입을 시작합니다.");
		System.out.println("ID를 입력하세요 : ");
		member.setMemberId(sc.nextLine());
		System.out.println("PASSWORD를 입력하세요 : ");
		member.setMemberPwd(sc.nextLine());
		return member;
	}

	private void logIn() {
		Member member= inputId();
		memberDAO.selectOne(member);
		
	}

	private Member inputId() {
		Member member = new Member();
		System.out.println("ID : ");
		member.setMemberId(sc.nextLine());
		System.out.println("PASSWORD : ");
		member.setMemberPwd(sc.nextLine());
		return member;
	}

	private int menuSelect() {
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

	private void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}
}
