package keyboard.components;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Arrays;


public class KeyBoardPanel extends JPanel  {
	private static final long serialVersionUID = 1L;

	private JPanel  panelFunction,
					panelStandard,
					panelMiddle,
					panelKeyPad;
	private JPanel standardRows[];
	private PanelLockStatues panelLockStatues;
	private KeyList keyList;
//	private Map<Integer , JButton> lockKeys;
	
	
	
	
	public PanelLockStatues getPanelLockStatue() {return panelLockStatues;}
	public KeyList getKeyList(){return keyList;}
	
	
	public KeyBoardPanel() {
		panelFunction=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelStandard=new JPanel(new GridLayout(5, 1));
		panelMiddle=new JPanel(new GridLayout(2,1,0,40));
		panelKeyPad=new JPanel();
		panelLockStatues=new PanelLockStatues();
		standardRows=new JPanel[5];
		keyList=new KeyList();
		
//		lockKeys=new TreeMap<>();

		
		
		setFocusTraversalKeysEnabled(false);
		
		GridBagLayout gbLayout=new GridBagLayout();
		gbLayout.columnWidths=new int[] {0,0,0};
		gbLayout.rowHeights = new int[]{0, 0, 0, 0,0,0};
		setLayout(gbLayout);
		
		
		
		GridBagConstraints gbcPanelFunction=new GridBagConstraints();
		gbcPanelFunction.gridx=0;
		gbcPanelFunction.gridy=0;
		gbcPanelFunction.gridwidth=2;
		gbcPanelFunction.insets=new Insets(0, 0, 10, 0);
		add(panelFunction,gbcPanelFunction);
		
		
		GridBagConstraints gbcPanelStandard=new GridBagConstraints();
		gbcPanelStandard.gridx=0;
		gbcPanelStandard.gridy=1;
		gbcPanelStandard.fill=GridBagConstraints.BOTH;
		gbcPanelStandard.gridheight=5;
		gbcPanelStandard.insets=new Insets(0, 0, 0, 0);
		add(panelStandard,gbcPanelStandard);
		
		
		GridBagConstraints gbcPanelMiddle=new GridBagConstraints();
		gbcPanelMiddle.gridx=1;
		gbcPanelMiddle.gridy=1;
		gbcPanelMiddle.fill=GridBagConstraints.BOTH;
		gbcPanelMiddle.gridheight=5;
		gbcPanelMiddle.insets=new Insets(0, 0, 0, 0);
		add(panelMiddle,gbcPanelMiddle);
		
		GridBagConstraints gbcPanelKeyPad=new GridBagConstraints();
		gbcPanelKeyPad.gridx=2;
		gbcPanelKeyPad.gridy=1;
		gbcPanelKeyPad.fill=GridBagConstraints.BOTH;
		gbcPanelKeyPad.gridheight=5;
		gbcPanelKeyPad.insets=new Insets(0, 0, 0, 0);
		add(panelKeyPad,gbcPanelKeyPad);
		
		GridBagConstraints gbcPanelLockStatues=new GridBagConstraints();
		gbcPanelLockStatues.gridx=2;
		gbcPanelLockStatues.gridy=0;
		gbcPanelLockStatues.insets=new Insets(20, 20, 20, 20);
		gbcPanelLockStatues.fill=GridBagConstraints.BOTH;
		
		add(panelLockStatues,gbcPanelLockStatues);
		
		boolean isCapsLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
		boolean isNumLockOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_NUM_LOCK);

		panelLockStatues.setLockKeyState(KeyEvent.VK_CAPS_LOCK, isCapsLockOn);
		panelLockStatues.setLockKeyState(KeyEvent.VK_NUM_LOCK, isNumLockOn);
		keyList.enableCapsLock(isCapsLockOn);

