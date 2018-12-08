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

public class vitruvian extends PApplet {

PImage img;
int step = 0;

// Colors
int regularColor = 60;
int squareColor = 0xff265074;
int circleColor = 0xffB60E0E;
int phiColor = 0xff40B611;
// Sizes
float lineSize = 5;
int regularTextSize = 60;
int largeTextSize = 90;
// Square dimensions
float squareX;
float squareY;
float squareWidth;
float squareHeight;
// Circle dimensions
float circleX;
float circleY;
float circleRadius;

// Figures
Line bottom;
Line top;
Line right;
Line left;
Rectangle square;
Line radius;
Circle circle;
Proportion p8;

public void setup() {
     // 1299, 1294 for the final result
    

    // Image
    img = loadImage("vitruvian-man.jpg");
    img.resize(width, height);
    background(img);

    // Text
    PFont font = createFont("Cambria Math", 32);
    // String[] fontList = PFont.list();
    // printArray(fontList);
    textFont(font);

    // Square
    squareX = width / 2;
    squareY = height / 2 + 59;
    squareWidth = width - 127;
    squareHeight = height - 135;
    square = new Rectangle(squareColor, lineSize, squareX, squareY, squareWidth, squareHeight, 0);
    square.newPainting(0, 100, 2);
    square.newBlink(8);

    // Lines
    bottom = new Line(squareColor, lineSize, squareX, squareY + squareHeight / 2, squareHeight / 2, -90);
    top = new Line(squareColor, lineSize, squareX, squareY - squareHeight / 2, squareHeight / 2, 90);
    right = new Line(squareColor, lineSize, squareX + squareWidth / 2, squareY, squareWidth / 2, 180);
    left = new Line(squareColor, lineSize, squareX - squareWidth / 2, squareY, squareWidth / 2, 0);
    bottom.newGrowth(0, 1, 2);
    top.newGrowth(0, 1, 2);
    right.newGrowth(0, 1, 2);
    left.newGrowth(0, 1, 2);

    // Circle
    circleX = width / 2;
    circleY = height / 2 - 2;
    circleRadius = (width - 10) / 2;
    circle = new Circle(circleColor, lineSize, circleX, circleY, circleRadius, 0);
    circle.newPainting(0, 100, 2);

    // Radius
    radius = new Line(circleColor, lineSize, circleX, circleY, circleRadius, 0);
    radius.newGrowth(0, 1, 2);
    radius.newRotation(0, 360, 2);
    radius.newBlink(8);

    // Proportions
    p8 = new Proportion(squareColor, lineSize, 40, squareY - squareHeight / 2, squareHeight, 8);
    p8.newPainting(0, 8, 2);

    // EXAMPLES
    // dot.newTranslation(width / 2, height / 2, 100 + width / 2, 100 + height / 2, 1);
    // dot.newGrowth(0, 1, 1);
    // dot.newRotation(0, 180, 1);
    // dot.newPainting(0, 100, 1);
}

public void draw() {
    background(img);

    // EXAMPLES
    // dot.move();
    // dot.grow();
    // dot.turn();
    // dot.display();
    // dot.paint();

    // p8.paint();

    switch(step) {
    case 0: // Square appears
        square.paint();
        if(frameCount / frameRate > 2) step++; // second 2
        break;
    case 1: // Lines to the center
        square.display();
        bottom.grow();
        bottom.display();
        top.grow();
        top.display();
        right.grow();
        right.display();
        left.grow();
        left.display();
        if(frameCount / frameRate > 4) step++; // second 4
        break;
    case 2: // Square and lines stay
        square.display();
        bottom.display();
        top.display();
        right.display();
        left.display();
        if(frameCount / frameRate > 6) step++; // second 6
        break;
    case 3: // Radius appears
        radius.grow();
        radius.display();
        if(frameCount / frameRate > 8) step++; // second 8
        break;
    case 4: // Circle appears
        radius.turn();
        radius.display();
        circle.paint();
        if(frameCount / frameRate > 10) step++; // second 10
        break;
    case 5: // Circle and radius stay
        radius.turn();
        radius.display();
        circle.display();
        if(frameCount / frameRate > 12) step++; // second 12
        break;
    case 6: // Radius blinks
        radius.display();
        radius.blink();
        circle.display();
        drawText("R", circleX + circleRadius / 2 - 20, circleY - 10, circleColor);
        if(frameCount / frameRate > 14) { // Square blinks (second 14)
            square.blink();
            square.display();
            drawText("L", squareX - 20, squareY - squareHeight / 2 - 10, squareColor);
        }
        // Phi formula:
        if(frameCount / frameRate > 16) drawText("R", width / 5, height * 3/5, circleColor); // R (second 16)
        if(frameCount / frameRate > 17) drawText("⎯", width / 5 - 4, height * 3/5 + regularTextSize / 2 + 6, regularColor, largeTextSize); // - (second 17)
        if(frameCount / frameRate > 18) drawText("L", width / 5, height * 3/5 + regularTextSize, squareColor); // L (second 18)
        if(frameCount / frameRate > 19) drawText("=", width / 5 + 48, height * 3/5 + 28, regularColor); // = (second 19)
        if(frameCount / frameRate > 20) drawText("\u03C6", width / 5 + 97, height * 3/5 + 20, phiColor); // Phi (second 20)
        break;
    }
}

public void drawText(String t, float x, float y, int textColor, int s) {
    textSize(s);
    fill(textColor);
    text(t, x, y);
}

public void drawText(String t, float x, float y, int textColor) {
    textSize(regularTextSize);
    fill(textColor);
    text(t, x, y);
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

    protected float cosine(float time, float frequency, float amplitude) {
        return cos(time * frequency + PI) * amplitude;
    }

    protected float arctan(float time, float frequency, float amplitude) {
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
    public void paint() {
        draw();
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        ellipseMode(RADIUS);
        arc(0, 0, radius, radius, 0, TWO_PI * howMuchPaint);
        popMatrix();
    }
}
class DiscreteAnimation extends Animation {
    // Position
    int x;
    private float start;
    private float end;

    DiscreteAnimation (float start, float end, float duration) {
        this.start = start;
        this.end = end;
        this.x = round(start);
        amplitudeX = end - start;
        this.duration = duration;
        speed = 1 / (duration * frameRate);
        frame = 0;
    }

    // A sigmoid animation (ease in - ease out) for x and y
    public boolean anim() {
        if (frame / frameRate < duration) {
            x = round(sigmoid(frame, speed, amplitudeX) + start);
            frame++;
            return true;
        } else {
            return false;
        }
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
    protected boolean draw() {
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
class Proportion {

    ArrayList<Line> lines;
    final float gap = 7;
    float fullHeight;

    private DiscreteAnimation painting;

    Proportion(int lineColor, float lineSize, float locationX, float locationY, float fullHeight, int parts) {
        lines = new ArrayList();
        float length = fullHeight / parts - gap;
        for (int i = 0; i < parts; i++) {
            lines.add(new Line(lineColor, lineSize, locationX, gap + locationY + (i * (length + gap)), length, 90));
        }
    }

    public void newPainting(float from, float to, float duration) {
        painting = new DiscreteAnimation(from, to, duration);
    }

    public void display() {
        for (Line l : lines) {
            l.display();
        }
    }

    protected boolean paint() {
        if (painting == null) return false;
        boolean stillPainting = painting.anim();
        if (stillPainting) {
            for (int i = 0; i < painting.x; i++) {
                lines.get(i).display();
            }
        }
        return stillPainting;
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
        rect(0, 0, (base / 2) * size, (tall / 2) * size);
        popMatrix();
    }

    // Call this every frame to paint the square
    public void paint() {
        draw();
        pushMatrix();
        // Position
        translate(locationX - base / 2, locationY - tall / 2);
        // Rotation
        rotate(radians(angle));
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        line(0, 0, howMuchPaint * base, 0); // Top
        line(base, tall, base - howMuchPaint * base, tall); // Bottom
        line(base, 0, base, howMuchPaint * tall); // Right
        line(0, tall, 0, tall - howMuchPaint * tall); // Left
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

    // A sigmoid animation (ease in - ease out) for x and y
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
  public void settings() {  size(700, 700);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "vitruvian" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
