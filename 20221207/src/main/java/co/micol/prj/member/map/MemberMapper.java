package co.micol.prj.member.map;

import java.util.List;

import co.micol.prj.member.service.MemberVO;

public interface MemberMapper {
//MemberService의 내용과 같은 가끔 다를때가 있지만 거의 같음
//xml에서 사용함 
		List<MemberVO> memberSelectList();
		MemberVO memberSelect(MemberVO vo); 
		
		int memberInsert(MemberVO vo);
		int memberDelete(MemberVO vo);
		int memberUpdate(MemberVO vo);
		
		boolean isIdCheck(String id);
	
}
