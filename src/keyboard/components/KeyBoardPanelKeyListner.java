package keyboard.components;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class KeyBoardPanelKeyListner implements KeyListener{

	private KeyList keyList;
	private PanelLockStatues panelLockStatues;
	
	private JTextPane logPane;
	private StyledDocument doc;
	private Style successfulStyle , wrongStyle;
	private String logFormat;
	private Color defaultBtnColor;
	
	public KeyBoardPanelKeyListner( KeyList keyList) {
		this.keyList=keyList;
		defaultBtnColor=new JButton().getBackground();
		logFormat="-%s key(code: %d , text : %s ,location : %s).\n";

	}

	public KeyBoardPanelKeyListner(KeyList keyList,JTextPane log) {
		this(keyList);
		
		this.logPane=log;
		
		doc = logPane.getStyledDocument();
		
		successfulStyle = logPane.addStyle("", null);
	    StyleConstants.setForeground(successfulStyle, Color.decode("#00aa00"));
	    
	    wrongStyle = logPane.addStyle("", null);
	    StyleConstants.setForeground(wrongStyle, Color.decode("#aa0000"));
	  	    
	}
	
	public KeyBoardPanelKeyListner( KeyList keyList,JTextPane log , PanelLockStatues panelLockStatues) {
		this(keyList,log);
		this.panelLockStatues=panelLockStatues;
	}
	public KeyBoardPanelKeyListner( KeyList keyList , PanelLockStatues panelLockStatues) {
		this(keyList);
		this.panelLockStatues=panelLockStatues;
	}
	private void writeLog(String log,Style style) {
		if(logPane==null)return;
		
		try {
			doc.insertString(doc.getLength(), log, style);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		String log=String.format(logFormat, "Pressed",
											e.getKeyCode(),
											KeyEvent.getKeyText(e.getKeyCode())
											,KeyCodeReference.locationToString(e.getKeyLocation())
											);			
		
		
		
		KeyCodeReference kcr=new KeyCodeReference(e.getKeyCode(), e.getKeyLocation());
		if(!keyList.containsKey(kcr)) {
			writeLog(log, wrongStyle);
			return;
		}
		
		writeLog(log, successfulStyle);
		JButton btn = keyList.getKey(kcr);
		btn.setBackground(Color.gray);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		String log=String.format(logFormat, "Released",
											e.getKeyCode(),
											KeyEvent.getKeyText(e.getKeyCode())
											,KeyCodeReference.locationToString(e.getKeyLocation())
											);			

		
			
		KeyCodeReference kcr=new KeyCodeReference(e.getKeyCode(), e.getKeyLocation());
		if(!keyList.containsKey(kcr)) {
			writeLog(log, wrongStyle);
			return;
		}
		
		writeLog(log, successfulStyle);
		JButton btn = keyList.getKey(kcr);
		btn.setBackground(defaultBtnColor);
		if(panelLockStatues!=null ) {
			if(kcr.getKeyCode()== KeyEvent.VK_CAPS_LOCK) {
				boolean isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
				panelLockStatues.setLockKeyState(KeyEvent.VK_CAPS_LOCK, isCapsLockOn);	
				keyList.enableCapsLock(isCapsLockOn);
			}
			if(kcr.getKeyCode()== KeyEvent.VK_NUM_LOCK) {
				boolean isNumLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK);
				panelLockStatues.setLockKeyState(KeyEvent.VK_NUM_LOCK, isNumLockOn);
			}
		}
	}

	
	
	
	

}
