package co.micol.prj.notice.map;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.prj.notice.service.NoticeAttechVO;
import co.micol.prj.notice.service.NoticeVO;

public interface NoticeMapper {

	List<NoticeVO> noticeSelectList(); //전체 리스트 가지고오기 
	List<NoticeVO> noticeSelect(NoticeVO vo);//상세 조회 -> 첨부파일 때문에 리스트로 받음
	
	int noticeInsert(NoticeVO vo);//게시글 저장
	int noticeDelete(NoticeVO vo);//게시글 삭제
	int noticeUpdate(NoticeVO vo);//게시글 수정
	
	
	int noticeAttechInster(NoticeAttechVO vo); //첨부파일 저장
	int noticeAttechDelete(NoticeAttechVO vo);//첨부파일 삭제
		
	List<NoticeVO> noticeSearchList(@Param("key") String key,@Param("val") String val); //게시글 내 검색을 위해
	//★★★두개ㅣ상의 매개변수를 저장하려면 @Param사용해야함(파라미터) //Mapper에만 존재
	//저쪽에서 넘어온 key을 String key로 쓰겠다.
	//저쪽에서 넘어온 val을 String val로 쓰겠다
}
