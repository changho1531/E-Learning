package dbAll;

public class FindDAO extends DBConn{
	
	public String getidpwFind(String user_name, int user_SN, String user_PN, String user_major, String user_job, int i){
		String id = null;
		String pw = null;
		try {
			sql = "select ID, PW from member where NAME like ? and STUID like ? and PHONENUM like ? and MAJOR like ? and JOB like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setInt(2, user_SN);
			pstmt.setString(3, user_PN);
			pstmt.setString(4, user_major);
			pstmt.setString(5, user_job);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getString("ID");
				pw = rs.getString("PW");
			}
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {}
		// 아이디 찾기 버튼 or 비밀번호 재설정 버튼 실행 시 (아이디 : 0반환, 비밀번호 : 1반환)
		if (i == 0) {
			return id;
		}
		else {
			return pw;
		}
	}

	// 아이디, 비밀번호 찾기 / 회원 유무 확인
	public boolean getFindCheck(String user_name, int user_SN, String user_PN, String user_major, String user_job) {
		try {
			getConn();
			sql = "select ID, PW from member where NAME like ? and STUID like ? and PHONENUM like ? and MAJOR like ? and JOB like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setInt(2,user_SN);
			pstmt.setString(3, user_PN);
			pstmt.setString(4, user_major);
			pstmt.setString(5, user_job);
			rs = pstmt.executeQuery();
			return rs.next();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {}
		return false;
	}
}
