package Main;

import GUI.Window;

import java.util.LinkedList;

public class T extends Thread{

    private final Window w;

    public T(Window w) {
        this.w =w;
    }

    @Override
    public void run() {
        //Thread.sleep(2000);
        double l = Math.max(Body.MAX.x-Body.MIN.x,Body.MAX.y-Body.MIN.y);
        while (true){
//                System.out.println(bodies.get(0).pos);
            Quad q = new Quad(Body.MIN,new Point(l,l));
            long t1 = System.currentTimeMillis();
            for (Body b:w.bodies){
                q.add(b);
            }
            t1 = System.currentTimeMillis() - t1;

            long t2 = System.currentTimeMillis();
            for (Body b:w.bodies){
                b.acc = q.calcForce(b).scale(1/b.mass);
            }
            t2 = System.currentTimeMillis() - t2;

            for(Body b:w.bodies){
                b.update(w.timestep);
            }
        }

    }
}
