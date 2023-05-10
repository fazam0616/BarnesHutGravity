package GUI;

import Main.Body;
import Main.Point;
import Main.Quad;

import javax.swing.JPanel;
import java.awt.*;

public class Panel extends JPanel {
    private Window parentWindow;

    public Panel(Window p){
        this.parentWindow = p;
    }
    public void paintComponent(Graphics g){
        double l = Math.max(Body.MAX.x-Body.MIN.x,Body.MAX.y-Body.MIN.y);
        Quad q = new Quad(Body.MIN,new Point(l,l));
        for (Body b:parentWindow.bodies){
            q.add(b);
        }
        for (Body b:parentWindow.bodies){
            b.acc = q.calcForce(b).scale(1/b.mass);
        }
        for (Body b:parentWindow.bodies){
            b.update(this.parentWindow.timestep);
            if(!parentWindow.debug)b.paint(g,parentWindow);
//            System.out.println(b.acc);
        }
        parentWindow.time += parentWindow.timestep;

        if(parentWindow.debug)q.paint(g,parentWindow);
        g.setColor(Color.BLACK);
        g.drawString("Time: " + parentWindow.time + "\n",0,10);
        g.drawString("Timstep: " + parentWindow.timestep + "\n",0,20);
        g.drawString("Scale: " + 1/parentWindow.scale + "\n",0,30);
    }

    public Window getParentWindow() {
        return parentWindow;
    }
}
