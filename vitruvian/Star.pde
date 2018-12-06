class Star extends Figure {

    int points;
    float radius;

    Star (int lineColor, float lineSize, float locationX, float locationY, int points, float radius, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.points = points;
        this.radius = radius;
    }

    PShape create(int points, float radius) {
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
    void display() {
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
