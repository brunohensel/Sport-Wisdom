# Sport-Wisdom
> An Android study case, written in Kotlin as a final project for the Nanodegree course.

## Overview

This project is a simple demo for retrieving data from the https://www.thesportsdb.com/api.php, presenting a list of Sports, Leagues and next Events. The user can save an Event
and at the day that the Event ocours, the user will receive a Notification about it. It's all possible search for teams of various sports and see some info about them. It is also possible to save this information.

The architecture implemented was a MVVM + MVI, in order to benefits the unidirectional bahvaior with Actions, Intents and States.

## Knowledge Stack

This project leverages on

- 100% AndroidX
- one single Activity and multiple Fragments
- Kotlin Coroutines to manage background services
- Kotlin Flow for end-to-end reactive programming
- Dagger Hilt for Dependecy Injection
- Moshi to serialize Json
- OkHttp + Retrofit for networking over HTTP
- Room for database
- Coil for load images
- Navigation component to manage the navigation between fragments
- Workmanager to asynchronously handle the notification part
- Threetenabp to manage Dates
- Mockito for Tests and Truth for assertions

