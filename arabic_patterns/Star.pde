class Star {
    PShape s;

    Star(int lineColor, int points, float radius) {
        s = createShape();
        s.beginShape();
        s.noFill();
        s.stroke(lineColor);
        s.strokeWeight(2);

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
    }

    void display(float x, float y) {
        pushMatrix();
        translate(x, y);
        shape(s);
        popMatrix();
    }
}
