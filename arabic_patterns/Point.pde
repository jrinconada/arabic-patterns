class Point extends Figure {

    Point(int lineColor, float lineSize, float locationX, float locationY) {
        super(lineColor, lineSize, locationX,  locationY);
    }

    void display() {
        pushMatrix();
        translate(locationX, locationY);
        println(locationX);
        stroke(lineColor);
        strokeWeight(lineSize);
        point(0, 0);
        popMatrix();
    }
}
