class Line extends Figure {

    float length;

    Line(int lineColor, float lineSize, float locationX, float locationY, float length) {
        super(lineColor, lineSize, locationX,  locationY);
        this.length = length;
    }

    // Call this every frame to display the line
    void display() {
        pushMatrix();
        translate(locationX, locationY);
        stroke(lineColor);
        strokeWeight(lineSize);
        line(0, 0, length, 0);
        popMatrix();
    }
}
