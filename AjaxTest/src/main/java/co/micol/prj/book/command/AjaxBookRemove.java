package co.micol.prj.book.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.impl.BookServiceImpl;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class AjaxBookRemove implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 삭제
		String id = request.getParameter("id"); 
		BookVO vo = new BookVO();
		vo.setBookCode(id);
		
		BookService dao = new BookServiceImpl();
		
		int delCnt = dao.bookDelete(vo);
		
		String json = null;
		if (delCnt > 0) {
			
			json = "{\"retCode\":\"Success\",\"id\": " + id + "}";
		} else {
			
			json = "{\"retCode\":\"False\"}";
		}

		return "ajax:" + json;
	}

}
