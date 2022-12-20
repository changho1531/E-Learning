package dbAll;
import adminGui.ShaEncoding;
public class DBVo {
	private String user_id;
	private String user_pwd;
	private String user_name;
	private int user_StudentNumber;
	private String user_tel;
	private String user_address;
	private String user_major;
	private String user_job;
	private String user_grade;
	private String user_gruop;

	public DBVo(String user_id, String user_pwd, 
			String user_name, int user_StudentNumber, String user_tel, String user_address, 
			String user_major, String user_job, String user_grade, String user_gruop) {
		this.user_id = user_id;
		this.user_pwd = ShaEncoding.getSHA256(user_pwd);//암호화
		this.user_name = user_name;
		this.user_StudentNumber = user_StudentNumber;
		this.user_tel = user_tel;
		this.user_address = user_address;
		this.user_major = user_major;
		this.user_job = user_job;
		if(user_job.equals("교수")) {
			this.user_grade = "0";
			this.user_gruop = "0";
		}
		else {
		this.user_grade = user_grade;
		this.user_gruop = user_gruop;
		}
	}
	
	public DBVo() {
	}
	
	public DBVo(String user_id) {
		this.user_id = user_id;
	}
	
	// get/set 메서드
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_StudentNumber() {
		return user_StudentNumber;
	}
	public void setUser_StudentNumber(int i) {
		this.user_StudentNumber = i;
	}
	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	public String getUser_major() {
		return user_major;
	}
	
	public void setUser_major(String user_major) {
		this.user_major = user_major;
	}
	
	public String getUser_job() {
		return user_job;
	}
	
	public void setUser_job(String user_job) {
		this.user_job = user_job;
	}public String getUser_grade() {
		return user_grade;
	}
	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}
	public String getUser_gruop() {
		return user_gruop;
	}
	public void setUser_gruop(String user_gruop) {
		this.user_gruop = user_gruop;
	}
	
}