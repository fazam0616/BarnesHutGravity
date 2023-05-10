package Main;

import GUI.Window;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        Body.MIN = new Point(-400,-400);
        Body.MAX = new Point(400,400);

        double l = 5.0e15;

        LinkedList<Body> bodies = new LinkedList<>();

//        Body earth = new Body(new Point(0,0),5.97e24,6378000);
//        Body moon = new Body(new Point(384400000,0),7.34e22,1.737000);
//        earth.setOrbit(moon);
//        bodies.add(earth);
//        bodies.add(moon);

//

        Point offset = new Point(-20.0*l,-20.0*l);
        Body center = new Body(new Point(0,0),1.0e32,1.0e15,Color.YELLOW);
        Body center2= new Body(offset,1.0e30,1.0e11,Color.green);
        for (int i = 0; i < 10000; i++) {
            double dis = r.nextDouble()*l*10+5*l;
            double a = r.nextGaussian()*Math.PI*2;
            Point p = new Point(dis*Math.cos(a),dis*Math.sin(a));
            double m = 1.0e1*r.nextInt(10);
            bodies.add(new Body(p,m));
            double v = r.nextDouble()*100;
            center.setOrbit(bodies.getLast());
//            bodies.getLast().color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
//            bodies.getLast().vel = bodies.getLast().vel.add(new Point(-v,0));
//            bodies.getLast().vel = (new Point(v*Math.sin(a),-v*Math.cos(a))).scale(100);
        }

        for (int i = 0; i < 10000; i++) {
            double dis = r.nextDouble()*l+2*l;
            double a = r.nextGaussian()*Math.PI*2;
            Point p = (new Point(dis*Math.cos(a),dis*Math.sin(a))).add(offset);
            double m = 1.0e10*r.nextInt(100000);
            bodies.add(new Body(p,m,Color.blue));
            double v = r.nextDouble()*100;

            center2.setOrbit(bodies.getLast());
            bodies.getLast().vel = bodies.getLast().vel.add(new Point(v,0));
//            bodies.getLast().vel = (new Point(-v*Math.sin(a),v*Math.cos(a)));
        }

        bodies.add(center);
        bodies.add(center2);

        Window w = new Window(800,800);
        w.bodies = bodies;

        T thread = new T(w);
        thread.start();

        w.setVisible(true);
        while (true){
            w.repaint();
        }
    }
}