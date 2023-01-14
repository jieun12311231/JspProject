package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.serviceImpl.MemberServiceImpl;

public class AjaxMemberIdCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 아이디 중복체크를 Ajax로 처리하기.
		MemberService dao = new MemberServiceImpl();
		//아이디 중복체크 0이면 폴스 1이면 트루
		String id = request.getParameter("id");
		System.out.println(id + "=================");
		//값을 돌려줄
		String result = "1"; //기본값으로 아이디를 사용 할 수 잇도록 하기 위해 //존재하지않으면1
		boolean b = dao.isIdCheck(id);
		System.out.println(b + "=================");
		if(!b) {  //false가 날아오면 !을 만나 true가 됨 >> 사용할 수 없음
			result = "0"; //아이디가 존재하면 0
		}
		System.out.println(result + "=================");
		return "Ajax:"+result; //ajax처리하는 것을 view Resolve에 알림 , 아이작스인것을 알리기위해 아이작스를 붙여줌
		}

}
