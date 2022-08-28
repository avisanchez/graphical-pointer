# graphical-pointer
Java Swing extension for ease-of-use GUI development

## Setup ##
* Copy the Pointer.java file into your project folder
* In your screen class's constructor, initialize the pointer by calling **Pointer.setReference(this)**
* In your screen's paintComponent() method, call **Pointer.draw()** passing in whatever you chose to call your Graphics variable
  * *For best practice, invoke Pointer.draw() at the end of your paintComponent() method to ensure it is not hidden behind other components*

## How to use ##
* Press and hold 'shift' to see live mouse coordinates
* Click to drop pinpoint
* To move a pinpoint, press and drag the circle surrounding it
* To delete a pinpoint, click inside the circle surrounding it

## Other Methods ##
1. **Pointer.setCustomColor(Color c)** - sets the color of the pointer text to whatever is passed as the argument
2. **Pointer.setCustomFont(Font f)** - sets the font of the pointer text to whatever is passed as the argument

## Sample implementation ##
```javascript

var specificLanguage_code = 
    {
        "data": {
            "lookedUpPlatform": 1,
            "query": "Kasabian+Test+Transmission",
            "lookedUpItem": {
                "name": "Test Transmission",
                "artist": "Kasabian",
                "album": "Kasabian",
                "picture": null,
                "link": "http://open.spotify.com/track/5jhJur5n4fasblLSCOcrTp"
            }
        }
    }
```


