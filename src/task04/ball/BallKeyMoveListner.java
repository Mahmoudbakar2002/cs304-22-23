package task04.ball;

import com.bakar.assest.opengl.DrawableVertex;
import com.bakar.assest.opengl.shapes.Circle;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BallKeyMoveListner implements KeyListener, GLEventListener {

    private static final int BALL_SIZE=20;
    private static final int SPEED_MOVING=5;
    private static final int SAFE_ZONE= BALL_SIZE/4;
    private static final int MAX_JUMP= 150;

    private int yState=0,xState=0;
    private int yPlussing=0,xPlussing=0;
    private int currentY=0,currentX=0;


    // array for save clicked it work in clockwise from up  : up->right....
    private boolean keyState[]=new boolean[4];

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-300, 300, -300, 300, -1.0, 1.0);

    }


    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        /********************************************************
         *        Regular drawing for circle and horizontal line
         ******************************************************/
        gl.glColor3f(1,1,1);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(-300,-BALL_SIZE);
            gl.glVertex2d(300,-BALL_SIZE);
        gl.glEnd();


        Circle.builder(BALL_SIZE)
                .setColor(Color.red)
                .setFilled(true)
                .setLocation(currentX,currentY)
                .build().draw(gl);

        /*******************************
         *         Moving Logic
         *******************************/

        // for up/down moving
        if(yState==1) {
            if ( yPlussing == 0)yPlussing = SPEED_MOVING;
            else if (yPlussing > 0 && currentY >= MAX_JUMP) yPlussing = -SPEED_MOVING;
            else if (yPlussing < 0 && currentY <= 0) yState =currentY =yPlussing = 0;
        }
        else if(yState==2){
            if(yPlussing==0) yPlussing = -SPEED_MOVING;
            else if(yPlussing<0 && currentY<=-MAX_JUMP) yPlussing=SPEED_MOVING;
            else if(yPlussing>0 && currentY>=0) yState=currentY=yPlussing=0;
        }

        // for left/right moving
        if(xState==1){
            if(xPlussing==0) xPlussing=SPEED_MOVING;
            else if(xPlussing>0 && currentX>=MAX_JUMP) xPlussing=-SPEED_MOVING;
            else if(xPlussing<0 && currentX<=0) xState=currentX=xPlussing=0;
        }
        else if(xState==2){
            if(xPlussing==0) xPlussing=-SPEED_MOVING;
            else if(xPlussing<0 && currentX<=-MAX_JUMP) xPlussing=SPEED_MOVING;
            else if(xPlussing>0 && currentX>=0) xState=currentX=xPlussing=0;
        }


        // Some Debugging Print
//            System.out.println("x:"+currentX+" - y:"+currentY);
//            System.out.println("xPlus:"+xPlussing+" - yPlus:"+yPlussing);
//            System.out.println("xstate:"+xState+" - ystate:"+yState);


        // update x and y location
        currentY+=yPlussing;
        currentX+=xPlussing;

        /** handling state if it moved in axis before than another axis
        *       => getting max distance from origin and (x,0) ,(0,y)
         *          then make x and y equal to max distance but in
         *          there direction move
         * */
        if(xState!=0&&yState!=0 && Math.abs(currentX) != Math.abs(currentY)) {
            int mx = Math.max(Math.abs(currentX), Math.abs(currentY));
            currentX= (xPlussing/SPEED_MOVING) * mx;
            currentY = (yPlussing/SPEED_MOVING) * mx;
            /*
                //debugging print
                System.out.println(mx);
                System.out.println(currentX);
                System.out.println(currentY);
                System.out.println("-------");
            */
        }



    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // return if ball is out safe zone
        if( currentX*currentX+currentY*currentY >= SAFE_ZONE  )
            return;
//        System.out.println("Pressed:"+KeyEvent.getKeyText(e.getKeyCode()));

        /**
         * saving button press in array :
         *      using this techince to handling stay pressing two buttons for time
        **/
        if(e.getKeyCode()==KeyEvent.VK_UP && yState==0 ) keyState[0]=true;
        if(e.getKeyCode()==KeyEvent.VK_RIGHT&& xState==0) keyState[1]=true;
        if(e.getKeyCode()==KeyEvent.VK_DOWN&& yState==0) keyState[2]=true;
        if(e.getKeyCode()==KeyEvent.VK_LEFT&& xState==0) keyState[3]=true;


        /* set state if any key is pressed */
        if(keyState[0]) yState=1;
        else if(keyState[2]) yState=2;
        if(keyState[1]) xState=1;
        else if(keyState[3]) xState=2;



    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("Release:"+KeyEvent.getKeyText(e.getKeyCode()));

        /* reset keys to un-press state if it released*/
        if(e.getKeyCode()==KeyEvent.VK_UP) keyState[0]=false;
        if(e.getKeyCode()==KeyEvent.VK_RIGHT) keyState[1]=false;
        if(e.getKeyCode()==KeyEvent.VK_DOWN) keyState[2]=false;
        if(e.getKeyCode()==KeyEvent.VK_LEFT) keyState[3]=false;

    }

}
