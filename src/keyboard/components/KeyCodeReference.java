package keyboard.components;

import java.awt.event.KeyEvent;

public class KeyCodeReference implements Comparable<KeyCodeReference> {
	private final int keyCode;
	private final int keyLocation;
	
	public KeyCodeReference(int keyCode,int keyLocation) {
		this.keyCode=keyCode;
		this.keyLocation=keyLocation;
	}
	


	@Override
	public int compareTo(KeyCodeReference o) {
		int ret=Integer.compare(keyCode,o.keyCode);
		if(ret==0)
			return Integer.compare(keyLocation, o.keyLocation);
		
		return ret;
	}
	
	
	public int getKeyCode() {
		return keyCode;
	}



	public int getKeyLocation() {
		return keyLocation;
	}
	public static String locationToString(int location) {
		switch (location) {
		case KeyEvent.KEY_LOCATION_LEFT:   return "left";
		case KeyEvent.KEY_LOCATION_NUMPAD: return "numpad";
		case KeyEvent.KEY_LOCATION_RIGHT: return "right";
		case KeyEvent.KEY_LOCATION_STANDARD: return "standard";
		default:return "unkown";
		}
	}
	public String getLocationName() {
		return locationToString(keyLocation);
	}
}
