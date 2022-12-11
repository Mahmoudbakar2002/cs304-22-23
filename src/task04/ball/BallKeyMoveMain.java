package task04.ball;


import com.sun.opengl.util.FPSAnimator;
import task04.lines.MouseLinesEvent;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class BallKeyMoveMain extends JFrame {


    public static void main(String[] args) {
        final BallKeyMoveMain app = new BallKeyMoveMain();

        SwingUtilities.invokeLater (() -> app.setVisible(true));
    }

    protected BallKeyMoveMain(){
        //set the JFrame title
        super("Task 4 - Ball Moving");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        BallKeyMoveListner listner=new BallKeyMoveListner();
        glcanvas.addGLEventListener(listner);
        glcanvas.addKeyListener(listner);
        this.addKeyListener(listner);
        // Animator
        FPSAnimator animator =new FPSAnimator(glcanvas,60);
        animator.start();

        // add the GLCanvas just like we would	 any Component
        add(glcanvas, BorderLayout.CENTER);
        setSize(600, 600);

        //center the JFrame on the screen
        this.setLocationRelativeTo(null);
//        this.setAutoRequestFocus(false);
//        this.setAutoRequestFocus(false);

        setFocusTraversalKeysEnabled(false);

        glcanvas.requestFocus();


    }
}
