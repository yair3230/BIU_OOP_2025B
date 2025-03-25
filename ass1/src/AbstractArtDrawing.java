import ass1.src.Line;
import ass1.src.Point;

import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * Draws 10 lines on a surface, shows their middles and intersections, highlights triangles.
 *
 */
public class AbstractArtDrawing {
    /**
     * Draws the Line l on a given surface d.
     *
     * @param l Line comprised of two points
     * @param d DrawSurface object
     */
    static void drawLine(Line l, DrawSurface d) {
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
        Point mid = l.middle();
        d.setColor(Color.blue);
        d.fillCircle((int) mid.getX(), (int) mid.getY(), 5);
        d.setColor(Color.black);
    }

    static void drawTriangleEdge(Point one, Point two, DrawSurface d) {
        d.setColor(Color.green);
        d.drawLine((int) one.getX(), (int) one.getY(), (int) two.getX(), (int) two.getY());
        d.setColor(Color.black);
    }

    static void drawIntersection(Point p, DrawSurface d) {
        if (p == null) {
            return;
        }
        d.setColor(Color.red);
        d.fillCircle((int) p.getX(), (int) p.getY(), 5);
        d.setColor(Color.black);
    }

    /**
     * Draws 10 lines on a surface, shows their middles and intersections, highlights triangles.
     * @param args Command line args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Intersecting lines", 800, 600);
        DrawSurface d = gui.getDrawSurface();
        // Generate 10 random lines
        Random rand = new Random();
        Line[] lines = new Line[10];

        // Save intersections in the following format: if lines 0 and 3 intersect, save "03" in intersections
        for (int i = 0; i < 10; i++) {
            double x1 = rand.nextInt(800) + 1;
            double x2 = rand.nextInt(800) + 1;
            double y1 = rand.nextInt(600) + 1;
            double y2 = rand.nextInt(600) + 1;
            Line l = new Line(x1, y1, x2, y2);
            lines[i] = l;
            drawLine(l, d);
        }

        // Go over all lines and check intersections
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                Point intersection = lines[i].intersectionWith(lines[j]);
                if (intersection != null) {
                    drawIntersection(intersection, d);

                    // Find if there is a third line that intersects with both.
                    for (int k = j + 1; k < 10; k++) {
                        Line thirdLine = lines[k];
                        if (thirdLine.isIntersecting(lines[i], lines[j])) {
                            Point secondIntersection = thirdLine.intersectionWith(lines[i]);
                            Point thirdIntersection = thirdLine.intersectionWith(lines[j]);
                            drawTriangleEdge(secondIntersection, thirdIntersection, d);
                            drawTriangleEdge(intersection, secondIntersection, d);
                            drawTriangleEdge(intersection, thirdIntersection, d);
                        }
                    }
                }
            }
        }

        gui.show(d);
    }
}
