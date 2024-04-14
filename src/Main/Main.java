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

        double l = 5.0e18;

        LinkedList<Body> bodies = new LinkedList<>();

//        Body earth = new Body(new Point(0,0),5.97e24,6378000);
//        Body moon = new Body(new Point(384400000,0),7.34e22,1.737000);
//        earth.setOrbit(moon,1);
//        bodies.add(earth);
//        bodies.add(moon);



        Point offset = new Point(20*l,20*l);
        Body center = new Body(offset.scale(-1),1.0e35,1.0e15,Color.blue);
        Body center2= new Body(offset,1.0e35,1.0e15,Color.green);
        double v = 100;

        for (int i = 0; i < 1000; i++) {
            double dis = r.nextDouble()*l*10+10*l;
            double a = r.nextGaussian()*Math.PI*2;
            Point p = new Point(dis*Math.cos(a),dis*Math.sin(a)).add(center.pos);
            double m = r.nextDouble()*1.0e30;
            bodies.add(new Body(p,m));

            bodies.getLast().color = new Color(0,0,(int)(254*(dis-10*l)/(10*l)));
            center.setOrbit(bodies.getLast(),1);
        }

        for (int i = 0; i < 1000; i++) {
            double dis = r.nextDouble()*l*10+10*l;
            double a = r.nextGaussian()*Math.PI*2;
            Point p = new Point(dis*Math.cos(a),dis*Math.sin(a)).add(center2.pos);
            double m = r.nextDouble()*1.0e30;
            bodies.add(new Body(p,m));

            bodies.getLast().color = new Color(0,(int)(254*(dis-10*l)/(10*l)),0);
            center2.setOrbit(bodies.getLast(),-1);
        }

        bodies.add(center2);
        bodies.add(center);

//        center2.vel=center2.vel.add(new Point(0,-v));
//        center.vel=center.vel.add(new Point(0,v));

//        double k = l*100;
//        for (int i = 0; i < 5000; i++) {
//            Point p = new Point(r.nextDouble()*k-k/2,r.nextDouble()*k-k/2);
//            Body b = new Body(p,r.nextDouble()*1.0e25+1.0e5);
//            b.vel = new Point(r.nextDouble()*v-v/2,r.nextDouble()*v-v/2);
//            b.color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
//            bodies.add(b);
//        }

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