package screen;

import gameEngine.*;

import javax.swing.*;

public class Screen {
	public Screen() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}			
		});
	}
}
