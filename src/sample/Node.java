package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;

public class Node {
   private String value;
   private int id;
   private Vector position;
   private Vector displacement;
   private LinkedList<Node>adjList;

   public Node(){
       id=-1;
       position =new Vector(0,0);
       adjList=new LinkedList<>();
       value="";

    }
    public Node(String value, Vector v){
       this.value=value;
       position =v;
       id=-1;
       adjList=new LinkedList<>();
    }
    public void setId(int i)
    {
        this.id=i;
    }
    public int getId()
    {
        return id;
    }
    public Vector getPosition()
    {
        return this.position;
    }
    public void setPosition(float x, float y)
    {
       position.setX(x);
       position.setY(y);
    }
    public int getDegree()
    {
        return adjList.size();
    }
    public void addEdge(Node vertex)
    {
       adjList.add(vertex);
    }
    public void removeEdge(Node vertex)
    {
        adjList.remove(vertex);
    }
    public boolean isNeighbour(Node vertex)
    {
        return adjList.contains(vertex);
    }
    public void setDisplacement(Vector displacement)
    {
        this.displacement = displacement;
    }
    public Vector getDisplacement()
    {
        return this.displacement;
    }
    public String getValue()
    {
        return this.value;
    }
    public boolean Equals(Node vertex)
    {
       // &&(position.equals(vertex.getPosition()))&&
          //  (displacement.equals(vertex.getDisplacement()))
        return (value.equals(vertex.getValue()))&&(id==vertex.getId());
    }
    void draw(GraphicsContext gc)
    {
        gc.setFill(Color.AQUAMARINE);
        gc.fillOval((int)(getDisplacement().getX()-10),(int)(getDisplacement().getY()-10),20,20);
        gc.strokeText(value,(int)(getDisplacement().getX()-10),(int)(getDisplacement().getY()-10));

    }
    public LinkedList<Node> getNeighbors()
    {
        return adjList;
    }


}
