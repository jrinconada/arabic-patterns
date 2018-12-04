
int backgroundColor = 50;
int lineColor = 200;
float lineSize = 2;

int step = 0;

Line dot;

void setup() {
    size(640, 480);
    smooth();
    background(backgroundColor);
    dot = new Line(lineColor, 3, width / 2, height / 2, 100, 0);
    dot.newGrowth(new TimedAnimation(0, 1, 1));
    dot.newRotation(new TimedAnimation(0, 180, 1));
}

void draw() {
    background(backgroundColor);

    dot.turn();
    dot.grow();
    dot.display();

    // switch(step) {
    // case 0:
    //     if(frameCount / frameRate >= 1) { // Wait 1 second
    //         step++;
    //     }
    //     break;
    // case 1:
    //     if(frameCount / frameRate < 4) { // Wait 4 seconds
    //         dot.blink();
    //         dot.display();
    //     } else {
    //         step++;
    //     }
    //     break;
    // }
}
