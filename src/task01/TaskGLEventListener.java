package task01;

import com.bakar.assest.opengl.shapes.Polygon;
import com.bakar.assest.opengl.shapes.StarLines;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.geom.Point2D;

public class TaskGLEventListener implements GLEventListener {


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

//        gl.glViewport(0, 0, 600, 300);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400.0, -300, 300, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);


        // draw fill octagon shape
        Polygon.builder(8,100)
                .setColor(Color.CYAN)
                .setLocation(-250,0)
                .setFilled(true)
                .build().draw(gl);


        // draw fill hexagon shape
        Polygon.builder(6,100)
                .setColor(Color.magenta)
                .setLocation(250,0)
                .setFilled(true)
                .build().draw(gl);


        // draw un-fill circle shape
        Polygon.builder(360,100)
                .setColor(Color.green)
                .setLocation(0,0)
                .setFilled(false)
                .build().draw(gl);

        // draw star lines shape
        StarLines starLines =new StarLines(100,5,2,18,new Point2D.Double(0,0));
        starLines.draw(gl);


    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }
}
