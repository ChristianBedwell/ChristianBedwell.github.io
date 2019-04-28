#include <SPI.h>
#include <Ethernet.h>
#include <Servo.h>
#include <SD.h>
 
Servo reload_servo;  // create servo object to control reload
Servo lock_servo;  // create servo object to control lock
Servo tension_servo;  // create servo object to control tension

byte mac[] = {0xA8, 0x61, 0x0A, 0xAE, 0x14, 0xE8}; // physical mac address
byte ip[] = {69, 88, 163, 17};  // global ip address
EthernetServer server(80); // server port (HTTP)

File webPage; // HTTP web interface
String httpRequest; // request sent by AJAX

void setup() {

  // disable Ethernet chip
  pinMode(10, OUTPUT);
  digitalWrite(10, HIGH);
  
  reload_servo.attach(6);  // assign PWM pin 6 for reload servo
  lock_servo.attach(2);  // assign PWM pin 2 for lock servo
  tension_servo.attach(3); // assign PWM pin 3 for tension servo

  reload_servo.write(45); // set initial position of reload servo to 45 degrees
  lock_servo.write(90); // set initial position of lock servo to 90 degrees
  tension_servo.write(0); // set initial position of tension servo to 0 degrees  
  
  // enable serial data print for debugging
  Serial.begin(9600);

  // begin Ethernet server
  Ethernet.begin(mac, ip);
  
  // configuration successful
  Serial.print(F("SUCCESS - Server is at IP: "));
  Serial.println(Ethernet.localIP());
    
  server.begin();
  Serial.println(F("Server Started"));  

  if (!SD.begin(4)) {
    Serial.println(F("ERROR - SD card intitialization failed!"));
    return; // init failed
  }
  // init successful
  Serial.println(F("SUCCESS - SD card initialized."));
  
  // check for index.htm file
  if (!SD.exists("index.htm")) {
    Serial.println(F("ERROR - Can't find index.htm file!"));
    return; // can't find index file
  }
  // found index file
  Serial.println(F("SUCCESS - Found index.htm file."));
  Serial.println(F("-------------------------------"));
}

void loop() {
  EthernetClient client = server.available();
  // if server is available
  if (client) {
    Serial.println(F("Client status: connected"));
    boolean currentLineIsBlank = true;
    // while client is connected
    while (client.connected()) {
      // if client is available, read HTTP GET requests into char buffer
      if (client.available()) {
        char c = client.read();

        if (httpRequest.length() < 100) {
          httpRequest += c;
        }

        // if HTTP GET request has ended
        if (c == '\n' && currentLineIsBlank) {

          // send a standard http response header
          client.println("HTTP/1.1 200 OK");
		  
		      // if HTTP GET request contains button state, check button state
		      if(httpRequest.indexOf("button_state?") > 0) {
            getButtonState();
		      }
          // else send HTML page to client
          else {
			      Serial.println(F("Response Sent to Client: A HTML Page"));
            client.println("Content-Type: text/html");
			      client.println("Connection: keep-alive");
			      client.println();
			
            // send web page
            webPage = SD.open("index.htm"); // open web page file
            if(webPage) {
              while(webPage.available()) {
                client.write(webPage.read()); // send web page to client
              }
              webPage.close();
            }
          }
		      Serial.println(httpRequest + "\n");
		      httpRequest = "";
		      break;
		    }

        // if char is a newline, current line is blank
        if(c == '\n') {
          currentLineIsBlank = true;
        }

        // if char is not a carriage return, current line is not blank
        else if(c != '\r') {
          currentLineIsBlank = false;
        }
      }
    }
    delay(100);
    client.stop();
  }
}

// performs an action corresponding to the current button state
void getButtonState(void) {
    // Reload button clicked
    if (httpRequest.indexOf("reload") > 0) {
        // set the angle of the reload servo to 0 degrees
        reload_servo.write(0); 
        delay(300);

        // set the angle of the reload servo to 45 degrees
        reload_servo.write(45);        
        Serial.println(F("Reloading catapult..."));
    }
 
    // Arm button clicked
    else if (httpRequest.indexOf("arm") > 0) {
        // set the angle of the lock servo to 0 degrees
        lock_servo.write(0);   
            
        // set the angle of the tension servo to 180 degrees
        tension_servo.write(180);        
        Serial.println(F("Arming catapult..."));
    }
	
	  // Launch button clicked
    else if (httpRequest.indexOf("launch") > 0) {
        // set the angle of the lock servo to 90 degrees
        lock_servo.write(90);
        delay(1000);
            
        // set the angle of the tension servo to 0 degrees
        tension_servo.write(0);          
        Serial.println(F("Launching catapult..."));
    }
}
