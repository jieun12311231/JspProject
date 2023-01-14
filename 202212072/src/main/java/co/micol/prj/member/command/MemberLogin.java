package co.micol.prj.member.command;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.AES256Util;
import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 처리
		// db에 갔다와야함 -> dao 필요
		MemberService dao = new MemberServiceImpl();
		// 담을 vo
		MemberVO vo = new MemberVO();
		// 세션객체 불러주기 HttpSessiond인터페이스 -> 구현체 호출
		// 서버가 만들어 보관하고있는 세션객체를 호출(나의 세션을 가지고 올수있음)
		// 들어오는 브라우저에 한해서 세션아이디를 가지고있음 request를 통해 세션객체를 불러옴
		HttpSession session = request.getSession();

		// 패스워드 암호화 테스트 내가만든거 -> 로그인을 db로 보낼때 암호로 넘어가게 만들어줌
		String password = request.getParameter("memberPassword");
		try {
			AES256Util aes = new AES256Util();
			try {
				password = aes.encrypt(password); // 암호화됨
			} catch (NoSuchAlgorithmException e) { // 발생할 수 있는 오류
				e.printStackTrace();
			} catch (GeneralSecurityException e) { // 발생할 수 있는 오류
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) { // 발생할 수 있는 오류
			e.printStackTrace();
		}

		String message = null;
		// 넘어온 값들을 vo에 담음 -> 값이 없으면 null값이 담김
		vo.setMemberId(request.getParameter("memberId"));
		//로그인 할때 암호화 된 패스워드 실어보냄
		vo.setMemberPassword(password);

		vo = dao.memberSelect(vo);
		if (vo != null) {
			// 세션 성공 setAttribute: 변수id에 vo.getMemberId()값을 담아서 session에 넣음
			// session 범위 : 어플리케이션 전체 -> 사용법 : jsp에서 el표현식으로 꺼내씀 ex) ${id}를 하면 id가 불려짐
			// 마이바티스는 #{}//jsp는 ${}
			session.setAttribute("id", vo.getMemberId());
			session.setAttribute("author", vo.getMemberAuthor());
			session.setAttribute("name", vo.getMemberName());
			// 비밀번호도 담아서 게시글의 비밀번호 확인에 사용가능

			// 로그인성공시
			message = vo.getMemberName() + "님 환영합니다.";
			request.setAttribute("message", message);
//			request.setAttribute("member", vo);  //화면에 출력을 안할거면 없어도 됨(돌려주기위해서)
		} else {
			message = "아이디 또는 패스워드가 틀립니다.";
			request.setAttribute("message", message);

		}
		// 보여줄페이지
		return "member/memberLogin.tiles";
	}

}
