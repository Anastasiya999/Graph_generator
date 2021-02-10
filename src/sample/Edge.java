package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class Edge {
    private Node a,b;
    private String value;
    public Edge(Node a, Node b, String value)
    {
        this.a=a;
        this.b=b;
        this.value=value;
    }
    public void draw(GraphicsContext gc)
    {
        double x,y;
        x= (a.getPosition().getX()  + b.getPosition().getX() )/2;
        y= (a.getPosition().getY()  + b.getPosition().getY() )/2;
        gc.setFont(Font.font("Calibri", FontPosture.ITALIC,14));
        gc.strokeText(""+value,(int) x,(int) y);


    }
    public String getValue()
    {
        return value;
    }


    public boolean Equals(Edge edge) {
        if(this==edge)return true;
        if(edge==null)return false;

        if(!edge.a.equals(a))return false;
        if(!edge.b.equals(b))return false;
        return true;

    }
}
