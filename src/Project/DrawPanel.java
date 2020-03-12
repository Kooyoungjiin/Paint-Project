package Project;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *  그림이 그려지는 영역을 가지는 패널 클래스
 *
 */

public class DrawPanel extends JPanel  {

	private BufferedImage b1 = new BufferedImage(1120,840,BufferedImage.TYPE_3BYTE_BGR); 
	private Graphics g1= b1.getGraphics();;  //실제 그려지는 영역
	private BufferedImage b2 = new BufferedImage(1120,840,BufferedImage.TYPE_3BYTE_BGR);
	private Graphics g2= b2.getGraphics();; // 그려지는 모습이 보이는 영역
	private Rectangle drag;
	private Form shape = null;
	/**
	 *  일반 그리기 모드와 선택 모드를 결정하는 상수
	 */
	public static int select=0;
	/**
	 *  도형을 그릴때 채우기 유무를 저장하는 상수
	 */
	public static int fillMode=0;
	/**
	 *  그림그리는 모드 선택하는 변수, 기본셋팅은 연필그리기
	 */
    public static int drawMode = 1;
	/**
	 *  연필을 그리는 모드 설정 상수
	 */
    public static final int PEN=1;
    /**
	 *  선을 그리는 모드 설정 상수
	 */
    public static final int LINE=2;
    /**
	 *  사각형을 그리는 모드 설정 상수
	 */
    public static final int RECT=3;
    /**
	 *  원을 그리는 모드 설정 상수
	 */
    public static final int ECLIPSE=4;
    /**
	 *  지우개를 그리는 모드 설정 상수
	 */
    public static final int ERASER=5;
   
   
	/**
	 *  DrawPanel 생성자
	 */
    
	public DrawPanel(String name) {
		
		g1.fillRect(0,0,1120,840);
		g1.drawImage(new ImageIcon(name).getImage(),0,0,null);
		drag= new Rectangle(-1,0,0,0);
		Listener listener = new Listener(); // 마우스 리스너와 모션리스너 구현한 클래스 생성
		addMouseListener(listener); // 마우스리스너 설정
		addMouseMotionListener(listener); //마우스모션리스너 설정
		
		repaint();
	}
	
	
	/**
	 *  선택된 영역을 복사해 두는 메소드
	 */

   

	/**
	 *  현재 그리기 모드를 설정하는 메소드
	 */
	public void setDrawMode(int dmode) {
		
		drawMode = dmode;
	}
	
	
	
	/**
	 *  새로만들기 메소드, 현재 내용의 저장유무를 물은후 새로만들어진다.
	 *  @return 새로만들기로 파일이름 리턴해 타이틀 설정
	 */
	
	public String creation(){
		g1.drawImage(new ImageIcon("image/제목 없음.jpg").getImage(),0,0,null);
		String name="제목 없음.jpg";
		repaint();
		return name;
	}
	
	/**
	 *  파일을 불러와서 DrawPanel에 그려주는 메소드. png 파일과 jpg 파일을 지원한다.
	 *  @return 파일을 열고 파일이름을 리턴해 타이틀 설정
	 */
	
	public String open(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG(*.png)","png"); //png파일필터
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("JPG(*.jpg)","jpg");//jpg파일필터
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter); //png와 jpg파일필터 각각 따로 추가
		fc.setFileFilter(filter2);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(null);
		
