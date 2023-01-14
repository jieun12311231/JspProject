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

public class NoticeInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 공지사항 글 등록 하기 MutiPartRequest를 이용해야한다 -첨부파일이 있기때문에
		//1)cos.jar 이용하는 방법 2) part객체 3) apachefile upload방법 
		
		//cos.jar 이용
		NoticeService dao = new NoticeServiceImpl();
		 
		NoticeVO vo = new NoticeVO();
		
		String saveDir = request.getServletContext().getRealPath("/attech/"); //현재 프로젝트 디렏토리로
		
		int maxSize = 1024*1024*1024; //최대 10M까지 업로드
		
		//멀티파트 객체(multi)를 초기화할 때 
		try {    //파일 업로드시 request를 대체form에서 멀티파트로 올라오기때문에 그것을 읽을수없음   //넘어오는것, 저장하는것, 최대사이즈, 넘어오는 파일 인코팅 타입,
			//DefaultFileRenamePolicy동일한 이름이 있으면 자동으로 (1)(2)
			MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, "utf-8" , new DefaultFileRenamePolicy());

			//파일이 없더라도 무조건 저장되어야함 request로 보낼수없어서 multi를 이용해여야함
			//등록할때는 작성자를 불러와야함.(수정할때는 id)
			vo.setNoticeWriter(multi.getParameter("noticeWriter"));
			vo.setNoticeDate(Date.valueOf(multi.getParameter("noticeDate")));
			vo.setNoticeTitle(multi.getParameter("noticeTitle"));
			vo.setNoticeSubject(multi.getParameter("noticeSubject"));
			
			//System.out.println(vo);
			
			//오리지날 파일 이름이 ofileName여기로 들어옴nfile이건 폼에서 넘기는 파일이름
			String ofileName = multi.getOriginalFileName("nfile"); //-> 원본파일명을 가지고 옴 
			
			//물리적인 파일이름 저장 될 이름 -실제 저장되는 이름 -> 동일한 이름이 들어오면 자동으로 (1), (2)이 붙음
			String pfileName =multi.getFilesystemName("nfile");  //-> 이게 실행되는 순간 파일을 실제로 저장함.
			
			if(ofileName != "") { //ofileName이 비어있지 않으면 -> vo에 실어야함
				vo.setNoticeFile(ofileName);  //-> 오리지널 네임을 담고
				pfileName = saveDir + pfileName;  //-> 정확하게 저장된 위치를 알기 위해 저장directory와 저장명
				vo.setNoticeFileDir(pfileName);
			}
			
			int n = dao.noticeInsert(vo);  //vo객체로 보내버리기 
			if( n != 0) {
				request.setAttribute("message", "공지사항이 등록되었습니다.");
			}else {
				
				request.setAttribute("message", "공지사항이 등록 실패하였습니다.");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
		return "notice/noticeMessage.tiles";
	}

}
