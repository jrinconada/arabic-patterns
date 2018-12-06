class Polygon extends Figure {

    int sides;
    float radius;

    Polygon (int lineColor, float lineSize, float locationX, float locationY, int sides, float radius, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.sides = sides;
        this.radius = radius;
    }

    PShape create(int sides, float radius) {
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
    void display() {
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
