package dbAll;

public class ModifyPW extends DBConn{
	public ModifyPW(String idFind, String mp_tf_PW) {
		try {
			getConn();
			sql = "update member set PW = ? where ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mp_tf_PW);
			pstmt.setString(2, idFind);
			pstmt.executeUpdate();
			pstmt.close();
		} catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			dbClose();
		}
	}
}

