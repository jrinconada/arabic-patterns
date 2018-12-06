class Rectangle extends Figure {

    float base;
    float tall;

    Rectangle (int lineColor, float lineSize, float locationX, float locationY, float base, float tall, float angle) {
        super(lineColor, lineSize, locationX,  locationY);
        this.base = base;
        this.tall = tall;
        this.angle = angle;
    }

    // Call this every frame to display the circle
    void display() {
        pushMatrix();
        // Position
        translate(locationX, locationY);
        // Rotation
        rotate(radians(angle));
        // Drawing and scaling with size, base and tall
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        rectMode(RADIUS);
        rect(0, 0, (base / 2) * size, (tall / 2) * size);
        popMatrix();
    }

    // Call this every frame to paint the square
    void paint() {
        draw();
        pushMatrix();
        // Position
        translate(locationX - base / 2, locationY - tall / 2);
        // Rotation
        rotate(radians(angle));
        stroke(lineColor);
        noFill();
        strokeWeight(lineSize);
        line(0, 0, howMuchPaint * base, 0); // Top
        line(base, tall, base - howMuchPaint * base, tall); // Bottom
        line(base, 0, base, howMuchPaint * tall); // Right
        line(0, tall, 0, tall - howMuchPaint * tall); // Left
        popMatrix();
    }
}
