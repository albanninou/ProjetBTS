#include <Adafruit_NeoPixel.h>

#define PIN            9

#define NUMPIXELS      56
#define BRIGHTNESS 2
int b = 5;
float tension;
int valeur;
int vitesse = 50;

Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);


void setup() {

  strip.begin();
  Serial.begin(9600);

  strip.show();
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
}


void loop() {
  strip.setBrightness(20);
  
  if (b == 0)
  {
    defaut();
  }
  if (b == 1) {
    red();
  } 
  if (b == 2) {
    green();
  }
  if (b == 3) {
    white();
  }
  if (b == 4) {
    bleu();
  }
  if (b == 5) {
    clig_red();
  }
  if (b == 6) {
    clig_green();
  }
  if (b == 7) {
    charge();
  }

  valeur = analogRead(A0);
  tension = valeur * (3.3 / 1023.0);
  if (tension >= 2.04)
  {
    digitalWrite(7, HIGH);
    digitalWrite(8, HIGH);
  }
  if (tension <= 2.03  || tension >= 2.92)
  {
    digitalWrite(7, LOW);
    digitalWrite(8, LOW);
  }
 
}


void charge() {
  strip.setBrightness(50);

  Serial.println(tension);
  if (tension <= 3.15 && tension >= 3.02)
  {
    Serial.println("100%");
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
  if (tension <= 3.01 && tension >= 2.9)
  {
    Serial.println("87.5%");
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
  if (tension <= 2.89 && tension >= 2.78)
  {
    Serial.println("75%");
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
  if (tension <= 2.77 && tension >= 2.66)
  {
    Serial.println("62.5%");
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
  if (tension <= 2.65 && tension >= 2.54)
  {
    Serial.println("50%");
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
  if (tension <= 2.53 && tension >= 2.42)
  {
    Serial.println("37.5%");
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
  if (tension <= 2.41 && tension >= 2.30)
  {
    Serial.println("12.5%");
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
  if (tension <= 2.29)
  {
    Serial.println("0% WARNING!");
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

void defaut() {
  for (int i = 0; i < 8; i++) {
    strip.setPixelColor(i, 255, 0, 0);
    strip.show();
  }
  delay(500);
  for (int i = 0; i < 8; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
  delay(500);
}

void clig_green() {
  for (int i = 0; i < 24; i++) {
    strip.setPixelColor(i, 0, 255, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 48; i++) {
    strip.setPixelColor(i, 0, 255, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 0, 255, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 24; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 48; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
  delay(vitesse);
}



void clig_red() {
  for (int i = 0; i < 24; i++) {
    strip.setPixelColor(i, 255, 0, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 48; i++) {
    strip.setPixelColor(i, 255, 0, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 255, 0, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 24; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 48; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
  delay(vitesse);
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
  delay(vitesse);
}



void red() {
  strip.setPixelColor(0, 255, 0, 0);
  strip.setPixelColor(1, 255, 0, 0);
  strip.setPixelColor(2, 255, 0, 0);
  strip.setPixelColor(3, 255, 0, 0);
  strip.setPixelColor(4, 255, 0, 0);
  strip.setPixelColor(5, 255, 0, 0);
  strip.setPixelColor(6, 255, 0, 0);
  strip.setPixelColor(7, 255, 0, 0);
  strip.setPixelColor(8, 255, 0, 0);
  strip.setPixelColor(9, 255, 0, 0);
  strip.setPixelColor(10, 255, 0, 0);
  strip.setPixelColor(11, 255, 0, 0);
  strip.setPixelColor(12, 255, 0, 0);
  strip.setPixelColor(13, 255, 0, 0);
  strip.setPixelColor(14, 255, 0, 0);
  strip.setPixelColor(15, 255, 0, 0);
  strip.setPixelColor(16, 255, 0, 0);
  strip.setPixelColor(17, 255, 0, 0);
  strip.setPixelColor(18, 255, 0, 0);
  strip.setPixelColor(19, 255, 0, 0);
  strip.setPixelColor(20, 255, 0, 0);
  strip.setPixelColor(21, 255, 0, 0);
  strip.setPixelColor(22, 255, 0, 0);
  strip.setPixelColor(23, 255, 0, 0);
  strip.setPixelColor(24, 255, 0, 0);
  strip.setPixelColor(25, 255, 0, 0);
  strip.setPixelColor(26, 255, 0, 0);
  strip.setPixelColor(27, 255, 0, 0);
  strip.setPixelColor(28, 255, 0, 0);
  strip.setPixelColor(29, 255, 0, 0);
  strip.setPixelColor(30, 255, 0, 0);
  strip.setPixelColor(31, 255, 0, 0);
  strip.setPixelColor(32, 255, 0, 0);
  strip.setPixelColor(33, 255, 0, 0);
  strip.setPixelColor(34, 255, 0, 0);
  strip.setPixelColor(35, 255, 0, 0);
  strip.setPixelColor(36, 255, 0, 0);
  strip.setPixelColor(37, 255, 0, 0);
  strip.setPixelColor(38, 255, 0, 0);
  strip.setPixelColor(39, 255, 0, 0);
  strip.setPixelColor(40, 255, 0, 0);
  strip.setPixelColor(41, 255, 0, 0);
  strip.setPixelColor(42, 255, 0, 0);
  strip.setPixelColor(43, 255, 0, 0);
  strip.setPixelColor(44, 255, 0, 0);
  strip.setPixelColor(45, 255, 0, 0);
  strip.setPixelColor(46, 255, 0, 0);
  strip.setPixelColor(47, 255, 0, 0);
  strip.setPixelColor(48, 255, 0, 0);
  strip.setPixelColor(49, 255, 0, 0);
  strip.setPixelColor(50, 255, 0, 0);
  strip.setPixelColor(51, 255, 0, 0);
  strip.setPixelColor(52, 255, 0, 0);
  strip.setPixelColor(53, 255, 0, 0);
  strip.setPixelColor(54, 255, 0, 0);
  strip.setPixelColor(55, 255, 0, 0);
  strip.show();

  delay(500);
  strip.setPixelColor(0, 0, 0, 0);
  strip.setPixelColor(1, 0, 0, 0);
  strip.setPixelColor(2, 0, 0, 0);
  strip.setPixelColor(3, 0, 0, 0);
  strip.setPixelColor(4, 0, 0, 0);
  strip.setPixelColor(5, 0, 0, 0);
  strip.setPixelColor(6, 0, 0, 0);
  strip.setPixelColor(7, 0, 0, 0);
  strip.setPixelColor(8, 0, 0, 0);
  strip.setPixelColor(9, 0, 0, 0);
  strip.setPixelColor(10, 0, 0, 0);
  strip.setPixelColor(11, 0, 0, 0);
  strip.setPixelColor(12, 0, 0, 0);
  strip.setPixelColor(13, 0, 0, 0);
  strip.setPixelColor(14, 0, 0, 0);
  strip.setPixelColor(15, 0, 0, 0);
  strip.setPixelColor(16, 0, 0, 0);
  strip.setPixelColor(17, 0, 0, 0);
  strip.setPixelColor(18, 0, 0, 0);
  strip.setPixelColor(19, 0, 0, 0);
  strip.setPixelColor(20, 0, 0, 0);
  strip.setPixelColor(21, 0, 0, 0);
  strip.setPixelColor(22, 0, 0, 0);
  strip.setPixelColor(23, 0, 0, 0);
  strip.setPixelColor(24, 0, 0, 0);
  strip.setPixelColor(25, 0, 0, 0);
  strip.setPixelColor(26, 0, 0, 0);
  strip.setPixelColor(27, 0, 0, 0);
  strip.setPixelColor(28, 0, 0, 0);
  strip.setPixelColor(29, 0, 0, 0);
  strip.setPixelColor(30, 0, 0, 0);
  strip.setPixelColor(31, 0, 0, 0);
  strip.setPixelColor(32, 0, 0, 0);
  strip.setPixelColor(33, 0, 0, 0);
  strip.setPixelColor(34, 0, 0, 0);
  strip.setPixelColor(35, 0, 0, 0);
  strip.setPixelColor(36, 0, 0, 0);
  strip.setPixelColor(37, 0, 0, 0);
  strip.setPixelColor(38, 0, 0, 0);
  strip.setPixelColor(39, 0, 0, 0);
  strip.setPixelColor(40, 0, 0, 0);
  strip.setPixelColor(41, 0, 0, 0);
  strip.setPixelColor(42, 0, 0, 0);
  strip.setPixelColor(43, 0, 0, 0);
  strip.setPixelColor(44, 0, 0, 0);
  strip.setPixelColor(45, 0, 0, 0);
  strip.setPixelColor(46, 0, 0, 0);
  strip.setPixelColor(47, 0, 0, 0);
  strip.setPixelColor(48, 0, 0, 0);
  strip.setPixelColor(49, 0, 0, 0);
  strip.setPixelColor(50, 0, 0, 0);
  strip.setPixelColor(51, 0, 0, 0);
  strip.setPixelColor(52, 0, 0, 0);
  strip.setPixelColor(53, 0, 0, 0);
  strip.setPixelColor(54, 0, 0, 0);
  strip.setPixelColor(55, 0, 0, 0);
  strip.show();
  delay(500);
}


void green() {
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 0, 255, 0);
    strip.show();
  }
  delay(500);
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 0, 255 , 0);
    strip.show();
  }
  delay(500);
}

void white() {
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 255, 255, 255);
    strip.show();
  }
  delay(500);
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 255, 255 , 255);
    strip.show();
  }
  delay(500);
}

void bleu() {
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 0, 0, 255);
    strip.show();
  }
  delay(500);
  for (int i = 0; i < 56; i++) {
    strip.setPixelColor(i, 0, 0 , 255);
    strip.show();
  }
  delay(500);
}




