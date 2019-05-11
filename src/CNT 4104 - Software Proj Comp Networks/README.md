Remotely-Controlled Catapult
==========================================================================
The objective of this project is to apply the fundamentals of an embedded system to remotely control a catapult, launching small-scale projectiles via an Android device. The catapult project will fulfill a need to effectively reduce user intervention, such that the user won’t have to manually retract or lock the catapult arm into place but will only be required to reload the device. 

Android Application Source Code
--------------------------------------------------------------------------
Executable file (.apk) for the Android application, which provides input to the Arduino through button clicks. The Android application also displays system state when the device is not connected to a Bluetooth device or when a button click event is handled.

Arduino Source Code
--------------------------------------------------------------------------
The Arduino source code, written in a C based language, continually listens for input from the Android application and determines the behavior exhibited by each micro-servo on the catapult hardware.

