package adminGui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AdminFrame extends JFrame implements ActionListener {
	private JRadioButton[] radioButtonG;
	private ButtonGroup radioGroup;
	private JTextField searchField;
	private DefaultTableModel model;
	private String [] listImageLink = {"./images/Job.png","./images/Major.png","./images/Name.png","./images/StuID.png","./images/PhoneNum.png"
			,"./images/Address.png","./images/Grade.png","./images/Class.png"};
	private ImageIcon[] imgs;
	public String [] comboString = {"직책", "학과", "이름", "학번", "연락처", "주소", "학년", "반"};
	private String [] sqlString = {"Job", "Major", "Name", "Stuid", "PhoneNum", "Address", "grade", "class"};
	private Image img;
	private JButton[] attributeButton;
	private JComboBox searchComboBox;
	private String[] radioString = {"전체","교수","학생"};
	private JCheckBox[] cb;
	private JTable dataTable;
	private Vector <String> dataTableVector;
	private Container backPane;
	private JButton searchButton;
	private JLabel title = new JLabel("Manage");
	private JButton[] fucButton;
	private String[] fucButtonImgLink = {"./images/Add.png","./images/Delete.png","./images/Edit.png","./images/Exit.png"};
	//public DBConnect dbc = new DBConnect();
	private ResultSet rs;
	private String sql="select * from member";
	private String whereSql_R="";//라디오 버튼에서 온 질의어
	private String whereSql_S="";//서치필드에서 온 질의어
	private String resultWhere="";//양쪽에서 온 질의어를 합쳐준 String
	private String attributeSql=" order by job";
	private MemberAddFrame addFrame;
	private int buttonCheck=1;
	private String updateSql;
	public AdminFrame(){
		
		setTitle("회원 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(600, 300);
		setSize(1000, 600);
		setLayout(new BorderLayout());
		setBackPane();
		setTableAbove();
		setTableRight();
		setTable();
		setVisible(true);
		
	}
	private void setBackPane() {
		
		backPane=getContentPane();
		backPane.setLayout(null);
		title.setSize(200,50);
		title.setLocation(50, 0);
		Font titleFont = new Font("나눔 고딕",Font.BOLD,20);
		title.setFont(titleFont);
		backPane.add(title);
		
	}
	private void setTableAbove() { // 리스트 위쪽 검새기능들
		
		JPanel TableAboveLeftPane = new JPanel();
		TableAboveLeftPane.setLayout(new GridLayout(1,3));
		radioGroup=new ButtonGroup();
		radioButtonG=new JRadioButton[radioString.length];
		for(int i=0;i<radioString.length;i++) {
			radioButtonG[i]=new JRadioButton(radioString[i]);
			radioGroup.add(radioButtonG[i]);
			radioButtonG[i].setVerticalTextPosition(JRadioButton.TOP);
			radioButtonG[i].setHorizontalTextPosition(JRadioButton.CENTER);//라벨 라디오 버튼 위로 올리고 가운데 정렬
			radioButtonG[i].setBackground(Color.WHITE);
			radioButtonG[i].addActionListener(this);
			TableAboveLeftPane.add(radioButtonG[i]);
		}
		
		radioButtonG[0].setSelected(true);//디폴트 처음에 전체 라디오칸 선택되게
		JPanel TableAboveRightPane = new JPanel();
		searchComboBox = new JComboBox(comboString);
		searchComboBox.setSize(75,25);
		searchComboBox.setLocation(10, 7);
		((JLabel)searchComboBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.addActionListener(this);
		searchField = new JTextField();
		searchField.setLocation(110, 7);
		searchField.setSize(325, 25);
		searchField.addActionListener(this);
		img=new ImageIcon("./images/Search_Img.png").getImage();
		img=img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon searchImg = new ImageIcon(img);
		searchButton = new JButton(searchImg);
		searchButton.setBackground(Color.WHITE);
		//searchButton.setContentAreaFilled(false);
		searchButton.setSize(75,25);
		searchButton.setLocation(457, 7);
		searchButton.addActionListener(this);
		TableAboveRightPane.add(searchButton);
		TableAboveRightPane.add(searchComboBox);
		TableAboveRightPane.add(searchField);
		TableAboveRightPane.setLayout(null);
		TableAboveRightPane.setSize(552,40);
		TableAboveRightPane.setLocation(200,50);
		TableAboveLeftPane.setLocation(50, 50);
		TableAboveLeftPane.setSize(110,40);
		EtchedBorder eborder =  new EtchedBorder();
		TableAboveLeftPane.setBorder(eborder);
		TableAboveRightPane.setBorder(eborder);
		TableAboveLeftPane.setBackground(Color.WHITE);
		add(TableAboveLeftPane);		
		add(TableAboveRightPane);		
	}
	private void setTable() { // 리스트 설정
		JPanel tablePane = new JPanel();
		tablePane.setLayout(null);
		attributeButton = new JButton[listImageLink.length];
		for(int i=0;i<listImageLink.length;i++) {
			if(i<4) {
				attributeButton[i] = new JButton(getScaledImage(listImageLink[i], 76, 50));
				attributeButton[i].setSize(76,50);
				attributeButton[i].setLocation(0+76*i, 0);
			}
			else if(i==4) {
				attributeButton[i] = new JButton(getScaledImage(listImageLink[i], 102, 50));
				attributeButton[i].setSize(102,50);
				attributeButton[i].setLocation(0+76*i, 0);
			}
			else if(i==5){
				attributeButton[i] = new JButton(getScaledImage(listImageLink[i], 201, 50));
				attributeButton[i].setSize(201,50);
				attributeButton[i].setLocation(0+76*i+26, 0);
			}
			else if(i==6) {
				attributeButton[i] = new JButton(getScaledImage(listImageLink[i], 77, 50));
				attributeButton[i].setSize(77,50);
				attributeButton[i].setLocation(0+76*i+151, 0);
			}
			else if(i==7) {
				attributeButton[i] = new JButton(getScaledImage(listImageLink[i], 77, 50));
				attributeButton[i].setSize(77,50);
				attributeButton[i].setLocation(0+76*i+152, 0);
			}
			attributeButton[i].setBorderPainted(false);
			attributeButton[i].addActionListener(this);
			tablePane.add(attributeButton[i]);
		}
		attributeButton[0].setIcon(getScaledImage("./images/Job_Asc.png", 76, 50));//db 불러올 때 첫 시작을 job을 기준으로 오름차순으로 정렬이 되어 설정해줘야함 그래서 이미지 변경
		dataTableVector=new Vector<String>();
		for(String str : comboString) {
			dataTableVector.add(str);
		}
		model = new DefaultTableModel(dataTableVector,0){
			public boolean isCellEditable(int rowIndex, int mColIndes) {//내용 수정 불가
				return false;
			}
		};
		sql="select * from member"; // Job을 기준으로 오름차순
		setTableData();
		dataTable = new JTable(model);
		setTableCenter(dataTable);
		dataTable.setTableHeader(null);
		dataTable.getColumnModel().getColumn(4).setPreferredWidth(100);//연락처도 좀 길어야함
		dataTable.getColumnModel().getColumn(5).setPreferredWidth(200);//주소는 길어야지
		JScrollPane tableScrollPane = new JScrollPane(dataTable,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tableScrollPane.getViewport().setBackground(Color.WHITE);
		tableScrollPane.setSize(778,398);
		tableScrollPane.setLocation(0,50);
		tablePane.setSize(778,450);
		tablePane.setLocation(50,100);
		tablePane.add(tableScrollPane);
		add(tablePane);
	}
	public void setTableData() {
		//System.out.println(sql+resultWhere+attributeSql);
		rs=DBConnect.DataSelect(sql+resultWhere+attributeSql);
		if(model.getRowCount()!=0) {
			if (model.getRowCount() > 0) {
				for (int i = model.getRowCount() - 1; i > -1; i--) {
					model.removeRow(i);
				}
			}
		}
		try {
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("JOB"));
				v.add(rs.getString("MAJOR"));
				v.add(rs.getString("NAME"));
				v.add(Integer.toString(rs.getInt("STUID")));
				v.add(rs.getString("PHONENUM"));
				v.add(rs.getString("ADDRESS"));
				v.add(rs.getString("grade"));
				v.add(rs.getString("class"));
				model.addRow(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void setTableCenter(JTable t) { //테이블 안에 있는 값을 가운데 정렬
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(); 
		renderer.setHorizontalAlignment(JLabel.CENTER);//가운데 정렬
		TableColumnModel centerModel = t.getColumnModel();
		for(int i=0;i<8; i++)
			centerModel.getColumn(i).setCellRenderer(renderer);
	}
	private void setTableRight() {
		img=new ImageIcon("./images/Inha_Logo.png").getImage();
		img=img.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
		ImageIcon logoImg = new ImageIcon(img);
		JLabel logoLabel = new JLabel(logoImg);
		logoLabel.setSize(125, 125);
		logoLabel.setLocation(840,10);
		imgs=new ImageIcon[listImageLink.length];
		fucButton=new JButton[fucButtonImgLink.length];
		for(int i=0;i<fucButtonImgLink.length;i++) {
			fucButton[i] = new JButton(getScaledImage(fucButtonImgLink[i], 130, 80));
			fucButton[i].setSize(130,80);
			fucButton[i].setLocation(840, 150+106*i);
			fucButton[i].addActionListener(this);
			fucButton[i].setBorderPainted(false);//버튼 위에 마우스 올렸을 때 나오는 테두리 없애기
			backPane.add(fucButton[i]);
		}
		backPane.add(logoLabel);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object where = e.getSource(); // 누구인가
		if(where==fucButton[0]) {//Add버튼
			fucButton[0].setIcon(getScaledImage("./images/Add_On.png", 130, 80));
			MemberAddFrame mdf = new MemberAddFrame(this);
			setTableData();
		}
		else if(where==fucButton[1]){//Delete버튼
			fucButton[1].setIcon(getScaledImage("./images/Delete_On.png", 130, 80));
			for(int i = 0 ;i<dataTable.getSelectedRows().length;i++) {
				if(JOptionPane.showConfirmDialog(this, "학번 : "+dataTable.getValueAt(dataTable.getSelectedRows()[i], 3)+"에 대한 정보를 Delete하시겠습니까?","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
					setUpdateSql("DELETE from member where stuid like "+dataTable.getValueAt(dataTable.getSelectedRows()[i], 3));
				}
			}
			if(dataTable.getSelectedRows().length==0)
				JOptionPane.showMessageDialog(null, "리스트를 선택한 뒤 다시 눌러주세요", "경고", JOptionPane.WARNING_MESSAGE);
			setDefaultFucButton();
			setTableData();

		}
		else if(where==(fucButton[2])) {//Edit버튼
			fucButton[2].setIcon(getScaledImage("./images/Edit_On.png", 130, 80));
			try {
				MemberEditFrame mef = new MemberEditFrame(this,dataTable.getSelectedRow(),dataTable.getModel());
			}catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "리스트를 선택한 뒤 다시 눌러주세요", "경고", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
				setDefaultFucButton();
			}


		}
		else if(where==fucButton[3]) {//Exit버튼
			dispose();
		}
		else if(where==radioButtonG[0]) {//라디오 버튼 전체
			if(searchField.getText().equals(""))//만약에 텍스트 필드를 지우고 엔터를 안눌렀을 때 sql 문이 꼬이는 것을 방지하기 위해 sql_s를 초기화함.
				whereSql_S="";
			else if(whereSql_S.contains("and"))
				whereSql_S=whereSql_S.replace("and", "where");
			whereSql_R="";
			setResultWhere(whereSql_R,whereSql_S);
			setTableData();
		}
		else if(where==radioButtonG[1]) {//라디오 버튼 교수
			if(searchField.getText().equals("")) {
				whereSql_S="";
			}
			if(whereSql_S.equals("")||whereSql_S.contains("and")) {
				whereSql_R=" where Job like '교수'";
			}
			else
				whereSql_R=" and Job like '교수'";
			setResultWhere(whereSql_R,whereSql_S);
			setTableData();
		}
		else if(where==radioButtonG[2]) {//라디오 버튼 학생
			if(searchField.getText().equals(""))
				whereSql_S="";
			if(whereSql_S.equals("")||whereSql_S.contains("and"))
				whereSql_R=" where Job like '학생'";
			else
				whereSql_R=" and Job like '학생'";
			setResultWhere(whereSql_R,whereSql_S);
			setTableData();
		}
		else if(where==attributeButton[0]) {//직책 버튼
			if(!attributeSql.contains("job"))//다른 버튼 누르다가 누르면 다시 오름차순부터 시작
				buttonCheck=0;
			if(buttonCheck%2==1) {
				attributeSql = " order by job desc";
				setTableData();
				setDefaultAttributeButton();
				attributeButton[0].setIcon(getScaledImage("./images/Job_Desc.png", 76, 50));
				buttonCheck=0;
			}
			else {
				attributeSql = " order by job";
				setTableData();
				setDefaultAttributeButton();
				attributeButton[0].setIcon(getScaledImage("./images/Job_Asc.png", 76, 50));
				buttonCheck=1;
			}
		}
		else if(where==attributeButton[1]) {//학과 버튼
			if(!attributeSql.contains("major"))//다른 버튼 누르다가 누르면 다시 오름차순부터 시작
				buttonCheck=0;
			if(buttonCheck%2==1) {
				attributeSql = " order by major desc";
				setTableData();
				setDefaultAttributeButton();
				attributeButton[1].setIcon(getScaledImage("./images/Major_Desc.png", 76, 50));
				buttonCheck=0;
			}
			else {
				attributeSql = " order by major";
				setTableData();
				setDefaultAttributeButton();
				attributeButton[1].setIcon(getScaledImage("./images/Major_Asc.png", 76, 50));
				buttonCheck=1;
			}
		}
		else if(where==attributeButton[2]) {//이름 버튼
			if(!attributeSql.contains("name"))//다른 버튼 누르다가 누르면 다시 오름차순부터 시작
				buttonCheck=0;
			if(buttonCheck%2==1) {
				attributeSql = " order by name desc";
				setTableData();
				setDefaultAttributeButton();
				attributeButton[2].setIcon(getScaledImage("./images/Name_Desc.png", 76, 50));
				buttonCheck=0;
			}
			else {
				attributeSql = " order by name";
				setTableData();
				setDefaultAttributeButton();
				attributeButton[2].setIcon(getScaledImage("./images/Name_Asc.png", 76, 50));
				buttonCheck=1;
			}
		}
		else if(where==searchButton||where==searchField&&!(searchField.getText().equals(""))) {//서치 필드 || 버튼

			if(!(whereSql_R.equals(""))&&!whereSql_R.contains("and")) {
				whereSql_S=" and "+sqlString[searchComboBox.getSelectedIndex()]+" like '%"+searchField.getText()+"%'";
				setResultWhere(whereSql_R,whereSql_S);
				setTableData();

			}
			else {
				whereSql_S=" where "+sqlString[searchComboBox.getSelectedIndex()]+" like '%"+searchField.getText()+"%'";
				setResultWhere(whereSql_R,whereSql_S);
				setTableData();
			}
		}
		else if(where==searchField) {//서치 필드가 아무것도 없을 때
			whereSql_S="";
			if(whereSql_R.contains("and"))//오류 처리
				whereSql_R=whereSql_R.replace("and", "where");
			setResultWhere(whereSql_R,whereSql_S);
			setTableData();
		}
	}
	private ImageIcon getScaledImage(String link,int w,int h) {//이미지를 원하는 비율만큼 스케일링 해주고 그 값을 ImageIcon으로 반환해주는 메서드
		img=new ImageIcon(link).getImage();
		img=img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ImageIcon tempImg = new ImageIcon(img);
		return tempImg;
	}
	private void setDefaultAttributeButton() {
		for(int i=0;i<3;i++) {
			attributeButton[i].setIcon(getScaledImage(listImageLink[i], 76, 50));
		}
	}
	public void setDefaultFucButton() {
		for(int i = 0; i<3;i++) {
			fucButton[i].setIcon(getScaledImage(fucButtonImgLink[i], 130, 80));
		}
	}
	private void setResultWhere(String R, String S) {
		if(R.contains("and"))
			resultWhere = S + R;
		else
			resultWhere = R + S;
	}
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
		DBConnect.DataUpdate(updateSql);
		DBConnect.Close();
		//setTableData();
	}
}
