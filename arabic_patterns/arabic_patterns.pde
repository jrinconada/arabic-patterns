
int backgroundColor = 50;
int lineColor = 200;
float lineSize = 2;

int step = 0;

Circle dot;

void setup() {
    size(640, 480);
    smooth();
    background(backgroundColor);
    dot = new Circle(lineColor, 3, width / 2, height / 2, 100);
    // dot.newGrowth(0, 1, 1);
    // dot.newRotation(0, 180, 1);
    dot.newPainting(0, 100, 2);
}

void draw() {
    background(backgroundColor);

    // dot.turn();
    // dot.grow();
    dot.paintIt();
    // dot.display();

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
