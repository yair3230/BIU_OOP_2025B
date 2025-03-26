package ass1.src;

/**
 * Class representing a line using two points.
 */
public class Line {
    private Point start;
    private Point end;
    private double slope;
    private double intercept;

    /**
     * Calcs the slope and intercept of the line, so we know it's func, i.e ax+b where a is slope and b is intercept.
     */
    private void calcFuncOfLine() {

        double diffX, diffY;

        // First, get the slope.
        if (start.getX() > end.getX()) {
            diffX = start.getX() - end.getX();
            diffY = start.getY() - end.getY();
        } else {
            diffX = end.getX() - start.getX();
            diffY = end.getY() - start.getY();
        }
        slope = diffY / diffX;

        // Calc the intercept.
        intercept = start.getY() - (start.getX() * slope);
    }

    /**
     * Constructor using two points.
     *
     * @param start starting point
     * @param end   end point
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
        calcFuncOfLine();
    }

    /**
     * Constructor using 4 doubles, representing two points.
     *
     * @param x1 x of starting point
     * @param y1 y of starting point
     * @param x2 x of end point
     * @param y2 y of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        calcFuncOfLine();
    }

    /**
     * Getter for line slope.
     *
     * @return slope
     */
    public double getSlope() {
        return slope;
    }

    /**
     * Getter for line intercept.
     *
     * @return intercept
     */
    public double getIntercept() {
        return intercept;
    }


    /**
     * Length of the line.
     *
     * @return length
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return Point representing the middle
     */
    public Point middle() {
        // Get the middle for X and Y individually
        double middleX, middleY;
        if (start.getX() > end.getX()) {

            // Half the distance
            middleX = (start.getX() - end.getX()) / 2;

            // Add to the smaller coordinate
            middleX += end.getX();
        } else {
            middleX = (end.getX() - start.getX()) / 2;
            middleX += start.getX();
        }
        // do the same for Y
        if (start.getY() > end.getY()) {

            // Half the distance
            middleY = (start.getY() - end.getY()) / 2;

            // Add to the smaller coordinate
            middleY += end.getY();
        } else {
            middleY = (end.getY() - start.getY()) / 2;
            middleY += start.getY();
        }
        return new Point(middleX, middleY);
    }

    /**
     * Returns the start point of the line.
     *
     * @return Point representing the start
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return Point representing the end
     */
    public Point end() {
        return end;
    }


    /**
     * Check intersection between two lines.
     *
     * @param other Other line
     * @return true if lines intersect, else false
     */
    public boolean isIntersecting(Line other) {
        // Move slopes to one side of the equation, and intercepts to the other.
        // Isolate x to find the intersection point
        // for example, ax+b=cx+d turns to ax-cx=d-b
        double finalSlope = slope - other.getSlope();
        double finalIntercept = other.getIntercept() - intercept;


        if (finalSlope == 0) {

            // Lines have the same slopes but on different heights = parallel
            if (finalIntercept != 0) {
                return false;
            }

            // else, lines sit on top of each other
            return true;
        }
        // Now we have an equation of type: qx=z and we need to divide z by q to find x, aka the intersection point
        double intersection = finalIntercept / finalSlope;

        // Now ensure that the x is in range of both lines (meaning, both lines are long enough in order to intersect).
        if (intersection > start.getX() && intersection > end.getX()) {
            return false;
        }
        if (intersection < start.getX() && intersection < end.getX()) {
            return false;
        }
        if (intersection > other.start().getX() && intersection > other.end().getX()) {
            return false;
        }
        if (intersection < other.start().getX() && intersection < other.end().getX()) {
            return false;
        }
        return true;
    }


    /**
     * Returns true if these 2 lines intersect with this line, false otherwise.
     *
     * @param other1 first other line
     * @param other2 second other line
     * @return boolean
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (!isIntersecting(other1)) {
            return false;
        }
        return isIntersecting(other2);
    }


    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other first other line
     * @return Point representing the intersection.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        }
        // Handle two lines with infinite slope sitting on top of each other
        if (Double.isInfinite(slope) && Double.isInfinite(other.getSlope())) {
            return null;
        }
        double finalSlope = slope - other.getSlope();


        // We already know that the lines intersect. if the final slope is 0,
        // that means the lines sit on top of each other, so we need to return null.
        if (finalSlope == 0) {
            return null;
        }
        // Handle case where slope is infinite
        if (Double.isInfinite(finalSlope)) {
            double x = start.getX();

            // Y is the same for our entire line, so we need the y of the other line.
            double y = other.getSlope() * x + other.getIntercept();
            return new Point(x, y);
        }
        double finalIntercept = other.getIntercept() - intercept;
        double x = finalIntercept / finalSlope;

        // We have x, slope and intercept. Calc y.
        double y = x * slope + intercept;
        return new Point(x, y);
    }

    /**
     * Return true if the lines are equal, false otherwise.
     *
     * @param other other line
     * @return boolean
     */
    public boolean equals(Line other) {
        if (start.getX() != other.start.getX() && start.getX() != other.end.getX()) {
            return false;
        }
        if (end.getX() != other.start.getX() && end.getX() != other.end.getX()) {
            return false;
        }
        if (start.getY() != other.start.getY() && start.getY() != other.end.getY()) {
            return false;
        }
        if (end.getY() != other.start.getY() && end.getY() != other.end.getY()) {
            return false;
        }
        return true;
    }

}