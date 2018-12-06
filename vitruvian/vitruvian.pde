
int backgroundColor = 50;
int lineColor = 200;
float lineSize = 2;

int step = 0;

Star dot;

void setup() {
    size(640, 480);
    smooth();
    background(backgroundColor);
    dot = new Star(lineColor, 2, width / 2, height / 2, 5, 60, 0);
    // dot.newTranslation(width / 2, height / 2, 100 + width / 2, 100 + height / 2, 1);
    // dot.newGrowth(0, 1, 1);
    dot.newRotation(0, 180, 1);
    // dot.newPainting(0, 100, 2);
}

void draw() {
    background(backgroundColor);

    // dot.move();
    // dot.grow();
    dot.turn();
    dot.display();
    // dot.paintIt();

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
