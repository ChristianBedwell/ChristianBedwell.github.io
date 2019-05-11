Arduino Turreted Laser Pointer Device
===========================================================================================
The objective of this project is to fulfill a need in a scenario where an indoors pet cat is not always occupied and provide a means to keep that cat from finding activities to do that are otherwise damaging to household furniture and/or electronics. When cats play, not only is it mentally stimulating, but it also keeps cats fit and in shape. Cats enjoy stalking, chasing, and pouncing on objects. The laser pointer device projects a red dot onto a surface and dances around for the cat to chase. An automated laser pointer toy would not only ensure that the user would not have to be present to use the device but could also be used in scenarios where the owner is not always around to wave a consumer model laser pointer around. If the user decides that they want to be more interactive with their cat, they may provide manual input to the device. Since younger cats are also the less well-behaved ones, this device will yield to consumers, for the reason that cats of a couple years of age or less will be more inclined to see the laser light clearly, in addition to being less likely to recognize the source (that being the pan and tilt mount). To address safety concerns regarding the radiation of the laser diode inflicting eye damage to a cat, a safeguard feature will be added to turn the laser off at any given moment.

Android Application Source Code
-------------------------------------------------------------------------------------------
The executable file (.apk) for the Android application, which provides input to the Arduino through button clicks. The Android application also displays system state when the device is not connected to a Bluetooth device or when a button click event is handled.

Arduino Source Code
-------------------------------------------------------------------------------------------
The Arduino source code, written in a C based language, continually listens for input from the Android application and determines the behavior exhibited by each micro-servo on the turreted laser pointer hardware.
