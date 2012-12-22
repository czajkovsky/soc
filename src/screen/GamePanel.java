package screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import main.Main;

public class GamePanel extends JPanel {
	protected int boardWidth, boardHeight;
	final int size = 30;
	int beginX, beginY;
	
	int xpos, ypos;
	
	GamePanel() {
		boardWidth = 8;
		boardHeight = 10;
		beginX = 160;
		beginY = 60;
		this.setBackground(Color.BLACK);
		ml = new mouseListener();
		this.setClickable(false);
		xpos = ypos = 0;
	}
	
	public void paintComponent(Graphics g) {
		beginX = this.getWidth()/2 - boardWidth*size/2;
		beginY = this.getHeight()/2 - boardHeight*size/2;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.GRAY);
		g.drawRect(beginX+(boardWidth/2-1)*size, beginY-size, size, size);
		g.drawRect(beginX+(boardWidth/2)*size, beginY-size, size, size);
		g.drawRect(beginX+(boardWidth/2-1)*size, beginY+boardHeight*size, size, size);
		g.drawRect(beginX+(boardWidth/2)*size, beginY+boardHeight*size, size, size);
		for (int i=beginY; i<beginY+boardHeight*size; i+=size) {
			for (int j=beginX; j<beginX+boardWidth*size; j+=size) {	
				g.drawRect(j, i, size, size);
			}
		}
		
		for (int i=beginY; i<=beginY+boardHeight*size; i+=size) {
			for (int j=beginX; j<=beginX+boardWidth*size; j+=size) {	
				for (int k=i-size; k<=i+size; k+=size) {
					for (int l = j-size; l<=j+size; l+=size) {
						int tmp = Main.controller.getLink((j-beginX)/size,(i-beginY)/size,(l-beginX)/size,(k-beginY)/size);
						if (tmp == 1) {
							g.setColor(Color.BLUE);
							g.drawLine(j, i, l, k);
						}
						else if (tmp == 2) {
							g.setColor(Color.RED);
							g.drawLine(j, i, l, k);
						}
					}
				}
			}
		}
		
		g.setColor(Color.GREEN);
		g.fillOval(beginX+xpos*size-4, beginY+ypos*size-4, 8, 8);
	}
	
	class mouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			Main.controller.makeMove(xpos, ypos);
			repaint();
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	mouseListener ml;
	
	void setClickable(boolean b) {
		if (b == false) {
			this.removeMouseListener(ml);
		}
		else {
			this.addMouseListener(ml);
			repaint();
			this.addMouseMotionListener(new MouseMotionListener() {
				@Override
				public void mouseDragged(MouseEvent arg0) {}
				@Override
				public void mouseMoved(MouseEvent e) {
					int x = (int) Math.round((double)(e.getX()-beginX)/size);
					int y = (int) Math.round((double)(e.getY()-beginY)/size);
					if (x != xpos || y != ypos) {
						xpos = x;
						ypos = y;
						repaint();
					}	
				}				
			});
		}
	}
	
	
	
}
