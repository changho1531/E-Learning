package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import adminGui.AdminFrame;
import loginGui.LoginGUI;

public class Main extends JFrame implements ActionListener{

	//DB설정
	public ResultSet rs;
	public Vector infoV;

	//프레임
	JPanel panTop, panTop2, panTop3, panCenter, panLeft, panRight;
	JPanel panMypage, panNotice, panClass, panCommu;
	JPanel panMain, panPro, panSche, panOffi, panNote, panLect, panAssign, panBoard, panContentBoard, panWriteBoard;
	JPanel msgMain, msgCenter, msgSouth;

	JSplitPane jspCenter;

	JMenuBar mb;
	JMenu menuFile, menuInfo, menuHelp;
	JMenuItem itemExit, itemInfo, itemHelp;

	JButton btnHome, btnLogout, btnMypage, btnNotice, btnClass, btnCommu;
	JButton btnMyinfo, btnSchedule, btnOfficial, btnNote, btnLecture, btnAssignment, btnBoard, buttonBoardBack, buttonOk, btnWrite;
	JButton schSearch, btnUpdate;

	JLabel lblHome, lblTime, lblDate, lblUser, mainLbl;
	JLabel a1, a2, a3, b1, b2, b3, c1, c2, c3, aaa;

	ImageIcon aa1, aa2, aa3, bb1, bb2, bb3, cc1, cc2, cc3;
	ImageIcon Homelogo, mainImg;


	String header[] = {"info"};
	String contents[][] = {
			{"직책"},
			{"학과"},
			{"이름"},
			{"학번"},
			{"번호"},
			{"주소"},
			{"ID"},
			{"PW"}
	};

	JTable infoTable, table2, table3;

	//String[] grade = {"1", "2", "3"};	//시간표
	String[] ban = {"A", "B", "C"};

	JComboBox g, b;

	String header2[] = {"번호", "제목", "작성자", "작성일", "조회수"};	//공지사항
	String contents2[][] = {
			{"", "", "", "", ""}
	};
	DefaultTableModel model2, model3, infoModel, scheduleModel,ProModel;

	JScrollPane scrollPane;
	JTextField search2;
	JButton btnSearch2;
	JLabel offiLbl;

	JTextField msgTf; 	//쪽지
	JTextArea msgTa;

	String header3[] = {"과목명", "개설학과", "담당교수", "강의실입장"};	//강의
	String contents3[][] = {
			{"JAVA프로그래밍 응용", "컴퓨터시스템과", "☆김기태 교수님☆", ""},
			{"데이터통신", "컴퓨터시스템과", "유치형 교수님", ""},
			{"c#프로그래밍", "컴퓨터시스템과", "전혜경 교수님", ""},
			{"모바일프로그래밍", "컴퓨터시스템과", "서지훈 교수님", ""},
			{"컴퓨터구조", "컴퓨터시스템과", "정찬웅 교수님", ""},
			{"데이터베이스", "컴퓨터시스템과", "박혜영 교수님", ""}
	};
	JScrollPane scrollPane2;

	String header4[] = {"번호", "제목", "작성자", "작성일", "조회수"};	//게시판
	String contents4[][] = {
			{"", "", "", "", ""}
	};

	JTable boardTable, messageTable, ProTable;
	DefaultTableModel boardModel, messageModel;
	JScrollPane boardScrollPane;


	//게시판
	JTextField search3;
	JButton btnSearch3;


	Color a = new Color(0xCCFFFF); //배경색
	BevelBorder bor = new BevelBorder(BevelBorder.RAISED); //버튼효과

	//이건
	private Vector <String> dataTableVector, messageTableVector, scheduleTableVector;
	private JTextField[] infoFields;
	private JScrollPane contentScrollPane, writeScrollPane,messageScrollPane;
	private String[] searchBoardCombo = {"제목","작성자"};
	private String[] searchMessageCombo = {"제목","보낸이"};
	private String[] messageHeader= {"쪽지번호","제목","보낸이","발신인"};
	private String[] scheduleHeader = {"교시","월","화","수","목","금"};
	private String testid = "333333";
	private String id="";
	private String infoJob,infoMajor,infoName,infoStuid,infoGrade,infoClass;
	private DefaultTableModel lectModel;
	private JPanel panJava;
	private DefaultTableModel classRoomModel;
	private JTextField ProFileTextField_Major;
	private JTextField ProFileTextField_Grade;
	private JTextField ProFileTextField_Name;
	private JTextField ProFileTextField_Class;
	private JTextField ProFileTextField_SchoolNumber;
	private JTextField ProFileTextField_PhoneNumber;
	private JTextField ProFileTextField_Address;
	private JTextField ProFileTextField_Job;
	String grade, classN;
	public Main(String title,String id) {

		this.id = id;

		//프레임
		seting(title);	// 프레임 세팅

		menuBar();		// 메뉴바

		baseFrame();	// 기본 프레임

		panelTop();		// 상단 패널

		PanelLeft();	// 좌측 패널

		detailList();	// 세부 리스트

		//-----------main-------------

		proPanel();		// 프로필 패널

		schePanel();	// 시간표 패널

		messagePanel(); // 쪽지 패널

		lecturePanel();	// 강의 패널

		boardPanel();	// 게시판 패널

		boardWritePanel();

		mainPanel(); //메인 패널
		//프레임
		this.setVisible(true);
	}

