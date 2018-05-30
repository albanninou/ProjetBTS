#include <Adafruit_NeoPixel.h>

#define PIN            3

#define NUMPIXELS      8*1
#define BRIGHTNESS 20
long NUMCOUNT = 1000;


Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);
// master:modefonctionnement/couleur
//²



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
long count = 0;
int connectedCard = 0;
String needToSend = "";  // buffer de se qui sera envoyer
float tension;
float niveauCharge = 100;
float valeur;

void setup() {
  Serial.begin(9600);
  Serial.setTimeout(100);
  strip.begin();
  pinMode(2, OUTPUT);
  digitalWrite(2, HIGH);
  charge();
  delay(1000);
  Serial.println(question);
  delay(1000);
  strip.show();
  while (Serial.available() > 0) {
    String trame = Serial.readString();
    char buf[trame.length()];
    trame.toCharArray(buf, sizeof(buf));
    const char s[2] = "\n";              // char permet de decouper une ligne
    char *token;
    token = strtok(buf, s); // decoupage de la ligne
    while (token != NULL) { // temp y a une ligne
      String test = token;
      int firstListItem = test.indexOf(you);     // verification de la présence du string you
      if (firstListItem != -1) {                  // si different de -1 present, sinon non
        test = test.substring(firstListItem);    // decoupage de tt se qu il y avant le string
        test = test.substring(you.length());    // je garde tt se qui vient apres
        numOfCard = test.toInt();
      }
      token = strtok(NULL, s); //decoupage
    }
  }
  if (numOfCard == 0) {         // si  c est la carte maitre
    mode = 1;                   // allumage continue
    color = 0;                  // couleur rouge
    eneable = true;
  } else {                      // tout les esclaves
    mode = 1;                   // clignotement régulier
    color = 2;                  //couleur bleu
    eneable = true;
  }
  Serial.print("Num card :");
  Serial.println(numOfCard);
}

