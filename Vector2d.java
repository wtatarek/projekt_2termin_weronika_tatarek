package oop;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int hashCode(){
        int hash=13;
        hash+= this.x*31;
        hash+=this.y*17;
        return hash;
    }

    public String toString(){
        return "("+x+","+y+")";
    }

    public int getX() { return x;}
    public int getY() { return y;}

    public boolean equals(Object other){
        if(this==other) // czy jest tym wektorem
            return true;
        if(!(other instanceof Vector2d)) // czy jest wektorem
            return false;
        Vector2d that = (Vector2d) other;
        if( this.x == that.x && this.y == that.y)
            return true;
        /*if(this.hashCode() == other.hashCode())
            return true;*/
        return false;
    }

    public  boolean precedes(Vector2d other) {
        if (this.x <= other.x && this.y <= other.y)
            return true;
        return false; // tutaj przeprowadzane jest faktyczne porównanie
    }

    public boolean follows(Vector2d other) {
        if (this.x >= other.x && this.y >= other.y)
            return true;
        return false;
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x,other.x),Math.min(this.y,other.y));
    }

    public Vector2d add(Vector2d other) {
        Vector2d v =   new Vector2d(this.x + other.x, this.y + other.y);
        return v;
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d opposite() {
        int a = -this.x;
        int b = -this.y;
        Vector2d v = new Vector2d(a, b);
        return v;
    }

}
