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
int lineSize = 2;
Animator anim = new Animator();

public void setup() {
    
    
    background(backgroundColor);
    anim.x = -PI / 2;
    anim.targetX = TWO_PI;
}

public void draw() {
    background(backgroundColor);

    anim.move();
    stroke(lineColor);
    strokeWeight(lineSize);
    noFill();
    arc(300, 200, 150, 150, 0, anim.x);
}
class Animator {
    // Position
    float x = 500;
    float y = 300;
    float targetX = 200;
    float targetY = 200;
    float dx;
    float dy;

    // Stroke
    float lineSize = 0;
    float maxLineSize = 10;

    // Animation parameters
    float easing = 0.12f;
    float blinkingRate = 0.1f;

    public void move() {
        dx = targetX - x;
        x += dx * easing;
        dy = targetY - y;
        y += dy * easing;
    }

    public void blink() {
        lineSize = abs(sine(frameCount, blinkingRate, maxLineSize));
    }

    public float sine(float time, float frequency, float amplitude) {
        return sin(time * frequency) * amplitude;
    }

    public float cosine(float time, float frequency, float amplitude) {
        return cos(time * frequency) * amplitude;
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
