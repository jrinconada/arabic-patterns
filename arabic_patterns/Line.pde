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
        translate(locationX, locationY);
        rotate(radians(angle));
        stroke(lineColor);
        strokeWeight(lineSize);
        line(0, 0, length * size, 0);
        popMatrix();
    }
}