	private void boardPanel() { //default 게시판
		panBoard = new JPanel();
		panBoard.setLayout(null);

		JLabel boardLbl = new JLabel("게시판");
		boardLbl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		boardLbl.setLocation(70, 20);
		boardLbl.setSize(400, 40);
		panBoard.add(boardLbl);
		ImageIcon boardImg = new ImageIcon("./images/board_In.png");
		JLabel boardImgLbl = new JLabel(boardImg);
		boardImgLbl.setLocation(170,0);
		boardImgLbl.setSize(100, 90);
		panBoard.add(boardImgLbl);
		dataTableVector=new Vector<String>();
		for(String str : header4) {
			dataTableVector.add(str);
		}
		boardModel = new DefaultTableModel(dataTableVector,0) {
			public boolean isCellEditable(int rowIndex, int mColIndes) {//내용 수정 불가
				return false;
			}
		};
		boardTable = new JTable(boardModel);
		boardTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//하나만 선택되게
		boardTable.getTableHeader().setReorderingAllowed(false);//제목 드래그로 위치 이동 안되게하기
		boardTable.getTableHeader().setResizingAllowed(false);//크기 조절 불가능하게하기
		boardTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {//2번 눌렀으면
				if(e.getClickCount()==2) {
					int row = boardTable.getSelectedRow();
					panMypage.setVisible(false);
					panNotice.setVisible(false);
					panClass.setVisible(false);
					panCommu.setVisible(false);
					panBoard.setVisible(false);
					boardContentPanel(row);
				}
			}
		});



		//setTableCenter(boardTable);
		boardTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		boardTable.getColumnModel().getColumn(1).setPreferredWidth(300);//제목
		boardTable.getColumnModel().getColumn(2).setPreferredWidth(30);//작성자
		boardTable.getColumnModel().getColumn(3).setPreferredWidth(50);//작성자
		boardTable.getColumnModel().getColumn(4).setPreferredWidth(10);//조회수
		boardScrollPane = new JScrollPane(boardTable);
		boardScrollPane.setLocation(70, 80);
		boardScrollPane.setSize(730, 330);
		panBoard.add(boardScrollPane);
		setTableCenter(boardTable);		
		search3 = new JTextField(10);
		btnSearch3 = new JButton("검색");
		JComboBox searchComboBox = new JComboBox(searchBoardCombo);
		((JLabel)searchComboBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		searchComboBox.setBackground(Color.white);
		searchComboBox.setSize(70,25);
		searchComboBox.setLocation(517, 45);
		panBoard.add(searchComboBox);
		btnSearch3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchComboBox.getSelectedIndex()==0)
					searchTitleBoard(search3.getText());
				else {
					searchNameBoard(search3.getText());
				}
			}
		});
		search3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchComboBox.getSelectedIndex()==0)
					searchTitleBoard(search3.getText());
				else {
					searchNameBoard(search3.getText());
				}
			}
		});
		search3.setLocation(597, 45);
		search3.setSize(130, 25);
		btnSearch3.setLocation(737, 45);
		btnSearch3.setSize(60, 25);
		panBoard.add(search3);
		panBoard.add(btnSearch3);
		setBoardData();
		btnWrite = new JButton("글쓰기");
		btnWrite.addActionListener(this);
		btnWrite.setLocation(720, 420);
		btnWrite.setSize(77, 27);
		panBoard.add(btnWrite);
	}

	private void boardContentPanel(int row) { //게시판 내용 패널
		panContentBoard = new JPanel();
		panContentBoard.setLayout(null);
		//panContentBoard.setBackground(new Color(0xCCE5FF));
		infoFields = new JTextField[4];
		JLabel[] infoLabel = new JLabel[4];
		EtchedBorder eborder =  new EtchedBorder();
		panContentBoard.add(panMypage);
		panContentBoard.add(panNotice);
		panContentBoard.add(panClass);
		panContentBoard.add(panCommu);
		for(int i=0;i<infoFields.length;i++) {
			infoFields[i] = new JTextField();
			infoLabel[i]= new JLabel();
			infoLabel[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			infoFields[i].setText(boardModel.getValueAt(row, i+1).toString());
			if(i==0) {
				infoFields[i].setLocation(70, 100);
				infoFields[i].setSize(400,25);
				infoLabel[i].setSize(400,25);
				infoLabel[i].setText("제목");
				infoLabel[i].setLocation(70, 70);
			}
			else {
				infoFields[i].setLocation(360+110*i, 100);
				infoFields[i].setSize(110,25);
				infoLabel[i].setSize(110,25);
				infoLabel[i].setLocation(360+110*i, 70);
			}
			infoLabel[i].setHorizontalAlignment(JLabel.CENTER);
			infoFields[i].setHorizontalAlignment(JLabel.CENTER);
			infoFields[i].setEditable(false);
			infoFields[i].setBackground(Color.WHITE);
			infoLabel[i].setOpaque(true);//배경색 변경 적용할것인가
			infoLabel[i].setBackground(Color.WHITE);
			infoLabel[i].setBorder(eborder);
			panContentBoard.add(infoLabel[i]);
			panContentBoard.add(infoFields[i]);
		}
		infoLabel[1].setText("작성자");
		infoLabel[2].setText("작성일");
		infoLabel[3].setText("조회수");
		infoFields[3].setText(String.valueOf(Integer.parseInt(boardModel.getValueAt(row, 4).toString())+1));//들어가면서 조회수 1 올라야함
		String sql = "select content, stuid from board where postnum like "+boardModel.getValueAt(row, 0).toString();
		try {
			rs=DBConnect.DataSelect(sql);
			rs.next();
			JTextArea contentArea = new JTextArea(rs.getString("content"));
			sql = "UPDATE BOARD SET VIEWS = "+(Integer.parseInt(boardModel.getValueAt(row, 4).toString())+1)+" WHERE postnum = "+boardModel.getValueAt(row,0);
			String stuid = rs.getInt("stuid")+"";
			contentArea.setEditable(false);
			DBConnect.DataUpdate(sql);
			DBConnect.Close();//db적으로도 조회수 수정
			contentScrollPane = new JScrollPane(contentArea);
			contentScrollPane.setSize(730,270);
			contentScrollPane.setLocation(70, 130);
			buttonBoardBack = new JButton("뒤로가기");
			buttonBoardBack.setLocation(697, 420);
			buttonBoardBack.setSize(100, 27);
			buttonBoardBack.addActionListener(this);
			panContentBoard.add(contentScrollPane);
			panContentBoard.add(buttonBoardBack);
			jspCenter.setRightComponent(panContentBoard);
			if(stuid.equals(infoStuid)||infoMajor.equals("관리자")){//접속한 아이디가 관리자거나 본인이라면
				JButton deleteButton = new JButton("삭제");
				deleteButton.setSize(100,27);
				deleteButton.setLocation(577,420);
				deleteButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String sql ="";
						sql="delete from board where postnum like "+boardModel.getValueAt(row, 0).toString();
						if(JOptionPane.showConfirmDialog(jspCenter.getRightComponent(), "게시글을 지우시겠습니까?","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
							DBConnect.DataUpdate(sql);
							DBConnect.Close();
							panBoard.add(panMypage);
							panBoard.add(panNotice); 
							panBoard.add(panClass);
							panBoard.add(panCommu);
							jspCenter.setRightComponent(panBoard);
							setBoardData();
							panBoard.setVisible(true);
							panMypage.setVisible(false);
							panNotice.setVisible(false);
							panClass.setVisible(false);
							panCommu.setVisible(false);
							panWriteBoard.setVisible(false);
						}
					}
				});
				panContentBoard.add(deleteButton);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnect.Close();
		}

	}
	private void boardWritePanel() { //게시판 글쓰기 패널
		panWriteBoard = new JPanel();
		panWriteBoard.setLayout(null);
		//panContentBoard.setBackground(new Color(0xCCE5FF));
		JLabel title = new JLabel("제목");
		JCheckBox noticeCheck = new JCheckBox("공지");
		noticeCheck.setSize(70,25);
		noticeCheck.setLocation(730,70);
		noticeCheck.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JTextField titleField = new JTextField();
		titleField.setLocation(70,100);
		titleField.setSize(730,25);
		titleField.setHorizontalAlignment(JLabel.CENTER);
		title.setLocation(70,70);
		title.setSize(730,25);
		title.setHorizontalAlignment(JLabel.CENTER);
		panWriteBoard.add(title);
		panWriteBoard.add(titleField);
		if(infoJob.equals("교수")||infoJob.equals("관리자")) {
			panWriteBoard.add(noticeCheck);
		}
		JTextArea contentArea = new JTextArea();
		writeScrollPane = new JScrollPane(contentArea);
		writeScrollPane.setSize(730,270);
		writeScrollPane.setLocation(70, 130);
		buttonBoardBack = new JButton("뒤로가기");
		buttonOk = new JButton("작성");
		buttonOk.addActionListener(this);
		buttonOk.setSize(100,27);
		buttonOk.setLocation(577,420);
		buttonBoardBack.setLocation(697, 420);
		buttonBoardBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panBoard.add(panMypage);
				panBoard.add(panNotice);
				panBoard.add(panClass);
				panBoard.add(panCommu);
				jspCenter.setRightComponent(panBoard);
				setBoardData();
				panBoard.setVisible(true);
				panMypage.setVisible(false);
				panNotice.setVisible(false);
				panClass.setVisible(false);
				panCommu.setVisible(false);
				panWriteBoard.setVisible(false);
				contentArea.setText("");
				titleField.setText("");
				noticeCheck.setSelected(false);			
			}
		});
		buttonBoardBack.setSize(100, 27);
		panWriteBoard.add(panMypage);
		panWriteBoard.add(panNotice);
		panWriteBoard.add(panClass);
		panWriteBoard.add(panCommu);
		jspCenter.setRightComponent(panWriteBoard);
		panWriteBoard.add(buttonOk);
		panWriteBoard.add(writeScrollPane);
		panWriteBoard.add(buttonBoardBack);
		buttonOk.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				int check=0;
				if(noticeCheck.isSelected())
					check=1;
				String sql;
				sql = "select max(postnum) from board";
				rs=DBConnect.DataSelect(sql);
				try {
					rs.next();
					if(rs.getString("max(postnum)")==null) {
						sql = "INSERT INTO BOARD values(1,'"+titleField.getText()+"',"
								+ "(select name from member where stuid = "+infoStuid+")"
								+ ",0,"+
								check+",TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),'"+contentArea.getText()+"',"+infoStuid+")";
					}
					else {
						sql = "INSERT INTO BOARD values((SELECT MAX(POSTNUM)+1 FROM BOARD),'"+titleField.getText()+"',"
								+ "(select name from member where stuid = "+infoStuid+")"
								+ ",0,"+
								check+",TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),'"+contentArea.getText()+"',"+infoStuid+")";
					}
					DBConnect.DataUpdate(sql);
					DBConnect.Close();
					panBoard.add(panMypage);
					panBoard.add(panNotice);
					panBoard.add(panClass);
					panBoard.add(panCommu);
					jspCenter.setRightComponent(panBoard);
					setBoardData();
					panBoard.setVisible(true);
					panMypage.setVisible(false);
					panNotice.setVisible(false);
					panClass.setVisible(false);
					panCommu.setVisible(false);
					panWriteBoard.setVisible(false);
					contentArea.setText("");
					titleField.setText("");
					noticeCheck.setSelected(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DBConnect.Close();
				}
			}
		});

	}



	private void lecturePanel() { //강의 목록 패널
		panLect = new JPanel();
		panLect.setLayout(null);

		JLabel lectLbl = new JLabel("강의실");
		lectLbl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lectLbl.setLocation(70, 20);
		lectLbl.setSize(400, 40);
		panLect.add(lectLbl);

		ImageIcon lecture = new ImageIcon("./images/teacher.png");
		JLabel lectureLbl = new JLabel(lecture);
		lectureLbl.setLocation(170, 0);
		lectureLbl.setSize(100, 90);
		panLect.add(lectureLbl);
		//table
		if(infoJob.equals("학생")) {
			String[] classRoomHeader = {"과목명","개설학과","담당교수"};
			lectModel = new DefaultTableModel(classRoomHeader,0) {
				public boolean isCellEditable(int rowIndex, int mColIndes) {//내용 수정 불가
					return false;
				}
			};
		}
		else if(infoJob.equals("교수")){
			String[] classRoomHeader = {"과목명","학년 반"};
			lectModel = new DefaultTableModel(classRoomHeader,0) {
				public boolean isCellEditable(int rowIndex, int mColIndes) {//내용 수정 불가
					return false;
				}
			};
		}
		JTable lectTable = new JTable(lectModel);
		//setOutClassRoomData();
		if(infoJob.equals("학생")) {
			lectTable.getColumn("과목명").setPreferredWidth(12);
			lectTable.getColumn("개설학과").setPreferredWidth(12);
			lectTable.getColumn("담당교수").setPreferredWidth(12);
		}
		JScrollPane lectScroll = new JScrollPane(lectTable);
		lectScroll.setLocation(70, 80);
		lectScroll.setSize(730, 330);
		lectScroll.setFont(new Font("굴림", Font.BOLD, 17));
		lectScroll.setEnabled(false);
		lectTable.setRowHeight(45);


		lectTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//하나만 선택되게
		lectTable.getTableHeader().setReorderingAllowed(false);//제목 드래그로 위치 이동 안되게하기
		lectTable.getTableHeader().setResizingAllowed(false);//크기 조절 불가능하게하기

		lectTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {	//마우스 리스너
				if(e.getClickCount()==2) {
					int row = lectTable.getSelectedRow();
					classRoomPanel(row);
				}

			}
		});
		//가운데정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = lectTable.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(dtcr);
		}
		panLect.add(lectScroll);
	}


	private void classRoomPanel(int row) { // 강의실 패널
		String classTitle = lectModel.getValueAt(row, 0)+"";
		String grade = lectModel.getValueAt(row, 1).toString().substring(0,1);
		String classN = lectModel.getValueAt(row, 1).toString().substring(2,3);
		JPanel classRoomPanel = new JPanel();
		classRoomPanel.setLayout(null);
		JLabel javaLbl = new JLabel(lectModel.getValueAt(row, 0)+"강의실");
		javaLbl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		javaLbl.setLocation(70, 20);
		javaLbl.setSize(400, 40);
		classRoomPanel.add(javaLbl);

		JButton lectAddButton = new JButton("강의 업로드");
		lectAddButton.setSize(110, 25);
		lectAddButton.setLocation(685, 410);
		JButton lectRemoveButton = new JButton("강의 삭제");
		lectRemoveButton.setSize(100, 25);
		lectRemoveButton.setLocation(580, 410);
		if(infoJob.equals("교수")) {
			classRoomPanel.add(lectAddButton);
			classRoomPanel.add(lectRemoveButton);
		}
		lectAddButton.setBackground(Color.WHITE);
		lectAddButton.setBorder(bor);
		lectRemoveButton.setBackground(Color.WHITE);
		lectRemoveButton.setBorder(bor);
		lectAddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "select * from classcode where classtitle like '"+classTitle+"'";
				System.out.println(sql);
				rs= DBConnect.DataSelect(sql);
				try {
					rs.next();
					String classCode =rs.getString("code");
					new LectAdd(grade,classN,classCode,row,Main.this);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Vector<String> javaVector=new Vector<String>();
		javaVector.add("강의번호");
		javaVector.add("강의명");
		classRoomModel = new DefaultTableModel(javaVector,0) {
			public boolean isCellEditable(int rowIndex, int mColIndes) {//내용 수정 불가
				return false;
			}
		};
		JTable classRoomTable = new JTable(classRoomModel);
		classRoomTable.setFont(new Font("a", Font.PLAIN, 20));
		classRoomTable.setRowHeight(30);
		classRoomTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		classRoomTable.getColumnModel().getColumn(1).setPreferredWidth(500);

		classRoomTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//하나만 선택되게
		classRoomTable.getTableHeader().setReorderingAllowed(false);//제목 드래그로 위치 이동 안되게하기
		classRoomTable.getTableHeader().setResizingAllowed(false);//크기 조절 불가능하게하기
		classRoomTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {//2번 눌렀으면
				if(e.getClickCount()==2) {
					int row = classRoomTable.getSelectedRow();
					// *************링크로 이동하도록 설정 *************
					Desktop desktop = Desktop.getDesktop();
					try {
						String sql;
						if(infoJob.equals("학생")) {
							sql= "select * from video where classcode like (SELECT classcode FROM class WHERE CLASSTITLE LIKE '"+classTitle+"' GROUP BY CLASSCODE) "
									+ "and grade like "+infoGrade+" and class like '"+infoClass+"' and videonum like "+classRoomTable.getValueAt(row, 0);
						}
						else {
							sql= "select * from video where classcode like (SELECT classcode FROM class WHERE CLASSTITLE LIKE '"+classTitle+"' GROUP BY CLASSCODE) "
									+ "and grade like "+grade+" and class like '"+classN+"' and videonum like "+classRoomTable.getValueAt(row, 0);
						}
						System.out.println(sql);
						rs=DBConnect.DataSelect(sql);
						rs.next();
						String url = rs.getString("videolink");
						URI uri = new URI(url);
						desktop.browse(uri);
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (URISyntaxException ex) {
						ex.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						DBConnect.Close();
					}
				}
			}
		});
		lectRemoveButton.addActionListener(new ActionListener() {	//강의삭제 버튼 리스너

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = classRoomTable.getSelectedRow();
				String videoNum = classRoomTable.getValueAt(row, 0).toString();
				String sql = "delete from video where grade = "+grade+" and class = '"+classN+"' and videonum Like " + videoNum + " and classcode like (SELECT classcode FROM class WHERE CLASSTITLE LIKE '"+classTitle+"' GROUP BY CLASSCODE)";
				System.out.print(sql);
				DBConnect.DataUpdate(sql);
				DBConnect.Close();
				setInClassRoomData(classTitle, grade, classN);
			}
		});

		JScrollPane javaScrollPane = new JScrollPane(classRoomTable);
		javaScrollPane.setLocation(70, 80);
		javaScrollPane.setSize(730, 330);
		classRoomPanel.add(javaScrollPane);
		setInClassRoomData(row);
		classRoomPanel.add(panMypage);
		classRoomPanel.add(panNotice);
		classRoomPanel.add(panClass);
		classRoomPanel.add(panCommu);
		setTableCenter(classRoomTable);
		jspCenter.setRightComponent(classRoomPanel);
		panNote.setVisible(true);
		panMypage.setVisible(false);
		panNotice.setVisible(false);
		panClass.setVisible(false);
		panCommu.setVisible(false);
	}

	private void messagePanel() { //쪽지함 패널
		panNote = new JPanel();
		panNote.setLayout(null);

		JLabel noteLbl = new JLabel("쪽지함");
		noteLbl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		noteLbl.setLocation(70, 20);
		noteLbl.setSize(400, 40);
		panNote.add(noteLbl);
		ImageIcon message = new ImageIcon("./images/message.png");
		JLabel messageLbl = new JLabel(message);
		messageLbl.setLocation(170,0);
		messageLbl.setSize(100, 90);
		panNote.add(messageLbl);

		messageTableVector=new Vector<String>();
		for(String str : messageHeader) {
			messageTableVector.add(str);
		}
		messageModel = new DefaultTableModel(messageTableVector,0) {
			public boolean isCellEditable(int rowIndex, int mColIndes) {//내용 수정 불가
				return false;
			}
		};
		messageTable = new JTable(messageModel);
		messageTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//하나만 선택되게
		messageTable.getTableHeader().setReorderingAllowed(false);//제목 드래그로 위치 이동 안되게하기
		messageTable.getTableHeader().setResizingAllowed(false);//크기 조절 불가능하게하기
		messageTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {//2번 눌렀으면
				if(e.getClickCount()==2) {
					int row = messageTable.getSelectedRow();
					panMypage.setVisible(false);
					panNotice.setVisible(false);
					panClass.setVisible(false);
					panCommu.setVisible(false);
					messageContentPanel(row);
				}
			}
		});



		//setTableCenter(boardTable);
		messageTable.getColumnModel().getColumn(0).setPreferredWidth(10);//쪽지번호
		messageTable.getColumnModel().getColumn(1).setPreferredWidth(300);//제목
		messageTable.getColumnModel().getColumn(2).setPreferredWidth(30);//보낸이
		messageTable.getColumnModel().getColumn(3).setPreferredWidth(50);//날짜
		messageScrollPane = new JScrollPane(messageTable);
		messageScrollPane.setLocation(70, 80);
		messageScrollPane.setSize(730, 330);
		panNote.add(messageScrollPane);
		setTableCenter(messageTable);

		ButtonGroup radioGroup = new ButtonGroup();
		JRadioButton noncheckButton = new JRadioButton("열람X");
		JRadioButton checkButton = new JRadioButton("열람");
		JRadioButton allButton = new JRadioButton("전체");
		radioGroup.add(noncheckButton);
		radioGroup.add(checkButton);
		radioGroup.add(allButton);
		checkButton.setVerticalTextPosition(JRadioButton.TOP);
		checkButton.setHorizontalTextPosition(JRadioButton.CENTER);//라벨 라디오 버튼 위로 올리고 가운데 정렬
		noncheckButton.setVerticalTextPosition(JRadioButton.TOP);
		noncheckButton.setHorizontalTextPosition(JRadioButton.CENTER);//라벨 라디오 버튼 위로 올리고 가운데 정렬
		allButton.setVerticalTextPosition(JRadioButton.TOP);
		allButton.setHorizontalTextPosition(JRadioButton.CENTER);
		checkButton.setSize(50,50);
		noncheckButton.setSize(50,50);
		allButton.setSize(50,50);
		checkButton.setLocation(450,25);
		noncheckButton.setLocation(400,25);
		allButton.setLocation(350,25);
		allButton.setSelected(true);
		panNote.add(allButton);
		panNote.add(checkButton);
		panNote.add(noncheckButton);
		JTextField searchMessageField = new JTextField(10);
		JButton buttonSearch = new JButton("검색");
		JComboBox searchComboBox = new JComboBox(searchMessageCombo);
		((JLabel)searchComboBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		searchComboBox.setBackground(Color.white);
		searchComboBox.setSize(70,25);
		searchComboBox.setLocation(517, 45);
		panNote.add(searchComboBox);
		buttonSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!searchMessageField.getText().equals("")) {
					if(allButton.isSelected()) {
						if(searchComboBox.getSelectedIndex()==0) {
							searchAllTitleMessage(searchMessageField.getText());
							System.out.println("!");
						}
						else {
							searchAllNameMessage(searchMessageField.getText());
						}
					}
					else if(checkButton.isSelected()) {
						if(searchComboBox.getSelectedIndex()==0) {
							searchCheckTitleMessage(searchMessageField.getText());
							System.out.println("!!");
						}
						else {
							searchCheckNameMessage(searchMessageField.getText());
						}
					}
					else {
						if(searchComboBox.getSelectedIndex()==0) {
							searchNonCheckTitleMessage(searchMessageField.getText());
							System.out.println("!!!");
						}
						else {
							searchNonCheckNameMessage(searchMessageField.getText());
						}
					}
				}
				else {
					if(allButton.isSelected()) {
						setMessageData();
					}
					else if(checkButton.isSelected()){
						defaultOpenMessage();
					}
					else {
						defaultNonOpenMessage();

					}
				}
			}
		});
		searchMessageField.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!searchMessageField.getText().equals("")) {
					if(allButton.isSelected()) {
						if(searchComboBox.getSelectedIndex()==0) {
							searchAllTitleMessage(searchMessageField.getText());
							System.out.println("!");
						}
						else {
							searchAllNameMessage(searchMessageField.getText());
						}
					}
					else if(checkButton.isSelected()) {
						if(searchComboBox.getSelectedIndex()==0) {
							searchCheckTitleMessage(searchMessageField.getText());
							System.out.println("!!");
						}
						else {
							searchCheckNameMessage(searchMessageField.getText());
						}
					}
					else {
						if(searchComboBox.getSelectedIndex()==0) {
							searchNonCheckTitleMessage(searchMessageField.getText());
							System.out.println("!!!");
						}
						else {
							searchNonCheckNameMessage(searchMessageField.getText());
						}
					}
				}
				else {
					if(allButton.isSelected()) {
						setMessageData();
					}
					else if(checkButton.isSelected()){
						defaultOpenMessage();
					}
					else {
						defaultNonOpenMessage();

					}
				}
			}
		});
		allButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchMessageField.getText().equals("")) {
					setMessageData();
				}else {
					if(searchComboBox.getSelectedIndex()==0) {
						searchAllTitleMessage(searchMessageField.getText());
						System.out.println("!");
					}
					else {
						searchAllNameMessage(searchMessageField.getText());
					}
				}
			}
		});
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchMessageField.getText().equals("")) {
					defaultOpenMessage();
				}
				else {
					if(searchComboBox.getSelectedIndex()==0) {
						searchCheckTitleMessage(searchMessageField.getText());
						System.out.println("!!");
					}
					else {
						searchCheckNameMessage(searchMessageField.getText());
					}
				}

			}
		});
		noncheckButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchMessageField.getText().equals("")) {
					defaultNonOpenMessage();
				}
				else {
					if(searchComboBox.getSelectedIndex()==0) {
						searchNonCheckTitleMessage(searchMessageField.getText());
						System.out.println("!!");
					}
					else {
						searchNonCheckNameMessage(searchMessageField.getText());
					}
				}

			}
		});
		searchMessageField.setLocation(597, 45);
		searchMessageField.setSize(130, 25);
		buttonSearch.setLocation(737, 45);
		buttonSearch.setSize(60, 25);
		panNote.add(searchMessageField);
		panNote.add(buttonSearch);
		setMessageData();
		JButton buttonMessageWrite = new JButton("글쓰기");
		buttonMessageWrite.addActionListener(this);
		buttonMessageWrite.setLocation(720, 420);
		buttonMessageWrite.setSize(77, 27);
		buttonMessageWrite.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				messageWritePanel();
			}
		});
		panNote.add(buttonMessageWrite);

		//panNote.setBackground(new Color(0xCCE5FF));

	}
	private void messageContentPanel(int row) { // 쪽지 더블클릭시 보일 내용 패널
		JPanel panContentMessage = new JPanel();
		panContentMessage.setLayout(null);
		//panContentBoard.setBackground(new Color(0xCCE5FF));
		JTextField[] infoFields = new JTextField[3];
		JLabel[] infoLabel = new JLabel[3];
		EtchedBorder eborder =  new EtchedBorder();
		panContentMessage.add(panMypage);
		panContentMessage.add(panNotice);
		panContentMessage.add(panClass);
		panContentMessage.add(panCommu);
		for(int i=0;i<infoFields.length;i++) {
			infoFields[i] = new JTextField();
			infoLabel[i]= new JLabel();
			infoLabel[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			infoFields[i].setText(messageModel.getValueAt(row, i+1).toString());
			if(i==0) {
				infoFields[i].setLocation(70, 100);
				infoFields[i].setSize(476,25);
				infoLabel[i].setSize(476,25);
				infoLabel[i].setText("제목");
				infoLabel[i].setLocation(70, 70);
			}
			else {
				infoFields[i].setLocation(420+126*i, 100);
				infoFields[i].setSize(126,25);
				infoLabel[i].setSize(126,25);
				infoLabel[i].setLocation(420+126*i, 70);
			}
			infoLabel[i].setHorizontalAlignment(JLabel.CENTER);
			infoFields[i].setHorizontalAlignment(JLabel.CENTER);
			infoFields[i].setEditable(false);
			infoFields[i].setBackground(Color.WHITE);
			infoLabel[i].setOpaque(true);//배경색 변경 적용할것인가
			infoLabel[i].setBackground(Color.WHITE);
			infoLabel[i].setBorder(eborder);
			panContentMessage.add(infoLabel[i]);
			panContentMessage.add(infoFields[i]);
		}
		infoLabel[1].setText("보낸이");
		infoLabel[2].setText("발송일");
		DefaultTableModel messageAllDataModel = new DefaultTableModel();
		String sql = "select content from message where messagenum like "+messageModel.getValueAt(row, 0)+" and receiveid like '"+id+"'";
		try {
			rs=DBConnect.DataSelect(sql);
			rs.next();
			JTextArea contentArea = new JTextArea(rs.getString("content"));
			DBConnect.Close();
			sql = "UPDATE message SET \"checkopen\" = "+1+" WHERE messagenum = "+messageModel.getValueAt(row,0);
			DBConnect.DataUpdate(sql);//db에 읽은거 체크
			DBConnect.Close();
			contentArea.setEditable(false);
			messageScrollPane = new JScrollPane(contentArea);
			messageScrollPane.setSize(730,270);
			messageScrollPane.setLocation(70, 130);
			JButton buttonBack = new JButton("뒤로가기");
			buttonBack.setLocation(697, 420);
			buttonBack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					panNote.add(panMypage);
					panNote.add(panNotice);
					panNote.add(panClass);
					panNote.add(panCommu);
					jspCenter.setRightComponent(panNote);


					panNote.setVisible(true);
					panMypage.setVisible(false);
					panNotice.setVisible(false);
					panClass.setVisible(false);
					panCommu.setVisible(false);

				}
			});
			buttonBack.setSize(100, 27);
			panContentMessage.add(messageScrollPane);
			panContentMessage.add(buttonBack);
			jspCenter.setRightComponent(panContentMessage);
			JButton deleteButton = new JButton("삭제");
			deleteButton.setSize(100,27);
			deleteButton.setLocation(577,420);
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String sql ="";
					sql="delete from message where messagenum like "+messageModel.getValueAt(row, 0)+" and receiveid like '"+id+"'";
					if(JOptionPane.showConfirmDialog(jspCenter.getRightComponent(), "쪽지를 지우시겠습니까?","Delete",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION) {
						System.out.println(sql);
						DBConnect.DataUpdate(sql);
						DBConnect.Close();
						panNote.add(panMypage);
						panNote.add(panNotice);
						panNote.add(panClass);
						panNote.add(panCommu);
						jspCenter.setRightComponent(panNote);

						panNote.setVisible(true);
						panMypage.setVisible(false);
						panNotice.setVisible(false);
						panClass.setVisible(false);
						panCommu.setVisible(false);
						setMessageData();
					}
				}
			});
			panContentMessage.add(deleteButton);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void messageWritePanel() { // 쪽지 보내기 패널
		JPanel panWriteMessage = new JPanel();
		panWriteMessage.setLayout(null);
		//panContentBoard.setBackground(new Color(0xCCE5FF));
		JLabel title = new JLabel("제목");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JTextField titleField = new JTextField();
		titleField.setLocation(70,100);
		titleField.setSize(550,25);
		titleField.setHorizontalAlignment(JLabel.CENTER);
		title.setLocation(70,70);
		title.setSize(550,25);
		JLabel receiveId = new JLabel("받는이(아이디)");
		receiveId.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		JTextField receiveField = new JTextField();
		receiveField.setHorizontalAlignment(JLabel.CENTER);
		receiveId.setHorizontalAlignment(JLabel.CENTER);
		receiveField.setSize(170,25);
		receiveId.setSize(170,25);
		receiveId.setLocation(630, 70);
		receiveField.setLocation(630, 100);
		panWriteMessage.add(receiveField);
		panWriteMessage.add(receiveId);
		title.setHorizontalAlignment(JLabel.CENTER);
		panWriteMessage.add(panMypage);
		panWriteMessage.add(panNotice);
		panWriteMessage.add(panClass);
		panWriteMessage.add(panCommu);
		panWriteMessage.add(title);
		panWriteMessage.add(titleField);
		JTextArea contentArea = new JTextArea();
		JScrollPane writeScrollPane = new JScrollPane(contentArea);
		writeScrollPane.setSize(730,270);
		writeScrollPane.setLocation(70, 130);
		JButton buttonBack = new JButton("뒤로가기");
		JButton buttonOk = new JButton("보내기");
		buttonOk.addActionListener(this);
		buttonOk.setSize(100,27);
		buttonOk.setLocation(577,420);
		buttonBack.setLocation(697, 420);
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jspCenter.setRightComponent(panNote);
				panNote.add(panMypage);
				panNote.add(panNotice);
				panNote.add(panClass);
				panNote.add(panCommu);
				setMessageData();
				panNote.setVisible(true);
				panMypage.setVisible(false);
				panNotice.setVisible(false);
				panClass.setVisible(false);
				panCommu.setVisible(false);
				contentArea.setText("");
				titleField.setText("");		
			}
		});
		buttonBack.setSize(100, 27);
		panWriteMessage.add(buttonOk);
		panWriteMessage.add(writeScrollPane);
		panWriteMessage.add(buttonBack);
		buttonOk.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(receiveField.getText().equals(""))) {
					try {
						String sql="select * from member where id like '"+receiveField.getText()+"'";
						rs=DBConnect.DataSelect(sql);
						if(rs.next()) {
							if(!(titleField.getText().equals(""))) {
								if(!(contentArea.getText().equals(""))) {
									sql="SELECT Max(messagenum)+1 FROM message WHERE RECEIVEID LIKE '"+receiveField.getText()+"'";
									ResultSet check = DBConnect.DataSelect(sql);
									check.next();
									if(check.getString("Max(messagenum)+1")==null){//받는 사람이 쪽지가 한개도 없으면
										sql="Insert into message values("
												+ "'"+titleField.getText()+"',"//제목
												+ "'"+contentArea.getText()+"',"//내용
												+ "(select name from member where id like '"+id+"'),"//보낸 사람 이름
												+ "'"+receiveField.getText()+"',"//받는 사람 id
												+ "0,"//읽은지 여부
												+ "TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),"//시간
												+ "1)";//받는사람 쪽지번호
									}
									else {
										sql="Insert into message values("
												+ "'"+titleField.getText()+"',"//제목
												+ "'"+contentArea.getText()+"',"//내용
												+ "(select name from member where id like '"+id+"'),"//보낸 사람 이름
												+ "'"+receiveField.getText()+"',"//받는 사람 id
												+ "0,"//읽은지 여부
												+ "TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),"//시간
												+ "(SELECT Max(messagenum)+1 FROM message WHERE RECEIVEID LIKE '"+receiveField.getText()+"'))";//받는사람 쪽지번호
									}
									//Insert into message values('제목','내용',(select name from member where stuid like 고름사람),333333,0,TO_CHAR(SYSTIMESTAMP ,'yyyy-mm-dd-hh24:mi'),
									//SELECT Max(messagenum)+1 FROM message WHERE sendname LIKE (SELECT name FROM MEMBER WHERE stuid LIKE 고른사람)
									DBConnect.DataUpdate(sql);
									DBConnect.Close();
									jspCenter.setRightComponent(panNote);
									panNote.add(panMypage);
									panNote.add(panNotice);
									panNote.add(panClass);
									panNote.add(panCommu);
									setMessageData();
									panNote.setVisible(true);
									panMypage.setVisible(false);
									panNotice.setVisible(false);
									panClass.setVisible(false);
									panCommu.setVisible(false);
									panWriteMessage.setVisible(false);
									contentArea.setText("");
									titleField.setText("");
									receiveField.setText("");
								}
								else {
									JOptionPane.showMessageDialog(null, "내용을 비우시면 안됩니다.", "경고", JOptionPane.WARNING_MESSAGE);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "제목을 비우시면 안됩니다.", "경고", JOptionPane.WARNING_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.", "경고", JOptionPane.WARNING_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						DBConnect.Close();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "아이디 칸은 비울 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		if(infoJob.equals("관리자")) {//관리자라면
			JButton allSend = new JButton("모두에게 보내기");
			allSend.setSize(150,27);
			allSend.setLocation(407,420);
			allSend.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!(titleField.getText().equals(""))) {
						if(!(contentArea.getText().equals(""))) {
							String sql = "select id from member";
							try {
								rs = DBConnect.DataSelect(sql);
								while(rs.next()) {
									String id=rs.getString("id");
									sql="SELECT Max(messagenum)+1 FROM message WHERE RECEIVEID LIKE (SELECT id FROM MEMBER WHERE id LIKE '"+id+"')";
									System.out.println("!"+sql);
									ResultSet check = DBConnect.DataSelect(sql);
									check.next();
									if(check.getString("Max(messagenum)+1")==null){//받는 사람이 쪽지가 한개도 없으면
										sql="Insert into message values("
												+ "'"+titleField.getText()+"',"//제목
												+ "'"+contentArea.getText()+"',"//내용
												+ "'관리자',"//보낸 사람 이름
												+ "'"+id+"',"//받는 사람 id
												+ "0,"//읽은지 여부
												+ "TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),"//시간
												+ "1)";//받는사람 쪽지번호
									}
									else {
										sql="Insert into message values("
												+ "'"+titleField.getText()+"',"//제목
												+ "'"+contentArea.getText()+"',"//내용
												+ "'관리자',"//보낸 사람 이름
												+ "'"+id+"',"//받는 사람 id
												+ "0,"//읽은지 여부
												+ "TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),"//시간
												+ "(SELECT Max(messagenum)+1 FROM message WHERE RECEIVEID LIKE '"+id+"'))";//받는사람 쪽지번호
									}
									System.out.println(sql);
									DBConnect.DataUpdate(sql);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}finally {
								jspCenter.setRightComponent(panNote);
								panNote.add(panMypage);
								panNote.add(panNotice);
								panNote.add(panClass);
								panNote.add(panCommu);
								setMessageData();
								panNote.setVisible(true);
								panMypage.setVisible(false);
								panNotice.setVisible(false);
								panClass.setVisible(false);
								panCommu.setVisible(false);
								panWriteMessage.setVisible(false);
								contentArea.setText("");
								titleField.setText("");
								receiveField.setText("");
								DBConnect.Close();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "내용을 비우시면 안됩니다.", "경고", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "제목을 비우시면 안됩니다.", "경고", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			panWriteMessage.add(allSend);
		}
		else if(infoJob.equals("교수")) {//교수라면 담당 학생들에게
			JButton allSend = new JButton("학부생들에게 보내기");
			allSend.setSize(150,27);
			allSend.setLocation(407,420);
			allSend.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(!(titleField.getText().equals(""))) {
						if(!(contentArea.getText().equals(""))) {
							String sql = "select id from member where major like '"+infoMajor+"' and Job like '학생'";//ex로 컴시과만ㅇ
							try {
								rs = DBConnect.DataSelect(sql);
								while(rs.next()) {
									String id=rs.getString("id");
									sql="SELECT Max(messagenum)+1 FROM message WHERE RECEIVEID LIKE (SELECT id FROM MEMBER WHERE id LIKE '"+id+"')";
									ResultSet check = DBConnect.DataSelect(sql);
									check.next();
									if(check.getString("Max(messagenum)+1")==null){//받는 사람이 쪽지가 한개도 없으면
										sql="Insert into message values("
												+ "'"+titleField.getText()+"',"//제목
												+ "'"+contentArea.getText()+"',"//내용
												+ "'"+infoName+"',"//보낸 사람 이름
												+ "'"+id+"',"//받는 사람 id
												+ "0,"//읽은지 여부
												+ "TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),"//시간
												+ "1)";//받는사람 쪽지번호
									}
									else {
										sql="Insert into message values("
												+ "'"+titleField.getText()+"',"//제목
												+ "'"+contentArea.getText()+"',"//내용
												+ "'"+infoName+"',"//보낸 사람 이름
												+ "'"+id+"',"//받는 사람 id
												+ "0,"//읽은지 여부
												+ "TO_CHAR(SYSTIMESTAMP,'yyyy-mm-dd-hh24:mi'),"//시간
												+ "(SELECT Max(messagenum)+1 FROM message WHERE RECEIVEID LIKE '"+id+"'))";//받는사람 쪽지번호
									}
									DBConnect.DataUpdate(sql);
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}finally {jspCenter.setRightComponent(panNote);
							panNote.add(panMypage);
							panNote.add(panNotice);
							panNote.add(panClass);
							panNote.add(panCommu);
							setMessageData();
							panNote.setVisible(true);
							panMypage.setVisible(false);
							panNotice.setVisible(false);
							panClass.setVisible(false);
							panCommu.setVisible(false);
							panWriteMessage.setVisible(false);
							contentArea.setText("");
							titleField.setText("");
							receiveField.setText("");
							DBConnect.Close();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "내용을 비우시면 안됩니다.", "경고", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "제목을 비우시면 안됩니다.", "경고", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			panWriteMessage.add(allSend);
		}
		jspCenter.setRightComponent(panWriteMessage);
		panWriteMessage.setVisible(true);
		panMypage.setVisible(false);
		panNotice.setVisible(false);
		panClass.setVisible(false);
		panCommu.setVisible(false);

	}



	private void schePanel() { // 스케쥴
		panSche = new JPanel(); // 콤보박스 사용하여 학년, 반 입력받아서 출력
		panSche.setLayout(null);

		JLabel schLbl = new JLabel("시간표");
		schLbl.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		schLbl.setLocation(70, 20);
		schLbl.setSize(400, 40);
		ImageIcon scheImg = new ImageIcon("./images/calendar.png");
		JLabel schImgLbl = new JLabel(scheImg);
		schImgLbl.setLocation(170,0);
		schImgLbl.setSize(100, 90);
		panSche.add(schImgLbl);
		scheduleModel = new DefaultTableModel(scheduleHeader,0){
			public boolean isCellEditable(int rowIndex, int mColIndes) {//내용 수정 불가
				return false;
			}
		};
		JTable scheduleTable = new JTable(scheduleModel);
		scheduleTable.setRowHeight(55);
		scheduleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//하나만 선택되게
		scheduleTable.getTableHeader().setReorderingAllowed(false);//제목 드래그로 위치 이동 안되게하기
		scheduleTable.getTableHeader().setResizingAllowed(false);//크기 조절 불가능하게하기
		JScrollPane scheduleScrollPane = new JScrollPane(scheduleTable);
		scheduleScrollPane.setLocation(70, 80);
		scheduleScrollPane.setSize(730, 330);
		JButton addButton = new JButton("강의 개설");
		addButton.setLocation(697, 420);
		addButton.setSize(100, 27);
		if(infoJob.equals("교수")) {
			panSche.add(addButton);
			setProScheduleData();
		}
		else if(infoJob.equals("학생")) {
			setStuScheduleData();
		}
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ClassAdd(infoName,infoStuid,infoMajor,Main.this);				
			}
		});
		panSche.add(schLbl);
		panSche.add(scheduleScrollPane);
	}

	private void proPanel() { //프로필 패널

		//프로필 패널 
		panPro = new JPanel();
		panPro.setLayout(null);

		ImageIcon proImg = new ImageIcon("./images/user.png");
		JLabel proImgLbl = new JLabel(proImg);
		proImgLbl.setLocation(160,0);
		proImgLbl.setSize(100, 90);
		panPro.add(proImgLbl);
		String header[] = {"info"};
		String contents[][] = {
				{"학과"},
				{"이름"},
				{"직책"},
				{"학번"},
				{"학년"},
				{"반"},
				{"번호"},
				{"주소"}
		};
		JLabel ProFileLabel_ProFile = new JLabel("프로필");
		ProFileLabel_ProFile.setLocation(70, 20);
		ProFileLabel_ProFile.setSize(400, 40);
		ProFileLabel_ProFile.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ProFileLabel_ProFile.setBackground(Color.black);
		panPro.add(ProFileLabel_ProFile);


		JPanel ProFilePanel_1 = new JPanel(); // 프로필 바탕
		ProFilePanel_1.setSize(800, 500);
		ProFilePanel_1.setBackground(new Color(240,240,240));
		ProFilePanel_1.setLayout(null);
		ProFilePanel_1.setVisible(true);
		panPro.add(ProFilePanel_1);


		ProModel = new DefaultTableModel(contents,header);
		ProTable = new JTable(ProModel);
		ProTable.setLocation(80, 100);
		ProTable.setSize(650, 320);
		ProTable.setEnabled(false);
		ProTable.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		ProTable.setRowHeight(40);
		ProTable.setBackground(Color.WHITE);

		ProFilePanel_1.add(ProTable);

		profilDB();

	}
	private void mainPanel() { // 홈 패널
		panMain = new JPanel();
		panMain.setLayout(null);
		panMain.add(panMypage);
		panMain.add(panNotice);
		panMain.add(panClass);
		panMain.add(panCommu);
		mainImg = new ImageIcon("./images/main.jpg");
		mainLbl = new JLabel(mainImg);
		panMain.add(mainLbl);
		mainLbl.setLocation(-130, -150);
		mainLbl.setSize(1110, 800);
		panMypage.setVisible(false);
		panNotice.setVisible(false);
		panClass.setVisible(false);
		panCommu.setVisible(false);
		jspCenter.setRightComponent(panMain);

	}


	private void detailList() {				//세부리스트

		//Mypage
		panMypage = new JPanel();
		panMypage.setLayout(new GridLayout(2, 1));

		if(infoJob.equals("관리자"))
			btnMyinfo = new JButton("회원관리");
		else
			btnMyinfo = new JButton("프로필");
		btnMyinfo.addActionListener(this);
		btnSchedule = new JButton("시간표");
		btnSchedule.addActionListener(this);
		btnMyinfo.setBackground(Color.WHITE);
		btnSchedule.setBackground(Color.WHITE);

		panMypage.add(btnMyinfo);
		panMypage.add(btnSchedule);
		panMypage.setSize(90, 122);
		panMypage.setLocation(0, 0);

		//Notice
		panNotice = new JPanel();
		panNotice.setLayout(new GridLayout(2, 1));

		btnNote = new JButton("쪽지");
		btnNote.addActionListener(this);
		btnNote.setBackground(Color.WHITE);

		panNotice.add(btnNote);
		panNotice.setSize(90, 122);
		panNotice.setLocation(0, 122);

		//Class
		panClass = new JPanel();
		panClass.setLayout(new GridLayout(2, 1));

		btnLecture = new JButton("강의 목록");
		btnLecture.addActionListener(this);
		btnLecture.setBackground(Color.WHITE);

		panClass.add(btnLecture);
		panClass.setSize(90, 122);
		panClass.setLocation(0, 244);

		//Commu
		panCommu = new JPanel();
		panCommu.setLayout(new GridLayout(2, 1));

		btnBoard = new JButton("게시판");
		btnBoard.addActionListener(this);
		btnBoard.setBackground(Color.WHITE);

		panCommu.add(btnBoard);
		panCommu.setSize(90, 122);
		panCommu.setLocation(0, 366);

		panMypage.setBackground(new Color(255, 0, 0, 0));
		panNotice.setBackground(new Color(255, 0, 0, 0));
		panClass.setBackground(new Color(255, 0, 0, 0));
		panCommu.setBackground(new Color(255, 0, 0, 0));

	}


	private void PanelLeft() {				//좌측패널
		panLeft = new JPanel();
		panLeft.setLayout(new GridLayout(4, 1));

		//*버튼에 마우스 올라오면 세부리스트 나타내기
		ImageIcon my = new ImageIcon("./images/my.png");
		ImageIcon noti = new ImageIcon("./images/noti.png");
		ImageIcon lect = new ImageIcon("./images/lect.png");
		ImageIcon comm = new ImageIcon("./images/board.png");

		btnMypage = new JButton(my);
		btnNotice = new JButton(noti);
		btnClass = new JButton(lect);
		btnCommu = new JButton(comm);

		btnMypage.addActionListener(this);
		btnNotice.addActionListener(this);
		btnClass.addActionListener(this);
		btnCommu.addActionListener(this);


		btnMypage.setBackground(Color.WHITE);
		btnNotice.setBackground(Color.WHITE);
		btnClass.setBackground(Color.WHITE);
		btnCommu.setBackground(Color.WHITE);

		panLeft.add(btnMypage);
		panLeft.add(btnNotice);
		panLeft.add(btnClass);
		panLeft.add(btnCommu);

		jspCenter.setLeftComponent(panLeft);
	}


	private void panelTop() {				//상단패널
		panTop.setLayout(new GridLayout(1, 4));
		panTop.setBackground(Color.WHITE);

		//홈버튼 - *라벨 클릭시 홈화면 이동
		Homelogo = new ImageIcon("./images/lblHome.png");
		lblHome = new JLabel(Homelogo);

		//날짜_시간 라벨
		panTop2 = new JPanel();
		panTop2.setLayout(new GridLayout(2, 1));
		panTop2.setBackground(Color.WHITE);

		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
		String formatedNow = time.format(formatter);

		lblDate = new JLabel(date + " (" + date.getDayOfWeek() + ")");
		lblTime = new JLabel("접속시간 : " + formatedNow);

		panTop2.add(lblDate);
		panTop2.add(lblTime);

		//사용자 정보 - *DB연동하여 사용자 정보 받아오기

		lblUser = new JLabel("컴퓨터시스템과 / 202145055 / 이인범 (학생)");
		lblUser.setFont(new Font("Consolase", Font.BOLD, 15));
		setUserInfo();

		//로그아웃_종료 버튼
		panTop3 = new JPanel();
		panTop3.setLayout(new GridLayout(1,2,10,10));
		panTop3.setBackground(Color.WHITE);
		panTop3.setBorder(BorderFactory.createEmptyBorder(10,15,10,15));

		btnHome = new JButton(" Home ");
		btnHome.addActionListener(this);
		btnHome.setBackground(Color.WHITE);
		panTop3.add(btnHome);

		btnLogout = new JButton("Logout");	// 나중에 클래스 합치면 이 창은 종료하고 로그인창 띄우기
		btnLogout.setBackground(Color.WHITE);
		btnLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Logout",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == (JOptionPane.YES_OPTION)) {
					dispose();
					new LoginGUI();
				}
			}
		});
		panTop3.add(btnLogout);

		panTop.add(lblHome);
		panTop.add(panTop2);
		panTop.add(lblUser);
		panTop.add(panTop3);
	}



	private void setUserInfo() { //유저정보 위에 올려주는거
		//컴퓨터시스템과 / 202145055 / 이인범 (학생)
		String sql = "select * from member where id like '"+id+"'";
		rs = DBConnect.DataSelect(sql);
		try {
			rs.next();
			infoMajor=rs.getString("major");
			infoStuid=rs.getInt("stuid")+"";
			infoName=rs.getString("name");
			infoJob=rs.getString("job");
			infoClass=rs.getString("Class");
			infoGrade=rs.getInt("Grade")+"";

			String info = infoMajor+" / "+infoStuid+" / "+infoName+" ("+infoJob+")";
			lblUser.setText(info);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnect.Close();
	}

	private void baseFrame() {				//프레임세팅
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		panCenter = new JPanel();
		panTop = new JPanel();

		c.add(panTop, BorderLayout.NORTH);
		c.add(panCenter, BorderLayout.CENTER);

		panCenter.setLayout(new BorderLayout());
		jspCenter = new JSplitPane();
		panCenter.add(jspCenter);
	}

	private void seting(String title) {		//기본세팅
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		setSize(1000, 600);
		setResizable(false);
		setLayout(new BorderLayout());
	}

	private void menuBar() {				//메뉴바
		mb = new JMenuBar();

		menuFile = new JMenu("File");
		menuInfo = new JMenu("Info");
		menuHelp = new JMenu("Help");

		itemExit = new JMenuItem("닫기");
		itemInfo = new JMenuItem("개발자 정보");
		itemHelp = new JMenuItem("도움말"); 

		itemExit.addActionListener(this);
		itemInfo.addActionListener(this);
		itemHelp.addActionListener(this);

		menuFile.add(itemExit);
		menuInfo.add(itemInfo);
		menuHelp.add(itemHelp);
		mb.add(menuFile);
		mb.add(menuInfo);
		mb.add(menuHelp);
		setJMenuBar(mb);
	}




	public static void main(String[] args) {
		new Main("E-Learning","rlarlxo");
		new Main("E-Learning","admin");
		new Main("E-Learning","ashrain8778");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == btnMyinfo) { //프로필 화면
			if(btnMyinfo.getText().equals("프로필")) {
				panPro.add(panMypage);
				panPro.add(panNotice);
				panPro.add(panClass);
				panPro.add(panCommu);
				jspCenter.setRightComponent(panPro);


				panMypage.setVisible(false);
				panNotice.setVisible(false);
				panClass.setVisible(false);
				panCommu.setVisible(false);
			}
			else {
				new AdminFrame();
			}

		}else if(obj == btnSchedule) {	// 시간표 화면
			panSche.add(panMypage);
			panSche.add(panNotice);
			panSche.add(panClass);
			panSche.add(panCommu);
			jspCenter.setRightComponent(panSche);
			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}else if(obj == btnOfficial) {	// 공지 화면
			panOffi.add(panMypage);
			panOffi.add(panNotice);
			panOffi.add(panClass);
			panOffi.add(panCommu);

			jspCenter.setRightComponent(panOffi);

			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}else if(obj == btnNote) {		// 쪽지 화면
			panNote.add(panMypage);
			panNote.add(panNotice);
			panNote.add(panClass);
			panNote.add(panCommu);
			jspCenter.setRightComponent(panNote);


			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}else if(obj == btnLecture) {	// 강의 화면
			panLect.add(panMypage);
			panLect.add(panNotice);
			panLect.add(panClass);
			panLect.add(panCommu);
			jspCenter.setRightComponent(panLect);
			setOutClassRoomData();
			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}else if(obj == btnAssignment) { // 과제 화면
			panAssign.add(panMypage);
			panAssign.add(panNotice);
			panAssign.add(panClass);
			panAssign.add(panCommu);
			jspCenter.setRightComponent(panAssign);


			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}else if(obj == btnBoard||obj == buttonBoardBack) {		// 게시판 화면	
			panBoard.add(panMypage);
			panBoard.add(panNotice);
			panBoard.add(panClass);
			panBoard.add(panCommu);
			jspCenter.setRightComponent(panBoard);
			setBoardData();
			panBoard.setVisible(true);
			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
			panWriteBoard.setVisible(false);
		}else if(obj == btnWrite) {
			panWriteBoard.add(panMypage);
			panWriteBoard.add(panNotice);
			panWriteBoard.add(panClass);
			panWriteBoard.add(panCommu);
			jspCenter.setRightComponent(panWriteBoard);

			panWriteBoard.setVisible(true);
			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}
		else if(obj == btnMypage) { //메인버튼
			panMypage.setVisible(true);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}
		else if(obj == btnNotice) {
			panMypage.setVisible(false);
			panNotice.setVisible(true);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}
		else if(obj == btnClass) {
			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(true);
			panCommu.setVisible(false);
		}
		else if(obj == btnCommu) {
			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(true);
		}else if(obj == itemExit) {	// File메뉴
			if(JOptionPane.showConfirmDialog(this, "정말 끝낼까요?", "프로그램 종료",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == (JOptionPane.YES_OPTION)) {
				System.exit(0);	// 알림창의 YES버튼을 누르면 종료
			}
		}else if(obj == itemInfo) {	// Info메뉴
			JOptionPane.showMessageDialog(this, "ManagerFrame by 이건\nStudentFrame by 이인범\nProfessor and Login by 이수, 신창호");
		}else if(obj == itemHelp) { // Help메뉴
			JOptionPane.showMessageDialog(this, "이러닝 프로그램\n학생 : 프로필, 시간표, 공지, 쪽지, 강의, 과제, 게시판\n"
					+ "교수 : 프로필, 교과목정보, 과제업로드, 강의공지사항, 강의정보, 학생정보, 공지사항, 공지업로드\n"
					+ "관리자 : user관리 및 정보들에 대한 모든 권한 부여");
		}else if(obj == btnHome) {	// Home버튼
			panMain.add(panMypage);
			panMain.add(panNotice);
			panMain.add(panClass);
			panMain.add(panCommu);
			jspCenter.setRightComponent(panMain);

			panMypage.setVisible(false);
			panNotice.setVisible(false);
			panClass.setVisible(false);
			panCommu.setVisible(false);
		}
		setMessageData();
		setBoardData();
		if(infoJob.equals("학생"))
			setStuScheduleData();
		else if(infoJob.equals("교수"))
			setProScheduleData();
	}
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡDataBase 연동 메서드ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	public void profilDB() {		//profil_값불러오기
		Vector infoV = new Vector<>();
		String sql = "select * from member where stuid like "+infoStuid;
		try { //백터로 값 검색하여 테이블에 삽입v
			rs = DBConnect.DataSelect(sql);
			rs.next();
			infoV.add(rs.getString("major"));  
			infoV.add(rs.getString("name"));  
			infoV.add(rs.getString("Job"));  
			infoV.add(rs.getString("stuid"));
			infoV.add(rs.getString("grade"));
			infoV.add(rs.getString("class"));
			infoV.add(rs.getString("phonenum"));
			infoV.add(rs.getString("address"));

			ProModel.addColumn(0, infoV);
			//가운데정렬, 너비조정
			TableColumnModel tcmSchedule = ProTable.getColumnModel();
			tcmSchedule.getColumn(1).setPreferredWidth(400);
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			tcmSchedule.getColumn(0).setCellRenderer(dtcr);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	public void setBoardData() {

		//rs=dbc.DataSelect(sql+resultWhere+attributeSql);
		setNotice();
		//select postnum, title, name, writedate, views, noticecheck from list where noticecheck=0;
		String sql = "select postnum, title, name, writedate, views from board where noticecheck=0 ORDER BY postnum DESC";
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("postnum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("name"));
				v.add(rs.getString("writedate")+"");
				v.add(Integer.toString(rs.getInt("views")));
				boardModel.addRow(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	public void setNotice() {
		String sql="select postnum, title, name, writedate, views from board where noticecheck=1 ORDER BY postnum DESC";
		if(boardModel.getRowCount()!=0) {
			if (boardModel.getRowCount() > 0) {
				for (int i = boardModel.getRowCount() - 1; i > -1; i--) {
					boardModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//table board values POSTNUM TITLE NAME VIEWS COLUMN1 WRITEDATE CONTENT
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("postnum"));
				v.add("[공지] "+rs.getString("title"));
				v.add(rs.getString("name"));
				v.add(rs.getString("writedate"));
				v.add(Integer.toString(rs.getInt("views")));
				boardModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void searchTitleBoard(String title) {
		String sql = "select postnum, title, name, writedate, views from board where noticecheck=1 and title like '%"+title+"%' ORDER BY postnum DESC";
		if(boardModel.getRowCount()!=0) {
			if (boardModel.getRowCount() > 0) {
				for (int i = boardModel.getRowCount() - 1; i > -1; i--) {
					boardModel.removeRow(i);
				}
			}
		}
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("postnum"));
				v.add("[공지] "+rs.getString("title"));
				v.add(rs.getString("name"));
				v.add(rs.getString("writedate"));
				v.add(Integer.toString(rs.getInt("views")));
				boardModel.addRow(v);
				System.out.println("!!!!");
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
		sql = "select postnum, title, name, writedate, views from board where noticecheck=0 and title like '%"+title+"%' ORDER BY postnum DESC";
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				System.out.println("!!!");
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("postnum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("name"));
				v.add(rs.getString("writedate")+"");
				v.add(Integer.toString(rs.getInt("views")));
				boardModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void searchNameBoard(String name) {
		String sql = "select postnum, title, name, writedate, views from board where noticecheck=1 and name like '%"+name+"%' ORDER BY postnum DESC";
		if(boardModel.getRowCount()!=0) {
			if (boardModel.getRowCount() > 0) {
				for (int i = boardModel.getRowCount() - 1; i > -1; i--) {
					boardModel.removeRow(i);
				}
			}
		}
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("postnum"));
				v.add("[공지] "+rs.getString("title"));
				v.add(rs.getString("name"));
				v.add(rs.getString("writedate")+"");
				v.add(Integer.toString(rs.getInt("views")));
				boardModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
		sql = "select postnum, title, name, writedate, views from board where noticecheck=0 and name like '%"+name+"%' ORDER BY postnum DESC";
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("postnum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("name"));
				v.add(rs.getString("writedate")+"");
				v.add(Integer.toString(rs.getInt("views")));
				boardModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void searchAllTitleMessage(String title) {
		String sql="select title, sendname, senddate, messagenum from message where title like '%"+title+"%' and receiveid like '"+id+"' order by messagenum desc";
		System.out.println(sql);
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void searchNonCheckTitleMessage(String title) {
		String sql="select title, sendname, senddate, messagenum from message where title like '%"+title+"%' and receiveid like '"+id+"' and \"checkopen\" like 0 "
				+ "order by messagenum desc";
		System.out.println(sql);
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void searchCheckTitleMessage(String title) {
		String sql="select title, sendname, senddate, messagenum from message where title like '%"+title+"%' and receiveid like '"+id+"' and \"checkopen\" like 1 "
				+ "order by messagenum desc";
		System.out.println(sql);
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void searchNonCheckNameMessage(String name) {
		String sql="select title, sendname, senddate, messagenum from message where sendname like '%"+name+"%' and receiveid like '"+id+"' and \"checkopen\" like 0 "
				+ "order by messagenum desc";
		System.out.println(sql);
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void defaultNonOpenMessage() {//라디오버튼만 눌렀을 때
		String sql="select title, sendname, senddate, messagenum from message where receiveid like '"+id+"' and \"checkopen\" like 0 "
				+ "order by messagenum desc";
		System.out.println(sql);
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void searchCheckNameMessage(String name) {
		String sql="select title, sendname, senddate, messagenum from message where sendname like '%"+name+"%' and receiveid like '"+id+"' and \"checkopen\" like 1 "
				+ "order by messagenum desc";
		System.out.println(sql);
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void defaultOpenMessage() {//라디오버튼만 눌렀을 때
		String sql="select title, sendname, senddate, messagenum from message where receiveid like '"+id+"' and \"checkopen\" like 1 "
				+ "order by messagenum desc";
		System.out.println(sql);
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void searchAllNameMessage(String name) {
		String sql="select title, sendname, senddate, messagenum from message where sendname like '%"+name+"%' and receiveid like '"+id+"' order by messagenum desc";
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate")+"");
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void setMessageData() {
		String sql="select title, sendname, senddate, messagenum from message where receiveid like '"+id+"' order by messagenum desc";
		System.out.println(sql);
		//select 위랑 동일 from message where receiveid like "로그인한 학번"
		if(messageModel.getRowCount()!=0) {
			if (messageModel.getRowCount() > 0) {
				for (int i = messageModel.getRowCount() - 1; i > -1; i--) {
					messageModel.removeRow(i);
				}
			}
		}//게시판 내용 다 지우고
		//select postnum, title, name, writedate, views from board where noticecheck=1;
		//title content sendname receiveid check senddate messagenum
		try {
			rs = DBConnect.DataSelect(sql);
			while(rs.next()){
				Vector<String> v = new Vector<String>();	
				v.add(rs.getString("messagenum"));
				v.add(rs.getString("title"));
				v.add(rs.getString("sendname"));
				v.add(rs.getString("senddate"));
				messageModel.addRow(v);
			}
			//setNoticeColor(boardTable);
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
		for(int i=0;i<centerModel.getColumnCount(); i++)
			centerModel.getColumn(i).setCellRenderer(renderer);
	}
	public void setProScheduleData() {
		//select postnum, title, name, writedate, views, noticecheck from list where noticecheck=0;
		if(scheduleModel.getRowCount()!=0) {
			if (scheduleModel.getRowCount() > 0) {
				for (int i = scheduleModel.getRowCount() - 1; i > -1; i--) {
					scheduleModel.removeRow(i);
				}
			}
		}
		System.out.println("여들어옴");
		for(int i=0;i<11;i++) {
			Vector<String> v = new Vector<String>();
			v.add("");//밑에서 1교시 2교시 넣을거
			v.add("");//월
			v.add("");//화
			v.add("");//수
			v.add("");//목
			v.add("");//금
			scheduleModel.addRow(v);
		}
		String [] day = {"<HTML>1교시<br>(09:00~09:50)</HTML>","<HTML>2교시<br>(09:55~10:45)</HTML>","<HTML>3교시<br>(10:50~11:40)</HTML>",
				"<HTML>4교시<br>(11:45~12:35)</HTML>","<HTML>5교시<br>(12:40:13:30)</HTML>","<HTML>6교시<br>13:35~14:25)</HTML>",
				"<HTML>7교시<br>(14:30~15:20)</HTML>","<HTML>8교시<br>(15:25~16:15)</HTML>","<HTML>9교시<br>(16:20~17:10)</HTML>",
				"<HTML>10교시<br>(17:15~18:05)</HTML>","<HTML>11교시<br>(18:10~18:55)</HTML>"};
		for(int i=0;i<day.length;i++)
			scheduleModel.setValueAt(day[i], i, 0);
		String sql = "SELECT * FROM class WHERE major LIKE '"+infoMajor+"' and proid like "+infoStuid;
		rs=DBConnect.DataSelect(sql);
		System.out.println(sql);
		try {
			while(rs.next()) {
				int row, column;
				String dayString;
				String content;
				dayString = rs.getString("day");
				row = rs.getInt("time")-1;
				content = "<HTML>"+rs.getString("classtitle")+"<br>"+rs.getString("classroom")+"<br>"+rs.getString("grade")+"학년 "+rs.getString("class")+"반"+"</HTML>";
				switch(dayString) {
				case "월":
					column=1;
					break;
				case "화":
					column=2;
					break;
				case "수":
					column=3;
					break;
				case "목":
					column=4;
					break;
				default:
					column=5;
				}
				scheduleModel.setValueAt(content, row, column);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void setStuScheduleData() {
		//select postnum, title, name, writedate, views, noticecheck from list where noticecheck=0;
		if(scheduleModel.getRowCount()!=0) {
			if (scheduleModel.getRowCount() > 0) {
				for (int i = scheduleModel.getRowCount() - 1; i > -1; i--) {
					scheduleModel.removeRow(i);
				}
			}
		}
		for(int i=0;i<11;i++) {
			Vector<String> v = new Vector<String>();
			v.add("");//밑에서 1교시 2교시 넣을거
			v.add("");//월
			v.add("");//화
			v.add("");//수
			v.add("");//목
			v.add("");//금
			scheduleModel.addRow(v);
		}
		String [] day = {"<HTML>1교시<br>(09:00~09:50)</HTML>","<HTML>2교시<br>(09:55~10:45)</HTML>","<HTML>3교시<br>(10:50~11:40)</HTML>",
				"<HTML>4교시<br>(11:45~12:35)</HTML>","<HTML>5교시<br>(12:40:13:30)</HTML>","<HTML>6교시<br>13:35~14:25)</HTML>",
				"<HTML>7교시<br>(14:30~15:20)</HTML>","<HTML>8교시<br>(15:25~16:15)</HTML>","<HTML>9교시<br>(16:20~17:10)</HTML>",
				"<HTML>10교시<br>(17:15~18:05)</HTML>","<HTML>11교시<br>(18:10~18:55)</HTML>"};
		for(int i=0;i<day.length;i++)
			scheduleModel.setValueAt(day[i], i, 0);
		String sql = "SELECT * FROM class WHERE major LIKE '"+infoMajor+"' and Grade like "+infoGrade+" and Class like '"+infoClass+"'";
		rs=DBConnect.DataSelect(sql);
		System.out.println(sql);
		try {
			while(rs.next()) {
				int row, column;
				String dayString;
				String content;
				dayString = rs.getString("day");
				row = rs.getInt("time")-1;
				content = "<HTML>"+rs.getString("classtitle")+"<br>"+rs.getString("classroom")+"</HTML>";
				switch(dayString) {
				case "월":
					column=1;
					break;
				case "화":
					column=2;
					break;
				case "수":
					column=3;
					break;
				case "목":
					column=4;
					break;
				default:
					column=5;
				}
				scheduleModel.setValueAt(content, row, column);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	private void setOutClassRoomData() {
		if(lectModel.getRowCount()!=0) {
			if (lectModel.getRowCount() > 0) {
				for (int i = lectModel.getRowCount() - 1; i > -1; i--) {
					lectModel.removeRow(i);
				}
			}
		}
		if(infoJob.equals("학생")) {
			String sql="select classcode, min(name) name,min(classtitle) classtitle,min(major) major "
					+ "from class where class like '"+infoClass+"'"+" and grade like "+infoGrade+" and major like '"+infoMajor+"' group by classcode";
			System.out.println(sql);
			//select 위랑 동일 from message where receiveid like "로그인한 학번"
			//select postnum, title, name, writedate, views from board where noticecheck=1;
			//title content sendname receiveid check senddate messagenum
			try {
				rs = DBConnect.DataSelect(sql);
				while(rs.next()){
					Vector<String> v = new Vector<String>();	
					v.add(rs.getString("classtitle"));
					v.add(rs.getString("major"));
					v.add(rs.getString("name"));
					lectModel.addRow(v);
				}
				//setNoticeColor(boardTable);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBConnect.Close();
			}		
		}
		else if(infoJob.equals("교수")) {
			String sql="select classtitle, grade, class, proid from class where proid like "+infoStuid+" group by classtitle, grade, class, proid";
			System.out.println(sql);
			//select 위랑 동일 from message where receiveid like "로그인한 학번"
			//select postnum, title, name, writedate, views from board where noticecheck=1;
			//title content sendname receiveid check senddate messagenum
			try {
				rs = DBConnect.DataSelect(sql);
				while(rs.next()){
					Vector<String> v = new Vector<String>();	
					v.add(rs.getString("classtitle"));
					v.add(rs.getString("grade")+"-"+rs.getString("class"));
					lectModel.addRow(v);
				}
				//setNoticeColor(boardTable);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBConnect.Close();
			}
		}
	}
	public void setInClassRoomData(int row) {//강의실_값불러오기
		if(classRoomModel.getRowCount()!=0) {
			if (classRoomModel.getRowCount() > 0) {
				for (int i = classRoomModel.getRowCount() - 1; i > -1; i--) {
					classRoomModel.removeRow(i);
				}
			}
		}
		String sql;
		if(infoJob.equals("학생")) {
			sql = "select videonum, title from video where classcode like (SELECT classcode FROM class WHERE CLASSTITLE LIKE '"+lectModel.getValueAt(row, 0)+"' GROUP BY CLASSCODE) and "
					+ "class like '"+infoClass+"' and grade like "+infoGrade+" order by videonum desc";
		}
		else {
			grade = lectModel.getValueAt(row, 1).toString().substring(0,1);
			classN = lectModel.getValueAt(row, 1).toString().substring(2,3);
			sql ="select videonum, title from video where classcode like (select classcode from class where classtitle like '"+lectModel.getValueAt(row, 0)+"' GROUP BY classcode) and "
					+"class like '"+classN+"' and grade like "+grade+" order by videonum desc";
		}
		//select title from video where classcode like 1 and grade like 2 and class like 'B'
		System.out.println(sql);
		try { //백터로 값 검색하여 테이블에 삽입 
			rs = DBConnect.DataSelect(sql);
			while(rs.next()) {
				Vector<String > v = new Vector<>();
				v.add(rs.getString("videonum"));
				v.add(rs.getString("title"));
				classRoomModel.addRow(v);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
	public void setInClassRoomData(String title, String grade, String classN) {//강의실_값불러오기
		if(classRoomModel.getRowCount()!=0) {
			if (classRoomModel.getRowCount() > 0) {
				for (int i = classRoomModel.getRowCount() - 1; i > -1; i--) {
					classRoomModel.removeRow(i);
				}
			}
		}
		String sql;
		sql ="select videonum, title from video where classcode like (select classcode from class where classtitle like '"+title+"' GROUP BY classcode) and "
				+"class like '"+classN+"' and grade like "+grade+" order by videonum desc";
		//select title from video where classcode like 1 and grade like 2 and class like 'B'
		System.out.println(sql);
		try { //백터로 값 검색하여 테이블에 삽입 
			rs = DBConnect.DataSelect(sql);
			while(rs.next()) {
				Vector<String > v = new Vector<>();
				v.add(rs.getString("videonum"));
				v.add(rs.getString("title"));
				classRoomModel.addRow(v);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnect.Close();
		}
	}
}

