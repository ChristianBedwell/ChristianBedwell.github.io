Remotely-Controlled Catapult
===========================================================================================
The objective of this project is to apply the fundamentals of embedded systems design to develop a remotely-controlled catapult, launching and reloading small-scale projectiles via web interface. The catapult project will fulfill a need to effectively reduce user intervention, with no need to reload, manually retract, or lock the catapult arm into place, but will only be required to fill the catapult with ammunition when it is empty. This is made possible by applying the latest technologies in software development, while emphasizing on an Internet-connected device. 

Arduino Web Server Source Code
-------------------------------------------------------------------------------------------
The Arduino web server source code, written in a C based language, continually listens for input from the web client and determines the behavior exhibited by each micro-servo on the catapult hardware. 

Web Client Source Code
--------------------------------------------------------------------------------------------
The Web client source code, written in Hypertext Markup Language (HTML) as well as Asynchronous JavaScript and XML (AJAX), allows the user to send button states (Reload, Arm, or Launch) to the web server in the form of HTTP GET request objects, while dynamically updating this status information on the screen.
