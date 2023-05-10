package Main;

import GUI.Window;

public class Point {
    public double x,y;
    public static Point O = new Point(0,0);
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point clone(){
        return new Point(this.x,this.y);
    }

    public Point add(Point p){
        Point n = new Point(this.x+p.x,this.y+p.y);
        if(!Double.isNaN(n.x) && !Double.isNaN(n.y)){
            return n;
        }
        return null;
    }

    public Point minus(Point p) {
        Point n = new Point(this.x-p.x,this.y-p.y);
        if(!Double.isNaN(n.x) && !Double.isNaN(n.y)){
            return n;
        }
        return null;
    }

    public Point scale(double a){
        Point n = new Point(this.x*a,this.y*a);
        if(!Double.isNaN(n.x) && !Double.isNaN(n.y)){
            return n;
        }
        else{
//            throw new Exception("Bad args: "+this.x+", "+this.y+", "+a);
            return Point.O.clone();
        }
    }

    public double dot(Point p){
        return p.x*this.x + p.y*this.y;
    }

    public double magnitude(){
        return Math.pow(this.dot(this),0.5);
    }

    public Point mathToScreen(Window w){
        Point n = this.minus(w.cam).scale(w.scale);
        double x = n.x + w.getWidth()/2.0;
        double y = w.getHeight()/2.0 - n.y;
        return new Point(x,y);
    }

    public String toString(){
        return this.x+", "+this.y;
    }
}
