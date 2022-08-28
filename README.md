# graphical-pointer
Java Swing extension for ease-of-use GUI development

## Setup ##
* Copy the Pointer.java file into your project folder
* In your screen class's constructor, initialize the pointer by calling **Pointer.setReference(this)**
* In your screen's paintComponent() method, call **Pointer.draw()** passing in whatever you chose to call your Graphics variable
  * *For best practice, invoke Pointer.draw() at the end of your paintComponent() method to ensure it is not hidden behind other components*

## How To Use ##
* Press and hold 'shift' to see live mouse coordinates
* Click to drop pinpoint
* To move a pinpoint, press and drag the circle surrounding it
* To delete a pinpoint, click inside the circle surrounding it

## Other Methods ##
1. **Pointer.setCustomColor(Color c)** - sets the color of the pointer text to whatever is passed as the argument
2. **Pointer.setCustomFont(Font f)** - sets the font of the pointer text to whatever is passed as the argument

## Sample implementation ##
```java swing

import javax.swing.JPanel;
import java.awt.Graphics;

public class Screen extends JPanel {

    public Screen() {
        // initializes graphical pointer
        Pointer.setReferenceScreen(this);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        randomMethod1();

        randomMethod2();

        // draws pointer: invoke at end of draw loop to ensure visibility
        Pointer.draw(g);
    }
}
```


