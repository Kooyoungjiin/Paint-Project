package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *  ���� ��ư�� ��Ÿ���� �г�
 *
 */

public class ButtonPanel extends JPanel implements ActionListener
{
	protected DrawPanel dp;
	private JToggleButton line, rect, eclipse, eraser, pencil, color, brush;
	private ButtonGroup bg = new ButtonGroup();
	
	/**
     *  ButtonPanel ������, ��ư�� ��۹�ư���� ����
     *  ��۹�ư���� ������ ���� �ٸ���ư�� ������ �ٽ� ����
     */
	public ButtonPanel(final DrawPanel dp){
		setBackground(new Color(200, 255, 255));  //�гλ� ����
		setBorder(BorderFactory.createRaisedBevelBorder()); // �׵θ� ����
		Dimension IconSize = new Dimension(40,50); // �������
		JPanel ToolBar = new JPanel();
		this.dp=dp;
		ToolBar.setLayout(new BorderLayout());
		JPanel text = new JPanel();
		JPanel tool = new JPanel();
					
		text.setBorder(BorderFactory.createRaisedBevelBorder());
		JCheckBox fillmode = new JCheckBox("ä���");  //üũ�ڽ� üũ�Ǹ� ���� �׸��� ä���� �׷��� �͸�Ŭ������ ����
		fillmode.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent e){
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				dp.fillMode=1; //fillmode1�̸� ä���� �׸�
			}
			else
				dp.fillMode=0;
			
		}
		});
     	text.add(fillmode);
     	ToolBar.add(text, BorderLayout.NORTH);
		tool.setBorder(BorderFactory.createRaisedBevelBorder());
		tool.setLayout(new GridLayout(6,2));    //������ư �׸��巹�̾ƿ� ����
     	tool.setCursor(new Cursor(Cursor.HAND_CURSOR)); //�г����Խ� �ո�� ���콺�� Ŀ������ 
		// ��۹�ư�� ����, ����, ������, ���õǾ����� ���°�ó�� ���̱� ���� �������� �Ѱ� �� ����
     	pencil = new JToggleButton(new ImageIcon("image/Pencil.jpg"));
     	pencil.setToolTipText("����");
     	pencil.setPreferredSize(IconSize);
     	pencil.addActionListener(this);
     	pencil.setSelectedIcon(new ImageIcon("image/Pencil2.jpg"));	
     	pencil.setSelected(true); //���ʹ�ư ó������ ���õ�����
     	
     	eraser = new JToggleButton(new ImageIcon("image/Eraser.jpg"));
     	eraser.setToolTipText("���찳");
     	eraser.setPreferredSize(IconSize);
     	eraser.addActionListener(this);
     	eraser.setSelectedIcon(new ImageIcon("image/Eraser2.jpg"));	
     	
					
     	line = new JToggleButton(new ImageIcon("image/Line.jpg"));
		line.setToolTipText("���׸���");
		line.setPreferredSize(IconSize);
		line.addActionListener(this);
		line.setSelectedIcon(new ImageIcon("image/Line2.jpg"));	
		
		
		rect = new JToggleButton(new ImageIcon("image/Rect.jpg"));
       	rect.setToolTipText("�簢���׸���");
     	rect.setPreferredSize(IconSize);
     	rect.addActionListener(this);
     	rect.setSelectedIcon(new ImageIcon("image/Rect2.jpg"));	
     	
     	
     	
     	
     	eclipse = new JToggleButton(new ImageIcon("image/Oval.jpg"));
     	eclipse.setToolTipText("���׸���");
     	eclipse.setPreferredSize(IconSize);
     	eclipse.addActionListener(this);	
     	eclipse.setSelectedIcon(new ImageIcon("image/Oval2.jpg"));	
     		     	
		
		
		bg.add(pencil);
     	bg.add(eraser);
     	bg.add(line);
     	bg.add(eclipse);
     	bg.add(rect);    	
     	
     	

     	tool.add(pencil);
     	tool.add(eraser);
     	tool.add(line);
     	tool.add(eclipse);
     	tool.add(rect);
     	
     	
 	
     	
     	ToolBar.add(tool, BorderLayout.SOUTH);
		add(ToolBar);
							
	}
	
	 /**
     *  ��ư�� ������������ �߻��ϴ� �̺�Ʈ�� �����ϴ� �޼ҵ�
     *  ���ù�ư�� ��� �߶󳻱�, ����, �ٿ��ֱ⿡ �ѿ��� ���ù�ư ���� �����뵵�� ������
     */
	
	public void actionPerformed(ActionEvent e){
		
		JToggleButton action =(JToggleButton)e.getSource();
		
		if(action==eraser)
		{
			dp.select=0;
			dp.setDrawMode(DrawPanel.ERASER);
		}
		else if(action==line)
		{
			dp.select=0;
			dp.setDrawMode(DrawPanel.LINE);
		}			
		else if(action==rect)
		{
			dp.select=0;	
			dp.setDrawMode(DrawPanel.RECT);
        }
		else if(action==eclipse)
		{
			dp.select=0;
			dp.setDrawMode(DrawPanel.ECLIPSE);
		}
		else if(action==pencil)
		{
			dp.select=0;
			dp.setDrawMode(DrawPanel.PEN);
		}
		
		
					
	}

}