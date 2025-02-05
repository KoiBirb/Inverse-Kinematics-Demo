package InverseKinematics;
import Main.Mouselistener;

import java.awt.*;

public class Leg {

    Color colour = Color.BLACK;
    public static int length, x, y, x2, y2, x3, y3;
    public static double a1, a2;

    public Leg() {
        length = 150;
        a1 = Math.PI/2;
        a2 = Math.PI/2;
        x = 340;
        y = 340;
    }

    public void update () {

        int mouseX = Mouselistener.x;
        int mouseY = Mouselistener.y;

        double distance = Angles.distance(x, y, mouseX, mouseY);
        if (distance > length*2) {
            int dx = mouseX - x;
            int dy = mouseY - y;
            double magnitude = Math.sqrt(dx * dx + dy * dy);
            mouseX = x + (int) ((dx / magnitude) * length * 2);
            mouseY = y + (int) ((dy / magnitude) * length * 2);
        }

        double C = Angles.findC(mouseX, mouseY);

        a1 = Angles.findA(mouseX, mouseY);
        a2 = Angles.findAP(mouseX, mouseY);

        if (mouseX < x) {
            x2 = (int) (x - (length * Math.cos(a1)));
            x3 = (int) (x - (C * Math.cos(a2)));
            y2 = (int) (y - (length * Math.sin(a1)));
            y3 = (int) (y - (C * Math.sin(a2)));
        } else {
            x2 = (int) (x + (length * Math.cos(a1)));
            x3 = (int) (x + (C * Math.cos(a2)));
            y2 = (int) (y + (length * Math.sin(a1)));
            y3 = (int) (y + (C * Math.sin(a2)));
        }
    }

    public void draw (Graphics2D g2) {
        g2.setColor(colour);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(x, y,x2 ,y2);
        g2.drawLine(x2, y2,x3, y3);
        g2.drawOval(40, 40, 600, 600);
    }
}
