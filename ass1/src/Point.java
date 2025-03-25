package ass1.src;

/**
 * Class representing a Point using x and y.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor using x and y.
     *
     * @param x x value
     * @param y y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the distance of this point to the other point.
     *
     * @param other Point to calculate distance to
     * @return double distance
     */

    public double distance(Point other) {
        // Calculated with Sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)))
        double distanceX = this.x - other.x;
        distanceX = Math.pow(distanceX, 2);

        double distanceY = this.y - other.y;
        distanceY = Math.pow(distanceY, 2);
        return Math.sqrt(distanceX + distanceY);
    }

    /**
     * Return true is the points are equal, false otherwise.
     *
     * @param other Point to compare to
     * @return boolean true if equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }


    /**
     * Return the x value of this point.
     *
     * @return double x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y value of this point.
     *
     * @return double y value
     */
    public double getY() {
        return this.y;
    }
}