package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.util.ArrayList;
import java.util.LinkedList;

public class DirectedGraph extends GraphAbstr {

    private ArrayList<Node> nodes1;
    private GraphicsContext gc;

    public DirectedGraph(double w, double h, GraphicsContext gc) {
        super(w, h);
        this.gc=gc;
        nodes1=new ArrayList<>();

    }

    @Override
    public void addEdge(Node v1, Node v2,String value) {
        super.addEdge(v1, v2,value);
        float x,y;
        v1.addEdge(v2);
        nodes1.get(v1.getId()).addEdge( nodes1.get(v2.getId()));
        nodes1.get(v2.getId()).addEdge( nodes1.get(v1.getId()));
        gc.strokeLine(v1.getPosition().getX(),v1.getPosition().getY(),v2.getPosition().getX(),v2.getPosition().getY());
        x= (v1.getPosition().getX()  + v2.getPosition().getX() )/2;
        y= (v1.getPosition().getY()  + v2.getPosition().getY() )/2;
        gc.setFont(Font.font("Cambial", FontPosture.ITALIC,14));
        drawArrowLine(gc,(int)v1.getPosition().getX(),(int)v1.getPosition().getY(),(int)v2.getPosition().getX(),(int)v2.getPosition().getY(),8,8);
    }

    @Override
    public LinkedList<Node> getNeighbors(Node a) {
        return a.getNeighbors();
    }

    @Override
    public void addNode(Node vertex) {
        super.addNode(vertex);

        nodes1.add(new Node(vertex.getValue(),vertex.getPosition()));

      
        gc.setFill(Color.AQUAMARINE);
        gc.fillOval(vertex.getPosition().getX()-10,vertex.getPosition().getY()-10,20,20);
        System.out.println(vertex.getPosition().getX()+" , "+vertex.getPosition().getY());
        gc.strokeText(vertex.getValue(),vertex.getPosition().getX()-10,vertex.getPosition().getY()-10);
    }
    public void drawGraph()
    {
        Fruch_Rein_Method();
        for(int i=0;i<capacity;i++)
        {
            Node vertex=nodes.get(i);
            gc.setFill(Color.AQUAMARINE);
            gc.fillOval(vertex.getPosition().getX()-10,vertex.getPosition().getY()-10,20,20);
            gc.strokeText(vertex.getValue(),vertex.getPosition().getX()+10,vertex.getPosition().getY()+10);
            for (Node v:
                    nodes.get(i).getNeighbors()) {
                gc.strokeLine(nodes.get(i).getPosition().getX(),nodes.get(i).getPosition().getY(),v.getPosition().getX(),v.getPosition().getY());


            }
        }
        for(int i=0;i<capacity;i++)
        {
            Node vertex=nodes.get(i);
            for (Node v:
                    nodes.get(i).getNeighbors()) {
                drawArrowLine(gc,(int)nodes.get(i).getPosition().getX(),(int)nodes.get(i).getPosition().getY(),(int)v.getPosition().getX(),(int)v.getPosition().getY(),5,5);
            }

        }
        for (Edge e:edges
             ) {
            e.draw(gc);
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
    }
    private double f_a(Node x1, Node x2, double k)
    {
        double dist=x1.getPosition().distance(x2.getPosition());
        return (dist*dist)/k;
    }
    private double f_r(Node x1, Node x2, double k)
    {
        double dist=x1.getPosition().distance(x2.getPosition());
        return (k*k)/(dist+0.000001);
    }

    public void Fruch_Rein_Method()
    {
        double cH=220;
        double cW=270;
        if(capacity<15)
        {
            cH-=50;
            cW-=80;
        }
        double area=(canvasHeight-cH)*(canvasWidth-cW);
        double k=Math.sqrt(area/capacity);
        double temperature=36;
        for(int s=0;s<50;s++)
        {                               ///calculating the repulsive forces
            for(int j=0;j<capacity;j++)
            {
                Node v;
                 v= nodes1.get(j);
                //v=nodes.get(j);
                v.setDisplacement(new Vector(0,0));
                for(int i=0;i<capacity;i++ )
                {
                   // Node u=nodes.get(i);
                    Node  u=nodes1.get(i);
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
                //Node v=nodes.get(i);
                Node v=nodes1.get(i);
                for(int j=i+1;j<capacity;j++)
                {
                   // Node u=nodes.get(j);
                    Node u=nodes1.get(j);
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

                Node v=nodes1.get(i);
                Vector disp=v.getDisplacement();
                Vector newPosition=v.getPosition().add(disp.unitVector().scale((float)Math.min(disp.length(),temperature)));
                v.setPosition(newPosition.getX(),newPosition.getY());
                float x = (float) Math.min((canvasWidth)-30, Math.max(30, v.getPosition().getX()));
                float y = (float) Math.min((canvasHeight) -20, Math.max(20, v.getPosition().getY()));
                v.setPosition(x,y);

            }
            temperature *= 0.9;

        }
        for(int i=0;i<capacity;i++)
        {
            Node v=nodes.get(i);
            Vector pos=nodes1.get(i).getPosition();
            v.setPosition(pos.getX(),pos.getY());

        }


    }

    public Integer getDegreeOfNode(Node a) {
        Node node= getNodeByValue(a.getValue());
        return node.getDegree();
    }
    public Integer getInDegreeOfNode(Node a) {
        Integer count=0;
        Node b= getNodeByValue(a.getValue());
        for (Node node:nodes
             ) {
            if(!a.equals(node))
            {
                if(node.isNeighbour(a))count++;
            }
        }

        return count;
    }
}
