package co.micol.prj.member.service;

import java.util.List;

public interface MemberService {
//DAO에서 사용 
	
	//전체 리스트 가지고 오기 
	//import java.util.List; 임포트 
	//값을 VO에 담아서 vo에 넣음
	//한사람의 정보가 한꺼번에 담겨져셔 오는 것이기 때문에 List로 담아줘야함
	//한 레코드에 이름, 전화번호 아이디 등등 여러가지 정보가 담겨져있음 
	List<MemberVO> memberSelectList();
	
	//한사람 조회 또는 로그인 
	MemberVO memberSelect(MemberVO vo); 
	
	//sql디벨로퍼에서 DML을 했을때 콘솔에 1행이 삭제(추가,수정)되었습니다. 라고 문구가 뜸
	//그렇기때문에 '1'이 넘어오게됨 >> int타입으로 적어주는 것임 
	
	//입력
	int memberInsert(MemberVO vo);
	//삭제
	int memberDelete(MemberVO vo);
	//수정
	int memberUpdate(MemberVO vo);
	
	
	//회원가입 시 id 중복체크 
	//is -id가 존재하면 false값을 넘겨줌 // 존재하지 않는다 true(사용가능하다)
	boolean isIdCheck(String id);
	
	
	
}
