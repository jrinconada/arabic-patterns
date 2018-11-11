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
Animator anim = new Animator();
Point dot;

public void setup() {
    
    
    background(backgroundColor);

    dot = new Point(lineColor, 5, 100, 100);
    dot.newAnimation(100, 100, 200, 200);
}

public void draw() {
    background(backgroundColor);
    dot.move();
    dot.display();
}
class Animator {
    // Position
    protected float x;
    protected float y;
    float startX = 100;
    float startY = 100;
    float endX = 300;
    float endY = 30;

    // Stroke
    private float lineSize = 0;
    float maxLineSize = 10;

    // Animation parameters
    float easing = 0.12f;
    float blinkingRate = 0.1f;
    private float amplitudeX;
    private float amplitudeY;
    float speed = 0.04f;

    public void newAnimation(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.x = startX;
        this.y = startY;
        amplitudeX = (endX - startX);
        amplitudeY = (endY - startY);
    }

    /*
        A sigmoid animation (ease in - ease out) for x and y
    */
    public boolean anim() {
        if (abs(endX - x) > 0.3f) {
            x = arctan(frameCount, speed, amplitudeX) + startX;
        } else {
            return false;
        }

        if (abs(endY - y) > 0.3f) {
            y = arctan(frameCount, speed, amplitudeY) + startY;
        } else {
            return false;
        }
        return true;
    }

    public void blink() {
        lineSize = abs(sine(frameCount, blinkingRate, maxLineSize));
    }

    private float sine(float time, float frequency, float amplitude) {
        return sin(time * frequency) * amplitude;
    }

    private float cosine(float time, float frequency, float amplitude) {
        return cos(time * frequency) * amplitude;
    }

    private float arctan(float time, float frequency, float amplitude) {
        return atan(time * frequency) * amplitude;
    }

}
abstract class Figure  extends Animator {
    int lineColor;
    float lineSize;
    float locationX;
    float locationY;

    Figure(int lineColor, float lineSize, float locationX, float locationY) {
        this.lineColor = lineColor;
        this.lineSize = lineSize;
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public boolean move() {
        boolean moving = anim();
        if (moving) {
            locationX = x;
            locationY = y;
        }
        return moving;
    }

    public abstract void display();
}
class Point extends Figure {

    Point(int lineColor, float lineSize, float locationX, float locationY) {
        super(lineColor, lineSize, locationX,  locationY);
    }

    public void display() {
        pushMatrix();
        translate(locationX, locationY);
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

    public void display(float x, float y) {
        pushMatrix();
        translate(x, y);
        shape(s);
        popMatrix();
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
