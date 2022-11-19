package keyboard;


import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import keyboard.components.KeyBoardPanel;
import keyboard.components.KeyBoardPanelKeyListner;


public class KeyBoardFrameTester extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JTextPane logPane;
	private KeyBoardPanel keyboard;
	private KeyBoardPanelKeyListner keyboardListner;
	public KeyBoardFrameTester() {
		setSize(1000,530);
		setLocationRelativeTo(null);
		setFocusTraversalKeysEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridLayout(2,1));
		
		logPane=new JTextPane();
		JScrollPane scroll=new JScrollPane(logPane);
		logPane.setEditable(false);
		logPane.setFocusable(false);
		logPane.setFocusTraversalKeysEnabled(false);
		add(scroll);
	
		
	      
		keyboard =new KeyBoardPanel();
		add(keyboard);
		
		keyboardListner=new KeyBoardPanelKeyListner(keyboard.getKeyList(),logPane,keyboard.getPanelLockStatue());
		addKeyListener(keyboardListner);
		
	}
}
