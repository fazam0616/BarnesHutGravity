package Main;

import GUI.Window;

import java.awt.*;
import java.util.LinkedList;

public class Quad {
    public Quad[] children = null;
    public double m = 0;
    public int depth;
    public Point pos,size,COM;
    public Body b = null;
    public static double THRESH = 5;
    public static final double G = 6.67e-11;
    public Quad(Point pos, Point size){
        this.pos = pos.clone();
        this.COM = this.pos.add(size.scale(0.5));
        this.size = size.clone();
        this.depth = 0;
//        System.out.println(this.pos +" To "+this.pos.add(this.size));
    }

    public Quad(Point pos, Point size, int d){
        this.pos = pos.clone();
        this.size = size.clone();
        this.COM = this.pos.add(size.scale(0.5));
        this.depth = d;
//        System.out.println(this.pos +" To "+this.pos.add(this.size));
    }
    public Point calcForce(Body p){
        Point f = new Point(0,0);
        Point r = COM.minus(p.pos);
        double d = r.magnitude();
        if (d>1) {
            double t = d/this.size.x;
            if (t > THRESH | children == null) {
//                System.out.print("Close: ");
                r = r.scale(1/d);
                if(p != this.b)
                    f=f.add(r.scale(G * this.m * p.mass / Math.pow(d, 2)));
            } else {
//                System.out.print("Far: ");
                for (Quad q : children) {
                    f=f.add(q.calcForce(p));
                }
            }
        }
//        System.out.println(f);
        return f;
    }


    public void add(Body p){
        if (b!= null){
            if(this.children == null)
                this.subdivide();
            for(Quad q:children){
                if (q.contains(p.pos)) {
                    q.add(p);
                    break;
                }
                if (q.contains(b.pos)) {
                    q.add(b);
                    break;
                }
            }
            this.b = null;
        }
        else if (children == null){
                this.b = p;
        }else{
            for(Quad q:children){
                if (q.contains(p.pos)) {
                    q.add(p);
                    break;
                }
            }
        }
        if (children != null){
            this.COM = new Point(0,0);
            this.m = 0;
            for(Quad q:children){
                this.m += q.m;
                this.COM.add(q.COM.scale(q.m));
            }
            this.COM.scale(1/this.m);
        }else{
            if (b!=null){
                this.m = this.b.mass;
                this.COM = b.pos.clone();
            }
        }
    }

    public void subdivide(){
        Point s = this.size.scale(0.5);
        children = new Quad[]{null,null,null,null};
        children[0] = new Quad(this.pos,s,depth+1);
        children[1] = new Quad(this.pos.add(new Point(s.x,0)),s,depth+1);
        children[2] = new Quad(this.pos.add(new Point(0,s.y)),s,depth+1);
        children[3] = new Quad(this.pos.add(s),s,depth+1);
    }

    public boolean contains(Point p){
        double x,y;
        x = p.x - this.pos.x;
        y = p.y - this.pos.y;
        if (x>=0 && x <= size.x){
            return y >= 0 && y <= size.y;
        }
        return false;
    }
    public void paint(Graphics g, Window w){
        if (this.children != null){
            for (Quad q:this.children)
                q.paint(g,w);
        }
//        System.out.println(pos+":"+size);
        Point o = this.pos.mathToScreen(w);
        //int d = Math.max(Math.min(255,255-(int) (this.m/2000000*255)),0);
//        System.out.println(d);
        g.setColor(new Color(255,0,0));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1));
        g2.drawRect((int) o.x, (int) (o.y-size.y*w.scale), (int) ( size.x*w.scale), (int) (size.y*w.scale));
        //Point p = this.COM.mathToScreen(w);
        g.setColor(Color.green);
        //if(this.m > 0)g.fillOval((int) p.x, (int) p.y,10,10);
        //g.setColor(new Color(0,Math.max(0,Math.min(254*(10-this.depth)/10,255)),0));
        //Point p = this.COM.mathToScreen(w);
        //if(m>0)g.fillOval((int)p.x,(int)p.y,5,5);
    }
}
