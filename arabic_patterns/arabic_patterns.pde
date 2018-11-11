
int backgroundColor = 50;
int lineColor = 200;
float lineSize = 2;
Animator anim = new Animator();
Point dot;

void setup() {
    size(640, 480);
    smooth();
    background(backgroundColor);

    dot = new Point(lineColor, 5, 100, 100);
    dot.newAnimation(100, 100, 200, 200);
}

void draw() {
    background(backgroundColor);
    dot.move();
    dot.display();
}
