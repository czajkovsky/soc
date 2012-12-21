package screen;

import gameEngine.Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	protected int boardWidth, boardHeight;
	final int size = 20;
	final int beginX = 100, beginY = 30;
	void createBoard(Controller controller) {
		Graphics g = this.getGraphics();
		g.setColor(Color.GRAY);
		g.drawRect(beginX+(boardWidth/2-1)*size, beginY-size, size, size);
		g.drawRect(beginX+(boardWidth/2)*size, beginY-size, size, size);
		g.drawRect(beginX+(boardWidth/2-1)*size, beginY+boardHeight*size, size, size);
		g.drawRect(beginX+(boardWidth/2)*size, beginY+boardHeight*size, size, size);
		for (int i=beginY; i<beginY+boardHeight*size; i+=size) {
			for (int j=beginX; j<beginX+boardWidth*size; j+=size) {	
				g.drawRect(j, i, size, size);
				for (int k=i-size; k<=i+size; k+=size) {
					for (int l = j-size; l<=j+size; l+=size) {
						int tmp = controller.getLink((j-beginX)/size,(i-beginY)/size,(l-beginX)/size,(k-beginY)/size);
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
	}
	GamePanel(final Controller controller) {
		boardWidth = 10;
		boardHeight = 8;
		this.setBackground(Color.BLACK);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX()+","+e.getY());
				createBoard(controller);
				int x = Math.round((e.getX()-beginX)/5);
				int y = Math.round((e.getY()-beginY)/5);
				//controller.makeMove(x, y);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}			
		});
		//createBoard();
	}
}
