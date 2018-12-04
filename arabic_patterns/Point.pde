class Point extends Figure {

    Point(int lineColor, float lineSize, float locationX, float locationY) {
        super(lineColor, lineSize, locationX,  locationY);
    }

    // Call this every frame to display the point
    void display() {
        pushMatrix();
        translate(locationX, locationY);
        stroke(lineColor);
        strokeWeight(lineSize);
        point(0, 0);
        popMatrix();
    }
}
