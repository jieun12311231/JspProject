package co.micol.prj.notice.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;


public class NoticeEditForm implements Command {

	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 수정 폼 호출
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(Integer.valueOf(request.getParameter("noticeId")));
		vo = dao.noticeSelect(vo);
		request.setAttribute("notices", vo);
		
		
		return "notice/noticeEditForm.tiles";
	}

}
