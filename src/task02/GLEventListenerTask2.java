package task02;

import com.bakar.assest.opengl.shapes.Polygon;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;

public class GLEventListenerTask2 implements GLEventListener {
    private double leftEyeAngel=90,
                   rightEyeAngel=90;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400.0, -300, 300, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);


        // draw fill circle for main face shape
        Polygon.builder(360,200)
                .setColor(Color.yellow)
                .setLocation(0,0)
                .setFilled(true)
                .build().draw(gl);


        // draw mouth
        Polygon.builder(360,70)
                .setColor(Color.BLACK)
                .setLocation(0,-100)
                .setFilled(true)
                .build().draw(gl);

        // draw left-eye
        Polygon.builder(360,60)
                .setColor(Color.white)
                .setLocation(100*Math.cos(Math.toRadians(135)),100*Math.sin(Math.toRadians(135)))
                .setFilled(true)
                .build().draw(gl);

        // draw right-eye
        Polygon.builder(360,60)
                .setColor(Color.white)
                .setLocation(100*Math.cos(Math.toRadians(45)),100*Math.sin(Math.toRadians(45)))
                .setFilled(true)
                .build().draw(gl);

        /***************************************************
         * Animation moving eye Part
         **************************************************/
        // left-eye animation
         gl.glPushMatrix();
        gl.glTranslated(100*Math.cos(Math.toRadians(135)),100*Math.sin(Math.toRadians(135)),0);
        gl.glRotated(leftEyeAngel,0,0,1);
        Polygon.builder(360,20)
                .setColor(Color.black)
                .setLocation(35,0)
                .setFilled(true)
                .build().draw(gl);

        gl.glPopMatrix();


        // right-eye animation
        gl.glPushMatrix();
        gl.glTranslated(100*Math.cos(Math.toRadians(45)),100*Math.sin(Math.toRadians(45)),0);
        gl.glRotated(rightEyeAngel,0,0,1);
        Polygon.builder(360,20)
                .setColor(Color.black)
                .setLocation(35,0)
                .setFilled(true)
                .build().draw(gl);

        gl.glPopMatrix();


        // Change angel for two eyes
        leftEyeAngel-=5;
        rightEyeAngel+=5;

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }
}
