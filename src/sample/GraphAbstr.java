package sample;

import com.sun.prism.MaskTextureGraphics;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class GraphAbstr {
    protected int capacity=0;
    protected ArrayList<Node> nodes;
    protected ArrayList<Edge>edges;
    protected double canvasWidth;
    protected double canvasHeight;

    private final int ARR_SIZE = 8;
    public GraphAbstr(double w, double h)
    {
        nodes=new ArrayList<>();
        edges=new ArrayList<>();
        canvasHeight=h;
        canvasWidth=w;


    }
    public int getCapacity()
    {
        return capacity;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }
    public void addNode(Node vertex)
    {

        nodes.add(vertex);
        vertex.setId(capacity++);

    }
    public void addEdge(Node v1, Node v2,String value)
    {


        if(!isContainEdge(new Edge(v1,v2,value)))edges.add(new Edge(v1,v2,value));
        else
        {
            System.out.println("taka krawedz juz istnieje");
        }

    }

    public Node getNodeByValue(String value)
    {

        for(int i=0;i<capacity;i++)
        {
            if(nodes.get(i).getValue().equals(value))return nodes.get(i);
        }

        return new Node("",new Vector(0,0));
    }
    private boolean isContainEdge(Edge e)
    {
        for (int i=0;i<edges.size();i++)
        {
            if(edges.get(i).Equals(e))return true;
        }
        return false;
    }

    public abstract LinkedList<Node> getNeighbors(Node a);
    /*private double f_a(Node x1, Node x2, double k)
    {
        double dist=x1.getPosition().distance(x2.getPosition());
        return (dist*dist)/k;
    }
    private double f_r(Node x1, Node x2, double k)
    {
        double dist=x1.getPosition().distance(x2.getPosition());
        return (k*k)/(dist+0.000001);
    }*/
    /*
    public void BarycentricMethod()
    {
        double area=(canvasHeight-150)*(canvasWidth-150);
        double k=Math.sqrt(area/capacity);
        double temperature=37;
        for(int s=0;s<790;s++)
        {                               ///calculating the repulsive forces
            for(int j=0;j<capacity;j++)
            {
                Node v;
                v=nodes.get(j);
                v.setDisplacement(new Vector(0,0));
                for(int i=0;i<capacity;i++ )
                {
                    Node u=nodes.get(i);
                    if(!u.equals(v))
                    {
                        Vector delta=v.getPosition().subtract(u.getPosition()) ;
                        double fR = f_r(u,v,k);
                        v.setDisplacement(v.getDisplacement().add(delta.unitVector().scale(fR)));
                        if(Double.isNaN(v.getDisplacement().getX())){
                            System.out.println("something went wrong");
                            return;
                        }

                    }
                }
            }
            ///calculating the attractive forces
            for(int i=0;i<capacity;i++)
            {
                Node v=nodes.get(i);
                for(int j=i+1;j<capacity;j++)
                {
                    Node u=nodes.get(j);
                    if(v.isNeighbour(u))
                    {
                        Vector delta=v.getPosition().subtract(u.getPosition());
                        double fA=f_a(u,v,k);///
                        v.setDisplacement(v.getDisplacement().subtract(delta.unitVector().scale(fA)));
                        u.setDisplacement(u.getDisplacement().add(delta.unitVector().scale(fA)));
                    }
                }
            }
            ///preventing from displacement outside frame
            for(int i=0;i<capacity;i++)
            {
                Node v=nodes.get(i);
                Vector disp=v.getDisplacement();
                Vector newPosition=v.getPosition().add(disp.unitVector().scale((float)Math.min(disp.length(),temperature)));
                int x1= ThreadLocalRandom.current().nextInt((int)canvasWidth-40,(int)canvasWidth-19);
                int x2= ThreadLocalRandom.current().nextInt((int)20,(int)40);
                int y1= ThreadLocalRandom.current().nextInt((int)canvasHeight-40,(int)canvasHeight-19);
                int y2= ThreadLocalRandom.current().nextInt((int)20,(int)40);
                v.setPosition(newPosition.getX(),newPosition.getY());
                //float x = (float)Math.min(x1, Math.max(x2,v.getPosition().getX()));
                // float y = (float)Math.min(y1, Math.max(y2, v.getPosition().getY()));
                float x = (float)Math.min(canvasWidth-20, Math.max(0+20,v.getPosition().getX()));
                float y = (float)Math.min(canvasHeight-20, Math.max(0+20, v.getPosition().getY()));
                v.setPosition(x,y);

            }
            temperature *= 0.9;

        }
        double xmin=50;
        double ymin=50;
        double xmax=410;
        double ymax=310;
        for(int i=0;i<capacity;i++)
        {
            Vector v= nodes.get(i).getPosition();
            if(v.getX()<xmin||v.getX()>xmax||v.getY()<ymin||v.getY()>ymax)
            {
                for(int j=0;j<capacity;j++)
                {
                    Vector u= nodes.get(j).getPosition();
                    if(u.getX()<xmin||u.getX()>xmax||u.getY()<ymin||u.getY()>ymax)
                    {
                        if((Math.abs(v.getX()-u.getX())<10)&&(Math.abs(v.getY()-u.getY())>10))
                        {
                            nodes.get(i).setPosition(v.getX()-10,v.getY());
                        }
                        if((Math.abs(v.getY()-u.getY())<10)&&(Math.abs(v.getX()-u.getX())>10))
                        {
                            nodes.get(i).setPosition(v.getX(),v.getY()-10);
                        }
                    }

                }
            }


        }

    }*/
    /*
    public void drawGraph2()
    {
        for(int i=0;i<capacity;i++)
        {
            nodes.get(i).callForce(nodes);
            nodes.get(i).callEdgeForce();
            nodes.get(i).move();
        }
        Scale();
        //centroid
        Vector centoid = nodes.get(0).getCentroid(nodes);
        Vector temp = (new Vector(230,180)).subtract(centoid);
        for(int j=0;j< capacity;j++)
        {
            nodes.get(j).setDisplacement(nodes.get(j).getDisplacement().add(temp));
        }
        for(int i=0;i<capacity;i++)
        {
            nodes.get(i).draw(gc);
        }
    }
    public void Scale()
    {
        double XMin= Integer.MAX_VALUE;
        double YMin= Integer.MAX_VALUE;
        double XMax= Integer.MIN_VALUE;
        double YMax= Integer.MIN_VALUE;

        for(int j=0;j< capacity;j++)
        {
            if(nodes.get(j).getPosition().getX() < XMin)
            {
                XMin=nodes.get(j).getPosition().getX();
            }
            if(nodes.get(j).getPosition().getY() < YMin)
            {
                YMin=nodes.get(j).getPosition().getY();
            }
            if(nodes.get(j).getPosition().getX() > XMax)
            {
                XMax=nodes.get(j).getPosition().getX();
            }
            if(nodes.get(j).getPosition().getY() > YMax)
            {
                YMax=nodes.get(j).getPosition().getY();
            }
        }

        double length_x = XMax- XMin;
        double length_y = YMax- YMin;
        double length  = Math.max(length_x, length_y);
        for(int j=0;j< capacity;j++)
        {
            Vector vv = nodes.get(j).getPosition();
            vv= vv.scale(200.0 / length);
            nodes.get(j).setDisplacement(vv);
        }



    }
    private void drawArrowLine(GraphicsContext g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        double[] xpoints = {x2, (int) xm, (int) xn};
        double[] ypoints = {y2, (int) ym, (int) yn};
        g.setFill(Color.BLACK);
        g.fillPolygon(xpoints, ypoints, 3);
    }*/

}
