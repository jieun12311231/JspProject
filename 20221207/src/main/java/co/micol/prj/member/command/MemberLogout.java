package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.Command;

public class MemberLogout implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그아웃처리
		//세션을 없애버리면 됨
		HttpSession session = request.getSession();
		
		//로그아웃시 보여줄 메세지 
		//세션에서 네임을 불러옴 object로 오기때문에 String으로 캐스팅
		String message = (String) session.getAttribute("name");
		message += "님 정상적으로 로그아웃되었습니다.";
		
		//invalidate 세션 통째로 없애는 것 완전 삭제 
		//remove()느느 특정 항목만 삭제
		session.invalidate();
		
		//세션 지운 후 
		request.setAttribute("message",message);
		
		//로그아웃하면 메인 화면으로 보내벌임
		//메세지 뿌리는 창으로 이동
		return "member/memberLogin.tiles";
	}

}
