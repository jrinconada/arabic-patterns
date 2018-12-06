import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class arabic_patterns extends PApplet {


int backgroundColor = 50;
int lineColor = 200;
float lineSize = 2;

int step = 0;

Star dot;

public void setup() {
    
    
    background(backgroundColor);
    dot = new Star(lineColor, 2, width / 2, height / 2, 5, 60, 0);
    // dot.newTranslation(width / 2, height / 2, 100 + width / 2, 100 + height / 2, 1);
    // dot.newGrowth(0, 1, 1);
    dot.newRotation(0, 180, 1);
    // dot.newPainting(0, 100, 2);
}

public void draw() {
    background(backgroundColor);

    // dot.move();
    // dot.grow();
    dot.turn();
    dot.display();
    // dot.paintIt();

    // switch(step) {
    // case 0:
    //     if(frameCount / frameRate >= 1) { // Wait 1 second
    //         step++;
    //     }
    //     break;
    // case 1:
    //     if(frameCount / frameRate < 4) { // Wait 4 seconds
    //         dot.blink();
    //         dot.display();
    //     } else {
    //         step++;
    //     }
    //     break;
    // }
}
abstract class Animation {
    // Animation parameters
    protected float easing = 0.12f;
    protected float amplitudeX;
    protected float amplitudeY;
    protected float frame;
    protected float speed;
    protected float duration; // In seconds

    protected float sine(float time, float frequency, float amplitude) {
        return sin(time * frequency) * amplitude;
    }

    private float cosine(float time, float frequency, float amplitude) {
        return cos(time * frequency + PI) * amplitude;
    }

    private float arctan(float time, float frequency, float amplitude) {
        return atan(time * frequency) * amplitude;
    }

    protected float sigmoid(float time, float frequency, float amplitude) {
        return (cosine(time + PI, frequency / 2, 1) + 1) * amplitude / 2;
    }

    // Call every frame to animate, return false when animation is over (if it is timed)
    public abstract boolean anim();
}
class BlinkAnimation extends Animation {
    // Stroke
    float lineSize;
    private float maxLineSize;
    private float blinkingRate = 0.1f;

    BlinkAnimation (float maxLineSize) {
        this.maxLineSize = maxLineSize;
        this.duration = duration;
        lineSize = 0;
    }

    // Blinks indefinitely
    public boolean anim() {
        lineSize = abs(sine(frameCount, blinkingRate, maxLineSize));
        return true;
    }
}
class Circle extends Figure {

    float radius;

    Circle (int lineColor, float lineSize, float locationX, float locationY, float radius, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.radius = radius;
        this.angle = angle;
    }

    // Call this every frame to display the circle
    public void display() {
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        // Drawing and scaling with size and radius
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        ellipseMode(RADIUS);
        ellipse(0, 0, radius * size, radius * size);
        popMatrix();
    }

    // Call this every frame to paint the circle
    public void paintIt() {
        paint();
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        ellipseMode(RADIUS);
        arc(locationX, locationY, radius, radius, 0, TWO_PI * howMuchPaint);
    }
}
abstract class Figure {
    // Figure properties
    protected int lineColor;
    protected float lineSize;
    protected float locationX;
    protected float locationY;
    protected float size;
    protected float angle; // In degrees
    protected float howMuchPaint; // Percentage to finish (0 to 1)

    // Animations
    private TimedAnimation movement;
    private BlinkAnimation blinking;
    private TimedAnimation growth;
    private TimedAnimation rotation;
    private TimedAnimation painting;

    Figure (int lineColor, float lineSize, float locationX, float locationY) {
        this.lineColor = lineColor;
        this.lineSize = lineSize;
        this.locationX = locationX;
        this.locationY = locationY;
        size = 1;
        angle = 0;
    }

    public void newTranslation(float xFrom, float yFrom, float xTo, float yTo, float duration) {
        movement = new TimedAnimation(xFrom, yFrom, xTo, yTo, duration);
    }

    public void newBlink(float maxSize) {
        blinking = new BlinkAnimation(maxSize);
    }

    public void newGrowth(float from, float to, float duration) {
        growth = new TimedAnimation(from, to, duration);
    }

    public void newRotation(float from, float to, float duration) {
        rotation = new TimedAnimation(from, to, duration);
    }

    // from and to a are percentages (0 - 100)
    public void newPainting(float from, float to, float duration) {
        painting = new TimedAnimation(from / 100, to / 100, duration);
    }

    // Call this every frame to move the figure
    public boolean move() {
        if (movement == null) return false;
        boolean moving = movement.anim();
        if (moving) {
            locationX = movement.x;
            locationY = movement.y;
        }
        return moving;
    }

    // Call this every frame to do a blink animation
    public void blink() {
        if (blinking == null) return;
        blinking.anim();
        lineSize = blinking.lineSize;
    }

    // Call this every frame to do a grow animation
    public boolean grow() {
        if (growth == null) return false;
        boolean growing = growth.anim();
        if (growing) {
            size = growth.x;
        }
        return growing;
    }

    // Call this every frame to do a rotation animation
    public boolean turn() {
        if (rotation == null) return false;
        boolean rotating = rotation.anim();
        if (rotating) {
            angle = rotation.x;
        }
        return rotating;
    }

    // Call this every frame to do a painting animation
    protected boolean paint() {
        if (painting == null) return false;
        boolean stillPainting = painting.anim();
        if (stillPainting) {
            howMuchPaint = painting.x;
        }
        return stillPainting;
    }

    public abstract void display();
}
class Line extends Figure {

    float length;

    Line(int lineColor, float lineSize, float locationX, float locationY, float length, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.length = length;
        this.angle = angle;
    }

    // Call this every frame to display the line
    public void display() {
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        // Drawing and scaling with size and length
        stroke(lineColor);
        strokeWeight(lineSize);
        line(0, 0, length * size, 0);
        popMatrix();
    }
}
class Point extends Figure {

    Point (int lineColor, float lineSize, float locationX, float locationY) {
        super(lineColor, lineSize, locationX,  locationY);
    }

    // Call this every frame to display the point
    public void display() {
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        // Scale
        scale(size);
        // Drawing
        stroke(lineColor);
        strokeWeight(lineSize);
        point(0, 0);
        popMatrix();
    }
}
class Polygon extends Figure {

    int sides;
    float radius;

    Polygon (int lineColor, float lineSize, float locationX, float locationY, int sides, float radius, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.sides = sides;
        this.radius = radius;
    }

    public PShape create(int sides, float radius) {
        PShape s = createShape();
        s.beginShape();
        s.noFill();
        s.stroke(lineColor);
        s.strokeWeight(lineSize);

        // 360º / number of sides (in radians)
        float angle = TWO_PI / sides;
        float x, y;
        for(int i = 0; i < sides; i++) {
            // Polar to cartesian
            x = radius * cos(i*angle);
            y = radius * sin(i*angle);
            s.vertex(x, y);
        }

        s.endShape(CLOSE);
        return s;
    }

    // Call this every frame to display the polygon
    public void display() {
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        // Drawing and scaling with size and radius
        shape(create(sides, radius * size));
        popMatrix();
    }
}
class Rectangle extends Figure {

    float base;
    float tall;

    Rectangle (int lineColor, float lineSize, float locationX, float locationY, float base, float tall, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.base = base;
        this.tall = tall;
        this.angle = angle;
    }

    // Call this every frame to display the circle
    public void display() {
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        // Drawing and scaling with size, base and tall
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        rectMode(RADIUS);
        rect(0, 0, base * size, tall * size);
        popMatrix();
    }
}
class Star extends Figure {

    int points;
    float radius;

    Star (int lineColor, float lineSize, float locationX, float locationY, int points, float radius, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.points = points;
        this.radius = radius;
    }

    public PShape create(int points, float radius) {
        PShape s = createShape();
        s.beginShape();
        s.noFill();
        s.stroke(lineColor);
        s.strokeWeight(lineSize);

        // 360º / 2 * number of sides (in radians)
        float angle = TWO_PI / (2 * points); // Ex: 360 / 2*8 = 22.5º
        float x, y;
        float smallToBigRatio = (1 / cos(2 * angle));
        float smallRadius = (radius / smallToBigRatio) / cos(angle);
        for(int i = 0; i < 2 * points; i++) {
            // Polar to cartesian
            if(i % 2 == 0) {
                x = radius * cos(i*angle);
                y = radius * sin(i*angle);
            } else {
                x = smallRadius * cos(i*angle);
                y = smallRadius * sin(i*angle);
            }
            s.vertex(x, y);
        }

        s.endShape(CLOSE);
        return s;
    }

    // Call this every frame to display the star
    public void display() {
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        // Drawing and scaling with size and radius
        shape(create(points, radius * size));
        popMatrix();
    }
}
class TimedAnimation extends Animation {
    // Position
    float x;
    float y;
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    TimedAnimation (float startX, float endX, float duration) {
        init(startX, 0, endX, 0, duration);
    }

    TimedAnimation (float startX, float startY, float endX, float endY, float duration) {
        init(startX, startY, endX, endY, duration);
    }

    public void init(float startX, float startY, float endX, float endY, float duration) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.x = startX;
        this.y = startY;
        amplitudeX = endX - startX;
        amplitudeY = endY - startY;
        this.duration = duration;
        speed = 1 / (duration * frameRate);
        frame = 0;
    }

    // A translation sigmoid animation (ease in - ease out) for x and y
    public boolean anim() {
        if (frame / frameRate < duration) {
            x = sigmoid(frame, speed, amplitudeX) + startX;
            y = sigmoid(frame, speed, amplitudeY) + startY;
            frame++;
            return true;
        } else {
            return false;
        }
    }
}
  public void settings() {  size(640, 480);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "arabic_patterns" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
