class Line extends Figure {

    float length;

    Line(int lineColor, float lineSize, float locationX, float locationY, float length, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.length = length;
        this.angle = angle;
    }

    // Call this every frame to display the line
    void display() {
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
