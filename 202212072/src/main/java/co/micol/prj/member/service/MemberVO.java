package co.micol.prj.member.service;
//DTO랑 같은것임! 
import lombok.Getter;
import lombok.Setter;

//jsp는 getter,setter까지만 만들어도됨
//어노테이션 사이에는 주석 넣으면 안됨 
//게터세터를 만들어줌 임포트하면 생김 >> 롬복이 있어서 사용할수있는 것임 
//setter를 통해서 값을 주고
//getter로 값을 가지고 옴
//VO 객체는 테이블과 1:1로 대응 됨,
//private를 안하고 public을 하면 정보 은닉에 위배됨 
//setter을 안넣으면 값을 이미 가지고있는 상태 
//getter랑setter중에 하나만 있는 경우도 있음
//필요없는 요소의 게터세터는 안만들어도됨

//@Data - toString()까지 만들어줌 > java에서 사용

@Setter
@Getter
public class MemberVO {
	//필드이름 절대 틀리면안됨 마이바티스가 자동으로 변환해주기때문에 
	private String memberId;
	private String memberName;
	private String memberPassword;
	private int memberAge;
	private String memberAddress;
	private String memberTell;
	private String memberAuthor;
	
	
}
