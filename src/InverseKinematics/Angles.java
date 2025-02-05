
package InverseKinematics;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Angles {

    public static double findAlpha (double C) {
        double A = Leg.length;
        double B = Leg.length;
        return Math.acos(((B*B) + (C*C) - (A*A)) / (2 * B * C));
    }

    public static double findAP (double endX, double endY) {
        return Math.atan((endY - Leg.y)/(endX - Leg.x));
    }

    public static double findA (int endX, int endY) {
        double C = findC(endX, endY);
        return findAlpha(C) + findAP(endX, endY);
    }

    public static double findC (int endX, int endY) {
        return distance(endX, endY, Leg.x, Leg.y);
    }

    public static double distance (int x1, int y1, int x2, int y2){
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }

}
