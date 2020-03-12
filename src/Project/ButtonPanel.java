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
 *  왼쪽 버튼을 나타내는 패널
 *
 */

public class ButtonPanel extends JPanel implements ActionListener
{
	protected DrawPanel dp;
	private JToggleButton line, rect, eclipse, eraser, pencil, color, brush;
	private ButtonGroup bg = new ButtonGroup();
	
	/**
     *  ButtonPanel 생성자, 버튼은 토글버튼으로 만듬
     *  토글버튼으로 눌르면 들어가고 다른버튼을 누르면 다시 나옴
     */
	public ButtonPanel(final DrawPanel dp){
		setBackground(new Color(200, 255, 255));  //패널색 설정
		setBorder(BorderFactory.createRaisedBevelBorder()); // 테두리 설정
		Dimension IconSize = new Dimension(40,50); // 사이즈설정
		JPanel ToolBar = new JPanel();
		this.dp=dp;
		ToolBar.setLayout(new BorderLayout());
		JPanel text = new JPanel();
		JPanel tool = new JPanel();
					
		text.setBorder(BorderFactory.createRaisedBevelBorder());
		JCheckBox fillmode = new JCheckBox("채우기");  //체크박스 체크되면 도형 그릴때 채워서 그려짐 익명클래스로 설정
		fillmode.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent e){
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				dp.fillMode=1; //fillmode1이면 채워서 그림
			}
			else
				dp.fillMode=0;
			
		}
		});
     	text.add(fillmode);
     	ToolBar.add(text, BorderLayout.NORTH);
		tool.setBorder(BorderFactory.createRaisedBevelBorder());
		tool.setLayout(new GridLayout(6,2));    //도구버튼 그리드레이아웃 설정
     	tool.setCursor(new Cursor(Cursor.HAND_CURSOR)); //패널진입시 손모양 마우스로 커서변경 
		// 토글버튼들 사진, 툴팁, 사이즈, 선택되었을때 들어가는것처럼 보이기 위해 작은사진 한개 더 넣음
     	pencil = new JToggleButton(new ImageIcon("image/Pencil.jpg"));
     	pencil.setToolTipText("연필");
     	pencil.setPreferredSize(IconSize);
     	pencil.addActionListener(this);
     	pencil.setSelectedIcon(new ImageIcon("image/Pencil2.jpg"));	
     	pencil.setSelected(true); //연필버튼 처음부터 선택되있음
     	
     	eraser = new JToggleButton(new ImageIcon("image/Eraser.jpg"));
     	eraser.setToolTipText("지우개");
     	eraser.setPreferredSize(IconSize);
     	eraser.addActionListener(this);
     	eraser.setSelectedIcon(new ImageIcon("image/Eraser2.jpg"));	
     	
					
     	line = new JToggleButton(new ImageIcon("image/Line.jpg"));
		line.setToolTipText("선그리기");
		line.setPreferredSize(IconSize);
		line.addActionListener(this);
		line.setSelectedIcon(new ImageIcon("image/Line2.jpg"));	
		
		
		rect = new JToggleButton(new ImageIcon("image/Rect.jpg"));
       	rect.setToolTipText("사각형그리기");
     	rect.setPreferredSize(IconSize);
     	rect.addActionListener(this);
     	rect.setSelectedIcon(new ImageIcon("image/Rect2.jpg"));	
     	
     	
     	
     	
     	eclipse = new JToggleButton(new ImageIcon("image/Oval.jpg"));
     	eclipse.setToolTipText("원그리기");
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
     *  버튼이 눌렸을때마다 발생하는 이벤트를 설정하는 메소드
     *  선택버튼의 경우 잘라내기, 복사, 붙여넣기에 한에서 선택버튼 개속 유지대도록 설정함
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