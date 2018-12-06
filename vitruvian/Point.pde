class Point extends Figure {

    Point (int lineColor, float lineSize, float locationX, float locationY) {
        super(lineColor, lineSize, locationX,  locationY);
    }

    // Call this every frame to display the point
    void display() {
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
