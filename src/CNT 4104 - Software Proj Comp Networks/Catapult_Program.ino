#include <SoftwareSerial.h>
#include <Servo.h>

SoftwareSerial btSerial(12,13); // RX, TX

Servo lock_servo;
Servo tension_servo;
String button_state;  
  
// setup code which will only run once
void setup() {
  // estabilish Bluetooth serial connection with baud rate of 38400
  btSerial.begin(9600);
  Serial.begin(9600);

  // attach a pin as a servo driver for lock and tension servos
  lock_servo.attach(10);
  tension_servo.attach(11);
  
  // set initial position of lock and tension servos
  lock_servo.write(90);
  tension_servo.write(0);
}

// main code which will be ran repeatedly
void loop() {
  if(btSerial.available() > 0) {
    while(btSerial.available() > 0) {
      button_state = btSerial.readString();
    }
    actionPerformed(button_state);
  }
}

void actionPerformed(String button_state) {
  // print the string recieved to serial monitor for debugging
  Serial.print("The full string recieved is: ");
  Serial.println(button_state);    
  
  // if button state is equal to "arm"
  if(button_state == "arm") {
    // set the angle of the lock servo to 90 degrees
    lock_servo.write(0);
    // set the angle of the tension servo to 180 degrees
    tension_servo.write(180);
  }
  // if button state is equal to "launch"
  else if(button_state == "launch") {
    // set the angle of the lock servo to 0 degrees
    lock_servo.write(90);
    delay(1000);
    // set the angle of the tension servo to 0 degrees
    tension_servo.write(0);
  }
}
