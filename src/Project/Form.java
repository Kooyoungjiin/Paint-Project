package Project;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

/**
 *  ��, ��, ����, �簢��, ���찳�� �� ��ü�� ���� ������ ������ �ִ� Ŭ����
 *
 */

public  class Form {
	
	private Vector<Point> point = new Vector<Point>();
	private Color color;
	
	/**
	 *   Form ������
	 */

	public Form() {}
	
	/**
	 *  ���� ����Ʈ�� �����Ѵ�
	 */
	
	public void addPoint(Point p) {
	point.add(p);
	}
	
	/**
	 *  ���� �÷��� �����Ѵ�
	 */
	
	public void setColor(Color c) {
		color = c;
	}
	/**
	 *  ���� �÷��� ���´�
	 *  @return ������ ������ ��ȯ�Ѵ�.
	 */

	public Color getColor() {
		return color;
	}
	
	/**
	 *  ���� ����Ʈ�� ���´�
	 *  @return ���� ����Ʈ�� ��ȯ�Ѵ�.
	 */
	
	public Vector<Point> getPoint() {
		return point;
	}

	
	/**
	 *   ���� p1, p2 ������ ������ ��ȯ�Ѵ�
	 *   @return ���ڷ� �޾ƿ� ������ ����� ��ȯ�Ѵ�.   
	 */
	public Rectangle getRect(Point p1, Point p2) {
		Rectangle rect = null;

		int minX = Math.min(p1.x, p2.x);
		int maxX = Math.max(p1.x, p2.x);
		int minY = Math.min(p1.y, p2.y);
		int maxY = Math.max(p1.y, p2.y);

		rect = new Rectangle(minX, minY, maxX - minX, maxY - minY);

		return rect;
	}
	
}
