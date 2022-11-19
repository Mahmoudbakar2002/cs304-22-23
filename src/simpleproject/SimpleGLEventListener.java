package simpleproject;

import simpleproject.polygons.Polygon;
import simpleproject.polygons.PolygonState;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.*;
import java.awt.geom.Point2D;

public class SimpleGLEventListener implements GLEventListener {



  /**
   * Take care of initialization here.
   */
  public void init(GLAutoDrawable drawable) {
	  	GL gl = drawable.getGL();

	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	    
	    gl.glViewport(0, 0, 600, 300);
	    gl.glMatrixMode(GL.GL_PROJECTION);
	    gl.glLoadIdentity();
	    gl.glOrtho(-400, 400.0, -400.0, 400, -1.0, 1.0);

  }
	
  /**
   * Take care of drawing here.
   */
  int angle=45,rightAngle=45;
  public void display(GLAutoDrawable drawable) {
	  GL gl = drawable.getGL();
	  gl.glClear(GL.GL_COLOR_BUFFER_BIT);

	  gl.glColor3f(1.0f, 1.0f, 1.0f);
	  	gl.glBegin(GL.GL_LINES);
	  	gl.glVertex2i(-400,0);
	  	gl.glVertex2i(400,0);
	  	gl.glVertex2i(0,-450);
	  	gl.glVertex2i(0,450);
	  gl.glEnd();


//	  com.bakar.lib.graphic.shapes.Polygon
//			  .builder(5,200)
//			  .setRotateAngel(18)
//			  .setColor(Color.red)
//			  .setFilled(true)
//			  .setLocation(new Point2D.Double(200,20))
//			  .build().draw(gl);
//	  com.bakar.lib.graphic.shapes.StarPolygon
//			  .builder(7,50,200)
//			  .setRotateAngel(18)
//			  .setColor(Color.red)
//			  .setFilled(true)
//			  .setLocation(new Point2D.Double(200,20))
//			  .build().draw(gl);



//	  new com.bakar.lib.graphic.shapes.StarLines(100,5,2).draw(gl);





//	  gl.glColor3f(1.0f, 0.0f, 0.0f);

//
	  Polygon.builder()
			  .setRadius(200)
			  .setLocation(0,0)
			  .setRotateAngel(0)
			  .setNumberOfEdges(360)
			  .setColor(Color.yellow)
			  .setState(PolygonState.FILL)
			  .build().draw(gl);

	  Polygon.builder()
			  .setRadius(60)
			  .setLocation(0,-100)
			  .setRotateAngel(0)
			  .setNumberOfEdges(360)
			  .setColor(Color.BLACK)
			  .setState(PolygonState.FILL)
			  .build().draw(gl);

	  Polygon.builder()
			  .setRadius(50)
			  .setLocation(80,80)
			  .setRotateAngel(0)
			  .setNumberOfEdges(360)
			  .setColor(Color.WHITE)
			  .setState(PolygonState.FILL)
			  .build().draw(gl);
//
	  Polygon.builder()
			  .setRadius(50)
			  .setLocation(-80,80)
			  .setRotateAngel(0)
			  .setNumberOfEdges(360)
			  .setColor(Color.WHITE)
			  .setState(PolygonState.FILL)
			  .build().draw(gl);
//
//	  gl.glPushMatrix();
//	  gl.glTranslated(-80,80,0);
//	  gl.glRotatef(angle,0,0,1);
//	  angle+=3;
//	  Polygon.builder()
//			  .setRadius(20)
//			  .setLocation(20,20)
//			  .setRotateAngel(0)
//			  .setNumberOfEdges(360)
//			  .setColor(Color.black)
//			  .setState(PolygonState.FILL)
//			  .build().draw(gl);
//
//	  gl.glPopMatrix();
//
//
//	  gl.glPushMatrix();
//	  gl.glTranslated(80,80,0);
//	  gl.glRotatef(rightAngle,0,0,1);
//	  rightAngle-=3;
//
//	  Polygon.builder()
//			  .setRadius(20)
//			  .setLocation(20,20)
//			  .setRotateAngel(0)
//			  .setNumberOfEdges(360)
//			  .setColor(Color.black)
//			  .setState(PolygonState.FILL)
//			  .build().draw(gl);
//
//	  gl.glPopMatrix();
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
