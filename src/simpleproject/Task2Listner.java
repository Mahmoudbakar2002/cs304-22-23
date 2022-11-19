package simpleproject;

import simpleproject.polygons.Polygon;
import simpleproject.polygons.PolygonState;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Task2Listner implements GLEventListener {



    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        gl.glViewport(0, 0, 600, 300);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-300, 300.0, -300.0, 300, -1.0, 1.0);

    }

    /**
     * Take care of drawing here.
     */
    int rotationAngel=0;

    double movingAngel=90 , xImoji=0,yImoji=0;

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//
//        gl.glColor3f(1.0f, 1.0f, 1.0f);
//        gl.glBegin(GL.GL_LINES);
//        gl.glVertex2i(-400,0);
//        gl.glVertex2i(400,0);
//        gl.glVertex2i(0,-450);
//        gl.glVertex2i(0,450);
//        gl.glEnd();


        int maxSide= 240;
//        movingR+=5;

//        double xMove= movingR * Math.cos( Math.toRadians(movingAngel))  +cx;
//        double yMove= movingR * Math.sin( Math.toRadians(movingAngel) ) +cy;
//        if(yMove>=maxSide || yMove<=-maxSide || xMove>=maxSide || xMove<=-maxSide) {
//            cx=xMove;
//            cy=yMove;
//            movingR=0;
//
//            Random ran=new Random();
//            if(yMove >=maxSide)movingAngel= ran.nextInt(179)+181;
//            else if(yMove <= -maxSide) movingAngel=ran.nextInt(179)+1;
//            else if(xMove >= maxSide) movingAngel=ran.nextInt(179)+91;
//            else if(xMove <= -maxSide) movingAngel=ran.nextInt(179)-89;
//
//        }
////        if(xMove>260) xMove=260;
        Random ran=new Random();
        xImoji+= 5 * Math.cos( Math.toRadians(movingAngel));
        yImoji+= 5 * Math.sin( Math.toRadians(movingAngel));
        if(xImoji>=maxSide) xImoji=maxSide;
        if(xImoji<= -maxSide) xImoji=-maxSide;

        if(yImoji>=maxSide) yImoji=maxSide;
        if(yImoji<= -maxSide) yImoji=-maxSide;


        if(yImoji>= maxSide) movingAngel=ran.nextInt(179)+181;
        else if(yImoji<= -maxSide) movingAngel=ran.nextInt(179)+1;
        else if(xImoji >=maxSide) movingAngel=ran.nextInt(179)+91;
        else if(xImoji <= -maxSide) movingAngel =ran.nextInt(179)-89;

        gl.glPushMatrix();
        gl.glTranslated(xImoji,yImoji,0);
        imoji(gl);
        gl.glPopMatrix();

//        if(yMove>= 260){
//            gl.glPushMatrix();
//            gl.glTranslated(xMove,260,0);
//            gl.glPopMatrix();
//
//        }


    }

    void imoji(GL gl){
        Polygon.builder()
                .setRadius(60)
                .setLocation(0,0)
                .setRotateAngel(0)
                .setNumberOfEdges(360)
                .setColor(Color.yellow)
                .setState(PolygonState.FILL)
                .build().draw(gl);

        {
            gl.glColor3f(0.0f, 0.0f, 0.0f);

            gl.glBegin(GL.GL_POLYGON);
            double angel = 180, rotateAngel = 180, plussingAngel = 1, radius = 40;

            while (angel <= rotateAngel + 180.0) {
                double x = radius * Math.cos(Math.toRadians(angel)) + 0;
                double y = radius * Math.sin(Math.toRadians(angel)) - 10;
                gl.glVertex2d(x, y);
                angel += plussingAngel;
            }
            gl.glEnd();
        }


        gl.glColor3f(1.0f,0f,0f);
        gl.glPushMatrix();
        gl.glTranslated(-20,25,0);

        gl.glRotated(rotationAngel,0,0,1);

        drawFillStar(gl,20,10,0,0,54,5);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslated(20,25,0);
        gl.glRotated(-rotationAngel,0,0,1);

        drawFillStar(gl,20,10,0,0,54,5);
        gl.glPopMatrix();


        rotationAngel+=2;
    }



    void drawStar(GL gl,int r, int x,int y,double rotateAngel){
        Point2D.Double points[]=new Point2D.Double[5];
        double plussing=360.0/5;
        int i=0;
        for (float angel=0 ;angel<360;angel+=plussing)
            points[i++]=new Point2D.Double(r*Math.cos(Math.toRadians(angel+rotateAngel))+x ,r*Math.sin(Math.toRadians(angel+rotateAngel))+y);

        int p=0;
        gl.glBegin(GL.GL_LINE_LOOP);
        do{
            gl.glVertex2d(points[p].x,points[p].y);
            p=(p+2)%5;
        }while(p!=0);
        gl.glEnd();

    }
    void drawFillStar(GL gl,int r0,int r1, int x,int y,double rotateAngel, int countOfLegs){
//		Point2D.Double points[]=new Point2D.Double[10];
        double plussing=360.0/(2*countOfLegs);

        double angel=rotateAngel;
        boolean hap=false;
        gl.glBegin(GL.GL_POLYGON);
        while (angel < rotateAngel+ 360.0){
            double xp=(hap?r0:r1)* Math.cos(Math.toRadians(angel))+ x;
            double yp=(hap?r0:r1)* Math.sin(Math.toRadians(angel))+ y;
            gl.glVertex2d(xp,yp);
            hap=!hap;
            angel+=plussing;
        }
        gl.glEnd();


    }

    /**
     * Called when the GLDrawable (GLCanvas
     * or GLJPanel) has changed in size. We
     * won't need this, but you may eventually
     * need it -- just not yet.
     */
    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height
    ) {}

    /**
     * If the display depth is changed while the
     * program is running this method is called.
     * Nowadays this doesn't happen much, unless
     * a programmer has his program do it.
     */
    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged
    ) {}

    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub

    }

}
