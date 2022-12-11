package task04.lines;

import com.bakar.assest.opengl.DrawableVertex;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseLinesEvent implements GLEventListener, MouseListener {
    public static final double  minX=-300,
                                maxX=300,
                                minY=-300,
                                maxY=300;

    ArrayList<DrawableVertex> vertices=new ArrayList<>();
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(minX, maxX, minY, maxY, -1.0, 1.0);

        System.out.println("--------INIT-----");
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glPointSize(5.0f);
        gl.glColor3f(0,0,1);
        gl.glBegin(GL.GL_POINTS);
        vertices.forEach(vertex->{
            gl.glVertex2d(vertex.getX(),vertex.getY());
        });
        gl.glEnd();

        gl.glColor3f(1,0,1);

        gl.glBegin(GL.GL_LINES);
        vertices.forEach(vertex->{
            gl.glVertex2d(vertex.getX(),vertex.getY());
        });
        gl.glEnd();



    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        double x=e.getX();
        double y=e.getY();
        x/= e.getComponent().getWidth();
        y/= e.getComponent().getHeight();

        x= (x*Math.abs(maxX-minX))+minX;
        y= maxY-(y*Math.abs(maxY-minY));
        vertices.add(new DrawableVertex(x,y));
        System.out.println(x+","+y);
        e.getComponent().repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
