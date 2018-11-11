
int backgroundColor = 50;
int lineColor = 200;
int lineSize = 2;
Animator anim = new Animator();

void setup() {
    size(640, 480);
    smooth();
    background(backgroundColor);
    anim.x = -PI / 2;
    anim.targetX = TWO_PI;
}

void draw() {
    background(backgroundColor);

    anim.move();
    stroke(lineColor);
    strokeWeight(lineSize);
    noFill();
    arc(300, 200, 150, 150, 0, anim.x);
}
