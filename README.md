## Inspiration
Usually, during Gym Workouts, people are prone to injuries due to a lack of guidance about the correct form or posture of any particular exercise. In most cases, it's problematic for beginners to work out properly or they get trapped by myths and overload themselves leading to long-term injuries. Generally, people are not able to keep a track of their workout and hence under or over train themselves which usually hampers their Fitness goals. These issues inspired us to build *Wumbell* which is an AR-powered tracking service

## What it does
These days everything is getting IOTfyed, hence the idea is to IOTfy Gym equipment in Gyms as well as at home.

*3-D AR model to help beginners learn the right form to use different gym equipment and their exercises *:  
Our service aims at installing QR codes on every gym equipment which when scanned through our Wumbell app enables the user to view the correct exercise posture with the help of our 3-D AR Model. The mobile app also allows the user to check the correct exercise posture anytime with the help of our 3-D AR Model 

*Wumbell Tag to track Workout*:
Along with the QR code feature, we aim at installing a piece of hardware called Wumbell tag which tracks the user workout and updates it on the app.

*Workout routine to train multiple body part from specific equipment*:
Usually, people are aware of only a few sets of exercises limited to just one body part but the Wumbell app provides the user with multiple types of exercises for different body parts from the same equipment.


## How we built it
-The Hardware prototype (Wumbell tag) is built around NodeMCU which comes with an inbuilt wi-fi module to transfer data easily and an ADXL sensor that measures acceleration forces around the 3-axises.
-We used Figma for designing UI/UX for our app and components for our video. 
-Android App written in Kotlin which uses ARCore and Sceneform SDK for Android for Augmented reality.


## Show your support

Give a ⭐ if you liked this project!

## Contributions

- Feel Free to Open a PR/Issue for any feature or bug(s).
- Make sure you follow the community guidelines!
- Feel free to open an issue to ask a question/discuss anything about Wumbell.
- Have a feature request? Open an Issue!

## Development Setup

Before you begin, you should have already downloaded the Android Studio SDK and set it up correctly. You can find a guide on how to do this here: [Setting up Android Studio](http://developer.android.com/sdk/installing/index.html?pkg=studio).

## Building the Code

1. Clone the repository using HTTP: git clone https://github.com/shubhamji88/WumBell

2. Open Android Studio.

3. Click on 'Open an existing Android Studio project'

4. Browse to the directory where you cloned the repo and click OK.

5. Let Android Studio import the project.

6. Build the application in your device by clicking run button.
