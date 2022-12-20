package adminGui;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class MemberAddFrame extends JFrame {
	private JTextField [] attributeField;
	private JLabel [] attributeLabel;
	private JComboBox job;
	private JComboBox major;
	private String[] jobString= {"교수","학생"};
	private String[] majorString= {"컴시과","컴정과","건축과"};
	private JButton addButton;
	private AdminFrame af;
	private JButton cancelButton;
	private ResultSet rs;
	public String [] comboString = {"직책", "학과", "이름", "학번", "연락처", "주소", "ID", "PW"};
	public MemberAddFrame(AdminFrame af) {
		this.af=af;
		setTitle("정보 추가");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(700, 500);
		setSize(840, 400);
		setLayout(null);
		job=new JComboBox<>(jobString);
		major=new JComboBox<>(majorString);
		((JLabel)job.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)major.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		attributeField = new JTextField[comboString.length];
		attributeLabel = new JLabel[comboString.length];
		int y=25;
		int x=215;
		int c=0;
		for(int i=0;i<comboString.length;i++) {
			attributeField[i] = new JTextField();
			attributeLabel[i] = new JLabel(comboString[i]);
			attributeField[i].setSize(100,25);

			if(i==0) {
				job.setLocation(x+100*i,y);
				job.setSize(100, 25);
				job.setBackground(Color.WHITE);
				add(job);
			}
			else if(i==1) {
				major.setLocation(x+100*i,y);
				major.setSize(100,25);
				major.setBackground(Color.WHITE);
				add(major);
			}
			else {
				attributeField[i].setSize(100, 25);
				attributeField[i].setLocation(x+100*i-c*400,y);
				add(attributeField[i]);
			}
			attributeLabel[i].setSize(100, 25);
			attributeLabel[i].setLocation(x+100*i-c*400, 50+y-25);
			attributeLabel[i].setHorizontalAlignment(JLabel.CENTER);
			add(attributeLabel[i]);
			if(i==3) {
				y=75;
				c=1;
			}
		}
		BevelBorder bor = new BevelBorder(BevelBorder.RAISED);
		addButton = new JButton("Add");
		cancelButton = new JButton("Cancel");
		addButton.setBorder(bor);
		cancelButton.setBorder(bor);
		addButton.setBackground(new Color(136,210,255));
		cancelButton.setBackground(new Color(136,210,255));
		addButton.setSize(150,50);
		addButton.setLocation(235,185);
		cancelButton.setSize(150,50);
		cancelButton.setLocation(435, 185);
		String[] grade = {"1","2","3","0"};
		JComboBox gradeBox = new JComboBox(grade);
		((JLabel)gradeBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		String[] class_ = {"A","B","C","0"};
		JComboBox classBox = new JComboBox(class_);
		((JLabel)classBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		gradeBox.setBackground(Color.white);
		classBox.setBackground(Color.white);
		gradeBox.setSize(100, 25);
		classBox.setSize(100, 25);
		gradeBox.setLocation(250,125);
		classBox.setLocation(450,125);
		JLabel [] grade_class_lbl = new JLabel[2];
		grade_class_lbl[0] = new JLabel();
		grade_class_lbl[1] = new JLabel();
		grade_class_lbl[0].setText("학년");
		grade_class_lbl[1].setText("반");
		grade_class_lbl[0].setSize(100,25);
		grade_class_lbl[1].setSize(100,25);
		grade_class_lbl[0].setLocation(250,150);
		grade_class_lbl[1].setLocation(450,150);
		grade_class_lbl[0].setHorizontalAlignment(JLabel.CENTER);
		grade_class_lbl[1].setHorizontalAlignment(JLabel.CENTER);
		add(gradeBox);
		add(classBox);
		add(grade_class_lbl[0]);
		add(grade_class_lbl[1]);
		for(int i=0;i<comboString.length;i++) {
			attributeField[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(attributeField[3].getText().equals(""))
						JOptionPane.showMessageDialog(null, "학번은 비울 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					else {
						rs=DBConnect.DataSelect("select stuid from member where stuid like "+attributeField[3].getText());
						try {
							if(!(rs.next())) {
								String str="INSERT INTO member VALUES('"+attributeField[3].getText().toString()+"', '"+job.getSelectedItem().toString()+"', '"
										+major.getSelectedItem().toString()+"', '"+attributeField[2].getText()+"', '"+attributeField[4].getText()+"', '"+attributeField[5].getText()+"', '"
										+attributeField[6].getText()+"', '"+ShaEncoding.getSHA256(attributeField[7].getText())+"', '"+gradeBox.getSelectedItem().toString()+"', '"+classBox.getSelectedItem().toString()+"')";
								System.out.println(str);
								af.setUpdateSql(str);
								af.setDefaultFucButton();
								af.setTableData();
								dispose();		
							}
							else {
								JOptionPane.showMessageDialog(null, "동일한 학번이 존재합니다. 다시 입력해주십시오.", "경고", JOptionPane.WARNING_MESSAGE);
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		}
		attributeField[3].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)&&(c!=KeyEvent.VK_ENTER)) {
					JOptionPane.showMessageDialog(null, "학번은 숫자만 입력 가능합니다", "경고", JOptionPane.WARNING_MESSAGE);
					e.consume();  // if it's not a number, ignore the event
				}
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//INSERT INTO JAVADB."member" VALUES(201945049, '교수', '컴시과', '이건', '010-4166-0045', '강서로 24길 5 2층', 'gell8778', 'pw')
				if(attributeField[3].getText().equals("")) {
					JOptionPane.showMessageDialog(null, "학번은 비울 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}
				else {
					rs=DBConnect.DataSelect("select stuid from member where stuid like "+attributeField[3].getText());
					try {
						if(!(rs.next())) {
							String str="INSERT INTO member VALUES('"+attributeField[3].getText().toString()+"', '"+job.getSelectedItem().toString()+"', '"
									+major.getSelectedItem().toString()+"', '"+attributeField[2].getText()+"', '"+attributeField[4].getText()+"', '"+attributeField[5].getText()+"', '"
									+attributeField[6].getText()+"', '"+ShaEncoding.getSHA256(attributeField[7].getText())+"', '"+gradeBox.getSelectedItem().toString()+"', '"+classBox.getSelectedItem().toString()+"')";
							//System.out.println(str);
							System.out.println(str);
							af.setUpdateSql(str);
							af.setTableData();
							af.setDefaultFucButton();
							dispose();		
						}
						else {
							JOptionPane.showMessageDialog(null, "동일한 학번이 존재합니다. 다시 입력해주십시오.", "경고", JOptionPane.WARNING_MESSAGE);
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}				
			}
		});
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				af.setDefaultFucButton();
				dispose();			
			}
		});
		add(cancelButton);
		add(addButton);
		setVisible(true);
	}
}
