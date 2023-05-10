package GUI;

import Main.Body;
import Main.Point;
import Main.Quad;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Window extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
    public double time = 0;
    public double total;
    private Point MOUSE = null;
    public double scale = .000000000000005;

    public double timestep = Math.pow(20,8);
    public Point cam = new Point(0,0);
    public LinkedList<Body> bodies;
    double camSpeed = 10;
    public boolean debug = false;
    public Window(int length, int height){
        super();
        Panel p =new Panel(this);
        this.setSize(length, height);
        this.setTitle("Quadtree-Based Gravity Sim");
        this.setContentPane(p);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        this.setVisible(false);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                cam.y += camSpeed/scale;
                break;
            case KeyEvent.VK_S:
                cam.y -= camSpeed/scale;
                break;
            case KeyEvent.VK_D:
                cam.x += camSpeed/scale;
                break;
            case KeyEvent.VK_A:
                cam.x -= camSpeed/scale;
                break;
            case KeyEvent.VK_P:
                debug=!debug;
                break;
            case KeyEvent.VK_UP:
                scale *= 1.1;
                break;
            case KeyEvent.VK_DOWN:
                scale /= 1.1;
                break;
            case KeyEvent.VK_RIGHT:
                timestep *= 1.01;
                break;
            case KeyEvent.VK_LEFT:
                timestep /= 1.01;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {}

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
