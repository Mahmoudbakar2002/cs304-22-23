package task06;

import com.bakar.assest.opengl.DrawableVertex;
import com.bakar.assest.opengl.texture.Image;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements GLEventListener, MouseListener {


    public static final double  minX=0,
            maxX=900,
            minY=0,
            maxY=500;



    private String [] names={"img1.png","img2.png","img3.png"};
    private Image images[]=new Image[3];
    private Image back;
    private int selectCard=-1;



    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        GLU glu= new GLU();
        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(minX, maxX, minY, maxY, -1.0, 1.0);

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);




        try{
            back=new Image("Assets//task5//back-card.png");
            back.loadInGl(gl,glu);
            back.setHeight(200);
            back.setWidth(200);

            for(int i=0,x=100;i<3;i++,x+=250){
                images[i]=new Image("Assets//task5//"+names[i]);
                images[i].loadInGl(gl,glu);
                images[i].setUseSizeRatio(true);
                images[i].setHeight(200);
//                images[i].setWidth(200);
                images[i].setY(150);
                images[i].setX(x);

            }
            images[0].setX(images[0].getX()-30);

        }catch(Exception ex){
            ex.printStackTrace();
        }


    }


    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);       //Clear The Screen And The Depth Buffer
        for(int i=0;i<3;i++)
            if(selectCard==i)
                images[i].draw(gl);
            else {
                back.setY(images[i].getY());
                back.setX(images[i].getX());
                if(i==0) back.setX(back.getX()+30);
                back.draw(gl);
            }
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
//        System.out.println(x+"--"+y);
        selectCard=-1;
        for(int i=0;i<3;i++){
            double centerX=images[i].getX()+(images[i].getWidth()/2.0);
            double centerY=images[i].getY()+(images[i].getHeight()/2.0);
            double raduis= images[i].getHeight()/2.0;

            double eq=(x-centerX)*(x-centerX) + (y-centerY)*(y-centerY);
//            System.out.println(eq);
            if(eq<=raduis*raduis )
                selectCard=i;
        }
//        System.out.println(selectCard);

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
