package co.micol.prj.member.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.prj.common.DataSource;
import co.micol.prj.member.map.MemberMapper;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.service.MemberVO;

public class MemberServiceImpl implements MemberService {
//구현체
	//데이터베이스 연결, true을 안쓰면 오토 커밋이 안됨!
	SqlSession sqlSession = DataSource.getInstance().openSession(true); 
	
	//mapper연결 인터페이스는 자기자신을 초기화 못함
	// 
	MemberMapper map = sqlSession.getMapper(MemberMapper.class);
	
	//내 메서드와 동일한것 리턴하면됨
	@Override
	public List<MemberVO> memberSelectList() {

		
		return map.memberSelectList();
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		return map.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		return map.memberInsert(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		return map.memberDelete(vo);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		return map.memberUpdate(vo);
	}

	@Override
	public boolean isIdCheck(String id) {
		return map.isIdCheck(id);
	}

}
