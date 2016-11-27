/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractal;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author adiel
 */
public class Fractal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Lienzo forma = new Lienzo(new Lienzo.GrapichsInformer() {
            @Override
            public void inform(Graphics g) {
                g.setColor(Color.red);
                fractal(40, 200, 1 , 0, 400, 5, g);
                fractal(440, 200, -0.5 , Math.sqrt(3)/2, 400, 5, g);
                fractal(440-0.5*400, 200+Math.sqrt(3)/2*400, -0.5 , -Math.sqrt(3)/2, 400, 5, g);
            }
        });

        forma.setSize(600, 600);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                forma.setVisible(true);
            }
        });
    }

    public static void fractal(double x, double y, double vx, double vy, double distance, int order, Graphics g) {
        if (order == 0) {
            g.drawLine((int) x, (int) y, (int) (x + vx * distance), (int) (y + vy * distance));
        } else {
            fractal(x, y, vx, vy, distance / 3, order - 1, g);
            double vx2 = vx*Math.cos(-Math.PI/3)-vy*Math.sin(-Math.PI/3);
            double vy2 = vy*Math.cos(-Math.PI/3)+vx*Math.sin(-Math.PI/3);
            fractal(x + vx * distance  / 3, y + vy * distance  / 3, vx2, vy2, distance / 3, order - 1, g);
            double vx3 = vx*Math.cos(Math.PI/3)-vy*Math.sin(Math.PI/3);
            double vy3 = vy*Math.cos(Math.PI/3)+vx*Math.sin(Math.PI/3);
            fractal(x + (vx+vx2) * distance  / 3, y + (vy+vy2) * distance  / 3, vx3, vy3, distance / 3, order - 1, g);

            fractal(x + vx * distance * 2 / 3, y + vy * distance * 2 / 3, vx, vy, distance / 3, order - 1, g);
        }
    }

    public static class Lienzo extends JFrame {

        private GrapichsInformer informer;

        public Lienzo(GrapichsInformer informer) {
            this.informer = informer;
        }

        public void paint(Graphics g) {
            if (informer != null) {
                informer.inform(g);
            }

        }

        public interface GrapichsInformer {

            public void inform(Graphics g);
        }

    }

}
