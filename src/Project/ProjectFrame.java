package Project;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *  ProjectFrame ��ü �׸����� �������� ��Ÿ���� Ŭ����
 */

public class ProjectFrame extends JFrame implements ActionListener {
	private JMenuItem New, Open, SaveAs, Exit,  Line, Rectangle, Eclipse, Eraser, Pen;//
 	private JMenu File, Home,Sample;
 	private DrawPanel dp;
 	private ColorPanel cp;
 	private ButtonPanel bp;
 	 /**
     *  ���� ������ �÷� 
     */
 	public static Color color = Color.black; 
 	 /**
     *  �⺻������ �����̸� 
     */
 	public static String filename;
    
    /**
     *  ProjectFrame ������ 
     */
 	   
	public ProjectFrame(String title) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	//�г��� �����ӿ� �����ϰ� ���̾ƿ�����	
		
		filename="�׸���.jpg"; 
		dp = new DrawPanel(filename);
		cp = new ColorPanel();
		bp = new ButtonPanel(dp);
		createMenu(); //�޴��߰�
		
		add(dp, BorderLayout.CENTER);
		add(cp, BorderLayout.SOUTH);
		add(bp, BorderLayout.WEST);
		setTitle(filename+" - "+"������"); //�ʱ������̸� ����
		setSize(1000,800); //������ ����
		setLocation(200, 60); //�⺻ ��ġ����
		setResizable(true);// �������
		setVisible(true);
		
	}
	
	 /**
     *  �������� �޴��� �����ϴ� �޼ҵ�
     */
	public void createMenu(){
		
		JMenuBar menuBar= new JMenuBar();
		//���޴��� ����Ű, �̹���, �̺�Ʈ ����
		File = new JMenu("File(F)");
		File.setMnemonic('f');
		New = new JMenuItem("���� �����(N)", new ImageIcon("image/New.jpg"));
		New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
   	    New.addActionListener(this);		
		Open = new JMenuItem("����(O)", new ImageIcon("image/Open.jpg"));
		Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		Open.addActionListener(this);
	
	    SaveAs = new JMenuItem("����(S)", new ImageIcon("image/Save.jpg"));
	    SaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		SaveAs.addActionListener(this);
		Exit = new JMenuItem("����", new ImageIcon("image/Exit.jpg"));
		Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
	    Exit.addActionListener(this);
					
	    File.add(New);
	    File.add(Open);
	    File.add(SaveAs);
		File.addSeparator();
		File.add(Exit);  //���ϸ޴� �߰�
		
		
		
		
		
		
				
		Home = new JMenu("Home(h)");
	    Home.setMnemonic('h');
				
		Pen = new JMenuItem("����(P)", new ImageIcon("image/Pencil2.jpg"));
		Pen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		Pen.addActionListener(this);		
		Home.add(Pen);
		
		
		Line = new JMenuItem("���׸���(L)", new ImageIcon("image/Line2.jpg"));
		Line.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		Line.addActionListener(this);		
		Home.add(Line);
		
		
		Rectangle = new JMenuItem("�簢��(R)", new ImageIcon("image/Rect2.jpg"));
		Rectangle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
		Rectangle.addActionListener(this);	
		Home.add(Rectangle);
		
		
		Eclipse = new JMenuItem("���׸���(C)", new ImageIcon("image/Oval2.jpg"));
		Eclipse.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		Eclipse.addActionListener(this);	
		Home.add(Eclipse);
		
		Eraser = new JMenuItem("���찳(A)", new ImageIcon("image/Eraser2.jpg"));
		Eraser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		Eraser.addActionListener(this);		
		Home.add(Eraser);  //Shape�޴� �߰�
		
		Sample = new JMenu("Sample(s)");
	    Sample.setMnemonic('s');
		
		
					
		menuBar.add(File);
		menuBar.add(Home);
		menuBar.add(Sample);
		setJMenuBar(menuBar);
		}
	
	
	/**
	 *  �������� �޴��� ���õǾ����� �߻��ϴ� �̺�Ʈ�� ��Ÿ���� �޼ҵ�
	 */
	public void actionPerformed(ActionEvent e) {
		String col = (String)e.getActionCommand(); //�߻��� �̺�Ʈ �޴� ��ȯ
		if (col.equals("���� �����(N)")) {
			JLabel msg = new JLabel();
			msg.setText("���� ������ �����ϰڽ��ϱ�?");
			if (JOptionPane.showConfirmDialog(null, msg, "Ȯ��",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION) {
				    filename=dp.creation();      // ���θ���� �����̸� ����
				    setTitle(filename+" - "+"�ƹ���");
			}
			else {
				
				dp.reNameSave(); //�����ϰ� ���θ���
				dp.creation();
			}
		}

		else if(col.equals("����(O)")){    //���Ϻҷ����� �����̸� ����
			JLabel msg = new JLabel();       
			msg.setText("���� ������ �����ϰڽ��ϱ�?");
			if (JOptionPane.showConfirmDialog(null, msg, "Ȯ��",	JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
				dp.reNameSave(); //����
				filename=dp.open();
				setTitle(filename+" - "+"�ƹ���"); //������Ÿ��Ʋ ����
				}
			else{
				filename=dp.open();
				setTitle(filename+" - "+"�ƹ���");
				}
			}
				
		
		else if(col.equals("����(S)")){  // ����
			dp.reNameSave();
			setTitle(filename);
		}
		else if(col.equals("����")){  //����
			JLabel msg = new JLabel();
			msg.setText("���� �Ͻðڽ��ϱ�?");

			if (JOptionPane.showConfirmDialog(null, msg, "Ȯ��",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) != JOptionPane.YES_OPTION)
				return;

			System.exit(0); //Ȯ�ν� ����
		} //�޴����ý� �ش��� ��������  select 1�̸� ���ø��. setDrawMode�� ������ �������� ������ ��� ����
		
		else if(col.equals("���׸���(L)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.LINE);
		}
		else if(col.equals("�簢��(R)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.RECT);
		}
		
		else if(col.equals("���׸���(C)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.ECLIPSE);
		}
		else if(col.equals("����(P)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.PEN);
		}
		else if(col.equals("���찳(A)")){
			dp.select=0;
			dp.setDrawMode(DrawPanel.ERASER);
		}
		
		
	}
	
	/**
	 *  �������� ���� �޼ҵ�
	 */

	public static void main(String args[]) {
		new ProjectFrame("�׸���");

	}
}
	
			
			
	