		reloadFunctionButtons();
		reloadStandardButtons();
		reloadMiddleButtons();
		reloadKeyPadButtons();
		
	}
	
	private void reloadFunctionButtons() {
		/* in this function put buttons in function row**/
		
		
		// Escape Button
		JButton btnESC=new JButton("ESC");
		btnESC.setFocusTraversalKeysEnabled(false);
		btnESC.setFocusable(false);
		JPanel panelBtnEsc=new JPanel();
		panelBtnEsc.setBorder(new EmptyBorder(0, 0, 0, 20));
		panelBtnEsc.add(btnESC);
		panelFunction.add(panelBtnEsc);
		keyList.addKey(new KeyCodeReference(KeyEvent.VK_ESCAPE, KeyEvent.KEY_LOCATION_STANDARD), btnESC);
		btnESC.setFont(btnESC.getFont().deriveFont(9.0f));
		
		
		int functionCodes[]=   {KeyEvent.VK_F1,KeyEvent.VK_F2,KeyEvent.VK_F3,KeyEvent.VK_F4,KeyEvent.VK_F5,KeyEvent.VK_F6,KeyEvent.VK_F7,KeyEvent.VK_F8,KeyEvent.VK_F9,KeyEvent.VK_F10,KeyEvent.VK_F11,KeyEvent.VK_F12};

		for(int i=0;i<12;i++) {
			JButton btn=new JButton(KeyEvent.getKeyText(functionCodes[i]));
			btn.setFocusTraversalKeysEnabled(false);
			btn.setFocusable(false);
			btn.setFont(btn.getFont().deriveFont(9.0f));
			// add space after some buttons
			if((i+1)%4==0) {
				JPanel panel=new JPanel();
				panel.setBorder(new EmptyBorder(0, 0, 0, 5 ));
				panel.add(btn);
				panelFunction.add(panel);
			}else 
				panelFunction.add(btn);
			
			// save maping from code and location to button
			keyList.addKey(new KeyCodeReference(functionCodes[i], KeyEvent.KEY_LOCATION_STANDARD), btn);
				
		}
		
	}
	
	private void reloadStandardButtons() {
		
		
		// caps lock : (2,0)
		// shift lef : (3,0)
		// shift lef : (3,11)
		char standardRowsCharacters[][]= {
				{'`' ,'1','2','3','4','5','6','7','8','9','0','-','=','\b'},
				{'\t','q','w','e','r','t','y','u','i','o','p','[',']','\\'},
				{'-' ,'a','s','d','f','g','h','j','k','l',';','\'','\n'},
				{'-' ,'z','x','c','v','b','n','m',',','.','/','-'},
		};
		
		
		for(int i=0;i<4;i++) {
			standardRows[i]=new JPanel(new FlowLayout(FlowLayout.LEFT));
			panelStandard.add(standardRows[i]);
			
			for(int j=0;j<standardRowsCharacters[i].length;j++) {
				
				int keyCode= KeyEvent.getExtendedKeyCodeForChar(standardRowsCharacters[i][j]);
				int keyLocation = KeyEvent.KEY_LOCATION_STANDARD;
				if(i==2&& j==0) keyCode=KeyEvent.VK_CAPS_LOCK;
				else if(i==3&& j==0) {
					keyCode=KeyEvent.VK_SHIFT;
					keyLocation=KeyEvent.KEY_LOCATION_LEFT;
				}
				else if(i==3&& j==11) {
					keyCode=KeyEvent.VK_SHIFT;
					keyLocation=KeyEvent.KEY_LOCATION_RIGHT;
				}
				
				String text=standardRowsCharacters[i][j]+"";
				if(keyCode==KeyEvent.VK_SHIFT)
					text="  Shift ↑      ";
				else if(keyCode==KeyEvent.VK_TAB) {
					text=KeyEvent.getKeyText(keyCode);
				}else if(keyCode==KeyEvent.VK_ENTER)
					text="  Enter   ";
				else if(keyCode==KeyEvent.VK_CAPS_LOCK)
					text="CAPS";
				else if(keyCode==KeyEvent.VK_BACK_SPACE)
					text="←";
				
				
				JButton btn=new JButton(text);
				btn.setFocusTraversalKeysEnabled(false);
				btn.setFocusable(false);
//				btn.setFont(btn.getFont().deriveFont(13.0f));

				
				standardRows[i].add(btn);
				keyList.addKey(new KeyCodeReference(keyCode, keyLocation), btn);
				
			}
		}
		
		
		standardRows[4]=new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelStandard.add(standardRows[4]);
		
		int keyCodes[]= {KeyEvent.VK_CONTROL,0,KeyEvent.VK_WINDOWS,KeyEvent.VK_ALT,KeyEvent.VK_SPACE,KeyEvent.VK_ALT,0,0,KeyEvent.VK_CONTROL};
		
		char [] space=new char[60];
		Arrays.fill(space, ' ');
		String keyText[]= {"Ctrl  "," ","⸬","Alt  ",new String(space),
				"Alt  "," "," ","Ctrl  "};
		
		for(int i=0;i<keyCodes.length;i++) {
			int keyLocation = KeyEvent.KEY_LOCATION_STANDARD;
			if(i<4)keyLocation=KeyEvent.KEY_LOCATION_LEFT;
			if(i>4)keyLocation=KeyEvent.KEY_LOCATION_RIGHT;
			
			
			JButton btn=new JButton(keyText[i]);
			btn.setFocusTraversalKeysEnabled(false);
			btn.setFocusable(false);
			
			
			standardRows[4].add(btn);
			keyList.addKey(new KeyCodeReference(keyCodes[i], keyLocation), btn);
			
		}
		
		
	}
	
	private void reloadMiddleButtons() {
		JPanel panel=new JPanel(new GridLayout(2,3));
		panel.setBorder(new EmptyBorder(10, 5, 5, 5));
		panelMiddle.add(panel);
		
		int upperBtnCodes[]= {KeyEvent.VK_INSERT,KeyEvent.VK_HOME,KeyEvent.VK_PAGE_UP,KeyEvent.VK_DELETE,KeyEvent.VK_END,KeyEvent.VK_PAGE_DOWN};
		String upperBtntxt[]= {"Ins","Home","Pgu","Del","End","Pgd"};
		
		for (int i = 0; i < 6; i++) {
			JButton btn=new JButton(upperBtntxt[i]);
			btn.setFont(btn.getFont().deriveFont(8.0f));
			btn.setFocusTraversalKeysEnabled(false);
			btn.setFocusable(false);
			panel.add(btn);
			keyList.addKey(new KeyCodeReference(upperBtnCodes[i],KeyEvent.KEY_LOCATION_STANDARD), btn);
		}
		
		
		JPanel panelArrow=new JPanel(new GridLayout(2,1,0,0));
		panelMiddle.add(panelArrow);
		
		
		JPanel panelBtnUp=new JPanel();
		JButton btnArrows[]=new JButton[4];
		String txtArrow[]= {"↑","←","↓","→"};
		
		btnArrows[0]=new JButton(txtArrow[0]);
		btnArrows[0].setFocusTraversalKeysEnabled(false);
		btnArrows[0].setFocusable(false);
		panelBtnUp.add(btnArrows[0]);
		panelArrow.add(panelBtnUp);

		JPanel panelOtherArrows=new JPanel();
		panelArrow.add(panelOtherArrows);
		
		
		for(int i=1;i<4;i++) {
			btnArrows[i]=new JButton(txtArrow[i]);
			btnArrows[i].setFocusTraversalKeysEnabled(false);
			btnArrows[i].setFocusable(false);
			panelOtherArrows.add(btnArrows[i]);
		}
		
		int arrowCodes[]= {KeyEvent.VK_UP,KeyEvent.VK_LEFT,KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT};
		for(int i=0;i<4;i++)
			keyList.addKey(new KeyCodeReference(arrowCodes[i],KeyEvent.KEY_LOCATION_STANDARD), btnArrows[i]);
			
	}
	
	private void reloadKeyPadButtons() {
		GridBagLayout gbLayout=new GridBagLayout();
		gbLayout.columnWidths=new int[] {0,0,0,0};
		gbLayout.rowHeights = new int[]{0, 0, 0, 0,0};
		
		panelKeyPad.setLayout(gbLayout);
		
		
		int codesFirstRow[]= {KeyEvent.VK_NUM_LOCK,KeyEvent.VK_DIVIDE,KeyEvent.VK_MULTIPLY,KeyEvent.VK_SUBTRACT};
		String txtFirstRow[]= {"N","/","*","-"};
		
		for(int i=0;i<4;i++) {
			JButton btn=new JButton(txtFirstRow[i]);
			btn.setFocusTraversalKeysEnabled(false);
			btn.setFocusable(false);
			keyList.addKey(new KeyCodeReference(codesFirstRow[i], KeyEvent.KEY_LOCATION_NUMPAD), btn);
			
			GridBagConstraints gbcBtnNumLock=new GridBagConstraints();
			gbcBtnNumLock.gridx=i;
			gbcBtnNumLock.gridy=0;
			gbcBtnNumLock.insets=new Insets(0, 0, 20, 0);
			
			panelKeyPad.add(btn,gbcBtnNumLock);
		}
		
		for(int row=1,val=KeyEvent.VK_NUMPAD7 ;row<4;row++,val-=3) {
			
			for(int col=0;col<3;col++) {
				JButton btn=new JButton((val+col-KeyEvent.VK_NUMPAD0)+"");
				btn.setFocusTraversalKeysEnabled(false);
				btn.setFocusable(false);
				keyList.addKey(new KeyCodeReference(val+col, KeyEvent.KEY_LOCATION_NUMPAD), btn);
				
				GridBagConstraints gbcBtnNum=new GridBagConstraints();
				gbcBtnNum.gridx=col;
				gbcBtnNum.gridy=row;
				gbcBtnNum.insets=new Insets(0, 0, 10, 0);
				panelKeyPad.add(btn,gbcBtnNum);
			}
			
		}
		
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=3;
		gbc.gridy=1;
		gbc.insets=new Insets(0, 5, 10, 0);
		gbc.gridheight=2;
		gbc.fill=GridBagConstraints.VERTICAL;
		
		JButton btnADD=new JButton("+");
		btnADD.setFocusTraversalKeysEnabled(false);
		btnADD.setFocusable(false);
		keyList.addKey(new KeyCodeReference(KeyEvent.VK_ADD, KeyEvent.KEY_LOCATION_NUMPAD), btnADD);
		panelKeyPad.add(btnADD,gbc);
		
		
		JButton btnEnter=new JButton("☺");
		btnEnter.setFocusTraversalKeysEnabled(false);
		btnEnter.setFocusable(false);
		keyList.addKey(new KeyCodeReference(KeyEvent.VK_ENTER, KeyEvent.KEY_LOCATION_NUMPAD), btnEnter);
		gbc.gridx=3;
		gbc.gridy=3;
		gbc.insets=new Insets(0, 5, 0, 0);
		gbc.fill=GridBagConstraints.BOTH;
		panelKeyPad.add(btnEnter,gbc);
		
		
		JButton btnZero=new JButton("0");
		btnZero.setFocusTraversalKeysEnabled(false);
		btnZero.setFocusable(false);
		keyList.addKey(new KeyCodeReference(KeyEvent.VK_NUMPAD0, KeyEvent.KEY_LOCATION_NUMPAD), btnZero);
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth=2;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets=new Insets(0, 0, 0, 0);
		panelKeyPad.add(btnZero,gbc);
		
		JButton btnDot=new JButton(".");
		btnDot.setFocusTraversalKeysEnabled(false);
		btnDot.setFocusable(false);
		keyList.addKey(new KeyCodeReference(KeyEvent.VK_DECIMAL, KeyEvent.KEY_LOCATION_NUMPAD), btnDot);
		gbc.gridx=2;
		gbc.gridy=4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		panelKeyPad.add(btnDot,gbc);
		
		
		
	}
	
//	public static void main(String[] args) {
////		System.out.println(KeyEvent.getKeyText(KeyEvent.getExtendedKeyCodeForChar('\b')));
//		
//		JFrame frame=new JFrame();
//		frame.setFocusTraversalKeysEnabled(false);
//		KeyboardPanel panel=new KeyboardPanel();
//		frame.setSize(1000,400);
//		frame.addKeyListener(new KeyBoardPanelKeyListner(panel.references));
//		frame.getContentPane().add(panel,BorderLayout.CENTER);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//		
//	}
	

}
