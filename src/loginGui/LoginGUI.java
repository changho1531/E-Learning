package loginGui;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Main.Main;
import adminGui.ShaEncoding;
import dbAll.DBVo;
import dbAll.FindDAO;
import dbAll.LoginDAO;
import dbAll.SignUpDAO;

@SuppressWarnings("serial")
public class LoginGUI extends JFrame implements ActionListener {
	// LoginGUI() 1.최종 컨텐트펜
	private JPanel contentPane;
	private SignUpDAO sdb = new SignUpDAO();
	private FindDAO fdb = new FindDAO();
	private LoginDAO ldb = new LoginDAO();

	// Login_Panel() 1.좌측 로그인 패널
	private JPanel panel_Login;

	// Inha_Panel() 1.우측 그림 패널
	private JPanel lg_panel_Inha;
	private JLabel lg_lbl_Inha;

	// Input_Panel() 1.로그인화면 및 버튼(회원가입, 아이디/비번 찾기)
	private JPanel lg_panel_Input;
	private Button lg_ip_btn_Login;
	private JLabel lg_ip_lbl_ID;
	private JTextField lg_ip_tf_ID;
	private JLabel lg_ip_lbl_PW;
	private JPasswordField lg_ip_tf_PW;
	private Button lg_ip_btn_Fine;
	private Button lg_ip_btn_SignUp;
	private JCheckBox lg_ip_cb_IDSave;

	// SignUpGuI() 2. 회원가입 패널
	private JPanel panel_SignUp;
	private JLabel su_lbl_SignUp;
	private JLabel su_lbl_ID;
	private JTextField su_tf_ID;
	private JButton su_btn_DuCo;
	private JLabel su_lbl_PW;
	private JPasswordField su_tf_PW;
	private JLabel su_lbl_CPW;
	private JPasswordField su_tf_CPW;
	private JLabel su_lbl_Name;
	private JTextField su_tf_Name;
	private JLabel su_lbl_StuNum;
	private JTextField su_tf_StuNum;
	private JLabel su_lbl_PN;
	private JLabel su_lbl_Add;
	private JTextField su_tf_Add;
	private JLabel su_lbl_Major;
	private JLabel su_lbl_Job;
	private JLabel su_star1;
	private JLabel su_star2;
	private JLabel su_star3;
	private JButton su_btn_GoBack;
	private JButton su_btn_SignUp;
	private JScrollPane su_jsp;
	private JTextField su_tf_PN1;
	private JLabel su_tf_minus1;
	private JTextField su_tf_PN2;
	private JLabel su_tf_minus2;
	private JTextField su_tf_PN3;
	private JLabel su_lbl_Grade;
	private JLabel su_lbl_Gruop ;
	private JLabel su_star4;
	private JLabel su_star5;
	private JPanel su_panel_StudentSelect;
	private JLabel su_star3_1;
	private JLabel su_star3_2;
	private JLabel su_star3_3;
	private JLabel su_star3_4;
	// 회원가입 중복 처리 필드(아이디 중복확인, 학번 중복확인 횟수 체크)
	private int idCheck = 0;
	private int snCheck = 0;

	// Img_Panel() 1. 이미지 패널
	private JPanel img_Panel;
	private JLabel lblImg;

	// FineIDPW 3. 아이디/비번 찾기 패널
	private JPanel panel_FineIDPW;
	private JLabel fine_lbl_Id;
	private JLabel fine_lbl_PN;
	private JButton fine_btn_GoBack;
	private JButton fine_btn_FineID;
	private JButton fine_btn_FinePW;
	private JLabel fine_lbl_Name;
	private JTextField fine_tf_Name;
	private JLabel fine_lbl_StuNum;
	private JTextField fine_tf_StuNum;
	private JTextField fine_tf_PN1;
	private JLabel fine_lbl_Major;
	private JLabel fine_lbl_Job;
	private JLabel fine_tf_minus1;
	private JTextField fine_tf_PN2;
	private JTextField fine_tf_PN3;
	private JLabel fine_tf_minus2;

	// 콤보박스 필드
	private JComboBox<String> jcb_Major;
	private JComboBox<String> jcb_Job;
	private JComboBox<String> jcb_Grade;
	private JComboBox<String> jcb_Gruop;
	private String[] major = {"컴시과", "컴정과", "건축과"};
	private String[] job = {"학생", "교수"};
	private String[] grade = {"1", "2", "3"};
	private String[] group = {"A", "B", "C"};
	private DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(major);	// Windowbuilder가 체크박스 지원을 안함.(ㅠㅠ)
	private DefaultComboBoxModel<String> comboMode2 = new DefaultComboBoxModel<String>(job);	// Windowbuilder가 체크박스 지원을 안함.(ㅠㅠ)
	private DefaultComboBoxModel<String> comboMode3 = new DefaultComboBoxModel<String>(grade);	// Windowbuilder가 체크박스 지원을 안함.(ㅠㅠ)
	private DefaultComboBoxModel<String> comboMode4 = new DefaultComboBoxModel<String>(group);	// Windowbuilder가 체크박스 지원을 안함.(ㅠㅠ)

