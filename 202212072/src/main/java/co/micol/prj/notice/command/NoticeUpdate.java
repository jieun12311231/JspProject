package co.micol.prj.notice.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.micol.prj.common.Command;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;
import co.micol.prj.notice.serviceImpl.NoticeServiceImpl;

public class NoticeUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 수정
		
		NoticeService dao = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();

		String saveDir = request.getServletContext().getRealPath("/attech/"); // 현재 프로젝트 디렏토리로
		int maxSize = 1024 * 1024 * 1024; // 최대 10M까지 업로드
		try { // 파일 업로드시 request를 대체form에서 멀티파트로 올라오기때문에 그것을 읽을수없음 //넘어오는것, 저장하는것, 최대사이즈, 넘어오는
				// 파일 인코팅 타입,
			MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, "utf-8",
					new DefaultFileRenamePolicy());
			
			//수정할때는 id가 필요함
			vo.setNoticeId(Integer.valueOf(multi.getParameter("noticeId")));
			vo.setNoticeDate(Date.valueOf(multi.getParameter("noticeDate")));
			vo.setNoticeHit(Integer.valueOf(multi.getParameter("noticeHit")));
			vo.setNoticeTitle(multi.getParameter("noticeTitle"));
			vo.setNoticeSubject(multi.getParameter("noticeSubject"));

			System.out.println(vo.getNoticeId());
			String ofileName = multi.getOriginalFileName("nfile"); // -> 원본파일명을 가지고 옴

			String pfileName = multi.getFilesystemName("nfile"); // -> 이게 실행되는 순간 파일을 실제로 저장함.

			if (ofileName != "") { // ofileName이 비어있지 않으면 -> vo에 실어야함
				vo.setNoticeFile(ofileName); // -> 오리지널 네임을 담고
				pfileName = saveDir + pfileName; // -> 정확하게 저장된 위치를 알기 위해 저장directory와 저장명
				vo.setNoticeFileDir(pfileName);
			}

			int n = dao.noticeUpdate(vo); // vo객체로 보내버리기
			if (n != 0) {
				request.setAttribute("message", "공지사항이 수정되었습니다.");
			} else {

				request.setAttribute("message", "공지사항이 수정 실패하였습니다.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
			return "notice/noticeMessage.tiles";
	}

}
