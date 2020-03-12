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
 *  �׸��� �׷����� ������ ������ �г� Ŭ����
 *
 */

public class DrawPanel extends JPanel  {

	private BufferedImage b1 = new BufferedImage(1120,840,BufferedImage.TYPE_3BYTE_BGR); 
	private Graphics g1= b1.getGraphics();;  //���� �׷����� ����
	private BufferedImage b2 = new BufferedImage(1120,840,BufferedImage.TYPE_3BYTE_BGR);
	private Graphics g2= b2.getGraphics();; // �׷����� ����� ���̴� ����
	private Rectangle drag;
	private Form shape = null;
	/**
	 *  �Ϲ� �׸��� ���� ���� ��带 �����ϴ� ���
	 */
	public static int select=0;
	/**
	 *  ������ �׸��� ä��� ������ �����ϴ� ���
	 */
	public static int fillMode=0;
	/**
	 *  �׸��׸��� ��� �����ϴ� ����, �⺻������ ���ʱ׸���
	 */
    public static int drawMode = 1;
	/**
	 *  ������ �׸��� ��� ���� ���
	 */
    public static final int PEN=1;
    /**
	 *  ���� �׸��� ��� ���� ���
	 */
    public static final int LINE=2;
    /**
	 *  �簢���� �׸��� ��� ���� ���
	 */
    public static final int RECT=3;
    /**
	 *  ���� �׸��� ��� ���� ���
	 */
    public static final int ECLIPSE=4;
    /**
	 *  ���찳�� �׸��� ��� ���� ���
	 */
    public static final int ERASER=5;
   
   
	/**
	 *  DrawPanel ������
	 */
    
	public DrawPanel(String name) {
		
		g1.fillRect(0,0,1120,840);
		g1.drawImage(new ImageIcon(name).getImage(),0,0,null);
		drag= new Rectangle(-1,0,0,0);
		Listener listener = new Listener(); // ���콺 �����ʿ� ��Ǹ����� ������ Ŭ���� ����
		addMouseListener(listener); // ���콺������ ����
		addMouseMotionListener(listener); //���콺��Ǹ����� ����
		
		repaint();
	}
	
	
	/**
	 *  ���õ� ������ ������ �δ� �޼ҵ�
	 */

   

	/**
	 *  ���� �׸��� ��带 �����ϴ� �޼ҵ�
	 */
	public void setDrawMode(int dmode) {
		
		drawMode = dmode;
	}
	
	
	
	/**
	 *  ���θ���� �޼ҵ�, ���� ������ ���������� ������ ���θ��������.
	 *  @return ���θ����� �����̸� ������ Ÿ��Ʋ ����
	 */
	
	public String creation(){
		g1.drawImage(new ImageIcon("image/���� ����.jpg").getImage(),0,0,null);
		String name="���� ����.jpg";
		repaint();
		return name;
	}
	
	/**
	 *  ������ �ҷ��ͼ� DrawPanel�� �׷��ִ� �޼ҵ�. png ���ϰ� jpg ������ �����Ѵ�.
	 *  @return ������ ���� �����̸��� ������ Ÿ��Ʋ ����
	 */
	
	public String open(){
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG(*.png)","png"); //png��������
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("JPG(*.jpg)","jpg");//jpg��������
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter); //png�� jpg�������� ���� ���� �߰�
		fc.setFileFilter(filter2);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(null);
		
