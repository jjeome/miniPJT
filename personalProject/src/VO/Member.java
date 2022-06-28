package VO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private String memberId;
	private int memberPassword;
	private String wantMovie;
	private String watchedMovie;

	//memberRole : 0-관리자, 1-일반회원
	private int memberRole; 
	
	@Override
	public String toString() {
		String info = "";
		
		//권한에 따라 다르게 출력
		if(memberRole == 0) {
			info = "관리자 계정 : "+memberId;
		} else {
			info = "일반 회원 계정 : "+memberId;
		}
		return info;
	}
}
