package task02;

import com.sun.opengl.util.FPSAnimator;
import task01.TaskGLEventListener;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class MainFrameTask2 extends JFrame{


    public static void main(String[] args) {
        final MainFrameTask2 app = new MainFrameTask2();

        SwingUtilities.invokeLater (() -> app.setVisible(true));
    }

    public MainFrameTask2() {
        //set the JFrame title
        super("Task 2");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(new GLEventListenerTask2());

        // Animator
        FPSAnimator animator =new FPSAnimator(glcanvas,60);
        animator.start();

        // add the GLCanvas just like we would	 any Component
        add(glcanvas, BorderLayout.CENTER);
        setSize(800, 600);

        //center the JFrame on the screen
        this.setLocationRelativeTo(null);

    }
}

