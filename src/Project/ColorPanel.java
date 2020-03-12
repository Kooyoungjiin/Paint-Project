package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


/**
 *  �Ʒ��ʿ� ������ �����ϴ� �г�
 *
 */

public class ColorPanel extends JPanel implements ActionListener, MouseListener
{
	
	JToggleButton black, white, blue, Brown, Gold, gray, gray2, Green, Lime, Orange, Pink, Red, Red2, Sky, violet, Wine, Yellow, Yellow2;
    JPanel selectColor;
    /**
     *  ColorPanel ������, ���� ���� ��ư���� ������ �гο� �߰�
     */
	public ColorPanel(){
		setBackground(new Color(200, 255, 255)); //�гλ� ����
		setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5)); //�÷ο췹�̾ƿ� �������� ������ 15,5
        setPreferredSize(new Dimension(640,60)); //������ ����
        setBorder(BorderFactory.createRaisedBevelBorder()); // �׵θ� ����
        Dimension IconSize = new Dimension(30,25); // �����ܻ����� ����
              
        JPanel nowColor = new JPanel();    //���� ���õ� ���� ���̴� �г�
     	nowColor.setBorder(BorderFactory.createRaisedBevelBorder());
     	        
     	selectColor = new JPanel();
	 	selectColor.setBorder(BorderFactory.createLoweredBevelBorder());	// �׵θ� ����
	 	selectColor.setBackground(Color.black);
   	 	selectColor.setToolTipText("���õ� ����");
   	    selectColor.setPreferredSize(new Dimension(20, 20));	
   	 	
   	    //��޼��� Ŭ���� �����̾�α� ��Ÿ���� �����ϰ� ��������
   	    JPanel nowcolortextPanel = new JPanel();
   	    
   	    nowcolortextPanel.add(new JLabel("��� ����"));
   	    nowColor.setLayout(new BorderLayout());
     	nowColor.add(nowcolortextPanel, BorderLayout.CENTER);        
     	nowColor.add(selectColor, BorderLayout.NORTH);
     	nowColor.setCursor(new Cursor(Cursor.HAND_CURSOR));//�ո��Ŀ��
     	nowColor.addMouseListener(this);
     	add(nowColor);
     	//�⺻���� ������ �г� ����
     	JPanel Color = new JPanel();
     	
     	Color.setLayout(new GridLayout(2,6));
     	Color.setBorder(BorderFactory.createRaisedBevelBorder()); // �׵θ� ����
     	Color.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�ո��Ŀ��
     	add(Color);
     	
     	//�����ư�� �̹����� ���� ������ �׼� ����
     	black = new JToggleButton(("����"),new ImageIcon("Color/Black.jpg"));
        black.setToolTipText("����");
        black.setPreferredSize(IconSize);
        black.addActionListener(this);
     	Color.add(black);
     	
     	white = new JToggleButton(("���"), new ImageIcon("Color/White.jpg"));
     	white.setToolTipText("���");
     	white.setPreferredSize(IconSize);
     	white.addActionListener(this);
     	Color.add(white);
        
        blue = new JToggleButton(("����"),new ImageIcon("Color/Blue.jpg"));
        blue.setToolTipText("����");
        blue.setPreferredSize(IconSize);
        blue.addActionListener(this);
        Color.add(blue);
        
        
        Brown = new JToggleButton(("����"),new ImageIcon("Color/Brown.jpg"));
        Brown.setToolTipText("����");
        Brown.setPreferredSize(IconSize);
        Brown.addActionListener(this);
        Color.add(Brown);
        
        Gold = new JToggleButton(("Ȳ�ݻ�"),new ImageIcon("Color/Gold.jpg"));
        Gold.setToolTipText("Ȳ�ݻ�");
        Gold.setPreferredSize(IconSize);
        Gold.addActionListener(this);
        Color.add(Gold);
        
        gray = new JToggleButton(("����ȸ��"),new ImageIcon("Color/gray.jpg"));
        gray.setToolTipText("����ȸ��");
        gray.setPreferredSize(IconSize);
        gray.addActionListener(this);
        Color.add(gray);
        
        gray2 = new JToggleButton(("����ȸ��"),new ImageIcon("Color/gray2.jpg"));
        gray2.setToolTipText("����ȸ��");
        gray2.setPreferredSize(IconSize);
        gray2.addActionListener(this);
        Color.add(gray2);
        
        Green = new JToggleButton(("���"),new ImageIcon("Color/Green.jpg"));
        Green.setToolTipText("���");
        Green.setPreferredSize(IconSize);
        Green.addActionListener(this);
        Color.add(Green);
        
        
        Lime = new JToggleButton(("����"),new ImageIcon("Color/Lime.jpg"));
        Lime.setToolTipText("����");
        Lime.setPreferredSize(IconSize);
        Lime.addActionListener(this);
        Color.add(Lime);
        
        
        Orange = new JToggleButton(("��Ȳ"),new ImageIcon("Color/Orange.jpg"));
        Orange.setToolTipText("��Ȳ");
        Orange.setPreferredSize(IconSize);
        Orange.addActionListener(this);
        Color.add(Orange);
        
        Pink = new JToggleButton(("��ũ��"),new ImageIcon("Color/Pink.jpg"));
        Pink.setToolTipText("��ũ��");
        Pink.setPreferredSize(IconSize);
        Pink.addActionListener(this);
        Color.add(Pink);
        
        
        Red = new JToggleButton(("����"),new ImageIcon("Color/Red.jpg"));
        Red.setToolTipText("����");
        Red.setPreferredSize(IconSize);
        Red.addActionListener(this);
        Color.add(Red);
        
        Red2 = new JToggleButton(("���ѻ���"),new ImageIcon("Color/Red2.jpg"));
        Red2.setToolTipText("���ѻ���");
        Red2.setPreferredSize(IconSize);
        Red2.addActionListener(this);
        Color.add(Red2);
        
        Sky = new JToggleButton(("�ϴû�"),new ImageIcon("Color/Sky.jpg"));
        Sky.setToolTipText("�ϴû�");
        Sky.setPreferredSize(IconSize);
        Sky.addActionListener(this);
        Color.add(Sky);
        
        
        violet = new JToggleButton(("���Ѻ���"),new ImageIcon("Color/violet.jpg"));
        violet.setToolTipText("���Ѻ���");
        violet.setPreferredSize(IconSize);
        violet.addActionListener(this);
        Color.add(violet);
        
        Wine = new JToggleButton(("����"),new ImageIcon("Color/Wine.jpg"));
     	Wine.setToolTipText("����");
        Wine.setPreferredSize(IconSize);
        Wine.addActionListener(this);
        Color.add(Wine);
        
        
        Yellow = new JToggleButton(("���"),new ImageIcon("Color/Yellow.jpg"));
        Yellow.setToolTipText("���");
        Yellow.setPreferredSize(IconSize);
        Yellow.addActionListener(this);
        Color.add(Yellow);
        
        Yellow2 = new JToggleButton(("���ѳ��"),new ImageIcon("Color/Yellow2.jpg"));
        Yellow2.setToolTipText("���ѳ��");
        Yellow2.setPreferredSize(IconSize);
        Yellow2.addActionListener(this);
        Color.add(Yellow2);
       
	}
	
	 /**
     *  ��ư�� ������������ �߻��ϴ� �̺�Ʈ�� ���� 
     */

	public void actionPerformed(ActionEvent e) { 
    // ��ư�� ������ ����������� �гκ��� �����ϰ� ������
		String col = (String)e.getActionCommand(); 
		Color c;
		if (col.equals("����")) {
			selectColor.setBackground(Color.black);
			ProjectFrame.color=Color.black;
			
			}
		else if (col.equals("���")) {
			selectColor.setBackground(Color.white);
			ProjectFrame.color=Color.white;
			}
		else if (col.equals("����")) {
			selectColor.setBackground(new Color(0,162,232));
			ProjectFrame.color=new Color(0,162,232);
		}
		else if (col.equals("����")) {
			selectColor.setBackground(new Color(185,122,87));
			ProjectFrame.color=new Color(185,122,87);
		}
		else if (col.equals("�ϴû�")) {
			selectColor.setBackground(new Color(128,255,255));
			ProjectFrame.color=new Color(128,255,255);
		}
		else if (col.equals("���")) {
			selectColor.setBackground(new Color(34,177,76));
			ProjectFrame.color=new Color(34,177,76);
		}
		else if (col.equals("����")) {
			selectColor.setBackground(new Color(181,230,29));
			ProjectFrame.color=new Color(181,230,29);
		}
		else if (col.equals("��Ȳ")) {
			selectColor.setBackground(new Color(255,127,39));
			ProjectFrame.color=new Color(255,127,39);
		}
		else if (col.equals("��ũ��")) {
			selectColor.setBackground(new Color(255,174,201));
			ProjectFrame.color=new Color(255,174,201);
		}
		else if (col.equals("����")) {
			selectColor.setBackground(new Color(237,28,36));
			ProjectFrame.color=new Color(237,28,36);
		}
		else if (col.equals("���ѻ���")) {
			selectColor.setBackground(new Color(136,0,21));
			ProjectFrame.color= new Color(136,0,21);
		}	
		else if (col.equals("���")) {
			selectColor.setBackground(new Color(255,242,0));
			ProjectFrame.color=new Color(255,242,0);
		}
		else if (col.equals("���ѳ��")) {
			selectColor.setBackground(new Color(239,228,176));
			ProjectFrame.color=new Color(239,228,176);
		}
		else if (col.equals("Ȳ�ݻ�")) {
			selectColor.setBackground(new Color(255,201,14));
			ProjectFrame.color=new Color(255,201,14);
		}
		else if (col.equals("���Ѻ���")) {
			selectColor.setBackground(new Color(200,191,231));
			ProjectFrame.color=new Color(200,191,231);
		}
		else if (col.equals("����")) {
			selectColor.setBackground(new Color(163,73,164));
			ProjectFrame.color=new Color(163,73,164);
		}
		else if (col.equals("����ȸ��")) {
			selectColor.setBackground(new Color(127,127,127));
			ProjectFrame.color=new Color(127,127,127);
		}
		else if (col.equals("����ȸ��")) {
			selectColor.setBackground(new Color(195,195,195));
			ProjectFrame.color=new Color(195,195,195);
		}
				
	}
	
	 /**
     *  ���콺 Ŭ���� ��޻� ���� �̺�Ʈ �߻� ����ư �ܿ� �����ϰ� �� ���� ���� 
     */
	public void mouseClicked(MouseEvent e) {
		JColorChooser chooser = new JColorChooser();
		Color selectedColor = chooser.showDialog(null,"Color",selectColor.getBackground());
		if(selectedColor != null)
		{
			selectColor.setBackground(selectedColor);
			ProjectFrame.color=selectedColor;
		}
	}
	/**
	 * �̻�� ���� �޼ҵ�
	 */
	public void mousePressed(MouseEvent e) {}
	/**
	 * �̻�� ���� �޼ҵ�
	 */
	public void mouseReleased(MouseEvent e) {}
	/**
	 * �̻�� ���� �޼ҵ�
	 */
	public void mouseEntered(MouseEvent e) {}
	/**
	 * �̻�� ���� �޼ҵ�
	 */
	public void mouseExited(MouseEvent e) {	}
	

}