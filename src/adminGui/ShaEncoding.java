package adminGui;

import java.security.MessageDigest;
import java.util.Arrays;

public class ShaEncoding {
	// ==== [SHA256 인코딩 - 문자열] ====
	public static String getSHA256(String data) {
		String SHA = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(data.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			SHA = null;
		}
		return SHA;
	}
}