		if(returnVal != JFileChooser.CANCEL_OPTION)
		{
			File file = fc.getSelectedFile(); //선택된파일 찾아옴
			g1.drawImage(new ImageIcon("image/제목 없음.jpg").getImage(),0,0,null); //작업후 패널사이즈보다 작은 사진을 불러오는경우 빈공간 작업이 남아있을 가능성
            //있어 먼저 빈이미지 설정후 불러온이미지 출력해 이미지가 작으면 없는공간 흰색으로 표현
			g1.drawImage(new ImageIcon(file.getPath()).getImage(),0,0,null); //불러온 이미지 설정
			String filename=file.getName();
			
			repaint();
			return filename;
  			
		}
		return ProjectFrame.filename;
	}
	

	/**
	 *  DrawPanel에 그려진 그림을 파일에 저장하는 메소드. png 파일과 jpg 파일을 지원한다.
	 *  뒤에 확장자를 붙이지 않으면 jpg 파일로 만들어진다.
	 *  동일한 이름의 파일이 존재하면 교체여부를 묻는다.
	 */
	public void reNameSave() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG(*.png)","png"); //png파일필터
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("JPG(*.jpg)","jpg");//jpg파일필터
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);//png와 jpg파일필터 각각 따로 추가
		fc.setFileFilter(filter2);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showSaveDialog(null);
		
		if(returnVal == JFileChooser.CANCEL_OPTION)
		{
			return;
		}
		String filename;
		File file = fc.getSelectedFile();
		if(file.getName().contains(".jpg") || file.getName().contains(".JPG") || file.getName().contains(".PNG") || file.getName().contains(".png"))
			filename=file.getName();  //파일창에 확장자 안적으면 .jpg자동으로 붙여서 저장
		else
		{
			file=new File(fc.getSelectedFile()+".jpg");
			filename=file.getName();
		}
		
		if(file.isFile()) //저장 하려는 파일이름이 이미 존재하면 물어보고 확인하면 덮어씀
		{
			JLabel msg = new JLabel();
			msg.setText("현재 파일이 있습니디. 바꾸시겠습니까?");
			if (!(JOptionPane.showConfirmDialog(null, msg, "확인",	JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)){
				return;
			}
		}
		
		ProjectFrame.filename=filename; //파일이름 설정
		try {
			ImageIO.write(b1, "png", file); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 *  repaint 할때마다 새로 그림을 그려주는 메소드
	 */
	
	public void paint (Graphics g) {
		g.drawImage(b1,0,0,null);
		Graphics g2 = g;
		if(select==1){ //선택모드일시
			if (drag.x != -1)
			{
				g2.setColor(Color.black);
				g2.drawRect(drag.x, drag.y, drag.width, drag.height);
			}
		}	
	}

	
	/**
	 *  마우스 이벤트가 발생할때마다 나타나는 마우스 모션리스너, 리스너를 구현한 클래스
	 */

	class Listener implements MouseMotionListener, MouseListener {

		/**
		 *  마우스가 눌렸을때의 반응, 현재 위치를 저장
		 */
		public void mousePressed(MouseEvent e) {
			
			if(select==1){
				drag.x = -1; // 드래그 시작 위치
			}
			else{

			
			switch (drawMode) {
	
			// 마우스가 눌렸을때 시작점을 저장하고 지우개모드 일땐 흰색으로 설정한다.
			case LINE: case PEN: case ERASER: case ECLIPSE:	case RECT: 
				if (shape == null)
				{
					Form form = null;
					form = new Form();
					if(drawMode==ERASER){
						form.setColor(Color.white); //지우개일땐 흰색으로 설정
					}
					else
						form.setColor(ProjectFrame.color); //그외엔 현재 설정된 색으로 설정
					
					shape=form;
				}
				shape.addPoint(e.getPoint()); // 처음 누른 포인트 저장
				break;
			}
			}
		}

		/**
		 *  마우스를 드래그 했을때의 반응
		 *  선, 원, 사각형, 둥근사각형은 위에 구현한 draw2 메소드를 통해 그려지는 모습이 보여지고
		 *  연필, 지우개, 색칠하기는 드래그 할때마다 실제로 그려진다.
		 */
		public void mouseDragged(MouseEvent e) {
			if(select==1){ //선택모드일경우 선택하는 과정이 나타난다.
				
				if (drag.x == -1)
				{
					drag.setBounds(e.getX(),e.getY(),0,0);
				}
				
				drag.width = e.getX() - drag.x;
				drag.height = e.getY() - drag.y;
				repaint();
			}
			
			else{
				Graphics g2 = getGraphics();

				if (shape != null) {
					
					Point p1 = shape.getPoint().firstElement(); //첫번째 요소 포인트
					Point p2 = shape.getPoint().lastElement(); // 마지막 요소 포인트

					switch (drawMode) {
					case LINE: case ECLIPSE: case RECT: 
						
						shape.addPoint(e.getPoint()); //현재 포인트 추가
						g2.setColor(shape.getColor()); //색을 얻어온다.						
						if (drawMode == LINE) {  // 모드가 선일때 드래그하는 과정에서 선을 그리는 과정이 나타난다.
							g2.drawLine(p1.x, p1.y, p2.x, p2.y);
						}
						else if (drawMode == ECLIPSE) {  // 모드가 원일때 원을  드래그하는 과정에서 그리는 과정이 나타난다. 채우기 모드일땐 채워서 나타난다.
						
							if(fillMode==0)
							{
								Rectangle rect = shape.getRect(p1, p2);
							    g2.drawOval(rect.x, rect.y, rect.width, rect.height);
							}
							else
							{
								Rectangle rect = shape.getRect(p1, p2);
							    g2.fillOval(rect.x, rect.y, rect.width, rect.height);
							}
						}
						else if (drawMode == RECT) { // 모드가 사각형일때  드래그하는 과정에서 사각형을 그리는 과정이 나타난다. 채우기 모드일땐 채워서 나타난다.
							if(fillMode==0)
							{
								Rectangle rect = shape.getRect(p1, p2);
							    g2.drawRect(rect.x, rect.y, rect.width, rect.height);
							}
							else
							{
								Rectangle rect = shape.getRect(p1, p2);
								g2.fillRect(rect.x, rect.y, rect.width, rect.height);
							}
						}
						
						break;
					case PEN:  // 모드가 팬일때 연필이 그려진다
						shape.addPoint(e.getPoint());
						p1 = shape.getPoint().size() > 1 ? shape.getPoint().get(shape.getPoint().size() - 2) : shape.getPoint().firstElement();
						p2 = shape.getPoint().lastElement();
						g1.setColor(shape.getColor());
						g1.drawLine(p1.x, p1.y, p2.x, p2.y);
						break;
				    case ERASER: // 모드가 지우개일때 지워진다.
				    	g1.setColor(Color.white);
						g1.fillRect(e.getX(), e.getY(),15, 15);
						break;
				 
					}
					getParent().repaint();  //잔상을 지우기 위해 부모컴포넌트 리페인트
				}
			}
	}
		/**
		 *  마우스를 놓았을때의 반응, 선, 원, 사각형, 둥근사각형이  실제로 그려지게 된다.
		 */
		public void mouseReleased(MouseEvent e) {
			
			if(select==1){ //선택모드 일경우 리턴
				return;
				
			}else{
				if (shape != null) {
				
					Point p1 = shape.getPoint().firstElement(); // 첫번째 요소 포인트
					Point p2 = shape.getPoint().lastElement(); // 마지막 요소 포인트
					switch (drawMode) {
					case LINE: case ECLIPSE: case RECT: 
						
						shape.addPoint(e.getPoint()); //현재 포인트 추가
						g1.setColor(shape.getColor()); //색설정
						if (drawMode == LINE) {   // 모드가 선일때 마우스를 놓는 지점에서 선이 그려진다.
							g1.drawLine(p1.x, p1.y, p2.x, p2.y);
						}
						else if (drawMode == ECLIPSE) { // 모드가 원일때 마우스를 놓는 지점에서 원이 그려진다. 채우기 모드일땐 채워서 그려진다.
							
							if(fillMode==0)
							{
								Rectangle rect = shape.getRect(p1, p2);
								g1.drawOval(rect.x, rect.y, rect.width, rect.height); 
							}
							else
							{
								Rectangle rect = shape.getRect(p1, p2);
								g1.fillOval(rect.x, rect.y, rect.width, rect.height); 
							}
						}
						else if (drawMode == RECT) { // 모드가 사각형일때 마우스를 놓는 지점에서 사각형이 그려진다. 채우기 모드일땐 채워서 그려진다.
							if(fillMode==0)
							{
								Rectangle rect = shape.getRect(p1, p2);
							    g1.drawRect(rect.x, rect.y, rect.width, rect.height); 
							}
							else
							{
								Rectangle rect = shape.getRect(p1, p2);
								g1.fillRect(rect.x, rect.y, rect.width, rect.height);
							}
						}
						
						break;
					}
					shape = null;
					repaint();
				}
			}
			
	    }
		/**
		 * 미사용 구현 메소드
		 */
		public void mouseMoved(MouseEvent e) {}
		/**
		 * 미사용 구현 메소드
		 */
		public void mouseClicked(MouseEvent e) {}
		/**
		 * 미사용 구현 메소드
		 */
		public void mouseEntered(MouseEvent e) {}
		/**
		 * 미사용 구현 메소드
		 */
		public void mouseExited(MouseEvent e) {}
    }
}
