# graphical-pointer
Java Swing extension for ease-of-use GUI development

# Setup
Copy the Pointer.java file into your project folder
In your Screen class constructor, initialize the pointer by calling Pointer.setReference(this);
In your Screen's paintComponent() method, call Pointer.draw(g) passing in whatever you chose to call your Graphics variable.
Note: For best practice, invoke Pointer.draw() at the end of your paintComponent() method to ensure it is not hidden behind other components

