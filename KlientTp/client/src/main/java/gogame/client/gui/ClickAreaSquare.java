package gogame.client.gui;

import java.awt.Graphics;

public class ClickAreaSquare {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public ClickAreaSquare(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void draw(Graphics g) {
		//g.setColor(color);
		g.drawRect(x, y, width, height);
	}
	public boolean isInsideSquare(int a,int b) {
		if((x<a) && (a <x+width) && (y<b)  && (b<y+height)) return true;
		else return false;
	}
	
}
