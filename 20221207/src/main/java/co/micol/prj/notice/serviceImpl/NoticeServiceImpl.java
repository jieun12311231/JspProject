package co.micol.prj.notice.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.prj.common.DataSource;
import co.micol.prj.notice.map.NoticeMapper;
import co.micol.prj.notice.service.NoticeAttechVO;
import co.micol.prj.notice.service.NoticeService;
import co.micol.prj.notice.service.NoticeVO;

public class NoticeServiceImpl implements NoticeService {
	//오토커밋
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	
	private NoticeMapper map = sqlSession.getMapper(NoticeMapper.class);
	
	//나랑 이름이 똑같은 것을 리턴해주면됨!
	@Override
	public List<NoticeVO> noticeSelectList() {
		// 
		return map.noticeSelectList();
	}

	@Override
	public List<NoticeVO> noticeSelect(NoticeVO vo) {
		// TODO Auto-generated method stub
		return map.noticeSelect(vo);
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		// TODO Auto-generated method stub
		return map.noticeInsert(vo);
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		// TODO Auto-generated method stub
		return map.noticeDelete(vo);
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		// TODO Auto-generated method stub
		return map.noticeUpdate(vo);
	}

	@Override
	public int noticeAttechInster(NoticeAttechVO vo) {
		// TODO Auto-generated method stub
		return map.noticeAttechInster(vo);
	}

	@Override
	public int noticeAttechDelete(NoticeAttechVO vo) {
		// TODO Auto-generated method stub
		return map.noticeAttechDelete(vo);
	}

	@Override
	public List<NoticeVO> noticeSearchList(String key, String val) {
		// TODO Auto-generated method stub
		return map.noticeSearchList(key, val);
	}

}
