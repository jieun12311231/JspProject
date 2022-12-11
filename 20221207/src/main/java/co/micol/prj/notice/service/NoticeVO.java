package co.micol.prj.notice.service;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeVO {
	//저장할때만 사용
	private int noticeId;
	private String noticeWriter;
	private Date noticeDate;
	private String noticeTitle;
	private String noticeSubject;
	private int noticeHit;
	
	//join을 위한 확장 -> join된 결과를 가지고 오기 위해서 이렇게 사용함.
	//조회할 때 위의 필드와 같이 사용함.  
	private int attechId;
	private String noticeFile;
	private String noticeFileDir;
	
}
