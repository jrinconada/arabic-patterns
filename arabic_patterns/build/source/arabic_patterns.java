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

Line dot;

public void setup() {
    
    
    background(backgroundColor);
    dot = new Line(lineColor, 3, width / 2, height / 2, 100, 0);
    dot.newGrowth(new TimedAnimation(0, 1, 1));
    dot.newRotation(new TimedAnimation(0, 180, 1));
}

public void draw() {
    background(backgroundColor);

    dot.turn();
    dot.grow();
    dot.display();

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
abstract class Figure {
    // Figure properties
    protected int lineColor;
    protected float lineSize;
    protected float locationX;
    protected float locationY;
    protected float size;
    protected float angle; // In degrees

    // Animations
    private TimedAnimation movement;
    private BlinkAnimation blinking;
    protected TimedAnimation growth;
    protected TimedAnimation rotation;

    Figure(int lineColor, float lineSize, float locationX, float locationY) {
        this.lineColor = lineColor;
        this.lineSize = lineSize;
        this.locationX = locationX;
        this.locationY = locationY;
        size = 1;
        angle = 0;
    }

    public void newTranslation(TimedAnimation anim) {
        movement = anim;
    }

    public void newBlink(BlinkAnimation anim) {
        blinking = anim;
    }

    public void newGrowth(TimedAnimation anim) {
        growth = anim;
    }

    public void newRotation(TimedAnimation anim) {
        rotation = anim;
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
        translate(locationX, locationY);
        rotate(radians(angle));
        stroke(lineColor);
        strokeWeight(lineSize);
        line(0, 0, length * size, 0);
        popMatrix();
    }
}
class Point extends Figure {

    Point(int lineColor, float lineSize, float locationX, float locationY) {
        super(lineColor, lineSize, locationX,  locationY);
    }

    // Call this every frame to display the point
    public void display() {
        pushMatrix();
        translate(locationX, locationY);
        rotate(radians(angle));
        scale(size);
        stroke(lineColor);
        strokeWeight(lineSize);
        point(0, 0);
        popMatrix();
    }
}
class Polygon {
    PShape s;

    Polygon(int lineColor, int sides, float radius) {
        s = createShape();
        s.beginShape();
        s.noFill();
        s.stroke(lineColor);
        s.strokeWeight(2);

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
    }

    // Call this every frame to display the polygon
    public void display(float x, float y) {
        pushMatrix();
        translate(x, y);
        shape(s);
        popMatrix();
    }
}
class Star {
    PShape s;

    Star(int lineColor, int points, float radius) {
        s = createShape();
        s.beginShape();
        s.noFill();
        s.stroke(lineColor);
        s.strokeWeight(2);

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
    }

    // Call this every frame to display the star
    public void display(float x, float y) {
        pushMatrix();
        translate(x, y);
        shape(s);
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
