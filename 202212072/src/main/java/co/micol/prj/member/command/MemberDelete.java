package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원삭제
		
		
		MemberService dao = new MemberServiceImpl();
		
		MemberVO vo = new MemberVO();
		
		vo.setMemberId(request.getParameter("memberId"));
		//한 행이 삭제 db에서 1행이 삭제되었습니다 라고 뜨기 때문에 n이 1임 int를 적어줘야함.
		//db에서 넘어오는 결과를 받기위해 넣는 것임 
		
		int n = dao.memberDelete(vo);
		
		if(n != 0) {
			//회원삭제 성공
			return "memberList.do";
		}else {
			request.setAttribute("message", "회원정보 삭제가 실패했습니다.");
			//메세지만 던져 주기 떄문에 메세지를 출력하는 jsp로 연결을 해주는 것임.
			return "member/memberLogin.tiles";
		}
		

	}

}
