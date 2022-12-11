package co.micol.prj.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	//함수원형만 설정해놓으면됨
	//커맨드를받아서 결과를 찾아줄것
	//결과를 찾아서 String으로 돌려줌
	String exec(HttpServletRequest request, HttpServletResponse response);
}
