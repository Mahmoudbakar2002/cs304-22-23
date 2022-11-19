package keyboard;

import keyboard.components.KeyBoardPanel;
import keyboard.components.KeyBoardPanelKeyListner;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CompleteTaskFrame extends JFrame{
private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	private KeyBoardPanel keyboard;
	private KeyBoardPanelKeyListner keyboardListner;
	public CompleteTaskFrame() {
		
		keyboard =new KeyBoardPanel();
		keyboardListner=new KeyBoardPanelKeyListner(keyboard.getKeyList(),keyboard.getPanelLockStatue());
		textArea=new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(14.f));
		JScrollPane scroll=new JScrollPane(textArea);
		
		
		setSize(1000,550);
		setLocationRelativeTo(null);
		setFocusTraversalKeysEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridLayout(2,1));
		
		keyboard.getKeyList().setActions((kcr,text)->{
			boolean isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			boolean isNumLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK);
 
			String addToTextArea=null;
			if((kcr.getKeyCode() >= 0x2C && kcr.getKeyCode()<=0x6F && text.length()==1)) { 
					
					if(kcr.getKeyCode()>=0x60&&kcr.getKeyCode()<=0x69 && !isNumLockOn)return;
					
					addToTextArea= text;
					addToTextArea= isCapsLockOn?addToTextArea.toUpperCase():addToTextArea.toLowerCase();
//					textArea.setText(textArea.getText()+str);
					

			}				
			
			if(kcr.getKeyCode() == KeyEvent.VK_SPACE) 
				addToTextArea=" ";
			if(kcr.getKeyCode() == KeyEvent.VK_ENTER) 
				addToTextArea="\n";
			if(kcr.getKeyCode() == KeyEvent.VK_TAB) 
				addToTextArea="\t";
			if(kcr.getKeyCode() == 0xC0) 
				addToTextArea="`";
			if(kcr.getKeyCode() ==	KeyEvent.VK_BACK_SPACE) {
				int cur=textArea.getCaretPosition();
				String tx=textArea.getText().substring(0,Math.max(cur-1,0))+
						textArea.getText().substring(cur);

				textArea.setText(tx);
				textArea.setCaretPosition(Math.max(0, cur-1));
			} 
			
			if(kcr.getKeyCode() ==	KeyEvent.VK_DELETE) {
				int cur=textArea.getCaretPosition();
				String tx=textArea.getText().substring(0,Math.max(cur,0))+
						textArea.getText().substring(Math.min(cur+1,textArea.getText().length()));

				textArea.setText(tx);
				textArea.setCaretPosition(cur);
			} 
			
			if(kcr.getKeyCode()==KeyEvent.VK_RIGHT)
				textArea.setCaretPosition(Math.min(textArea.getText().length(), textArea.getCaretPosition()+1));
			if(kcr.getKeyCode()==KeyEvent.VK_LEFT)
				textArea.setCaretPosition(Math.max(0, textArea.getCaretPosition()-1));

			
			if(addToTextArea!=null) {
				textArea.replaceSelection("");
				textArea.insert(addToTextArea, textArea.getCaretPosition());	
			}
			
			if(kcr.getKeyCode()== KeyEvent.VK_CAPS_LOCK) {
				Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, !isCapsLockOn);
				keyboard.getPanelLockStatue().setLockKeyState(KeyEvent.VK_CAPS_LOCK, !isCapsLockOn);
				keyboard.getKeyList().enableCapsLock(!isCapsLockOn);
			}			
			if(kcr.getKeyCode()== KeyEvent.VK_NUM_LOCK) {
				Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_NUM_LOCK, !isNumLockOn);
				keyboard.getPanelLockStatue().setLockKeyState(KeyEvent.VK_NUM_LOCK, !isNumLockOn);
			}
			
		});
		
		textArea.addKeyListener(keyboardListner);
		
		add(scroll);
		add(keyboard);
		
		
		
		
	}
}
