package adminGui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	private static String url = "jdbc:oracle:thin:@222.237.255.159:1521:xe";
	private static String userid = "javadb";
	private static String pwd ="1111";
	private static Connection con=null;
	private static Statement state =null;
	public DBConnect() {
		try {
			con = DriverManager.getConnection(url, userid, pwd);
			state = con.createStatement();
			System.out.println("성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실패");
			e.printStackTrace();
		}
	}
	public static void Connet() {
		try {
			con = DriverManager.getConnection(url, userid, pwd);
			state = con.createStatement();
			System.out.println("성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("실패");
			e.printStackTrace();
		}
	}
	public static ResultSet DataSelect(String sql) {//DB에 값 조회
		try {
			Connet();
			return state.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void DataUpdate(String sql) {//DB에 값 넣기, 삭제, 수정
		try {
			Connet();
			state.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void Close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
