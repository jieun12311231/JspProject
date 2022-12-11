package co.micol.prj.notice.service;

import java.util.List;

public interface NoticeService {
	
	List<NoticeVO> noticeSelectList(); //전체 리스트 가지고오기 
	List<NoticeVO> noticeSelect(NoticeVO vo);//상세 조회 -> 첨부파일 때문에 리스트로 받음
	
	int noticeInsert(NoticeVO vo);//게시글 저장
	int noticeDelete(NoticeVO vo);//게시글 삭제
	int noticeUpdate(NoticeVO vo);//게시글 수정
	
	
	int noticeAttechInster(NoticeAttechVO vo); //첨부파일 저장
	int noticeAttechDelete(NoticeAttechVO vo);//첨부파일 삭제
		
	List<NoticeVO> noticeSearchList(String key,String val); //게시글 내 검색을 위해
	//제목별검색, 내용별 검색, 저자별 검색 
	
}
