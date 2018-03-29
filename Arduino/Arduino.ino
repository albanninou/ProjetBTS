#include <Adafruit_NeoPixel.h>

#define PIN            24

#define NUMPIXELS      8*1
#define BRIGHTNESS 20
#define NUMCOUNT      100000


Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);
// master:modefonctionnement/couleur
//master:1/1


int mode = -1;
boolean eneable = false;
int color = 0;
String master = "master:";
String you = "you:";
String question = "master?";
int numOfCard = 0;
boolean needUpdate = false;
boolean masterState = false;
boolean masterUpdate = false;
String tick = "tick";
int count = 0;
int connectedCard = 0;
String needToSend = "";

void setup() {
  strip.begin();
  strip.show();
  Serial2.begin(115200);
  Serial.begin(9600);
  Serial2.setTimeout(10);
  green();
  delay(1000);
  Serial2.println(question);
  delay(1000);
  while (Serial2.available() > 0) {
    String trame = Serial2.readString();
    char buf[trame.length()];
    trame.toCharArray(buf, sizeof(buf));
    const char s[2] = "\n";
    char *token;
    token = strtok(buf, s);
    while (token != NULL) {
      String test = token;
      Serial.println("-------");
      Serial.println(test);
      int firstListItem = test.indexOf(you);
      if (firstListItem != -1) {
        test = test.substring(firstListItem);
        test = test.substring(you.length());
        Serial.println(test);
        numOfCard = test.toInt();
      }
      token = strtok(NULL, s);
    }
  }
  if (numOfCard == 0) {
    mode = 0;
    color = 0;
    eneable = true;
  } else {
    mode = 1;
    color = 2;
    eneable = true;
  }
  Serial.print("Num card :");
  Serial.println(numOfCard);
}

void loop() {
  while (Serial2.available() > 0) {
    String trame = Serial2.readString();
    char buf[trame.length()];
    trame.toCharArray(buf, sizeof(buf));
    const char s[2] = "\n";
    char *token;
    token = strtok(buf, s);
    while (token != NULL) {
      String test = token;
      int firstListItem = test.indexOf(master);
      if (firstListItem != -1) {
        test = test.substring(firstListItem);
        test = test.substring(master.length());
        setState(test);
      }
      firstListItem = test.indexOf(question);
      if (firstListItem != -1 && numOfCard == 0) {
        connectedCard++;
        needToSend += you;
        needToSend += connectedCard;
        needToSend += "\n";
      }
      firstListItem = test.indexOf(tick);
      if (firstListItem != -1 && numOfCard != 0) {
        test = test.substring(firstListItem);
        test = test.substring(tick.length() + 1);
        char buf25[sizeof(test)];
        test.toCharArray(buf25, sizeof(buf25));
        int d = atoi(buf25);
        if (d == 0) {
          numOfCard = 0;
          masterState = false;
        } else {
          masterState = true;
          numOfCard = 0;
        }
        masterUpdate = true;
        sendEtat();
      }
      token = strtok(NULL, s);
    }
  }
  strip.setBrightness(BRIGHTNESS);
  if (numOfCard == 0) {
    count++;
  }
  switch (mode) {
    case -1:
      if (count >= NUMCOUNT   || masterUpdate) {
        masterUpdate = false;
        blank();
        sendEtat();
        count = 0;
      }
      break;
    case 0:
      if (count >= NUMCOUNT   || masterUpdate) {
        if (numOfCard != 0) {
          eneable = masterState;
        }
        sendEtat();
        masterUpdate = false;
        setColor();
        count = 0;
      }
      break;
    case 1:

      if (count >= NUMCOUNT || masterUpdate) {
        masterUpdate = false;
        if (numOfCard != 0) {
          eneable = masterState;
        } else {
          eneable = !eneable;
        }
        sendEtat();
        if (eneable) {
          setColor();
        } else {
          blank();
        }
        count = 0;
      }
      break;
  }
  if (needToSend != "") {
    Serial2.print(needToSend);
    needToSend = "";
  }
}

void setColor() {
  switch (color) {
    case 0:
      red();
      break;
    case 1:
      green();
      break;
    case 2:
      blue();
      break;
  }
}

void sendEtat() {
  char str[20];
  if (numOfCard == 0) {
    if (eneable) {
      needToSend += tick + "/1" + "\n";
    } else {
      needToSend += tick + "/0" + "\n";
    }
  }
  if (eneable) {
    sprintf(str, "%d/1/%d", numOfCard, color);
  } else {
    sprintf(str, "%d/0/%d", numOfCard, color);
  }
  needToSend += str;
  needToSend += "\n";
}

void blank() {
  for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
}

void red() {
  for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 255, 0, 0);
    strip.show();
  }
}
void blue() {
  for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 0, 0, 255);
    strip.show();
  }
}
void green() {
  for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 0, 255, 0);
    strip.show();
  }
}

void setState(String trame) {
  char buf[sizeof(trame)];
  trame.toCharArray(buf, sizeof(buf));
  const char s[2] = "/";
  char *token;
  token = strtok(buf, s);
  if (token != NULL) {
    mode = atoi( token );
  }
  token = strtok(NULL, s);
  if (token != NULL) {
    color = atoi( token );
  }
  eneable = true;
  needUpdate = true;
}

