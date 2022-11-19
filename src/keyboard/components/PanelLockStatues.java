package keyboard.components;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelLockStatues extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private ImageIcon redLedImg,greenLedImg;
	private JLabel capsLockLight,numLockLight;
	
	public PanelLockStatues() {
		redLedImg= new ImageIcon(this.getClass().getResource("/keyboard/components/images/ledRed.png"));
		redLedImg =new ImageIcon(redLedImg.getImage().getScaledInstance(10, 10 ,Image.SCALE_SMOOTH));

		greenLedImg= new ImageIcon(this.getClass().getResource("/keyboard/components/images/ledGreen.png"));
		greenLedImg =new ImageIcon(greenLedImg.getImage().getScaledInstance(10, 10 ,Image.SCALE_SMOOTH));
		
		capsLockLight=new JLabel("CapsLock");
		capsLockLight.setFont(getFont().deriveFont(9.0f));
		add(capsLockLight);
		
		numLockLight=new JLabel("NumLock");
		numLockLight.setFont(getFont().deriveFont(9.0f));
		add(numLockLight);
	}
	
	public void setLockKeyState(int code,boolean state) {
		if(code == KeyEvent.VK_CAPS_LOCK) {
			if(state) capsLockLight.setIcon(greenLedImg);
			else capsLockLight.setIcon(redLedImg);
		}
		if(code == KeyEvent.VK_NUM_LOCK) {
			if(state) numLockLight.setIcon(greenLedImg);
			else numLockLight.setIcon(redLedImg);
		}
	}

}
