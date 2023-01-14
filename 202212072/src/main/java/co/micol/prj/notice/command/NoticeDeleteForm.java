package co.micol.prj.notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeDeleteForm implements Command {

	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 삭제
		
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		
		
		vo.setNoticeId(Integer.valueOf(request.getParameter("noticeId")));
		

		
		int n = dao.noticeDelete(vo);
		
		if( n!= 0 ) {
			//게시글 삭제되면 공지 목록으로 이동
			return "noticeList.do";
		}else
			request.setAttribute("message", "공지사항 삭제가 실패했습니다.");
		
		return "notice/noticeMessage";
	}

}
