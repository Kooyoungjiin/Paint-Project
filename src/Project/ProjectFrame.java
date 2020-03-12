package Project;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *  ProjectFrame 전체 그림판의 프레임을 나타내는 클래스
 */

public class ProjectFrame extends JFrame implements ActionListener {
	private JMenuItem New, Open, SaveAs, Exit,  Line, Rectangle, Eclipse, Eraser, Pen;//
 	private JMenu File, Home,Sample;
 	private DrawPanel dp;
 	private ColorPanel cp;
 	private ButtonPanel bp;
 	 /**
     *  현재 설정된 컬러 
     */
 	public static Color color = Color.black; 
 	 /**
     *  기본설정된 파일이름 
     */
 	public static String filename;
    
    /**
     *  ProjectFrame 생성자 
     */
 	   
	public ProjectFrame(String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	//패널을 프레임에 삽입하고 레이아웃설정	
		
		filename="그림판.jpg"; 
		dp = new DrawPanel(filename);
		cp = new ColorPanel();
		bp = new ButtonPanel(dp);
		createMenu(); //메뉴추가
		
		add(dp, BorderLayout.CENTER);
		add(cp, BorderLayout.SOUTH);
		add(bp, BorderLayout.WEST);
		setTitle(filename+" - "+"구영진"); //초기파일이름 설정
		setSize(1000,800); //사이즈 설정
		setLocation(200, 60); //기본 위치설정
		setResizable(true);// 사이즈변경
		setVisible(true);
		
	}
	
	 /**
     *  프레임의 메뉴를 설정하는 메소드
     */
	public void createMenu(){
		
		JMenuBar menuBar= new JMenuBar();
		//모든메뉴의 단축키, 이미지, 이벤트 설정
		File = new JMenu("File(F)");
		File.setMnemonic('f');
		New = new JMenuItem("새로 만들기(N)", new ImageIcon("image/New.jpg"));
		New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
   	    New.addActionListener(this);		
		Open = new JMenuItem("열기(O)", new ImageIcon("image/Open.jpg"));
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		Open.addActionListener(this);
	
	    SaveAs = new JMenuItem("저장(S)", new ImageIcon("image/Save.jpg"));
	    SaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		SaveAs.addActionListener(this);
		Exit = new JMenuItem("종료", new ImageIcon("image/Exit.jpg"));
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
	    Exit.addActionListener(this);
					
	    File.add(New);
	    File.add(Open);
	    File.add(SaveAs);
		File.addSeparator();
		File.add(Exit);  //파일메뉴 추가
		
		
		
		
		
		
				
		Home = new JMenu("Home(h)");
	    Home.setMnemonic('h');
				
		Pen = new JMenuItem("연필(P)", new ImageIcon("image/Pencil2.jpg"));
		Pen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		Pen.addActionListener(this);		
		Home.add(Pen);
		
		
		Line = new JMenuItem("선그리기(L)", new ImageIcon("image/Line2.jpg"));
		Line.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		Line.addActionListener(this);		
		Home.add(Line);
		
		
		Rectangle = new JMenuItem("사각형(R)", new ImageIcon("image/Rect2.jpg"));
		Rectangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		Rectangle.addActionListener(this);	
		Home.add(Rectangle);
		
		
		Eclipse = new JMenuItem("원그리기(C)", new ImageIcon("image/Oval2.jpg"));
		Eclipse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		Eclipse.addActionListener(this);	
		Home.add(Eclipse);
		
		Eraser = new JMenuItem("지우개(A)", new ImageIcon("image/Eraser2.jpg"));
		Eraser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		Eraser.addActionListener(this);		
		Home.add(Eraser);  //Shape메뉴 추가
		
		Sample = new JMenu("Sample(s)");
	    Sample.setMnemonic('s');
		
		
					
		menuBar.add(File);
		menuBar.add(Home);
		menuBar.add(Sample);
		setJMenuBar(menuBar);
		}
	
	
	/**
	 *  프레임의 메뉴가 선택되었을때 발생하는 이벤트가 나타나는 메소드
	 */
	public void actionPerformed(ActionEvent e) {
		String col = (String)e.getActionCommand(); //발생한 이벤트 메뉴 반환
		if (col.equals("새로 만들기(N)")) {
			JLabel msg = new JLabel();
			msg.setText("현재 내용을 저장하겠습니까?");
			if (JOptionPane.showConfirmDialog(null, msg, "확인",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) {
				    filename=dp.creation();      // 새로만들고 파일이름 변경
				    setTitle(filename+" - "+"아무개");
			}
			else {
				
				dp.reNameSave(); //저장하고 새로만듬
				dp.creation();
			}
		}

		else if(col.equals("열기(O)")){    //파일불러오고 파일이름 변경
			JLabel msg = new JLabel();       
			msg.setText("현재 내용을 저장하겠습니까?");
			if (JOptionPane.showConfirmDialog(null, msg, "확인",	JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				dp.reNameSave(); //저장
				filename=dp.open();
				setTitle(filename+" - "+"아무개"); //프레임타이틀 변경
				}
			else{
				filename=dp.open();
				setTitle(filename+" - "+"아무개");
				}
			}
				
		
		else if(col.equals("저장(S)")){  // 저장
			dp.reNameSave();
			setTitle(filename);
		}
		else if(col.equals("종료")){  //종료
			JLabel msg = new JLabel();
			msg.setText("종료 하시겠습니까?");

			if (JOptionPane.showConfirmDialog(null, msg, "확인",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION)
				return;

			System.exit(0); //확인시 종료
		} //메뉴선택시 해당기능 설정수행  select 1이면 선택모드. setDrawMode에 설정된 값에따라 설정후 기능 수행
		
		else if(col.equals("선그리기(L)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.LINE);
		}
		else if(col.equals("사각형(R)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.RECT);
		}
		
		else if(col.equals("원그리기(C)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.ECLIPSE);
		}
		else if(col.equals("연필(P)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.PEN);
		}
		else if(col.equals("지우개(A)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.ERASER);
		}
		
		
	}
	
	/**
	 *  프레임의 메인 메소드
	 */

	public static void main(String args[]) {
		new ProjectFrame("그림판");

	}
}
	
			
			
	