		if(returnVal != JFileChooser.CANCEL_OPTION)
		{
			File file = fc.getSelectedFile(); //���õ����� ã�ƿ�
			g1.drawImage(new ImageIcon("image/���� ����.jpg").getImage(),0,0,null); //�۾��� �гλ������ ���� ������ �ҷ����°�� ����� �۾��� �������� ���ɼ�
            //�־� ���� ���̹��� ������ �ҷ����̹��� ����� �̹����� ������ ���°��� ������� ǥ��
			g1.drawImage(new ImageIcon(file.getPath()).getImage(),0,0,null); //�ҷ��� �̹��� ����
			String filename=file.getName();
			
			repaint();
			return filename;
  			
		}
		return ProjectFrame.filename;
	}
	

	/**
	 *  DrawPanel�� �׷��� �׸��� ���Ͽ� �����ϴ� �޼ҵ�. png ���ϰ� jpg ������ �����Ѵ�.
	 *  �ڿ� Ȯ���ڸ� ������ ������ jpg ���Ϸ� ���������.
	 *  ������ �̸��� ������ �����ϸ� ��ü���θ� ���´�.
	 */
	public void reNameSave() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG(*.png)","png"); //png��������
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("JPG(*.jpg)","jpg");//jpg��������
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);//png�� jpg�������� ���� ���� �߰�
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
			filename=file.getName();  //����â�� Ȯ���� �������� .jpg�ڵ����� �ٿ��� ����
		else
		{
			file=new File(fc.getSelectedFile()+".jpg");
			filename=file.getName();
		}
		
		if(file.isFile()) //���� �Ϸ��� �����̸��� �̹� �����ϸ� ����� Ȯ���ϸ� ���
		{
			JLabel msg = new JLabel();
			msg.setText("���� ������ �ֽ��ϵ�. �ٲٽðڽ��ϱ�?");
			if (!(JOptionPane.showConfirmDialog(null, msg, "Ȯ��",	JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)){
				return;
			}
		}
		
		ProjectFrame.filename=filename; //�����̸� ����
		try {
			ImageIO.write(b1, "png", file); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 *  repaint �Ҷ����� ���� �׸��� �׷��ִ� �޼ҵ�
	 */
	
	public void paint (Graphics g) {
		g.drawImage(b1,0,0,null);
		Graphics g2 = g;
		if(select==1){ //���ø���Ͻ�
			if (drag.x != -1)
			{
				g2.setColor(Color.black);
				g2.drawRect(drag.x, drag.y, drag.width, drag.height);
			}
		}	
	}

	
	/**
	 *  ���콺 �̺�Ʈ�� �߻��Ҷ����� ��Ÿ���� ���콺 ��Ǹ�����, �����ʸ� ������ Ŭ����
	 */

	class Listener implements MouseMotionListener, MouseListener {

		/**
		 *  ���콺�� ���������� ����, ���� ��ġ�� ����
		 */
		public void mousePressed(MouseEvent e) {
			
			if(select==1){
				drag.x = -1; // �巡�� ���� ��ġ
			}
			else{

			
			switch (drawMode) {
	
			// ���콺�� �������� �������� �����ϰ� ���찳��� �϶� ������� �����Ѵ�.
			case LINE: case PEN: case ERASER: case ECLIPSE:	case RECT: 
				if (shape == null)
				{
					Form form = null;
					form = new Form();
					if(drawMode==ERASER){
						form.setColor(Color.white); //���찳�϶� ������� ����
					}
					else
						form.setColor(ProjectFrame.color); //�׿ܿ� ���� ������ ������ ����
					
					shape=form;
				}
				shape.addPoint(e.getPoint()); // ó�� ���� ����Ʈ ����
				break;
			}
			}
		}

		/**
		 *  ���콺�� �巡�� �������� ����
		 *  ��, ��, �簢��, �ձٻ簢���� ���� ������ draw2 �޼ҵ带 ���� �׷����� ����� ��������
		 *  ����, ���찳, ��ĥ�ϱ�� �巡�� �Ҷ����� ������ �׷�����.
		 */
		public void mouseDragged(MouseEvent e) {
			if(select==1){ //���ø���ϰ�� �����ϴ� ������ ��Ÿ����.
				
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
					
					Point p1 = shape.getPoint().firstElement(); //ù��° ��� ����Ʈ
					Point p2 = shape.getPoint().lastElement(); // ������ ��� ����Ʈ

					switch (drawMode) {
					case LINE: case ECLIPSE: case RECT: 
						
						shape.addPoint(e.getPoint()); //���� ����Ʈ �߰�
						g2.setColor(shape.getColor()); //���� ���´�.						
						if (drawMode == LINE) {  // ��尡 ���϶� �巡���ϴ� �������� ���� �׸��� ������ ��Ÿ����.
							g2.drawLine(p1.x, p1.y, p2.x, p2.y);
						}
						else if (drawMode == ECLIPSE) {  // ��尡 ���϶� ����  �巡���ϴ� �������� �׸��� ������ ��Ÿ����. ä��� ����϶� ä���� ��Ÿ����.
						
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
						else if (drawMode == RECT) { // ��尡 �簢���϶�  �巡���ϴ� �������� �簢���� �׸��� ������ ��Ÿ����. ä��� ����϶� ä���� ��Ÿ����.
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
					case PEN:  // ��尡 ���϶� ������ �׷�����
						shape.addPoint(e.getPoint());
						p1 = shape.getPoint().size() > 1 ? shape.getPoint().get(shape.getPoint().size() - 2) : shape.getPoint().firstElement();
						p2 = shape.getPoint().lastElement();
						g1.setColor(shape.getColor());
						g1.drawLine(p1.x, p1.y, p2.x, p2.y);
						break;
				    case ERASER: // ��尡 ���찳�϶� ��������.
				    	g1.setColor(Color.white);
						g1.fillRect(e.getX(), e.getY(),15, 15);
						break;
				 
					}
					getParent().repaint();  //�ܻ��� ����� ���� �θ�������Ʈ ������Ʈ
				}
			}
	}
		/**
		 *  ���콺�� ���������� ����, ��, ��, �簢��, �ձٻ簢����  ������ �׷����� �ȴ�.
		 */
		public void mouseReleased(MouseEvent e) {
			
			if(select==1){ //���ø�� �ϰ�� ����
				return;
				
			}else{
				if (shape != null) {
				
					Point p1 = shape.getPoint().firstElement(); // ù��° ��� ����Ʈ
					Point p2 = shape.getPoint().lastElement(); // ������ ��� ����Ʈ
					switch (drawMode) {
					case LINE: case ECLIPSE: case RECT: 
						
						shape.addPoint(e.getPoint()); //���� ����Ʈ �߰�
						g1.setColor(shape.getColor()); //������
						if (drawMode == LINE) {   // ��尡 ���϶� ���콺�� ���� �������� ���� �׷�����.
							g1.drawLine(p1.x, p1.y, p2.x, p2.y);
						}
						else if (drawMode == ECLIPSE) { // ��尡 ���϶� ���콺�� ���� �������� ���� �׷�����. ä��� ����϶� ä���� �׷�����.
							
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
						else if (drawMode == RECT) { // ��尡 �簢���϶� ���콺�� ���� �������� �簢���� �׷�����. ä��� ����϶� ä���� �׷�����.
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
		 * �̻�� ���� �޼ҵ�
		 */
		public void mouseMoved(MouseEvent e) {}
		/**
		 * �̻�� ���� �޼ҵ�
		 */
		public void mouseClicked(MouseEvent e) {}
		/**
		 * �̻�� ���� �޼ҵ�
		 */
		public void mouseEntered(MouseEvent e) {}
		/**
		 * �̻�� ���� �޼ҵ�
		 */
		public void mouseExited(MouseEvent e) {}
    }
}
