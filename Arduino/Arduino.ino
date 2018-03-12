#include <Adafruit_NeoPixel.h>

#define PIN            24

#define NUMPIXELS      8*1
#define BRIGHTNESS 20
#define NUMCOUNT      100000


Adafruit_NeoPixel strip = Adafruit_NeoPixel(60, PIN, NEO_GRB + NEO_KHZ800);


int mode = -1;
boolean eneable = false;
int color = 0;
String master = "master:";
int numOfCard = 0;
boolean needUpdate = false;
int count = 0;

void setup() {
  
  strip.begin();
  
  strip.show();
  Serial2.begin(115200);
  Serial.begin(9600);
  Serial2.setTimeout(10);
}

void loop() {
  if (Serial2.available() > 0) {           
        String trame = Serial2.readString();
         
        if(master.equalsIgnoreCase(trame.substring(0,7))){

            trame = trame.substring(7);
            Serial.println("master receive" );
            char buf[sizeof(trame)];
            trame.toCharArray(buf, sizeof(buf));
            const char s[2] = "/";
            char *token;
             
            token = strtok(buf, s);
            if(token !=NULL){
              mode = atoi( token );
             
            }
            Serial.print("Mode :" );
            Serial.println(mode );
            token = strtok(NULL, s);
            if(token !=NULL){
              color = atoi( token );
              
            }
            eneable = true;
            needUpdate = true;

       }    
  }else{
    strip.setBrightness(BRIGHTNESS);
    
        switch(mode){
          case -1:
           
            count++;
            if(count >= NUMCOUNT){
               blank();
               sendEtat();
               count = 0;
            }
          
          break;
          case 0:
          
            
            count++;
            if(count >= NUMCOUNT){
              setColor();
               sendEtat();
               count = 0;
            }
          
          break;
           case 1:
            count++;
            if(count >= NUMCOUNT){
               eneable = !eneable;
               if(eneable){
                setColor();
               }else{
                blank();
                
               }
               sendEtat();
               count = 0;
            }
          break;
          
        }
        //Serial.print(count);
  }
}

 void setColor(){
    switch(color){
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

void sendEtat(){
  char str[20];
  if(eneable){
  sprintf(str, "%d/1/%d", numOfCard,color);
  }else{
    sprintf(str, "%d/0/%d", numOfCard,color);
  }
  Serial2.println(str);
}

 void blank(){
for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 0, 0, 0);
    strip.show();
  }
}

 void red(){
for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 255, 0, 0);
    strip.show();
  }
}
 void blue(){
for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 0, 0, 255);
    strip.show();
  }
}
 void green(){
for (int i = 0; i < NUMPIXELS; i++) {
    strip.setPixelColor(i, 0, 255, 0);
    strip.show();
  }
}
