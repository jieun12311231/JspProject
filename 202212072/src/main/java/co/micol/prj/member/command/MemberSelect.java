package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원한명 조회
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();

		// vo에 아이디를 담아옴
		vo.setMemberId(request.getParameter("memberId"));

		// vo에 담긴 정보를 들고 셀렉트 해와라
		vo = dao.memberSelect(vo);

		String viewPage = null;
		if(vo!=null) { //viewPage값이 null이 아니면
			request.setAttribute("member", vo);
			//데이터가 존재하면memberSelect로 이동
			viewPage = "member/memberSelect.tiles";
		}else {//오류가 나면(키값을 잘못 넘기면
			request.setAttribute("message", "존재하지않는 회원입니다.");
			//존재하지않으면memberLogin로가서 메세지만 뿌림 
			viewPage = "member/memberLogin.tiles";
		}
		return viewPage;
	}

}
