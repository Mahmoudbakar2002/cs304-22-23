package task01;

import javax.media.opengl.GLCanvas;
import javax.swing.*;
import java.awt.*;

public class MainFrameTask1 extends JFrame{


    public static void main(String[] args) {
        final MainFrameTask1 app = new MainFrameTask1();

        SwingUtilities.invokeLater (() -> app.setVisible(true));
    }

    public MainFrameTask1() {
        //set the JFrame title
        super("Task 1");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //only three JOGL lines of code ... and here they are
        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(new TaskGLEventListener());

        // add the GLCanvas just like we would	 any Component
        add(glcanvas, BorderLayout.CENTER);
        setSize(800, 600);

        //center the JFrame on the screen
        centerWindow();
    }

    public void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize  = this.getSize();

        if (frameSize.width  > screenSize.width ) frameSize.width  = screenSize.width;
        if (frameSize.height > screenSize.height) frameSize.height = screenSize.height;

        this.setLocation (
                (screenSize.width  - frameSize.width ) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }
}

