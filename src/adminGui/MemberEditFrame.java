package adminGui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableModel;

public class MemberEditFrame extends JFrame {
	private JTextField [] attributeField;
	private JLabel [] attributeLabel;
	private JComboBox job;
	private JComboBox major;
	private String[] jobString= {"교수","학생"};
	private String[] majorString= {"컴시과","컴정과","건축과"};
	private JButton editButton;
	private JButton cancelButton;
	private AdminFrame af;
	private TableModel tm;
	private int row;
	public String [] comboString = {"직책", "학과", "이름", "학번", "연락처", "주소", "ID", "PW"};
	public MemberEditFrame(AdminFrame af,int row,TableModel tm) {
		this.tm=tm;
		this.row=row;
		this.af=af;
		setTitle("정보 추가");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(700, 500);
		setSize(840, 250);
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
		for(int i=0;i<comboString.length-2;i++) {
			attributeField[i] = new JTextField();
			attributeLabel[i] = new JLabel(comboString[i]);
			attributeField[i].setSize(100,25);

			if(i==0) {
				if(tm.getValueAt(row, i).equals("학생"))
					job.setSelectedIndex(1);
				job.setLocation(x+100*i,y);
				job.setSize(100, 25);
				job.setBackground(Color.WHITE);
				add(job);
			}
			else if(i==1) {
				if(tm.getValueAt(row, i).equals("컴정과"))
					major.setSelectedIndex(1);
				else if(tm.getValueAt(row, i).equals("건축과"))
					major.setSelectedIndex(2);
				major.setLocation(x+100*i,y);
				major.setSize(100,25);
				major.setBackground(Color.WHITE);
				add(major);
			}
			else {
				attributeField[i].setSize(100, 25);
				attributeField[i].setLocation(x+100*i-c*400,y);
				if(tm.getValueAt(row, i)!=null)
					attributeField[i].setText(tm.getValueAt(row, i).toString());
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
		attributeField[3].setBackground(Color.WHITE);
		attributeField[3].setEditable(false);
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
		gradeBox.setLocation(415,75);
		classBox.setLocation(515,75);
		int gradeIndex=0,classIndex=0;
		if(tm.getValueAt(row, 6).toString().equals("0")) {
			gradeIndex = 3;
		}else {
			gradeIndex = Integer.parseInt(tm.getValueAt(row, 6).toString())-1;
		}
		switch(tm.getValueAt(row, 7).toString()) {
		case "A":
			classIndex = 0;
			break;
		case "B":
			classIndex = 1;
			break;
		case "C":
			classIndex = 2;
			break;
		case "0":
			classIndex = 3;
		}
		gradeBox.setSelectedIndex(gradeIndex);
		classBox.setSelectedIndex(classIndex);
		add(gradeBox);
		add(classBox);
		editButton = new JButton("Edit");
		cancelButton = new JButton("Cancel");
		editButton.setSize(150,50);
		editButton.setLocation(235,125);
		cancelButton.setSize(150,50);
		cancelButton.setLocation(435, 125);
		BevelBorder bor = new BevelBorder(BevelBorder.RAISED);
		editButton.setBorder(bor);
		cancelButton.setBorder(bor);
		editButton.setBackground(new Color(136,210,255));
		cancelButton.setBackground(new Color(136,210,255));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str="UPDATE member set job = '"+job.getSelectedItem().toString()+"', major = '"+major.getSelectedItem().toString()
						+"', name = '"+attributeField[2].getText()+"', phonenum = '"+attributeField[4].getText()+"', address = '"+attributeField[5].getText()
						+"', grade = '"+gradeBox.getSelectedItem().toString()+"', class = '"+classBox.getSelectedItem().toString()+"' where Stuid like "+""+attributeField[3].getText();
				//System.out.println(str);
				System.out.println(str);
				af.setUpdateSql(str);
				af.setDefaultFucButton();
				af.setTableData();
				dispose();				
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
		add(editButton);
		setVisible(true);
	}
}
