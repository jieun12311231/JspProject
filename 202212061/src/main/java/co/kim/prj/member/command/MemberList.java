package co.kim.prj.member.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kim.prj.common.Command;
import co.kim.prj.member.service.MemberService;
import co.kim.prj.member.service.MemberVO;
import co.kim.prj.member.serviceImpl.MemberServiceImpl;

public class MemberList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse reponse) {
		MemberService dao = new MemberServiceImpl();
		
		List<MemberVO> members = new ArrayList<MemberVO>();
		
		members = dao.memberSelectList();
		
		request.setAttribute("members", members);
						
		return "member/memberList";
	}

}
