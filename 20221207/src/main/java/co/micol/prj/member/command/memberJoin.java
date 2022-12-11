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

public class memberJoin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원가입 처리
		// db접근
		MemberService dao = new MemberServiceImpl();
		// 넘어온거 실을 보
		MemberVO vo = new MemberVO();
		
		//패스워드 암호화 테스트 내가만든거 -> 회원 가입할때 패스워드를 암호화된 상태로 들어가게 해줌
		String password = request.getParameter("memberPassword");
		try {
			AES256Util aes = new AES256Util();
			try {
				password = aes.encrypt(password);  //암호화됨
			} catch (NoSuchAlgorithmException e) {  //발생할 수 있는 오류
				e.printStackTrace();
			} catch (GeneralSecurityException e) {  //발생할 수 있는 오류
				e.printStackTrace();
			} 
		} catch (UnsupportedEncodingException e) {  //발생할 수 있는 오류
			e.printStackTrace();
		}
		
		System.out.println(password+"=================");
		
		int n = 0;

//		String viewPage = null; //돌려줄 페이지 
		String message = null; // 메시지
		// 매퍼의 인서트가 요구하는 요소들을 다 적어야함
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberName(request.getParameter("memberName"));
		
		//암호화 된 패스워드 실어보냄
		vo.setMemberPassword(password);
		
				// 나이 안넣고 싶으면
		if (request.getParameter("memberAge") != "") {
			vo.setMemberAge(Integer.valueOf(request.getParameter("memberAge")));// 넘어오는 것이 숫자이기때문에 문자열로 변환해줘야함
		}
		vo.setMemberAddress(request.getParameter("memberAddress"));
		vo.setMemberTell(request.getParameter("memberTell"));
		vo.setMemberAuthor("USER");// 기본값이 유저이기때문에 값을 아예 유저로 담아놓음

		n = dao.memberInsert(vo);
		if (n != 0) {
			// 성공했을때 바로 목록으로 이동
			//return "memberList.do";
			//회원가입 성공시 메인화면으로 이동 
			return "main.do";
			// message = "회원가입이 성공적으로 처리되었습니다.";

		} else {
			message = "회원가입이 실패했습니다.";
		}
		// 결과를 request객체에 담기
		request.setAttribute("message", message);
		return "member/memberJoin.tiles";
	}

}
