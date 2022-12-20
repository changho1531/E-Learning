package dbAll;
import java.util.ArrayList;
import java.util.List;

public class SignUpDAO extends DBConn{

	public SignUpDAO() {}
	
	// 회원가입 db insert
	public int SignUpInsert(DBVo vo) {
		int result = 0;
		try{
			getConn();
			sql = "insert into member(ID,PW,NAME,STUID,PHONENUM,ADDRESS,MAJOR,JOB,GRADE,CLASS) "
			+ " values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getUser_pwd());
			pstmt.setString(3, vo.getUser_name());
			pstmt.setInt(4, vo.getUser_StudentNumber());
			pstmt.setString(5, vo.getUser_tel());
			pstmt.setString(6, vo.getUser_address());
			pstmt.setString(7, vo.getUser_major());
			pstmt.setString(8, vo.getUser_job());
			pstmt.setString(9, vo.getUser_grade());
			pstmt.setString(10, vo.getUser_gruop());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	
	// 회원 아이디 비밀번호 검색, 회원 유무 확인
	public List<DBVo> getidCheck(String user_id){
		List<DBVo> lst = new ArrayList<DBVo>();		
		try {
			getConn();
			sql = "select ID from member where ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DBVo vo = new DBVo();
				vo.setUser_id(rs.getString(1));
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
	// 학번 검색
		public List<DBVo> getidCheck(int user_sn){
			List<DBVo> lst = new ArrayList<DBVo>();		
			try {
				getConn();
				sql = "select STUID from member where STUID = ?";
				System.out.println("!");
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user_sn);
				
				rs = pstmt.executeQuery();
				System.out.println(sql);
				while(rs.next()) {
					System.out.println("!");
					DBVo vo = new DBVo();
					vo.setUser_StudentNumber(rs.getInt(1));
					lst.add(vo);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
			return lst;
		}
}