package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class ClassAdd extends JFrame{

	private JPanel InsertPanel_1;
	private JPanel panelSouth;
	private JButton UpLodbtn;
	private String infoName,infoStuid,infoMajor;
	private ResultSet rs;
	private Main studentMain;
	public ClassAdd(String infoName, String infoStuid, String infoMajor,Main studentMain) {
		this.infoName=infoName;
		this.infoStuid=infoStuid;
		this.infoMajor=infoMajor;
		this.studentMain=studentMain;
		setTitle("강의개설");
		setLocation(900, 200);
		setSize(450, 300);
		setLayout(new BorderLayout());

		setCenter();		

		setVisible(true);
	}

	private void setCenter() {
		InsertPanel_1 = new JPanel();
		InsertPanel_1.setLayout(null);
		add(InsertPanel_1);

		JPanel InsertPanel_2 = new JPanel();
		InsertPanel_2.setLocation(20, 10);
		InsertPanel_2.setSize(400, 200);
		InsertPanel_2.setBackground(Color.WHITE);
		InsertPanel_2.setLayout(null);
		InsertPanel_1.add(InsertPanel_2);

		EtchedBorder eborder =  new EtchedBorder();
		InsertPanel_2.setBorder(eborder);
		UpLodbtn = new JButton("저장");
		UpLodbtn.setSize(75,25);
		UpLodbtn.setLocation(340, 220);
		InsertPanel_1.add(UpLodbtn);



		JLabel InsertLabel_Major = new JLabel("학과 : ");
		InsertLabel_Major.setSize(70,40);
		InsertLabel_Major.setLocation(50, 5); 
		InsertLabel_Major.setFont(new Font("함초롬바탕", Font.BOLD, 15));//x,y
		InsertPanel_2.add(InsertLabel_Major);

		JLabel major = new JLabel(infoMajor);
		major.setSize(120,35);
		major.setLocation(100,8);
		major.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
		InsertPanel_2.add(major);



		JLabel InsertLabel_Credit = new JLabel("학점 : ");
		InsertLabel_Credit.setSize(70,40);
		InsertLabel_Credit.setLocation(280, 55); 
		InsertLabel_Credit.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		InsertPanel_2.add(InsertLabel_Credit);

		String[] ban_2 = {"1", "2", "3", "4","5","6"};  
		JComboBox InsertComboBox_Credit = new JComboBox(ban_2);
		((JLabel)InsertComboBox_Credit.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_Credit.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
		InsertComboBox_Credit.setBackground(Color.WHITE);
		InsertComboBox_Credit.setSize(65, 35);
		InsertComboBox_Credit.setLocation(325, 57); 
		InsertPanel_2.add(InsertComboBox_Credit);
		
		JLabel InsertLabel_Class = new JLabel("반 : ");
		InsertLabel_Class.setSize(70,40);
		InsertLabel_Class.setLocation(290, 5); 
		InsertLabel_Class.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		InsertPanel_2.add(InsertLabel_Class);

		String[] Class = {"A","B","C"};  
		JComboBox InsertComboBox_Class = new JComboBox(Class);
		((JLabel)InsertComboBox_Class.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_Class.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
		InsertComboBox_Class.setBackground(Color.WHITE);
		InsertComboBox_Class.setSize(65, 35);
		InsertComboBox_Class.setLocation(325, 7); 
		InsertPanel_2.add(InsertComboBox_Class);
		
		JLabel InsertLabel_Grade = new JLabel("학년 : ");
		InsertLabel_Grade.setSize(70,40);
		InsertLabel_Grade.setLocation(160, 5); 
		InsertLabel_Grade.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		InsertPanel_2.add(InsertLabel_Grade);
		
		String[] grade= {"1", "2", "3"};  
		JComboBox InsertComboBox_Grade = new JComboBox(grade);
		((JLabel)InsertComboBox_Grade.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_Grade.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
		InsertComboBox_Grade.setBackground(Color.WHITE);
		InsertComboBox_Grade.setSize(65, 35);
		InsertComboBox_Grade.setLocation(205, 7); 
		InsertPanel_2.add(InsertComboBox_Grade);

		JLabel InsertLabel_Subject = new JLabel("과목명 : ");
		InsertLabel_Subject.setSize(70,40);
		InsertLabel_Subject.setLocation(38, 55); 
		InsertLabel_Subject.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		InsertPanel_2.add(InsertLabel_Subject);
		Vector<String> classNameVector = new Vector<String>();
		String sql = "select classtitle from classcode where major like '"+infoMajor+"'";
		rs=DBConnect.DataSelect(sql);
		try {
			while(rs.next()) {
				classNameVector.add(rs.getString("classtitle"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBConnect.Close();
		}
		JComboBox InsertComboBox_ClassName = new JComboBox(classNameVector);
		InsertComboBox_ClassName.setSize(170,35);
		InsertComboBox_ClassName.setLocation(100,57);
		InsertComboBox_ClassName.setFont(new Font("함초롱돋움",Font.PLAIN,15));
		((JLabel)InsertComboBox_ClassName.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_ClassName.setBackground(Color.WHITE);
		InsertPanel_2.add(InsertComboBox_ClassName);



		JLabel InsertLabel_StartTime = new JLabel("시작시간 : ");
		InsertLabel_StartTime.setSize(70, 40);
		InsertLabel_StartTime.setLocation(25, 105);
		InsertLabel_StartTime.setFont(new Font("함초롬바탕", Font.BOLD, 14));
		InsertPanel_2.add(InsertLabel_StartTime);

		String[] ban_3 = {"1", "2", "3","4","5","6","7","8","9","10","11"};    
		JComboBox InsertComboBox_StartTime = new JComboBox(ban_3);
		((JLabel)InsertComboBox_StartTime.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_StartTime.setBackground(Color.WHITE);
		InsertComboBox_StartTime.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		InsertComboBox_StartTime.setSize(80, 35);
		InsertComboBox_StartTime.setLocation(100, 109); 
		InsertPanel_2.add(InsertComboBox_StartTime);




		JLabel InsertLabel_EndTime = new JLabel("종료시간 : ");
		InsertLabel_EndTime.setSize(70, 40);
		InsertLabel_EndTime.setLocation(230, 105);
		InsertLabel_EndTime.setFont(new Font("함초롬바탕", Font.BOLD, 14));
		InsertPanel_2.add(InsertLabel_EndTime);


		String[] ban_4 = {"1", "2", "3","4","5","6","7","8","9","10","11"};  
		JComboBox InsertComboBox_EndTime = new JComboBox(ban_4);
		((JLabel)InsertComboBox_EndTime.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_EndTime.setBackground(Color.WHITE);
		InsertComboBox_EndTime.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		InsertComboBox_EndTime.setSize(80, 35);
		InsertComboBox_EndTime.setLocation(310, 109); 
		InsertPanel_2.add(InsertComboBox_EndTime);



		JLabel InsertLabel_Week = new JLabel("요일 : ");
		InsertLabel_Week.setSize(70, 40);
		InsertLabel_Week.setLocation(255, 155);
		InsertLabel_Week.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		InsertPanel_2.add(InsertLabel_Week);

		String[] ban_5 = {"월", "화", "수","목","금"};  
		JComboBox InsertComboBox_Week = new JComboBox(ban_5);
		((JLabel)InsertComboBox_Week.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_Week.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
		InsertComboBox_Week.setBackground(Color.WHITE);
		InsertComboBox_Week.setSize(80, 35);
		InsertComboBox_Week.setLocation(310, 155); 
		InsertPanel_2.add(InsertComboBox_Week);



		JLabel InsertLabel_ClasRoom = new JLabel("강의실 : ");
		InsertLabel_ClasRoom.setSize(70,40);
		InsertLabel_ClasRoom.setLocation(35, 155);
		InsertLabel_ClasRoom.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		InsertPanel_2.add(InsertLabel_ClasRoom);

		String[] ban_6 = {"7-318호", "7-401호", "7-522호","4-205호","4-402호","4-404호","4-406호","4-407호"};  
		JComboBox InsertComboBox_ClassRoom = new JComboBox(ban_6);
		((JLabel)InsertComboBox_ClassRoom.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InsertComboBox_ClassRoom.setFont(new Font("함초롬돋움", Font.PLAIN, 13));
		InsertComboBox_ClassRoom.setBackground(Color.WHITE);
		InsertComboBox_ClassRoom.setSize(80, 35);
		InsertComboBox_ClassRoom.setLocation(100, 155); 
		InsertPanel_2.add(InsertComboBox_ClassRoom);
		UpLodbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(InsertComboBox_StartTime.getSelectedIndex()<=InsertComboBox_EndTime.getSelectedIndex()) {
					int check=0;
					int i=Integer.parseInt(InsertComboBox_StartTime.getItemAt(InsertComboBox_StartTime.getSelectedIndex())+"");
					for(;i<Integer.parseInt(InsertComboBox_EndTime.getItemAt(InsertComboBox_EndTime.getSelectedIndex())+"")+1;i++) {
						String sql = "select * from class where proid like "+infoStuid+" and time like "+i+" and \"Day\" like '"
								+InsertComboBox_Week.getItemAt(InsertComboBox_Week.getSelectedIndex())+"'";
						System.out.println(sql);
						rs=DBConnect.DataSelect(sql);
						try {
							while(rs.next()) {
								if(rs.getString("classtitle")!=null) {
									check++;
									break;
								}
								else {
								}
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(check!=0) {
							break;
						}
					}
					System.out.println(check);
					if(check==0) {
						i=Integer.parseInt(InsertComboBox_StartTime.getItemAt(InsertComboBox_StartTime.getSelectedIndex())+"");
						for(;i<Integer.parseInt(InsertComboBox_EndTime.getItemAt(InsertComboBox_EndTime.getSelectedIndex())+"")+1;i++){
							String sql = "INSERT INTO CLASS values((SELECT code FROM classcode WHERE CLASSTITLE LIKE '"
									+InsertComboBox_ClassName.getItemAt(InsertComboBox_ClassName.getSelectedIndex())+"'),"
									+ "'"+InsertComboBox_ClassName.getItemAt(InsertComboBox_ClassName.getSelectedIndex())
									+"',"+InsertComboBox_Credit.getItemAt(InsertComboBox_Credit.getSelectedIndex())+","
									+ "'"+InsertComboBox_ClassRoom.getItemAt(InsertComboBox_ClassRoom.getSelectedIndex())+"',"
									+infoStuid+","+i+",'"+infoName+"','"+infoMajor+"',"
									+ "'"+InsertComboBox_Week.getItemAt(InsertComboBox_Week.getSelectedIndex())+"',"
									+InsertComboBox_Grade.getItemAt(InsertComboBox_Grade.getSelectedIndex())+",'"
									+InsertComboBox_Class.getItemAt(InsertComboBox_Class.getSelectedIndex())+"')";
							DBConnect.DataUpdate(sql);
						}
						studentMain.setProScheduleData();
						dispose();
						DBConnect.Close();
					}
					else {
						JOptionPane.showMessageDialog(null, "이미 그 시간에는 스케쥴이 있습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "시작시간보다 종료시간이 더 빠를 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

	}
}