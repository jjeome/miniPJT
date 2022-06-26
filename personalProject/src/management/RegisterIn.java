package management;

import DAO.MemberDAO;
import VO.Member;

public class RegisterIn extends Management{
	public RegisterIn() {
		while(true) {
			
			printMenu();
			
			int menuNo = menuSelect();
			if(menuNo==1) {
				//회원 가입
				registerMemger();
			} else if(menuNo==2) {
				//관리자 가입
				registerManager();
			} else if(menuNo==3) {
				//뒤로가기
				back();
				break;
			}
		} 
	}

	private void printMenu() {
		System.out.println(" 1.회원 가입 | 2.관리자 가입 | 3.뒤로 가기");
	}

	private void back() {
		System.out.println("이전 메뉴로 돌아갑니다.");
	}

	private void registerManager() {
		Member member = inputManager();
		memberDAO.insertManager(member);
	}

	private Member inputManager() {
		Member member = new Member();
		System.out.println("관리자가입을 시작합니다.");
		System.out.println("ID를 입력하세요 : ");
		member.setMemberId(sc.nextLine());
		System.out.println("PASSWORD를 입력하세요 : ");
		member.setMemberPassword(sc.nextLine());
		return member;
	}

	private void registerMemger() {
		Member member = inputMember();
		memberDAO.insertInfo(member);
	}
	
	private Member inputMember() {
		Member member = new Member();
		System.out.println("회원가입을 시작합니다.");
		System.out.println("ID를 입력하세요 : ");
		member.setMemberId(sc.nextLine());
		System.out.println("PASSWORD를 입력하세요 : ");
		member.setMemberPassword(sc.nextLine());
		return member;
	}
}
