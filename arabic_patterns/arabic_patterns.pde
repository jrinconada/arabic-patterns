
int backgroundColor = 50;
int lineColor = 200;

void setup() {
    size(640, 480);
    background(backgroundColor);
    // Polygon polygon = new Polygon(lineColor, 6, 100);
    Star star = new Star(lineColor, 8, 100);
    star.display(width / 2, height / 2);
}

void draw() {
    
}
