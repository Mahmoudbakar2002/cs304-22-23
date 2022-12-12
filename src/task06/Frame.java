package task06;

import com.sun.opengl.util.FPSAnimator;
import texture.myexample.ExampleFrame;
import texture.myexample.GLTextureEvent;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public static void main(String[] args) {
        final Frame app = new Frame();
        SwingUtilities.invokeLater (() -> app.setVisible(true));
    }
    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        Listener listner=new Listener();
        glcanvas.addGLEventListener(listner);
        glcanvas.addMouseListener(listner);

        // Animator
        FPSAnimator animator =new FPSAnimator(glcanvas,60);
        animator.start();

        // add the GLCanvas just like we would	 any Component
        add(glcanvas, BorderLayout.CENTER);
        setSize(900 , 500);

        //center the JFrame on the screen
        this.setLocationRelativeTo(null);
    }

}
