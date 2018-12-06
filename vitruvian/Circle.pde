class Circle extends Figure {

    float radius;

    Circle (int lineColor, float lineSize, float locationX, float locationY, float radius, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.radius = radius;
        this.angle = angle;
    }

    // Call this every frame to display the circle
    void display() {
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
    void paint() {
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
