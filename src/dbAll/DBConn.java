package dbAll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			System.out.println("DB로딩 에러발생 "+e.getMessage());
		}
	}
	String sql = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement state = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@222.237.255.159:1521:xe";
	String userid = "javadb";
	String pwd ="1111";
	public DBConn() {}
	// DB 열기
	public Connection getConn() {
		try {
			conn = DriverManager.getConnection(url, userid, pwd);
			state = conn.createStatement();
			System.out.println("성공");
		}catch(Exception e) {
			System.out.println("DB연결 에러발생"+e.getMessage());
		}
		return conn;
	}
	
	// DB 닫기
	public void dbClose() {
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
		}catch(Exception e) {
			System.out.println("DB종료가 실패하였습니다."+e.getMessage());
		}
	}
	
	public ResultSet DataSelect(String sql) {//DB에 값 조회
		try {
			return state.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void DataUpdate(String sql) {//DB에 값 넣기, 삭제, 수정
		try {
			state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}