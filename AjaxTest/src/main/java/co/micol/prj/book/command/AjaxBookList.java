package co.micol.prj.book.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.impl.BookServiceImpl;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class AjaxBookList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 리스트 출력
		BookService dao = new BookServiceImpl();
		List<BookVO> list = dao.bookSelectList();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = null;
//		System.out.println(list);
		try {
			json = mapper.writeValueAsString(list);
//			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "ajax:" + json;
	}

}
