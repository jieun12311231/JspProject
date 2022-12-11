package co.micol.prj.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class MemberList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse reponse) {
// 멤버 목록보기 DAO작동
		//DAO연결
		MemberService dao = new MemberServiceImpl();
		
		//담을곳
		List<MemberVO> members = new ArrayList<MemberVO>();
		
		//동작하는 곳 / db에서 멤버테이블의 목록을 가져온다. 
		members = dao.memberSelectList();
		
		//결과를 request객체에 담음
		request.setAttribute("members",members); // 결과를 jsp페이지에 전달하기 위해 
		
		//결과 페이지
		return "member/memberList.tiles";  //member폴더 밑에 memberList.jsp로 가겠다. 
		
	}

}
