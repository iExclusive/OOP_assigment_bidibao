package ie.tudublin;

import processing.core.PApplet;

public class Star extends PApplet {

      //size(1200, 700, P3D);
      //fullScreen(P3D, SPAN);
 

    void something(){

      size(1200, 700); 

    int gridSize = 40;
  
    for (int x = gridSize; x <= width - gridSize; x += gridSize) {
      for (int y = gridSize; y <= height - gridSize; y += gridSize) {
      noStroke();
      fill(255);
      rect(x-1, y-1, 3, 3);
      stroke(255, 100);
      line(x, y, width/2, height/2);
    }
  }
  }
}
