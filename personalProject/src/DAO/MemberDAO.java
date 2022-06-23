package DAO;

import java.sql.SQLException;

import VO.Member;
import VO.Movie;
import common.DAO;

public class MemberDAO extends DAO {
	//싱글톤
	private static MemberDAO memberDAO = null;
	private MemberDAO () {}
	public static MemberDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	//회원가입
	public void insertInfo(Member member) {
		try {
			connect();
			String sql = "INSERT INTO members (member_id, member_pwd) VALUES( ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("회원가입이 완료되었습니다.");
			} else {
				System.err.println("회원가입에 실패하였습니다.");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} 
	}
	
	//로그인 여부와 권한만 가지고오기
	public Member selectOne(Member member) {
		Member loginInfo = null;
		try {
			connect();
			String sql = "SELECT * FROM members WHERE member_id = '"+member.getMemberId()+"'";
		
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			if(rs.getString("member_pwd").equals(member.getMemberPwd())) {
				loginInfo = new Member();
				loginInfo.setMemberId(rs.getString("member_id"));
				loginInfo.setMemberPwd(rs.getString("member_pwd"));
				loginInfo.setMemberRole(rs.getInt("member_role"));
		
			} else {
			System.out.println("비밀번호가 일치하지 않습니다.");
			}
		} else {
			System.out.println("아이디가 존재하지 않습니다.");
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return loginInfo;
	}	
	
}
