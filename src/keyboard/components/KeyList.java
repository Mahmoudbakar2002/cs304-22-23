package keyboard.components;

import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import javax.swing.JButton;

public class KeyList {
	private Map<KeyCodeReference , JButton> references;
	
	public KeyList() {
		references=new TreeMap<>();
	}
	
	
	public JButton getKey(KeyCodeReference kcr) {
		return references.get(kcr);
	}
	public JButton getKey(int keyCode,int keyLocation) {
		return getKey(new KeyCodeReference(keyCode, keyLocation));
	}
	public void addKey(KeyCodeReference kcr,JButton btn) {
		references.put(kcr, btn);
	}
	public void addKey(int keyCode,int keyLocation,JButton btn) {
		references.put(new KeyCodeReference(keyCode, keyLocation), btn);
	}
	public boolean containsKey(KeyCodeReference kcr) {
		return references.containsKey(kcr);
	}
	public boolean containsKey(int keyCode,int keyLocation) {
		return references.containsKey(new KeyCodeReference(keyCode, keyLocation));
	}
	
	
	public void setActions(BiConsumer<KeyCodeReference, String> actions) {
		references.forEach((a,b)-> b.addActionListener((event)->actions.accept(a, b.getText())));
//		actions.accept(a, b);
	}
	
	public void toUpperCase() {
		for(int k=KeyEvent.VK_A;k<=KeyEvent.VK_Z;k++) {
			JButton key=getKey(k,KeyEvent.KEY_LOCATION_STANDARD);
			if(key!=null)key.setText(key.getText().toUpperCase());
		}
	}
	public void toLowerCase() {
		for(int k=KeyEvent.VK_A;k<=KeyEvent.VK_Z;k++) {
			JButton key=getKey(k,KeyEvent.KEY_LOCATION_STANDARD);
			if(key!=null)key.setText(key.getText().toLowerCase());
		}
	}
	
	public void enableCapsLock(boolean state) {
		if(state)toUpperCase();
		else toLowerCase();
	}
	
	
	
}
