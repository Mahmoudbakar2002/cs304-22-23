package task03;

import com.sun.opengl.util.FPSAnimator;
import task02.GLEventListenerTask2;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class MainFrameTask3 extends JFrame{


    public static void main(String[] args) {
        final MainFrameTask3 app = new MainFrameTask3();

        SwingUtilities.invokeLater (() -> app.setVisible(true));
    }

    public MainFrameTask3() {
        //set the JFrame title
        super("Task 3");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(new GLEventListenerTask3());

        // Animator
        FPSAnimator animator =new FPSAnimator(glcanvas,60);
        animator.start();

        // add the GLCanvas just like we would	 any Component
        add(glcanvas, BorderLayout.CENTER);
        setSize(600, 600);

        //center the JFrame on the screen
        this.setLocationRelativeTo(null);

    }
}

