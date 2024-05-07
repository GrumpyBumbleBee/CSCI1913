import java.awt.*;
import java.util.Random;

/** DrawSolar has one method which uses the ShapeDrawer, Point, Ring, and Circle classes to draw a simple solar system
 * figure which is saved to the file "solar.png" (200x200 pixels tall).
 */
public class DrawSolar {
    public static void main(String[] args) {
        ShapeDrawer bobRossCanvas = new ShapeDrawer(200,200);           // Creates new ShapeDrawer canvas.
        Color cadmiumYellow = Color.yellow;                                         // Color yellow set to cadmiumYellow. Kind of unnecessary that I created a variable for it instead of doing "bobRossCanvas.setFill(Color.white)", but.... I was feeling creative with the naming.
        Color phthaloBlue = new Color(102,178,255);                        // Creates new blue color because I thought the pre-made "blue" was a bit boring. Now we got phthalo blue inspired by Bob Ross.
        Color titaniumWhite = Color.white;                                          // Titanum white color variable
        bobRossCanvas.setFill(titaniumWhite);                                       // Sets the fill to titanium white
        bobRossCanvas.setStroke(titaniumWhite);                                     // Sets the stroke to titanium white
        for(int i =0; i < 40; i++){                                                 // This for loop adds 40 stars to the image (in random locations)
            Random rand = new Random();                                                 // Here we get the random numbers for the x and y coordinates for the star circles vvv (down below)
            double x = rand.nextInt(200) ;
            double y = rand.nextInt(200);
            Circle stars = new Circle(new Point(x, y),0.5);                       // A star was born! makes a new Circle representing a star.
            bobRossCanvas.draw(stars);                                                  // A whole lot of "happy little accidents" drawn onto the bobRossCanvas until the loop runs 40 times so every star has its own friend!)
        }
        bobRossCanvas.setFill(cadmiumYellow);                                       // After washing your brush odorless paint thinner, beat the devil out of it before loading it with some cadmium yellow paint.
        bobRossCanvas.setStroke(cadmiumYellow);
        Circle sun = new Circle(new Point(100,100), 20);                // Now, we make a happy little sun for our painting.
        bobRossCanvas.draw(sun);                                                    // Let's draw that happy little sun.

        Ring orbitAroundSun = new Ring(new Circle(new Point(100,100),ShapeUtils.distance(new Point(25,50),new Point(100,100))),2);      // The sun needs an orbit (Ring). Let's make that next.
        bobRossCanvas.setFill(titaniumWhite);                                                                                                                     // As always, use odorless paint thinner to clean the brush and beat the bristles dry. Now, load some titanium white onto it again.
        bobRossCanvas.setStroke(titaniumWhite);
        bobRossCanvas.draw(orbitAroundSun);                                                                                                                       // Use nice clean strokes to draw the ring representing the Earth's orbit around our sun.

        bobRossCanvas.setFill(phthaloBlue);                                                                             // Let's get a new brush for our phthalo blue paint. Load it onto the brush and dab the bristles on the paint palete so that all bristles are evenly coated.
        bobRossCanvas.setStroke(phthaloBlue);
        Circle earth = new Circle(new Point(25,50), 9);                                                     // Now, create the earth. We'll make it smaller than the sun because that seems like a good idea.
        bobRossCanvas.draw(earth);                                                                                      // Draw the circular Earth onto our canvas.
        Ring orbitAroundEarth = new Ring(new Circle(new Point(25,50),25),2);                        // Make a sing around Earth to represent the moon's orbit.

        bobRossCanvas.setFill(titaniumWhite);                                                          // Returning to the first brush where we had the titanium white  (Actually, we're just changing the color of the Fill and Stroke so that the inside of the shape and it's outline are both white)
        bobRossCanvas.setStroke(titaniumWhite);
        bobRossCanvas.draw(orbitAroundEarth);                                                          // Draw the orbit around Earth so that it's feeling like it's being hugged.
        Circle earthMoon = new Circle(new Point(48,50), 5);                                // We want to give earth a little friend, so we'll make a little moon (circle) for it.
        bobRossCanvas.draw(earthMoon);                                                                 // Now we can draw Earth's little friend going around in an orbit around Earth. How cute!

        String fileNameToSave = "solar.png";                                                                // Here we're gonna create a string variable that points to a string "solar"
        bobRossCanvas.writeToFile(fileNameToSave);                                                          // to be used to create a file with the name/title of solar.png by using the writeToFile function from ShapeDrawer.
    }
}
