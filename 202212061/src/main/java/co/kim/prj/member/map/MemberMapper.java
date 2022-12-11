package co.kim.prj.member.map;

import java.util.List;

import co.kim.prj.member.service.MemberVO;

public interface MemberMapper {

	List<MemberVO> memberSelectList();
	
	MemberVO memberSelect(MemberVO vo);
	
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	boolean isIdCheck(String id); 
}
