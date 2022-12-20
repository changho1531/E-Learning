package dbAll;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO extends DBConn{
	public LoginDAO() {}
	
	public List<LoginVO> LoginAllSelect(){
		List<LoginVO> lst = new ArrayList<LoginVO>();
		try {
			getConn();
			sql = "select ID, PW from member";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LoginVO vo = new LoginVO(rs.getString(0),rs.getString(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	
	
	// 로그인 시, 회원인지 확인하는 메소드
	// 회원이 맞을 경우 1을 반환
	public int getLogin(String user_id, String user_pwd){
		int state = 0;
		try {
			getConn();
			sql = "select ID, PW from member where ID = ? and PW = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) 
				state = 1;
			
		} catch(Exception e) {
			System.out.println("DB 아이디 비밀번호 확인에러 " + e.getMessage());
		} finally {
			dbClose();
		}
		return state;
	}
	
}