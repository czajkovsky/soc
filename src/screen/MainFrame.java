package screen;

import gameEngine.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import main.Main;

public class MainFrame extends JFrame {	
	private final String[] players = {"Human", "Computer"};
	private JTextField txth, txtw;
	
	GamePanel pnbot;
	
	MainFrame() {		
		super("Paper Soccer");
		this.setSize(960, 640);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.setLayout(new GridLayout(2,1));
		
		pnbot = new GamePanel();
		
		JPanel pntop = new JPanel();
		pntop.setLayout(new GridLayout(5,1));
		
		pntop.add(new JPanel());
		
		JPanel pnl1 = new JPanel();
		pnl1.setLayout(new FlowLayout());
		pnl1.add(new JLabel("Select players"));
		JComboBox cb1 = new JComboBox();
		JComboBox cb2 = new JComboBox();
		for (int i=0; i<2; i++) {
			cb1.addItem(players[i]);
			cb2.addItem(players[i]);
		}
		pnl1.add(cb1);
		pnl1.add(new JLabel("vs"));
		pnl1.add(cb2);
		pntop.add(pnl1);
		
		JPanel pnl2 = new JPanel();
		pnl2.setLayout(new FlowLayout());
		pnl2.add(new JLabel("Enter height"));
		txth = new JTextField(8);
		txth.setText("10");
		pnl2.add(txth);
		pnl2.add(new JLabel("and width"));
		txtw = new JTextField(8);
		txtw.setText("8");
		pnl2.add(txtw);
		pntop.add(pnl2);		
		
		pntop.add(new JPanel());
		
		JButton but1 = new JButton("Play");
		but1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Start");
				//
				Main.controller.start(pnbot.boardWidth, pnbot.boardHeight, 0, 0);
			}			
		});
		
		JButton but2 = new JButton("Stop");
		
		JButton but3 = new JButton("Undo");
		JButton but4 = new JButton("Exit");
		JPanel pnl4 = new JPanel();
		pnl4.setLayout(new FlowLayout());
		pnl4.add(but1);
		pnl4.add(but2);
		pnl4.add(but3);
		pnl4.add(but4);
		pntop.add(pnl4);
		but4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "Exit button pressed.", "Exit", JOptionPane.WARNING_MESSAGE);
			}			
		});
		but2.setEnabled(false);
		but3.setEnabled(false);
		
		this.add(pntop);
		
		
		this.add(pnbot);
	}
}
