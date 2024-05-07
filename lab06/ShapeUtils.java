import java.util.ArrayList;

public class ShapeUtils {
    /** distance():
     * Takes two points and computes the distance between them.
     **/
    public static double distance(Point p1, Point p2){
        /** Takes two points and computes the distance between them. **/
        double y1, x1, y2, x2;
        y1 = p1.getY();                                                                         // y1 variable points to the y-coordinate value of point 1
        x1 = p1.getX();                                                                         // x1 variable points to the x-coordinate value of point 1
        y2 = p2.getY();                                                                         // y2 variable points to the y-coordinate value of point 2
        x2 = p2.getX();                                                                         // x2 variable points to the y-coordinate value of point 2
        return Math.sqrt((y2-y1) * (y2-y1) + (x2-x1) * (x2-x1));                                // Uses the Math.sqrt method to find the square root for the distance formula (which is calculated and the value representing the calculated distance is returned)
    }
    /** getCenter(Point array)
     * takes an array of points and returns a point that represents the "center" of those points.
     * If an empty array is given, the point (0,0) is returned.
     */
    public static Point getCenter(Point[] points){
        if(points.length == 0){                                                         // Checks if the points array is empty
            Point returnPoint = new Point(0, 0);                            // If it is, makes variable returnPoint point to a Point with the coordinates (0.0, 0.0)                            # FIGURE OUT HOW TO MAKE THIS WORK
            return returnPoint;                                                       // returns the returnPoint
        }
        int pointArrayLength = points.length;                                       // Otherwise, creates an int representing the length of the array containing different points.
        double totalXsum = 0, totalYsum = 0;
        for(int pointIndex = 0; pointIndex < pointArrayLength; pointIndex++) {           // Goes through each of the coordinates within the array of points
            double pX, pY;                                          // pX being the x-coordinate of the coordinate and pY being the corresponding y-coordinate. totalXsum will be used to point to the sum of x-coordinate values and totalYsum will do the same but for the y values.
            pX = points[pointIndex].getX();                                                 // Has pX point to the x-coordinate value of the point at the current pointIndex of the array.
            pY = points[pointIndex].getY();                                                 // Has pY point to the y-coordinate value of the point at the current pointIndex of the array.
            totalXsum += pX;                                                                    // the total sum of x-coordinate values is increased with each x-coordinate value accessed.
            totalYsum += pY;                                                                    // the total sum of y-coordinate values is increased with each y-coordinate value accessed.
        }
        double avgXsum = 0, avgYsum = 0;                                                     // avgXsum and avgYsum will be used to calculate the averages of the sum of all x-coordinate and all y-coordinate values
        avgXsum = totalXsum / pointArrayLength;                                                       // (sum of all x-coordinate values) divided by the array length (an easy way to know how many x-coordinates there are).
        avgYsum = totalYsum / pointArrayLength;                                                       // (sum of all y-coordinate values) divided by the array length (an easy way to know how many y-coordinates there are).
        Point returnPoint = new Point(avgXsum, avgYsum);                                        // makes a variable (a new Point) point to a Point with the coordinates being the average of the x-coordinates (for the new x-coordinate) and the average of the y-coordinates (for the new y-coordinate)
        return returnPoint;                                                                     // returns the new coordinate value
    }
    /** getArea(Ring)
     * takes a Ring and returns its area.
     */
    public static double getArea(Ring c){
        double radiusOuterCircle, radiusInnerCircle, areaOuterCircle, areaInnerCircle, thickness, areaRing;
        thickness = c.getThickness();                                                           // thickness variable points to the value of the circle ring's thickness
        radiusInnerCircle = c.getInnerCircle().getRadius();                                     // the radius of the inner circle is pointed to by the radiusInnerCircle variable.
        radiusOuterCircle = radiusInnerCircle + thickness;                                      // Radius of outer circle is found by taking the radius of the inner circle and adding 'thickness' to it.
        areaOuterCircle = Math.PI * (radiusOuterCircle * radiusOuterCircle);                    // Area of the outer circle calculated
        areaInnerCircle = Math.PI * (radiusInnerCircle * radiusInnerCircle);                    // Area of inner circle calculated
        areaRing = areaOuterCircle - areaInnerCircle;                                           // Area of the ring formed is calculated by taking the outer ring area and subtracting the area of the inner ring.
        return areaRing;                                                                        // returns the area of the ring
    }
    /** getArea(Circle)
     * takes a circle and returns its area.
     */
    public static double getArea(Circle c){
        double radius, area;
        radius = c.getRadius();                                                                  // The variable radius points to the radius of the circle
        area = Math.PI * (radius * radius);                                                      // area of the circle is calculated using the area of a circle formula and the circle's given radius.
        return area;                                                                             // returns... the area
    }
    /** isIn(Circle, Point)
     * takes a circle and checks if a point is in the circle.
     * Returns boolean 'True' if the point lies within the circle and 'False' if it does not.
     */
    public static boolean isIn(Circle c, Point p){
        double circleRadius, distanceFromCenterToPoint;
        circleRadius = c.getRadius();                                                           // circleRadius variable points to value of the radius of given circle
        Point centerOfCircle = c.getCenter();                                                   // The centerOfCircle point points to the coordinate values of the center of the circle.
        distanceFromCenterToPoint = distance(centerOfCircle, p);                                // distanceFromCenterToPoint variable points to the distance calculated using the distance() function and passing in the coordinates for the center of the given circle and p (the given point coordinates).
        if(distanceFromCenterToPoint <= circleRadius){                                          // Tests to see if the distance from the center to the point is less than or equal to the radius:
            return true;                                                                            // If it is, then the point is found within the circle and returns 'true'
        }
        else{                                                                                       // Otherwise the point is not found within the circle and returns 'false'
            return false;
        }
    }
}
