package kr.or.ddit.user.model;

public class UserVo {

	private String userid; 
	private String usernm;
	
	
	//인자가 있는 생성자가 있었으니까 인자가 없는 생성자 만들어 놓기 !!!!! 
	public UserVo() {}
	
	public UserVo(String userid, String usernm) {
	
		setUserid(userid);
		setUsernm(usernm);
	
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsernm() {
		return usernm;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	
	
	//tostring
	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", usernm=" + usernm + "]";
	} 
	
	
}