void loop() {
  while (Serial.available() > 0) {           //
    String trame = Serial.readString();
    char buf[trame.length()];
    trame.toCharArray(buf, sizeof(buf));
    const char s[2] = "\n";
    char *token;
    token = strtok(buf, s);
    while (token != NULL) {
      String test = token;
      int firstListItem = test.indexOf(master);       // verification de la présence du string you
      if (firstListItem != -1) {                       // si different de -1 present sinon nn
        test = test.substring(firstListItem);        // decoupage de tt se qu il y avant le string
        test = test.substring(master.length());       // je garde tt se qui vient apres
        setState(test);
      }
      firstListItem = test.indexOf(question);
      if (firstListItem != -1 && numOfCard == 0) {   // Si il voit quelqu un et si c la 1er carte
        connectedCard++;        // +1
        needToSend = you;           // envoi du tu sera quel numero
        needToSend += connectedCard; // envoi de la designation du numero de carte
        Serial.println(needToSend);
      }
      firstListItem = test.indexOf(tick);               //
      if (firstListItem != -1 && numOfCard != 0) {      //
        test = test.substring(firstListItem);          //
        test = test.substring(tick.length() + 1);       //
        char buf25[sizeof(test)];                      //
        test.toCharArray(buf25, sizeof(buf25));
        int d = atoi(buf25);                           //
        if (d == 0) {
          masterState = false;
        } else {
          masterState = true;
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
        if (numOfCard == 0) {
          delay(100);
        }
        if (eneable) {
          setColor();
        } else {
          blank();
        }
        count = 0;
      }
      break;
  }
  delay(1);
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

void charge(void) {
  strip.setBrightness(50);
  valeur = analogRead(A0);
  tension = valeur * (5 / 700);  // attention 700 remplace 1023 pour test
  Serial.println(valeur);
  tension = 4.8;
  Serial.println(tension);
  if (tension >= 3.55)
  {
    digitalWrite(2, HIGH);
  }
  if (tension <= 3.54  || tension >= 5)
  {
    digitalWrite(2, LOW);
  }
  if (tension <= 4.92 && tension >= 4.74)
  {
    // Serial.println("100%");
    strip.setPixelColor(0, 0, 255, 0);
    strip.setPixelColor(1, 0, 255, 0);
    strip.setPixelColor(2, 0, 255, 0);
    strip.setPixelColor(3, 0, 255, 0);
    strip.setPixelColor(4, 0, 255, 0);
    strip.setPixelColor(5, 0, 255, 0);
    strip.setPixelColor(6, 0, 255, 0);
    strip.setPixelColor(7, 0, 255, 0);
    strip.show();
  }
  if (tension <= 4.73 && tension >= 4.56)
  {
    //  Serial.println("87.5%");
    strip.setPixelColor(0, 0, 255, 0);
    strip.setPixelColor(1, 0, 255, 0);
    strip.setPixelColor(2, 0, 255, 0);
    strip.setPixelColor(3, 0, 255, 0);
    strip.setPixelColor(4, 0, 255, 0);
    strip.setPixelColor(5, 0, 255, 0);
    strip.setPixelColor(6, 0, 255, 0);
    strip.setPixelColor(7, 0, 0, 0);
    strip.show();
  }
  if (tension <= 4.55 && tension >= 4.37)
  {
    //  Serial.println("75%");
    strip.setPixelColor(0, 0, 255, 0);
    strip.setPixelColor(1, 0, 255, 0);
    strip.setPixelColor(2, 0, 255, 0);
    strip.setPixelColor(3, 0, 255, 0);
    strip.setPixelColor(4, 0, 255, 0);
    strip.setPixelColor(5, 0, 255, 0);
    strip.setPixelColor(6, 0, 0, 0);
    strip.setPixelColor(7, 0, 0, 0);
    strip.show();
  }
  if (tension <= 4.36 && tension >= 4.19)
  {
    // Serial.println("62.5%");
    strip.setPixelColor(0, 0, 0, 255);
    strip.setPixelColor(1, 0, 0, 255);
    strip.setPixelColor(2, 0, 0, 255);
    strip.setPixelColor(3, 0, 0, 255);
    strip.setPixelColor(4, 0, 0, 255);
    strip.setPixelColor(5, 0, 0, 0);
    strip.setPixelColor(6, 0, 0, 0);
    strip.setPixelColor(7, 0, 0, 0);
    strip.show();
  }
  if (tension <= 4.18 && tension >= 4)
  {
    // Serial.println("50%");
    strip.setPixelColor(0, 0, 0, 255);
    strip.setPixelColor(1, 0, 0, 255);
    strip.setPixelColor(2, 0, 0, 255);
    strip.setPixelColor(3, 0, 0, 255);
    strip.setPixelColor(4, 0, 0, 0);
    strip.setPixelColor(5, 0, 0, 0);
    strip.setPixelColor(6, 0, 0, 0);
    strip.setPixelColor(7, 0, 0, 0);
    strip.show();
  }
  if (tension <= 3.99 && tension >= 3.82)
  {
    //  Serial.println("37.5%");
    strip.setPixelColor(0, 255, 0, 0);
    strip.setPixelColor(1, 255, 0, 0);
    strip.setPixelColor(2, 255, 0, 0);
    strip.setPixelColor(3, 0, 0, 0);
    strip.setPixelColor(4, 0, 0, 0);
    strip.setPixelColor(5, 0, 0, 0);
    strip.setPixelColor(6, 0, 0, 0);
    strip.setPixelColor(7, 0, 0, 0);
    strip.show();
  }
  if (tension <= 3.81 && tension >= 3.63)
  {
    //   Serial.println("12.5%");
    strip.setPixelColor(0, 255, 0, 0);
    strip.setPixelColor(1, 255, 0, 0);
    strip.setPixelColor(2, 0, 0, 0);
    strip.setPixelColor(3, 0, 0, 0);
    strip.setPixelColor(4, 0, 0, 0);
    strip.setPixelColor(5, 0, 0, 0);
    strip.setPixelColor(6, 0, 0, 0);
    strip.setPixelColor(7, 0, 0, 0);
    strip.show();
  }
  if (tension <= 3.62)
  {
    //  Serial.println("0% WARNING!");
    strip.setPixelColor(0, 255, 0, 0);
    strip.setPixelColor(1, 0, 0, 0);
    strip.setPixelColor(2, 0, 0, 0);
    strip.setPixelColor(3, 0, 0, 0);
    strip.setPixelColor(4, 0, 0, 0);
    strip.setPixelColor(5, 0, 0, 0);
    strip.setPixelColor(6, 0, 0, 0);
    strip.setPixelColor(7, 0, 0, 0);
    strip.show();
  }
}

void sendEtat() {
  char str[20];
  needToSend = "";
  if (numOfCard == 0) {
    if (eneable) {
      needToSend += tick;
      needToSend += "/1";
    } else {
      needToSend += tick;
      needToSend += "/0";
    }
    needToSend += '\n';
  }
  if (eneable) {
    needToSend += numOfCard;
    needToSend += "/1/";
    needToSend += color;
  } else {
    needToSend += numOfCard;
    needToSend += "/0/";
    needToSend += color;
  }
  needToSend += '\n';
  needToSend += "/";
  needToSend += niveauCharge;
  needToSend += '\n';
  Serial.println(needToSend);
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

