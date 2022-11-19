package keyboard;



public class Main {

public static void main(String[] args){
		
		/* !!!!!  this is not important for you it's just simple code to 
		 * 			change style for frame   
		 * !!!!!! Please don't waste your time in it !!!!!!
		 * */
		try{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
                } 
				
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		KeyBoardFrameTester frame=new KeyBoardFrameTester();
		frame.setVisible(true);
		
		CompleteTaskFrame frame2=new CompleteTaskFrame();
		frame2.setVisible(true);
	}
}
