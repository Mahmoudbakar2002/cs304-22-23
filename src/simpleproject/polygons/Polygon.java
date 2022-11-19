package simpleproject.polygons;


import javax.media.opengl.GL;
import java.awt.*;
import java.awt.geom.Point2D;

public class Polygon {
    private double radius;
    private Point2D.Float location;
    private double rotateAngel;
    private int numberOfEdges;
    private Color color;
    private PolygonState state;

    private static PolygonBuilder builder;
    static {builder =new PolygonBuilder();}
    public static PolygonBuilder builder(){return builder;}

    private Polygon() {
        this.radius = 1;
        this.location = new Point2D.Float(0f,0f);
        this.rotateAngel = 0;
        this.numberOfEdges = 4;
        this.color = Color.white;
        this.state=PolygonState.FILL;
    }

    public void draw(GL gl){
        double angel = rotateAngel;
        double plussingAngel = 360.0/ numberOfEdges;

        gl.glColor3fv(color.getColorComponents(null),0);

        if(state .equals( PolygonState.NOT_FILL))
            gl.glBegin(GL.GL_LINE_LOOP);
        else
            gl.glBegin(GL.GL_POLYGON);

        while (angel <= rotateAngel+ 360.0){
            double x=radius* Math.cos(Math.toRadians(angel))+ location.x;
            double y=radius* Math.sin(Math.toRadians(angel))+ location.y;
            gl.glVertex2d(x,y);
            angel+=plussingAngel;
        }
        gl.glEnd();
    }

    public static class PolygonBuilder{
        private Polygon p;

        public PolygonBuilder() {
            this.p = new Polygon();
        }

        public PolygonBuilder setRadius(double radius) {
            this.p.radius = radius;
            return this;
        }


        public PolygonBuilder setLocation(Point2D.Float location) {
            this.p.location = location;
            return this;
        }
        public PolygonBuilder setLocation(float x, float y) {
            this.p.location = new Point2D.Float(x,y);
            return this;
        }

        public PolygonBuilder setRotateAngel(double rotateAngel) {
            this.p.rotateAngel = rotateAngel;
            return this;
        }

        public PolygonBuilder setNumberOfEdges(int numberOfEdges) {
            this.p.numberOfEdges = numberOfEdges;
            return this;
        }

        public PolygonBuilder setColor(Color color) {
            this.p.color = color;
            return this;
        }
        public PolygonBuilder setState(PolygonState state) {
            this.p.state = state;
            return this;
        }

        public Polygon build(){return p;}

    }



}
