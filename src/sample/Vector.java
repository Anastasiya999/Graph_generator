package sample;

public class Vector {
    private float x;
    private float y;

    public Vector(){
       this.x=0;
       this.y=0;
    }

    public Vector(float v, float v1) {
        this.x=v;
        this.y=v1;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }


    public boolean equals(Vector v) {
        return (v.getX()==x)&&(v.getY()==y);
    }
    public Vector subtract(Vector v)
    {

     return new Vector(x-v.getX(),y-v.getY());
    }
    public Vector add(Vector v)
    {
        this.x=x+v.getX();
        this.y=y+v.getY();
        return this;
    }
    public Vector unitVector()
    {
       if(length()!=0)
       {
           return new Vector(this.x/length(),this.y/length());
       }else
           return new Vector(0.000001f,0.0000001f);
    }
    public float length()
    {
        return (float)Math.sqrt(x*x+y*y);
    }
    public Vector scale(double a)
    {
        return new Vector((float)(x*a),(float)(y*a));
    }
    public float dot(Vector v)
    {
        return x*v.getX()+y*v.getY();
    }
    public double distance(Vector v)
    {
        float v1=this.x-v.getX();
        float v2=this.y-v.getY();
        return Math.sqrt(v1*v1+v2*v2);

    }
}
