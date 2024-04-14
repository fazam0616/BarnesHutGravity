package Main;

import GUI.Window;

import java.awt.*;

public class Body {
    public Point pos,acc,vel;
    public double mass;
    public double radius;
    public Color color;
    public static Point MAX,MIN;
    public Body(Point pos, double m,double r) {
        this.acc = new Point(0,0);
        this.vel = new Point(0,0);
        this.pos = pos;
        this.mass = m;
        this.radius = r;
        this.color = Color.red;
        update(0);
    }
    public Body(Point pos, double m) {
        this.acc = new Point(0,0);
        this.pos = pos;
        this.vel = new Point(0,0);
        this.mass = m;
        this.radius = Math.sqrt(m)/10;
        this.color = Color.red;
        update(0);
    }
    public Body(Point pos, double m,Color c) {
        this.acc = new Point(0,0);
        this.pos = pos;
        this.vel = new Point(0,0);
        this.mass = m;
        this.radius = Math.sqrt(m)/10;
        this.color = c;
        update(0);
    }
    public Body(Point pos, double m,double r,Color c) {
        this.acc = new Point(0,0);
        this.pos = pos;
        this.mass = m;
        this.vel = new Point(0,0);
        this.radius = r;
        this.color = c;
        update(0);
    }

    public void setOrbit(Body b, int dir){
        Point r = this.pos.minus(b.pos);
        double v = Math.sqrt(0.999*(Quad.G*this.mass)/r.magnitude());

        if (dir >=0)
            b.vel = (new Point(r.y,-r.x)).scale(v/r.magnitude());
        else
            b.vel = (new Point(-r.y,r.x)).scale(v/r.magnitude());
    }

    public void update(double t){
        vel = vel.add(acc.scale(t));
        pos = pos.add(vel.scale(t)).add(acc.scale(0.5*t*t));

        if (this.pos.x < MIN.x)
            MIN.x = pos.x;
        else if (this.pos.x> MAX.x)
            MAX.x = pos.x;

        if (this.pos.y < MIN.y)
            MIN.y = pos.y;
        else if (this.pos.y> MAX.y)
            MAX.y = pos.y;

    }

    public void paint(Graphics g, Window w) {
        Point p = this.pos.mathToScreen(w);
        g.setColor(this.color);

        g.fillOval((int) (p.x-radius*w.scale), (int) (p.y-radius*w.scale), (int) Math.max(3, this.radius*w.scale*2), (int) Math.max(3, this.radius* w.scale*2));
//        g.setColor(Color.blue);
        Point p2 = p.add(this.acc.scale(5/this.acc.magnitude()));
//        Point p3 = p.add(this.vel.scale(5/this.vel.magnitude()));
        //g.drawLine((int) (p.x), (int)( p.y), (int) (p2.x), (int) (p2.y));
//        g.setColor(Color.green);
//        g.drawLine((int) (p.x), (int)( p.y), (int) (p3.x), (int) (p3.y));
        g.setColor(Color.black);
        if(this.radius*w.scale*2>4)g.drawOval((int) (p.x-radius*w.scale), (int) (p.y-radius*w.scale), (int) Math.max(2, this.radius*w.scale*2), (int) Math.max(2, this.radius* w.scale*2));
    }
}