	// 4. 비밀번호 재설정 필드
	private String idFind;
	private JPanel panel_modifyPW;
	private JLabel mp_lbl_modifyPW;
	private Container mp_lbl_PW;
	private JPasswordField mp_tf_PW;
	private JLabel mp_lbl_CPW;
	private JPasswordField mp_tf_CPW;
	private JButton mp_btn_ModifyPW;
	private JButton mp_btn_Back;
	private String nameSearch;
	private String stuNumSearch;
	private String numberSearch;
	private String majorSearch;
	private String jobSearch;
	private File f = new File("C:\\temp\\Inha_LoginSave.txt");
	private JButton su_btn_SNDuCo;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		LoginGUI frame = new LoginGUI();
	}

	// LoginGUI() 1.최종 컨텐트펜
	public LoginGUI() {
		// 기본설정
		setResizable(false);
		setTitle("인하공업전문대학 이러닝시스템 플랫폼");
		setLocation(100, 100);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);

		// 컨텐트펜 설정
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 1. LoginPanel + ImgPanel
		Login_Panel();
		Img_Panel();
		setVisible(true);
	}

	// Login_Panel() 1.로그인 패널
	// LoginPanel = Inha_Panel(우측 상단), Input_Panel(우측 하단)로 나눠짐.
	private void Login_Panel() {
		panel_Login = new JPanel();
		panel_Login.setBackground(Color.WHITE);
		panel_Login.setBounds(500, 0, 500, 600);
		panel_Login.setLayout(null);

		// 1. 우측 상단 그림 패널 Inha_Panel
		Inha_Panel(panel_Login);
		
		// 1. 우측 하단 로그인 패널Input_Panel
		Input_Panel(panel_Login);

		// 2. 회원가입 패널 ('SignUp' 버튼 누를 시 생성)
		SignUpGuI(panel_Login);

		// 3. 아이디/비번 찾기 패널 ('Find ID/PW' 버튼 누를 시 생성)
		FindIDPW(panel_Login);

		// 4. 비밀번호 재설정 패널
		ModifyPW(panel_Login);

		contentPane.add(panel_Login);
	}

	// Inha_Panel() 1.우측 그림 패널
	private void Inha_Panel(JPanel login_Panel) {
		lg_panel_Inha = new JPanel();
		lg_panel_Inha.setBackground(Color.WHITE);
		lg_panel_Inha.setBounds(0, 0, 500, 250);
		lg_panel_Inha.setLayout(null);

		lg_lbl_Inha = new JLabel();
		lg_lbl_Inha.setIcon(new ImageIcon("./images/인하공전.png"));
		lg_lbl_Inha.setBounds(150, 50, 200, 200);
		lg_panel_Inha.add(lg_lbl_Inha);

		login_Panel.add(lg_panel_Inha);
	}

	// Input_Panel() 1.로그인화면 및 버튼(회원가입, 아이디/비번 찾기)
	private void Input_Panel(JPanel login_Panel) {
		lg_panel_Input = new JPanel();
		lg_panel_Input.setBackground(Color.WHITE);
		lg_panel_Input.setBounds(0, 250, 500, 350);
		lg_panel_Input.setLayout(null);

		lg_ip_btn_Login = new Button("Login");
		lg_ip_btn_Login.setFont(new Font("휴먼엑스포", Font.BOLD, 12));
		lg_ip_btn_Login.setForeground(Color.WHITE);
		lg_ip_btn_Login.setBackground(Color.BLUE);
		lg_ip_btn_Login.setBounds(320, 200, 120, 45);
		lg_ip_btn_Login.addActionListener(this);
		lg_panel_Input.add(lg_ip_btn_Login);

		lg_ip_lbl_ID = new JLabel("User ID");
		lg_ip_lbl_ID.setBounds(60, 20, 80, 15);
		lg_panel_Input.add(lg_ip_lbl_ID);

		lg_ip_tf_ID = new JTextField();
		lg_ip_tf_ID.setColumns(10);
		lg_ip_tf_ID.setBounds(60, 40, 380, 40);

		lg_ip_lbl_PW = new JLabel("Password");
		lg_ip_lbl_PW.setBounds(60, 90, 80, 15);
		lg_panel_Input.add(lg_ip_lbl_PW);
		lg_ip_tf_PW = new JPasswordField();
		lg_ip_tf_PW.setColumns(10);
		lg_ip_tf_PW.setBounds(60, 110, 380, 40);
		lg_ip_tf_PW.addActionListener(this);
		lg_panel_Input.add(lg_ip_tf_PW);

		// 회원가입 버튼
				lg_ip_btn_SignUp = new Button("Sign Up");
				lg_ip_btn_SignUp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						login_Panel.setVisible(false);
						su_jsp.setVisible(true);
					}
				});
				lg_ip_btn_SignUp.setFont(new Font("휴먼엑스포", Font.BOLD, 12));
				lg_ip_btn_SignUp.setForeground(Color.WHITE);
				lg_ip_btn_SignUp.setBackground(Color.BLUE);
				lg_ip_btn_SignUp.setActionCommand("Sign Up");
				lg_ip_btn_SignUp.setBounds(60, 200, 120, 45);
				lg_panel_Input.add(lg_ip_btn_SignUp);
				
		// 비밀번호 찾기 버튼
		lg_ip_btn_Fine = new Button("Find ID / PW ");
		lg_ip_btn_Fine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_Panel.setVisible(false);
				panel_FineIDPW.setVisible(true);
			}
		});
		lg_ip_btn_Fine.setFont(new Font("휴먼엑스포", Font.BOLD, 12));
		lg_ip_btn_Fine.setForeground(Color.WHITE);
		lg_ip_btn_Fine.setBackground(Color.BLUE);
		lg_ip_btn_Fine.setBounds(190, 200, 120, 45);
		lg_panel_Input.add(lg_ip_btn_Fine);

		// 아이디 저장 체크박스
		// exists() : 파일이 존재하는지 확인하는 메소드
		if(f.exists()) {
			lg_ip_cb_IDSave = new JCheckBox("ID 저장하기",true);
			lg_ip_cb_IDSave.isSelected();
			Path path = Paths.get("C:\\temp\\Inha_LoginSave.txt");
			try {
				String idSave = Files.readString(path);
				lg_ip_tf_ID.setText(idSave);
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		else 
			lg_ip_cb_IDSave = new JCheckBox("ID 저장하기");
		lg_ip_cb_IDSave.setBackground(Color.WHITE);
		lg_ip_cb_IDSave.setBounds(60, 160, 107, 23);
		lg_ip_cb_IDSave.addActionListener(this);
		lg_panel_Input.add(lg_ip_tf_ID);
		lg_panel_Input.add(lg_ip_cb_IDSave);
		login_Panel.add(lg_panel_Input);
	}

	// SignUpGuI() 2. 회원가입 패널
	private void SignUpGuI(JPanel login_Panel) {
		// 기본 설정
		panel_SignUp = new JPanel();
		panel_SignUp.setBorder(null);
		panel_SignUp.setBackground(Color.WHITE);
		panel_SignUp.setLayout(null);
		
		su_lbl_SignUp = new JLabel("회원가입");
		su_lbl_SignUp.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 30));
		su_lbl_SignUp.setBounds(192, 0, 125, 70);
		panel_SignUp.add(su_lbl_SignUp);

		su_lbl_ID = new JLabel("User ID/아이디");
		su_lbl_ID.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_ID.setBounds(60, 60, 400, 15);
		panel_SignUp.add(su_lbl_ID);
		su_tf_ID = new JTextField();
		// 아이디 중복확인 후 다시 변경 시 idCheck 0으로 초기화 
		su_tf_ID.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				idCheck=0;
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				idCheck=0;
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				idCheck=0;
			}
		});
		su_tf_ID.setColumns(10);
		su_tf_ID.setBounds(60, 80, 265, 40);
		panel_SignUp.add(su_tf_ID);
		
		su_btn_DuCo = new JButton("중복확인");
		su_btn_DuCo.setForeground(Color.WHITE);
		su_btn_DuCo.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		su_btn_DuCo.setBackground(Color.BLUE);
		su_btn_DuCo.setBounds(335, 80, 91, 40);
		su_btn_DuCo.addActionListener(this);
		panel_SignUp.add(su_btn_DuCo);

		su_lbl_PW = new JLabel("Password/비밀번호");
		su_lbl_PW.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_PW.setBounds(60, 130, 400, 15);
		panel_SignUp.add(su_lbl_PW);
		su_tf_PW = new JPasswordField();
		su_tf_PW.setColumns(10);
		su_tf_PW.setBounds(60, 150, 365, 40);
		panel_SignUp.add(su_tf_PW);

		su_lbl_CPW = new JLabel("Confirm Password/비밀번호 재확인");
		su_lbl_CPW.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_CPW.setBounds(60, 200, 400, 15);
		panel_SignUp.add(su_lbl_CPW);
		su_tf_CPW = new JPasswordField();
		su_tf_CPW.setColumns(10);
		su_tf_CPW.setBounds(60, 220, 365, 40);
		panel_SignUp.add(su_tf_CPW);

		su_lbl_Name = new JLabel("Full Name/이름");
		su_lbl_Name.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_Name.setBounds(60, 270, 400, 15);
		panel_SignUp.add(su_lbl_Name);
		su_tf_Name = new JTextField();
		su_tf_Name.setColumns(10);
		su_tf_Name.setBounds(60, 290, 365, 40);
		panel_SignUp.add(su_tf_Name);

		su_lbl_StuNum= new JLabel("Student Number/학번");
		su_lbl_StuNum.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_StuNum.setBounds(60, 340, 400, 15);
		panel_SignUp.add(su_lbl_StuNum);
		su_tf_StuNum = new JTextField();
		su_tf_StuNum.setColumns(10);
		su_tf_StuNum.setBounds(60, 360, 265, 40);
		panel_SignUp.add(su_tf_StuNum);

		// 학번 중복확인 후 다시 변경 시 snCheck 0으로 초기화
		su_tf_StuNum.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				snCheck = 0;
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				snCheck = 0;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				snCheck = 0;
			}
		});

		su_btn_SNDuCo = new JButton("학번확인");
		su_btn_SNDuCo.setForeground(Color.WHITE);
		su_btn_SNDuCo.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		su_btn_SNDuCo.setBackground(Color.BLUE);
		su_btn_SNDuCo.setBounds(335, 360, 91, 40);
		su_btn_SNDuCo.addActionListener(this);
		panel_SignUp.add(su_btn_SNDuCo);

		su_lbl_PN = new JLabel("Phone Number/휴대폰 번호");
		su_lbl_PN.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_PN.setBounds(60, 410, 400, 15);
		panel_SignUp.add(su_lbl_PN);
		su_tf_PN1 = new JTextField();
		su_tf_PN1.setColumns(10);
		su_tf_PN1.setBounds(60, 430, 105, 40);
		panel_SignUp.add(su_tf_PN1);

		su_tf_minus1 = new JLabel("-");
		su_tf_minus1.setBounds(170, 430, 10, 40);
		panel_SignUp.add(su_tf_minus1);

		su_tf_PN2 = new JTextField();
		su_tf_PN2.setColumns(10);
		su_tf_PN2.setBounds(185, 430, 110, 40);
		panel_SignUp.add(su_tf_PN2);
		su_tf_minus2 = new JLabel("-");
		su_tf_minus2.setBounds(300, 430, 10, 40);
		panel_SignUp.add(su_tf_minus2);
		su_tf_PN3 = new JTextField();
		su_tf_PN3.setColumns(10);
		su_tf_PN3.setBounds(315, 430, 110, 40);
		panel_SignUp.add(su_tf_PN3);

		su_lbl_Add = new JLabel("Address/집 주소");
		su_lbl_Add.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_Add.setBounds(60, 480, 400, 15);
		panel_SignUp.add(su_lbl_Add);
		su_tf_Add = new JTextField();
		su_tf_Add.setColumns(10);
		su_tf_Add.setBounds(60, 500, 365, 40);
		panel_SignUp.add(su_tf_Add);

		su_lbl_Major = new JLabel("Major/전공");
		su_lbl_Major.setBackground(Color.WHITE);
		su_lbl_Major.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_Major.setBounds(60, 550, 400, 15);
		panel_SignUp.add(su_lbl_Major);

		jcb_Major = new JComboBox<String>(comboModel);
		jcb_Major.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		jcb_Major.setBounds(60, 570, 172, 40);
		((JLabel)jcb_Major.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		jcb_Major.setBackground(Color.WHITE);
		panel_SignUp.add(jcb_Major);

		su_lbl_Job= new JLabel("Job/직책");
		su_lbl_Job.setBackground(Color.WHITE);
		su_lbl_Job.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_Job.setBounds(252, 550, 400, 15);
		panel_SignUp.add(su_lbl_Job);

		jcb_Job = new JComboBox<String>(comboMode2);
		jcb_Job.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		jcb_Job.setBounds(252, 570, 172, 40);
		((JLabel)jcb_Job.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		jcb_Job.setBackground(Color.WHITE);
		panel_SignUp.add(jcb_Job);

		su_panel_StudentSelect = new JPanel();
		su_panel_StudentSelect.setBorder(null);
		su_panel_StudentSelect.setLayout(null);
		su_panel_StudentSelect.setBackground(Color.WHITE);
		su_panel_StudentSelect.setBounds(50, 615, 400, 70);
		su_panel_StudentSelect.setVisible(true);

		// 직책 변경 기능 : 학생 or 교수 선택 시
		jcb_Job.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jcb_Job.getSelectedItem().toString().equals("학생"))
					su_panel_StudentSelect.setVisible(true);
				else
					su_panel_StudentSelect.setVisible(false);
			}
		});

		su_lbl_Grade= new JLabel("Grade/학년");
		su_lbl_Grade.setBackground(Color.WHITE);
		su_lbl_Grade.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_Grade.setBounds(10, 10, 400, 15);
		su_panel_StudentSelect.add(su_lbl_Grade);

		jcb_Grade = new JComboBox<String>(comboMode3);
		jcb_Grade.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		jcb_Grade.setBounds(10, 30, 172, 40);
		((JLabel)jcb_Grade.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		jcb_Grade.setBackground(Color.WHITE);
		su_panel_StudentSelect.add(jcb_Grade);

		su_lbl_Gruop = new JLabel("Gruop/반");
		su_lbl_Gruop.setBackground(Color.WHITE);
		su_lbl_Gruop.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_lbl_Gruop.setBounds(202, 10, 400, 15);
		su_panel_StudentSelect.add(su_lbl_Gruop);

		jcb_Gruop = new JComboBox<String>(comboMode4);
		jcb_Gruop.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		jcb_Gruop.setBounds(202, 30, 172, 40);
		((JLabel)jcb_Gruop.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		jcb_Gruop.setBackground(Color.WHITE);
		su_panel_StudentSelect.add(jcb_Gruop);
		panel_SignUp.add(su_panel_StudentSelect);

		su_star1 = new JLabel("*");
		su_star1.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star1.setForeground(Color.RED);
		su_star1.setBounds(45, 60, 15, 15);
		panel_SignUp.add(su_star1);

		su_star2 = new JLabel("*");
		su_star2.setForeground(Color.RED);
		su_star2.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star2.setBounds(45, 130, 15, 15);
		panel_SignUp.add(su_star2);

		su_star3 = new JLabel("*");
		su_star3.setForeground(Color.RED);
		su_star3.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star3.setBounds(45, 270, 15, 15);
		panel_SignUp.add(su_star3);

		su_star4 = new JLabel("*");
		su_star4.setForeground(Color.RED);
		su_star4.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star4.setBounds(45, 340, 15, 15);
		panel_SignUp.add(su_star4);

		su_star5 = new JLabel("*");
		su_star5.setForeground(Color.RED);
		su_star5.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star5.setBounds(45, 410, 15, 15);
		panel_SignUp.add(su_star5);

		su_btn_GoBack = new JButton("뒤로가기");
		su_btn_GoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_Panel.setVisible(true);
				su_tf_ID.setText("");
				su_tf_PW.setText("");
				su_tf_CPW.setText("");
				su_tf_Name.setText("");
				su_tf_StuNum.setText("");
				su_tf_PN1.setText("");
				su_tf_PN2.setText("");
				su_tf_PN3.setText("");
				su_tf_Add.setText("");
				su_jsp.setVisible(false);
			}
		});
		su_btn_GoBack.setForeground(Color.WHITE);
		su_btn_GoBack.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_btn_GoBack.setBackground(Color.BLUE);
		su_btn_GoBack.setBounds(60, 700, 159, 40);
		panel_SignUp.add(su_btn_GoBack);


		su_btn_SignUp = new JButton("회원가입");
		su_btn_SignUp.setForeground(Color.WHITE);
		su_btn_SignUp.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		su_btn_SignUp.setBackground(Color.BLUE);
		su_btn_SignUp.setBounds(265, 700, 159, 40);
		su_btn_SignUp.addActionListener(this);

		panel_SignUp.add(su_btn_SignUp);

		su_jsp = new JScrollPane();
		su_jsp.setBounds(500,0,485,565);
		su_jsp.setVisible(false);
		Dimension size = new Dimension();		//사이즈를 지정하기 위한 객체 생성
		size.setSize(0, 760);					//객체의 사이즈를 지정
		panel_SignUp.setPreferredSize(size);	//사이즈 정보를 가지고 있는 객체를 이용해 패널의 사이즈 지정
		su_jsp.setViewportView(panel_SignUp);	//스크롤 팬 위에 패널을 올린다.

		su_star3_1 = new JLabel("*");
		su_star3_1.setForeground(Color.RED);
		su_star3_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star3_1.setBounds(45, 200, 15, 15);
		panel_SignUp.add(su_star3_1);

		su_star3_2 = new JLabel("*");
		su_star3_2.setForeground(Color.RED);
		su_star3_2.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star3_2.setBounds(45, 340, 15, 15);
		panel_SignUp.add(su_star3_2);

		su_star3_3 = new JLabel("*");
		su_star3_3.setForeground(Color.RED);
		su_star3_3.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star3_3.setBounds(45, 550, 15, 15);
		panel_SignUp.add(su_star3_3);

		su_star3_4 = new JLabel("*");
		su_star3_4.setForeground(Color.RED);
		su_star3_4.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		su_star3_4.setBounds(242, 550, 15, 15);
		panel_SignUp.add(su_star3_4);
		contentPane.add(su_jsp);

	}

	// Img_Panel() 1. 이미지 패널
	private void Img_Panel() {
		img_Panel = new JPanel();
		img_Panel.setBackground(Color.WHITE);
		img_Panel.setBounds(0, 0, 500, 600);
		img_Panel.setLayout(null);
		lblImg = new JLabel("");
		lblImg.setIcon(new ImageIcon("./images/Isometric online education concept.png"));
		lblImg.setBounds(0, 0, 500, 600);

		img_Panel.add(lblImg);
		contentPane.add(img_Panel);
	}

	// FineIDPW 3. 아이디/비번 찾기 패널
	private void FindIDPW(JPanel login_Panel) {
		panel_FineIDPW = new JPanel();
		panel_FineIDPW.setBackground(Color.WHITE);
		panel_FineIDPW.setBounds(500, 0, 500, 600);
		panel_FineIDPW.setLayout(null);

		fine_lbl_Id = new JLabel("아이디 찾기 / 비밀번호 변경");
		fine_lbl_Id.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 30));
		fine_lbl_Id.setBounds(65, 0, 370, 70);
		panel_FineIDPW.add(fine_lbl_Id);

		fine_btn_GoBack = new JButton("뒤로가기");
		fine_btn_GoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_Panel.setVisible(true);
				panel_FineIDPW.setVisible(false);
				fine_tf_Name.setText("");
				fine_tf_StuNum.setText("");
				fine_tf_PN1.setText("");
				fine_tf_PN2.setText("");
				fine_tf_PN3.setText("");
			}
		});
		fine_btn_GoBack.setForeground(Color.WHITE);
		fine_btn_GoBack.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		fine_btn_GoBack.setBackground(Color.BLUE);
		fine_btn_GoBack.setBounds(67, 460, 115, 33);
		panel_FineIDPW.add(fine_btn_GoBack);

		fine_btn_FineID = new JButton("아이디 찾기");
		fine_btn_FineID.setForeground(Color.WHITE);
		fine_btn_FineID.setBackground(Color.BLUE);
		fine_btn_FineID.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		fine_btn_FineID.setBounds(192, 460, 115, 33);
		fine_btn_FineID.addActionListener(this);
		panel_FineIDPW.add(fine_btn_FineID);

		fine_btn_FinePW = new JButton("비밀번호 변경");
		fine_btn_FinePW.setForeground(Color.WHITE);
		fine_btn_FinePW.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		fine_btn_FinePW.setBackground(Color.BLUE);
		fine_btn_FinePW.setBounds(317, 460, 115, 33);
		fine_btn_FinePW.addActionListener(this);
		panel_FineIDPW.add(fine_btn_FinePW);

		fine_lbl_Name = new JLabel("Full Name/이름");
		fine_lbl_Name.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		fine_lbl_Name.setBounds(67,80, 365, 15);
		panel_FineIDPW.add(fine_lbl_Name);
		fine_tf_Name = new JTextField();
		fine_tf_Name.setColumns(10);
		fine_tf_Name.setBounds(67, 100, 365, 40);
		panel_FineIDPW.add(fine_tf_Name);

		fine_lbl_StuNum = new JLabel("Student Number/학번");
		fine_lbl_StuNum.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		fine_lbl_StuNum.setBounds(67, 150, 365, 14);
		panel_FineIDPW.add(fine_lbl_StuNum);
		fine_tf_StuNum = new JTextField();
		fine_tf_StuNum.setColumns(10);
		fine_tf_StuNum.setBounds(67, 170, 365, 40);
		panel_FineIDPW.add(fine_tf_StuNum);

		fine_lbl_PN = new JLabel("Phone Number/휴대폰 번호");
		fine_lbl_PN.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		fine_lbl_PN.setBounds(67, 220, 365, 14);
		panel_FineIDPW.add(fine_lbl_PN);
		fine_tf_PN1 = new JTextField();
		fine_tf_PN1.setColumns(10);
		fine_tf_PN1.setBounds(67, 240, 105, 40);
		panel_FineIDPW.add(fine_tf_PN1);
		fine_tf_minus1 = new JLabel("-");
		fine_tf_minus1.setBounds(178, 240, 10, 40);
		panel_FineIDPW.add(fine_tf_minus1);
		fine_tf_PN2 = new JTextField();
		fine_tf_PN2.setColumns(10);
		fine_tf_PN2.setBounds(190, 240, 110, 40);
		panel_FineIDPW.add(fine_tf_PN2);
		fine_tf_minus2 = new JLabel("-");
		fine_tf_minus2.setBounds(308, 240, 10, 40);
		panel_FineIDPW.add(fine_tf_minus2);
		fine_tf_PN3 = new JTextField();
		fine_tf_PN3.setColumns(10);
		fine_tf_PN3.setBounds(320, 240, 110, 40);
		panel_FineIDPW.add(fine_tf_PN3);

		fine_lbl_Major = new JLabel("Major/전공");
		fine_lbl_Major.setBackground(Color.WHITE);
		fine_lbl_Major.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		fine_lbl_Major.setBounds(67, 290, 365, 14);
		panel_FineIDPW.add(fine_lbl_Major);
		jcb_Major = new JComboBox<String>(comboModel);
		jcb_Major.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		jcb_Major.setBounds(67, 310, 365, 40);
		((JLabel)jcb_Major.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		jcb_Major.setBackground(Color.WHITE);
		panel_FineIDPW.add(jcb_Major);

		fine_lbl_Job = new JLabel("Job/직책");
		fine_lbl_Job.setBackground(Color.WHITE);
		fine_lbl_Job.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		fine_lbl_Job.setBounds(67, 360, 365, 14);
		panel_FineIDPW.add(fine_lbl_Job);
		jcb_Job = new JComboBox<String>(comboMode2);
		jcb_Job.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		jcb_Job.setBounds(67, 380, 365, 40);
		((JLabel)jcb_Job.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		jcb_Job.setBackground(Color.WHITE);
		panel_FineIDPW.add(jcb_Job);

		panel_FineIDPW.setVisible(false);
		contentPane.add(panel_FineIDPW);
	}

	// 4. 비밀번호 재설정 패널
	private void ModifyPW(JPanel login_Panel) {
		panel_modifyPW = new JPanel();
		panel_modifyPW.setBackground(Color.WHITE);
		panel_modifyPW.setBounds(500, 0, 500, 600);
		panel_modifyPW.setLayout(null);

		mp_lbl_modifyPW = new JLabel("비밀번호 재설정");
		mp_lbl_modifyPW.setForeground(Color.BLACK);
		mp_lbl_modifyPW.setBackground(Color.WHITE);
		mp_lbl_modifyPW.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 30));
		mp_lbl_modifyPW.setBounds(140, 0, 250, 70);
		panel_modifyPW.add(mp_lbl_modifyPW);

		mp_lbl_PW = new JLabel("Password");
		mp_lbl_PW.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		mp_lbl_PW.setBounds(60, 100, 80, 15);
		panel_modifyPW.add(mp_lbl_PW);
		mp_tf_PW = new JPasswordField();
		mp_tf_PW.setColumns(10);
		mp_tf_PW.setBounds(60, 120, 365, 40);
		panel_modifyPW.add(mp_tf_PW);

		mp_lbl_CPW = new JLabel("Confirm Password");
		mp_lbl_CPW.setFont(new Font("휴먼엑스포", Font.PLAIN, 12));
		mp_lbl_CPW.setBounds(60, 210, 140, 15);
		panel_modifyPW.add(mp_lbl_CPW);
		mp_tf_CPW = new JPasswordField();
		mp_tf_CPW.setColumns(10);
		mp_tf_CPW.setBounds(60, 230, 365, 40);
		panel_modifyPW.add(mp_tf_CPW);

		mp_btn_ModifyPW = new JButton("비밀번호 변경");
		mp_btn_ModifyPW.setForeground(Color.WHITE);
		mp_btn_ModifyPW.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mp_btn_ModifyPW.setBackground(Color.BLUE);
		mp_btn_ModifyPW.setBounds(255, 437, 170, 40);
		mp_btn_ModifyPW.addActionListener(this);
		panel_modifyPW.add(mp_btn_ModifyPW);

		mp_btn_Back = new JButton("뒤로가기");
		mp_btn_Back.setForeground(Color.WHITE);
		mp_btn_Back.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 12));
		mp_btn_Back.setBackground(Color.BLUE);
		mp_btn_Back.setBounds(60, 437, 170, 40);
		mp_btn_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_Panel.setVisible(true);
				panel_modifyPW.setVisible(false);
			}
		});
		panel_modifyPW.add(mp_btn_Back);

		panel_modifyPW.setVisible(false);
		contentPane.add(panel_modifyPW);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();

		// 1. 로그인 확인하는 버튼 이벤트 처리
		if(obj == lg_ip_btn_Login || obj == lg_ip_tf_PW) {
			getLoginData();
		}
		
		// 2. 회원가입 패널 이벤트 처리
		else if(obj == su_btn_SignUp) {
			String id = su_tf_ID.getText();
			String password = su_tf_PW.getText();
			String pwdCheck = su_tf_CPW.getText();
			
			// 학번 or 전화번호 숫자입력 확인하는 필드
			String stunumCheck = su_tf_StuNum.getText();
			String pnNumCheck1 = su_tf_PN1.getText();
			String pnNumCheck2 = su_tf_PN2.getText();
			String pnNumCheck3 = su_tf_PN3.getText();
			
			// 학번이 숫자인지 확인
			boolean output = NumCheck(stunumCheck);
			// 전화번호가 숫자인지 확인
			boolean outputPN1 = NumCheck(pnNumCheck1);
			boolean outputPN2 = NumCheck(pnNumCheck2);
			boolean outputPN3 = NumCheck(pnNumCheck3);

			if(id.equals(""))
				JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다");
			else if(id.length() < 6 || id.length() > 15)
				JOptionPane.showMessageDialog(this, "아이디는 6자리 이상, 15자리 이하만 가능 합니다.");
			else if(checkIDMethod(id)==1)
				JOptionPane.showMessageDialog(this, "아이디는 특수문자 포함이 불가능합니다");
			else if(idCheck <= 0)
				JOptionPane.showMessageDialog(this, "아이디 중복확인을 부탁드립니다.");
			else if(password.equals(""))
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하셔야 합니다");
			else if(password.length()<6 || password.length()>20)  
				JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상, 20자리 이하만 가능합니다.");
			else if(pwdCheck.equals(""))
				JOptionPane.showMessageDialog(this, "비교하는 비밀번호를 입력해 주시기 바랍니다");
			else if(!password.equals(pwdCheck))
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
			else if(checkPWDMethod(password)==1)
				JOptionPane.showMessageDialog(this, "비밀번호 특수문자는 !@#만 포함 가능 합니다");
			else if(su_tf_Name.getText().equals(""))
				JOptionPane.showMessageDialog(this, "이름(한글)을 입력하셔야 합니다");
			else if(snCheck <= 0)
				JOptionPane.showMessageDialog(this, "학번을 확인하셔야 합니다");
			else if(su_tf_PN1.getText().equals("") || su_tf_PN2.getText().equals("") || su_tf_PN3.getText().equals(""))
				JOptionPane.showMessageDialog(this, "전화번호를 재대로 입력하셔야 합니다");	
			else if(output == false)
					JOptionPane.showMessageDialog(this, "학번은 숫자만 입력이 가능합니다.");	
			else if(outputPN1 == false || outputPN2 == false || outputPN3 == false)
					JOptionPane.showMessageDialog(this, "전화번호는 숫자만 입력이 가능합니다.");

			// 회원가입 정보 입력
			else {
				DBVo vo = new DBVo(su_tf_ID.getText(), su_tf_PW.getText(), su_tf_Name.getText(),
						Integer.parseInt(su_tf_StuNum.getText().toUpperCase()),
						su_tf_PN1.getText() + "-" + su_tf_PN2.getText() + "-" + su_tf_PN3.getText(),
						su_tf_Add.getText(), (String) jcb_Major.getSelectedItem(), (String) jcb_Job.getSelectedItem(),
						(String) jcb_Grade.getSelectedItem(), (String) jcb_Gruop.getSelectedItem());
				int result = sdb.SignUpInsert(vo);

				// 회원등록 성공함
				if (result > 0)
					JOptionPane.showMessageDialog(this, "회원가입에 성공하였습니다\n원활한 이용을 위하여\n로그인 해주시기 바랍니다");
				// 회원등록 실패함
				else
					JOptionPane.showMessageDialog(this, "회원가입에 실패하였습니다\n 관리자에게 문의해 주시기 바랍니다");

				// 회원가입 창 초기화 + 메인 로그인 화면으로 변경
				su_tf_ID.setText("");
				su_tf_PW.setText("");
				su_tf_CPW.setText("");
				su_tf_Name.setText("");
				su_tf_StuNum.setText("");
				su_tf_PN1.setText("");
				su_tf_PN2.setText("");
				su_tf_PN3.setText("");
				su_tf_Add.setText("");
				su_jsp.setVisible(false);
				panel_Login.setVisible(true);
				;
			}
		}
		
		// 2. 회원가입 패널 안에서 버튼 기능구현
		// 아이디 중복확인 버튼
		else if(obj == su_btn_DuCo) {
			String idSearch = su_tf_ID.getText();
			
			// 아이디가 비어있는 경우
			if(idSearch.equals("")) 
				JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다");
			
				// 아이디 문자 수 제한
			else if(idSearch.length() < 6 || idSearch.length() > 15)
				JOptionPane.showMessageDialog(this, "아이디는 6자리 이상, 15자리 이하만 가능 합니다.");
			
				// 아이디가 특수문자가 포함되어있는 경우
			else if(checkIDMethod(idSearch)==1)
				JOptionPane.showMessageDialog(this, "아이디는 특수문자 포함이 불가능합니다");
			
			// 아이디의 조건을 만족했을 경우 (중복확인 기능 수행)
			else {
				List<DBVo> result = sdb.getidCheck(idSearch);
				if(result.size()==0&&idSearch.length()!=0) {
					JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다");
					idCheck +=1;
				} else {
					JOptionPane.showMessageDialog(this, "등록되어 있는 아이디 입니다");
				}

			}
		}

		// 2. 회원가입 패널 안에서 버튼 기능구현
		// 학번중복 확인 버튼
		else if(obj == su_btn_SNDuCo) {
			String snSearch = su_tf_StuNum.getText();
			// 학번이 비어있는 경우
			if(snSearch.equals("")) {
				JOptionPane.showMessageDialog(this, "학번을 입력하셔야 합니다");
				//아이디가 특수문자가 포함되어있는 경우
			} else if(checkIDMethod(snSearch)==1){
				JOptionPane.showMessageDialog(this, "학번은 특수문자 포함이 불가능합니다");
			} else {
				List<DBVo> result = sdb.getidCheck(Integer.parseInt(snSearch));
				if(result.size()==0) {
					JOptionPane.showMessageDialog(this, "사용 가능한 학번 입니다");
					snCheck +=1;
				} else {
					JOptionPane.showMessageDialog(this, "등록되어 있는 학번 입니다");
				}
			}
		}

		// 3. 아이디 찾기/비밀번호 재설정 이벤트 생성
		else if(obj == fine_btn_FineID || obj == fine_btn_FinePW) {
			nameSearch = fine_tf_Name.getText();
			stuNumSearch = fine_tf_StuNum.getText();
			numberSearch = fine_tf_PN1.getText()+"-"+fine_tf_PN2.getText()+"-"+fine_tf_PN3.getText();
			majorSearch = jcb_Major.getSelectedItem().toString();
			jobSearch = jcb_Job.getSelectedItem().toString();
			String pnNumCheck1 = fine_tf_PN1.getText();
			String pnNumCheck2 = fine_tf_PN2.getText();
			String pnNumCheck3 = fine_tf_PN3.getText();
			
			// 학번이 숫자인지 확인
			boolean output = NumCheck(stuNumSearch);
			// 전화번호가 숫자인지 확인
			boolean outputPN1 = NumCheck(pnNumCheck1);
			boolean outputPN2 = NumCheck(pnNumCheck2);
			boolean outputPN3 = NumCheck(pnNumCheck3);

			if (fine_tf_Name.getText().equals(""))
				JOptionPane.showMessageDialog(this, "이름을 입력하셔야 합니다");
			else if (fine_tf_StuNum.getText().equals(""))
				JOptionPane.showMessageDialog(this, "학번을 입력하셔야 합니다");
			else if (fine_tf_PN1.getText().equals("") || fine_tf_PN2.getText().equals("") || fine_tf_PN3.getText().equals(""))
				JOptionPane.showMessageDialog(this, "연락처를 재대로 입력하셔야 합니다");
			else if (output == false) {
				JOptionPane.showMessageDialog(this, "학번은 숫자만 입력이 가능합니다.");
			} else if (outputPN1 == false || outputPN2 == false || outputPN3 == false) {
				JOptionPane.showMessageDialog(this, "전화번호는 숫자만 입력이 가능합니다.");
			} else {
				// 기능 수행을 위한 회원정보 입력 (회원이 맞을 시 true를 반환)
				boolean result = fdb.getFindCheck(nameSearch, Integer.parseInt(stuNumSearch),
						pnNumCheck1 + "-" + pnNumCheck2 + "-" + pnNumCheck3, majorSearch, jobSearch);
				
				// 회원이 맞을 경우
				if (result) {
					// 아이디 찾기 버튼을 누를 시
					if (obj == fine_btn_FineID) {
						String find = fdb.getidpwFind(nameSearch, Integer.parseInt(stuNumSearch),
								pnNumCheck1 + "-" + pnNumCheck2 + "-" + pnNumCheck3, majorSearch, jobSearch, 0);
						JOptionPane.showMessageDialog(this, "회원님의 아이디는 " + find + "입니다.");
						
						// 비밀번호 재설정 버튼을 누를 시
					} else if (obj == fine_btn_FinePW) {
						idFind = fdb.getidpwFind(nameSearch, Integer.parseInt(stuNumSearch),
								pnNumCheck1 + "-" + pnNumCheck2 + "-" + pnNumCheck3, majorSearch, jobSearch, 0);
						JOptionPane.showMessageDialog(this, "정보가 확인되었습니다. 비밀번호를 재설정 해주십시오.");
						fine_tf_Name.setText("");
						fine_tf_StuNum.setText("");
						fine_tf_PN1.setText("");
						fine_tf_PN2.setText("");
						fine_tf_PN3.setText("");
						panel_FineIDPW.setVisible(false);
						panel_modifyPW.setVisible(true);
					}
				} 
				// 회원이 아닌 경우
				else {
					JOptionPane.showMessageDialog(this, "등록되지 않은 정보입니다.");
				}
			}
		}
		
		// 4. 비밀번호 재설정 이벤트 생성
		else if(obj == mp_btn_ModifyPW) {
			String password = mp_tf_PW.getText();
			String pwdCheck = mp_tf_CPW.getText();
			String encryptionPassword = ShaEncoding.getSHA256(password);

			if(password.equals(""))
				JOptionPane.showMessageDialog(this, "비밀번호를 입력하셔야 합니다");
			else if(password.length()<6 || password.length()>20)  
				JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상, 20자리 이하만 가능합니다.");
			else if(pwdCheck.equals(""))
				JOptionPane.showMessageDialog(this, "비교할 비밀번호를 입력해 주시기 바랍니다");
			else if(!password.equals(pwdCheck))
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
			else if(checkPWDMethod(password) == 1)
				JOptionPane.showMessageDialog(this, "비밀번호 특수문자는 !@#만 포함 가능 합니다");
			
			// 비밀번호의 조건을 충족했을 경우
			else {
				dbAll.ModifyPW mp = new dbAll.ModifyPW(idFind, encryptionPassword);
				// DB에 저장되어있는 비밀번호를 불러와서 비교하기
				String pwFind = fdb.getidpwFind(nameSearch, Integer.parseInt(stuNumSearch), numberSearch, majorSearch, jobSearch, 1);
				if(encryptionPassword.equals(pwFind))
					JOptionPane.showMessageDialog(this, "비밀번호 재설정이 성공하였습니다");
				else 
					JOptionPane.showMessageDialog(this, "비밀번호 재설정이 실패하였습니다\n 관리자에게 문의해 주시기 바랍니다");
				
				mp_tf_PW.setText("");
				mp_tf_CPW.setText("");
				panel_modifyPW.setVisible(false);
				panel_Login.setVisible(true);;
			}
		}
	}

	// 숫자인지 확인하는 메소드 (회원가입 시 학번과 전화번호 기능 수행)
	private boolean NumCheck(String stunumCheck) {
		char tmp; 							// 숫자인지 확인하기 위해 임시로 저장하는 필드
		boolean output = true; 				// 숫자인지 확인하기 위한 필드
		
		// 입력받은 배열의 길이만큼 반복문 진행
		for(int i = 0; i < stunumCheck.length(); i++) {
			tmp = stunumCheck.charAt(i); 	// 한글자씩 검사하기 위해서 char형 변수로 임시 저장
			
			// 숫자가 아닐 경우
			if(Character.isDigit(tmp)==false) 
				output = false;
		}
		return output;
	}

	// 1. 로그인 패널 메소드
	@SuppressWarnings("deprecation")
	public void getLoginData() {
		String user_id = lg_ip_tf_ID.getText();
		String user_pwd = ShaEncoding.getSHA256(lg_ip_tf_PW.getText());
		// 로그인 확인하는 필드 (회원이 맞으면 1을 저장)
		int loginCheck = 0;
		
		// 회원이 아닌 경우(잘못 입력한 경위 찾기)
		if (user_id.equals(""))
			JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다");
		else if (user_pwd.equals(""))
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하셔야 합니다.");
		else {
			loginCheck = ldb.getLogin(user_id, user_pwd);
			if (loginCheck == 0)
				JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 틀렸습니다.");

			// 로그인이 성공한 경우
			else if (loginCheck == 1) {
				JOptionPane.showMessageDialog(this, "로그인에 성공하셨습니다.");

				// 아이디 저장기능 구현
				if (lg_ip_cb_IDSave.isSelected()) {
					String loginSave = lg_ip_tf_ID.getText();
					FileWriter fout = null;
					try {
						fout = new FileWriter("C:\\temp\\Inha_LoginSave.txt");
						fout.write(loginSave); // 키보드로부터 받은 문자를 파일에 저장
						fout.close();
					} catch (IOException e1) {
						System.out.println("아이디 저장 오류");
					}
				}
				// 아이디 저장 버튼이 해제된 상태에서 파일이 만약 존재한다면 파일을 삭제시킨다.
				else {
					if (f.exists())
						f.delete(); // 파일을 삭제합니다.
				}

				dispose();
				new Main("메인", user_id);
			}
		}
	}

	// 2. 회원가입 시 아이디 특수문자 확인 메소드
	public int checkIDMethod(String id) {
		int check = 0;
		char alpha;
		int code;
		for(int i=0; i<id.length(); i++) {
			alpha = id.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
			}
		}
		return check;
	}
	// 2. 회원가입 시 비밀번호 특수문자 확인 메소드 (비밀번호 특수문자의 경우 : !@#만 포함 가능)
	public int checkPWDMethod(String pwd) {
		int check= 0;
		char alpha;
		int code;
		for(int i=0; i<pwd.length(); i++) {
			alpha = pwd.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=32 || code>=36 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
			}
		}
		return check;
	}
}