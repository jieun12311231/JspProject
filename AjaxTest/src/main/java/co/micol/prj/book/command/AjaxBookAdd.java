package co.micol.prj.book.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.impl.BookServiceImpl;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class AjaxBookAdd implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 도서등록
		BookVO vo = new BookVO();
		vo.setBookCode(request.getParameter("code"));
		vo.setBookTitle(request.getParameter("title"));
		vo.setBookAuthor(request.getParameter("author"));
		vo.setBookPress(request.getParameter("press"));
		vo.setBookPrice(Integer.valueOf(request.getParameter("price")));
		
		BookService dao = new BookServiceImpl();
		dao.bookInsert(vo);
		System.out.println("추가============="+vo.getBookAuthor());
		String json = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			json = mapper.writeValueAsString(vo);
			System.out.println("추가=============="+json);
		} catch (JsonProcessingException e) {
			// 
			e.printStackTrace();
		}
		
		return "ajax:" + json;
	}

}
