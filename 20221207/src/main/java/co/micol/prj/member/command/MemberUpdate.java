package co.micol.prj.member.command;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.AES256Util;
import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 멤버수정
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		
		//회원정보 수정도 비밀번호가 들어가있기때문에 암호화과정 넣어줘야함.
		String password = request.getParameter("memberPassword");
		if (password != "") { // 패스워드가 비어있지않으면 암호화해서 저장
			try {
				AES256Util aes = new AES256Util();
				try {
					password = aes.encrypt(password); // 암호화됨
					vo.setMemberPassword(password);
				} catch (NoSuchAlgorithmException e) { // 발생할 수 있는 오류
					e.printStackTrace();
				} catch (GeneralSecurityException e) { // 발생할 수 있는 오류
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) { // 발생할 수 있는 오류
				e.printStackTrace();
			}
		} else {
			password = null;
		}

		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberName(request.getParameter("memberName"));

		vo.setMemberAge(Integer.valueOf(request.getParameter("memberAge")));

		vo.setMemberAddress(request.getParameter("memberAddress"));
		vo.setMemberTell(request.getParameter("memberTell"));
		vo.setMemberAuthor(request.getParameter("memberAuthor"));

		System.out.println("=======   " + vo.getMemberId());

		int n = dao.memberUpdate(vo);

		if (n != 0) {
			// 회원수정 성공
			request.setAttribute("message", "회원정보 수정 성공하였습니다.");
//			회원수정 성공하면 멤버 리스트로 이동하기
			return "memberList.do";
			// 회원수정 성공하면 성공메시지 출력하기
//			return "member/memberLogin.tiles";
		} else {
			// 회원수정 실패 메시지
			request.setAttribute("message", "회원정보 수정 실패하였습니다.");
			return "member/memberLogin.tiles";
		}

	}

}
