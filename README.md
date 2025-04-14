
<p align="center">
  <img src="https://israelnoticias.com/wp-content/uploads/2022/08/Bereshit-Luna.jpg" alt="Logo" width="450" height="300" align="right">
</p>

#  Bereshit Project

**Welcome to Bereshit Project !**  
- In This project we simulate a successful landing with Beresheet spacecraft.  
- In addition, a report explaining the causes of the Beresheet crash is attached.  


## Team Members
Liron Cohen - 312324247

## Table of Contents
- [Background](#Background)
- [Beresheet Crash Landing Event](#Beresheet-Crash-Landing-Event)
- [Project Description](#project-description)
- [About the simulation](#About-the-simulation)
- [Results](#Results)



## Background
The Israeli spacecraft "Beresheet", launched by SpaceIL and Israel Aerospace Industries, was intended to be the first private spacecraft to land on the Moon.   
On April 11, 2019, during the final landing phase, a technical malfunction occurred, leading to a loss of control and a crash on the lunar surface.

![Israel spaceship](https://pic1.calcalist.co.il/PicServer3/2019/04/11/898977/3LM.jpg)


## Beresheet Crash Landing Event
- **Star tracker malfunction on launch night**: A pair of cameras designed to determine the spacecraft's angle in space were apparently blinded by dust particles that landed on their shields as it separated from the launch vehicle. The team had to find creative solutions to get around this problem - including tilting the spacecraft on its side during maneuvers, and **using accelerometers** instead of the star trackers.

- **Unexpected spacecraft computer reboots**: Several days after launch, the spacecraft's computer rebooted unexpectedly, and the reboot problem continued to plague the spacecraft, apparently due to a malfunction in the electronics box that mediates between the computer and the spacecraft's systems, possibly due to exposure to radiation.
The low cost materials were damaged by the sun radiations which caused the spaceship system to restart

- **Because there was only one computer**, software extensions designed to overcome problems were not burned into permanent memory and were erased at each reboot. And they had to be loaded again in a command file.
Because of the low budget the spaceship had no redundant system

- **The spacecraft had two such accelerometers called IMU (short for Inertial Measurement Unit)**
While landing,the acceleration sensor shutdown (IMU2),so the crew had to make a quick decision to rely on IMU1 or to restart IMU2, And they choose to restart

- **Activating IMU2 blocked data transmission from IMU1 because of some system logic**, Therefor the system did not receive any acceleration data for about a second
Therefore he declared a navigation failure,
In such a case he was programmed to reboot himself  
**NOTE-**
The reboot lasted less than two seconds, but the computer returned to activity without the software extensions, which according to the landing command file were supposed to be loaded every minute for safety. As a result, the computer rebooted itself over and over again, and only after about five such reboots did the extensions finally load.

- **The computer reboots caused the spacecraft's main engine to shut down**, which at this point was supposed to be running all the time and slowing down the landing. The computer was supposed to start the engine immediately, but here a malfunction occurred that the engineering team discovered before launch but did not have time to fix: to restart the engine, it must receive voltage from two sources, but following the reboot, only one of them worked – and the main engine did not start. The spacecraft continued its diagonal fall towards the moon, with only the small direction engines continuing to operate, and even maintaining its correct direction.

- **Crash** - The spacecraft hit the lunar surface at a speed of more than 3,000 km/h, and probably crashed into pieces.


**For more information on the technical causes of the crash and the technical sequence of events leading to the crash :**  
 www.github


![Israel spaceship Error](https://d15djgxczo4v72.cloudfront.net/s3fs-public/davidson_images/IMG_20190411_233237_580.jpg)

**Height: 149 meters, horizontal velocity: 946.7 meters per second, vertical velocity: 134.3 meters per second.**  
*Latest data from Beresheet | Source: SpaceIL*


## Causes of the spacecraft crash
- Mission rushed (more additional testing required)
- IMU2 issue
- Low cost materials
- Low experienced crew
- System restarts
- Data transfer block
- Critical control commands were not pre-programmed into the system but were instead loaded into RAM during the mission


## Project Description

This project provides tools to detect Welcome to Bereshit Project ! This repository contains __


## About the simulation
- In This project we simulate a successful landing with Beresheet spacecraft.    
- Using PID controller - use to control the engine thrust and vehicle angle, It's done by maintaining a specific vertical/horizontal speed and angle.  
- The starting point mimcs bereshit actual landing settings
- Landing safe

![‏‏CONTROLER](https://github.com/user-attachments/assets/c31baf72-ccdf-45e0-bd9e-f7dc9b61c7e2)



## Results
----

![fuel](https://github.com/user-attachments/assets/0c103555-399e-4f0d-acee-7a34db604123)
![alt](https://github.com/user-attachments/assets/34c1f8a7-f9d6-43f8-9fcb-57140e37e37f)
![vs](https://github.com/user-attachments/assets/bc8beb4c-1b30-41b3-bf67-2c9cac3c1af2)
![hs](https://github.com/user-attachments/assets/7b8178d0-2106-4e33-bf66-9c5e99c0b586)



![Israel spaceship to the Moon](https://i0.wp.com/www.enlacejudio.com/wp-content/uploads/2015/10/Israel-spaceship-to-the-Moon.jpg?fit=800%2C400&ssl=1)
