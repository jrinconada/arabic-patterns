class Circle extends Figure {

    float radius;

    Circle(int lineColor, float lineSize, float locationX, float locationY, float radius) {
        super(lineColor, lineSize, locationX,  locationY);
        this.radius = radius;
    }

    // Call this every frame to display the circle
    void display() {
        pushMatrix();
        translate(locationX, locationY);
        rotate(radians(angle));
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        ellipseMode(RADIUS);
        ellipse(0, 0, radius, radius);
        popMatrix();
    }

    // Call this every frame to paint the circle
    void paintIt() {
        paint();
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        ellipseMode(RADIUS);
        arc(locationX, locationY, radius, radius, 0, TWO_PI * howMuchPaint);
    }
}
