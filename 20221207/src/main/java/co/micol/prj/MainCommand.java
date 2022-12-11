package co.micol.prj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.common.Command;

//메인이니까 기본페키지에 만들어줌
public class MainCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 첫페이지에 보여주는 곳
		return "main/main.tiles";
	}

}
