package co.micol.prj.web;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.MainCommand;
import co.micol.prj.common.Command;
import co.micol.prj.member.command.AjaxMemberIdCheck;
import co.micol.prj.member.command.MemberDelete;
import co.micol.prj.member.command.MemberEdit;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberList;
import co.micol.prj.member.command.memberJoin;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberSelect;
import co.micol.prj.member.command.MemberUpdate;
import co.micol.prj.member.command.memberLoginForm;
import co.micol.prj.member.command.memberLogout;
import co.micol.prj.notice.command.NoticeEditForm;
import co.micol.prj.notice.command.NoticeInsert;
import co.micol.prj.notice.command.NoticeInsertFrom;
import co.micol.prj.notice.command.NoticeList;
import co.micol.prj.notice.command.NoticeSelect;
import co.micol.prj.notice.command.NoticeUpdate;
import co.micol.prj.notice.command.NoticeDeleteForm;

//@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Command> map = new HashMap<String, Command>();

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
//명령집단 map.put(k,v)
//키값에 '/' 꼭 넣어주기!!!!안넣으면 500에러 NullPointExption!!!!! 
		map.put("/main.do",new MainCommand()); //처음 실행하는 페이지 메인이니까 기본페키지에 만들어줌
		map.put("/memberList.do", new MemberList()); //회원목록
		map.put("/memberJoinForm.do", new MemberJoinForm()); //회원가입 폼
		map.put("/AjaxMemberIdCheck.do",new AjaxMemberIdCheck()); //회원아이디 중복체크
		map.put("/memberJoin.do", new memberJoin()); //회원가입처리 - vo에 입력받은 데이터를 Insert함. 등록 성공시 문구출력
		map.put("/memberLoginForm.do", new memberLoginForm());
		map.put("/memberLogin.do",new MemberLogin());//로그인처리
		map.put("/memberLogout.do", new memberLogout());//로그아웃처리
		map.put("/memberSelect.do", new MemberSelect());//멤보한명조회
		map.put("/memberEdit.do", new MemberEdit());//멤버 수정 폼 호출
		map.put("/memberDelete.do", new MemberDelete());//멤버 삭제
		map.put("/memberUpdate.do", new MemberUpdate()); //멤버 수정
		map.put("/noticeInsertForm.do", new NoticeInsertFrom()); //공지사항 등록 폼
		map.put("/noticeList.do", new NoticeList()); //공지사항 목록
		map.put("/noticeSelect.do", new NoticeSelect()); //공지사항 상세보기
		map.put("/noticeInsert.do", new NoticeInsert()); //공지사항 작성
		
		map.put("/noticeEditForm.do", new NoticeEditForm()); //공지사항 수정 폼
		map.put("/noticeUpdate.do", new NoticeUpdate()); //공지사항 수정
		map.put("/noticeDeleteForm.do", new NoticeDeleteForm()); //공지사항 삭제
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 요청을 분석, 실행, 결과를 돌려주는 곳
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		// 페이지값(키값)을 넘겨주면 value값을 리턴해줌
		Command command = map.get(page);
		String viewPage = command.exec(request, response);

		// 실행 된 결과를 통해서 뷰를 보여줘여함-어떤 페이지를 보여줄지 , 어떤것을 보여줄지(뷰리졸브)
		// view Resolve start
		if (!viewPage.endsWith(".do")) {
			if (viewPage.startsWith("Ajax:")) {
				// Ajax 라는 단어가 붙어있으면 처리하는 방법
				//컨텐츠 타입넣어줌
				//System.out.println(viewPage.substring(5)+ "=====================");
				response.setContentType("text/html; charset=UTF-8");
				//Ajax:viewPage 의 형태로 오기때문에 viewPage(데이터)만 받기위해서 
				//Ajax:  -> 5글자  => 얘 뒤부터 데이터라는 말임
				response.getWriter().print(viewPage.substring(5));
				return;  //★★★
			} else if(!viewPage.endsWith(".tiles")){
				//tiles가 아니면	//타일즈 적용 안하는 것	//타일즈 태우면 그냥 돔		
				viewPage = "WEB-INF/views/" + viewPage + ".jsp";
				
			}
			//tiles가 맞으면//
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(viewPage);
		}
		//view Resolve end
	}
}
