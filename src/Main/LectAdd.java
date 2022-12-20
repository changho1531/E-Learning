package Main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class LectAdd extends JFrame{
	private String grade, classN, classCode;
	private int row;
	Main stmain;
	public LectAdd(String grade, String classN,String classCode,int row, Main stmain) {
		this.grade=grade;
		this.classN=classN;
		this.classCode=classCode;
		this.row=row;
		this.stmain = stmain;
		setTitle("강의 업로드");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 300);
		setSize(210, 250);
		setLayout(new FlowLayout());
		
		Container c = getContentPane();
		
		JLabel addLbl = new JLabel("        강의 등록       ");
		addLbl.setFont(new Font("a", Font.BOLD, 25));
		c.add(addLbl);
		JLabel space = new JLabel("____________________________");
		c.add(space);
		JLabel titleLbl = new JLabel("강의명");
		JTextField tfTitle = new JTextField(15);
		JLabel linkLbl = new JLabel("강의링크");
		JTextField tfLink = new JTextField(15);
		JButton btnAdd = new JButton("업로드");
		btnAdd.setFont(new Font("a", Font.BOLD, 15));
		btnAdd.setBackground(Color.LIGHT_GRAY);
		btnAdd.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		c.add(titleLbl);
		c.add(tfTitle);
		c.add(linkLbl);
		c.add(tfLink);
		c.add(btnAdd);
		
		c.setBackground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql;
				sql = "select max(videonum) from video where class like '"+classN+"' and grade like "+grade+" and classcode like "+classCode;
				ResultSet rs;
				rs=DBConnect.DataSelect(sql);
				try {
					rs.next();
					if(rs.getString("max(videonum)")==null) {
						sql = "insert into video values('"+tfLink.getText()+"',"+classCode+",'"+tfTitle.getText()+"',"
								+"1, "
								+grade+", '"+classN+"')";
					}
					else {
						sql = "insert into video values('"+tfLink.getText()+"',"+classCode+",'"+tfTitle.getText()+"',"
								+"(SELECT max(videonum)+1 VIDEONUM FROM video WHERE CLASSCODE LIKE "+classCode+" AND CLASS LIKE '"+classN+"' AND GRADE like "+grade+"), "
								+grade+", '"+classN+"')";
					}
					DBConnect.DataUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DBConnect.Close();
					stmain.setInClassRoomData(row);
					dispose();
				}
			}
		});
		
		setVisible(true);
	}
}
