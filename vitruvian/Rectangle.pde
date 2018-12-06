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
        rect(0, 0, base * size, tall * size);
        popMatrix();
    }
}
