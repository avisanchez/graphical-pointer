# graphical-pointer
Java Swing extension for ease-of-use GUI development

## Setup ##
* Copy the Pointer.java file into your project folder
* In your screen class's constructor, initialize the pointer by calling **Pointer.setReference(this)**
* In your screen's paintComponent() method, call **Pointer.draw()** passing in whatever you chose to call your Graphics variable
  * *For best practice, invoke Pointer.draw() at the end of your paintComponent() method to ensure it is not hidden behind other components*

