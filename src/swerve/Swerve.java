/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swerve;

/**
 *
 * @author Dasty
 */
public class Swerve {

    double l = 23.0;
    double w = 19.5;

    
    public double thresholdX(double x1, double y1) {
        double d = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2));
        if (d > thresh) {
            return x1;
        }
        return 0;
    }

    public double thresholdY(double x1, double y1) {
        double d = Math.sqrt(Math.pow(x1,2) + Math.pow(y1,2));
        if (d > thresh) {
            return x1;
        }
        return -.001;
    }

    static double thresh = .1;
    
    public double sqr(double x) {
        return Math.pow(x, 2);
    }

    
    public void driveHandler(double x1, double y1, double x2) {

        x1 = thresholdX(x1, y1);
        y1 = thresholdY(y1, x1);
        if (Math.abs(x2) < thresh) {
            x2 = 0;
        }
        double fwd = -y1;
        double str = x1;
        double rcw = x2;

        double r, ax, bx, cx, dx, ws1, ws2, ws3, ws4, wa1, wa2, wa3, wa4, max;
        r = Math.sqrt(sqr(l) + sqr(w));
        ax = str - rcw * (l / r);
        bx = str + rcw * (l / r);

        cx = fwd - rcw * (w / r);
        dx = fwd + rcw * (w / r);

        ws1 = Math.sqrt(sqr(bx) + sqr(cx));
        ws2 = Math.sqrt(sqr(bx) + sqr(dx));
        ws3 = Math.sqrt(sqr(ax) + sqr(dx));
        ws4 = Math.sqrt(sqr(ax) + sqr(cx));
        wa1 = Math.atan2(bx, cx) * 180 / Math.PI;
        wa2 = Math.atan2(bx, dx) * 180 / Math.PI;
        wa3 = Math.atan2(ax, dx) * 180 / Math.PI;
        wa4 = Math.atan2(ax, cx) * 180 / Math.PI;
        max = Math.abs(ws1);
        if (Math.abs(ws2) > max) {
            max = Math.abs(ws2);
        }
        if (Math.abs(ws3) > max) {
            max = Math.abs(ws3);
        }
        if (Math.abs(ws4) > max) {
            max = Math.abs(ws4);
        }
        if (max > 1) {
            ws1 /= max;
            ws2 /= max;
            ws3 /= max;
            ws4 /= max;
        }
        
        if(Math.abs(wa1) > 90)
        {
            wa1 -= 180*(Math.abs(wa1)/wa1);
            ws1 *= -1;
        }
        if(Math.abs(wa2) > 90)
        {
            wa2 -= 180*(Math.abs(wa2)/wa2);
            ws2 *= -1;
        }
        if(Math.abs(wa3) > 90)
        {
            wa3 -= 180*(Math.abs(wa3)/wa3);
            ws3 *= -1;
        }
        if(Math.abs(wa4) > 90)
        {
            wa4 -= 180*(Math.abs(wa4)/wa4);
            ws4 *= -1;
        }
        
        if(wa1 < 0)
        {
            wa1 += 360;
        }
        if(wa2 < 0)
        {
            wa2 += 360;
        }
        if(wa3 < 0)
        {
            wa3 += 360;
        }
        if(wa4 < 0)
        {
            wa4 += 360;
        }
        
        System.out.println("Wheel 1 - Speed: "+ws1+", Angle: "+wa1);
        System.out.println("Wheel 2 - Speed: "+ws2+", Angle: "+wa2);
        System.out.println("Wheel 3 - Speed: "+ws3+", Angle: "+wa3);
        System.out.println("Wheel 4 - Speed: "+ws4+", Angle: "+wa4);

    }

    public static void main(String[] args) {
        Swerve s = new Swerve();
        s.driveHandler(0, 0, -1);
    }
}
