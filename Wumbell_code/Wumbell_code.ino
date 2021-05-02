#include <math.h>
#include <Wire.h>
#include <ESP8266WiFi.h>;
#include <WiFiClient.h>;
#include <ThingSpeak.h>;
#include <Adafruit_GFX.h> // For OLED
#include <Adafruit_SSD1306.h>// Foe OLED
#define SCREEN_WIDTH 128 // OLED display width, in pixels
#define SCREEN_HEIGHT 32 // OLED display height, in pixels
// Declaration for an SSD1306 display connected to I2C (SDA, SCL pins)
Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, -1);

WiFiClient client;// creating object for wifi access
const char* ssid = "KhushiNetplus4G";       // Your WiFi Id
const char* password = "jaimatadi0548"; //Your Network Password
unsigned long myChannelNumber = 1377253; //Your Channel Number for Distance (Without Brackets)
const char * myWriteAPIKey = "XXAH3RXSRVRBI96C";   // YOUR_API_KEY for channel XXAH3RXSRVRBI96C

int sensorPin = A0; // Analog Pin where the S is connected to
int period = 20; // delay 20 ms
int midPoint = 450;//410-480
long count = 0; // count for number of picks
long ExeRate = 0; // Heart rate (determined)
long Cal_Exe_rate = 0; // calibrated heart rate

// For calculation of the difference of time
long time1 = 0;
long time2 = 0;
long time3 = 0;
long time4 = 0;
long timeGap = 0;
long starttime = 0;
long stoptime = 0;
int taskmode = 0;

void setup() {
  Serial.begin(115200);
  delay(100);
  if (!display.begin(SSD1306_SWITCHCAPVCC, 0x3C)) { // Address 0x3D for 128x64
    Serial.println(F("SSD1306 allocation failed"));
    for (;;);
  }
  // Display Project details
  delay(1000);
  display.clearDisplay();
  display.setTextSize(1);
  display.setTextColor(WHITE);
  display.setCursor(0, 0);
  display.println("  MY GYM  WORKOUT");
  display.setCursor(0, 17);
  display.println("  MONITORING SYSTEM ");
  display.display();
  delay(3000);
  // Connect to WiFi network
  WiFi.begin(ssid, password);
  ThingSpeak.begin(client);
  // Display connection to OLED
  display.clearDisplay();
  display.setCursor(0, 0);
  display.println("Connected to WiFi");
  display.display();
  delay(2000);
  //display.clearDisplay();

  display.clearDisplay();
  display.setCursor(10, 0);
  display.println("GETTING SENSOR DATA");
  display.display();
  delay(1000);
}

void loop()
{


  while (1)
  {
    int data1 = averageedSignal();
    delay (period);
    int data2 = averageedSignal();
    int diff = data2 - data1; // difference of two simultanious values

    if (diff > 0) // graph is moving upwards
    {
      while (diff > 0)
      {
        data1 = averageedSignal();
        delay (period);
        data2 = averageedSignal();
        diff = data2 - data1;
      } // coming out of the while loop means the graph has started moving downward

      if (data1 > midPoint)
      {
        count = count + 1;
        display.clearDisplay();
        delay(100);
        display.setTextSize(1);
        display.setCursor(0, 0);
        display.println(" WORKOUT MONITORING");
        display.setTextSize(2);
        display.setCursor(15, 17);
        display.println(count);
        display.setCursor(55, 17);
        display.println("Counts");
        display.display();
        ThingSpeak.writeField(myChannelNumber, 1,count, myWriteAPIKey); //Update in ThingSpeak
        delay(100);
      }


    }

  }
} // loop stage closes


// Defining varios functions

//Function of averaging ADC signal
int averageedSignal()
{
  int n = 0;
  starttime = millis();
  int reader = 0.;
  do
  {
    reader += analogRead (sensorPin); // read and add values...
    n++;
    stoptime = millis();
  }
  while (stoptime < starttime + 20);           // ...until 20 ms have elapsed
  reader /= n;
  return (reader);
}
