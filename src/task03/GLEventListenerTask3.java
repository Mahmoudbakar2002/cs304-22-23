package task03;

import com.bakar.assest.opengl.DrawableCollection;
import com.bakar.assest.opengl.shapes.Circle;
import com.bakar.assest.opengl.shapes.StarPolygon;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class GLEventListenerTask3 implements GLEventListener {
    private double leftEyeAngel=-18,
                   rightEyeAngel=-18;

    private double movingAngel=-90,imojiX=0,imojiY=0;

    private final static double MAX_SIDE=250;
    private final static Random ran=new Random();
    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-300, 300.0, -300, 300, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        DrawableCollection collection=new DrawableCollection();

        // Create Collection for Imoji Shape
        collection.addObject(Circle.builder(50)
                                .setColor(Color.yellow)
                                .setLocation(0,0)
                                .setFilled(true)
                                .build());

        collection.addObject(Circle.builder(25)
                                .setColor(Color.BLACK)
                                .setLocation(0,-15)
                                .setFilled(true)
                                .setVisibleSlicePart(180)
                                .setRotateAngel(180)
                                .build());

        // draw left-eye
        collection.addObject( StarPolygon.builder(5,7,15)
                                        .setColor(Color.black)
                                        .setLocation(25*Math.cos(Math.toRadians(135)),25*Math.sin(Math.toRadians(135)))
                                        .setFilled(true)
                                        .setRotateAngel(leftEyeAngel)
                                        .build());

        // draw right-eye
        collection.addObject(StarPolygon.builder(5,7,15)
                                    .setColor(Color.black)
                                    .setLocation(25*Math.cos(Math.toRadians(45)),25*Math.sin(Math.toRadians(45)))
                                    .setFilled(true)
                                    .setRotateAngel(rightEyeAngel)
                                    .build());

        // Change angel for two eyes
        leftEyeAngel-=3;
        rightEyeAngel+=3;

        collection.setLocation(new Point2D.Double(imojiX,imojiY));
        collection.draw(gl);

        imojiX+= 5 * Math.cos(Math.toRadians(movingAngel));
        imojiY+= 5 * Math.sin(Math.toRadians(movingAngel));




        if(imojiX<= -MAX_SIDE) {
            imojiX=-MAX_SIDE;
            movingAngel=randomExclusive(-90,90);
        }
        if(imojiX>=MAX_SIDE) {
            imojiX=MAX_SIDE;
            movingAngel=randomExclusive(90,270);
        }
        if(imojiY<= -MAX_SIDE) {
            imojiY=-MAX_SIDE;
            movingAngel=randomExclusive(0,180);
        }
        if(imojiY>= MAX_SIDE) {
            imojiY=MAX_SIDE;
            movingAngel=randomExclusive(180,360);
        }

    }
    private int randomExclusive(int start,int end){
        return ran.nextInt(end-start-1)+start+1;
    }


    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }
}
