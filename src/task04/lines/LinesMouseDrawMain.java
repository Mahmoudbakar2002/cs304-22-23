package task04.lines;


import com.sun.opengl.util.FPSAnimator;
import task03.GLEventListenerTask3;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class LinesMouseDrawMain extends JFrame {


    public static void main(String[] args) {
        final LinesMouseDrawMain app = new LinesMouseDrawMain();

        SwingUtilities.invokeLater (() -> app.setVisible(true));
    }

    protected LinesMouseDrawMain(){
        //set the JFrame title
        super("Task 4 - Lines");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        MouseLinesEvent listner=new MouseLinesEvent();
        glcanvas.addGLEventListener(listner);
        glcanvas.addMouseListener(listner);

        // Animator
//        FPSAnimator animator =new FPSAnimator(glcanvas,60);
//        animator.start();

        // add the GLCanvas just like we would	 any Component
        add(glcanvas, BorderLayout.CENTER);
        setSize(600, 600);

        //center the JFrame on the screen
        this.setLocationRelativeTo(null);

    }
}
