# Revolut Recruitment Task ![](/app/src/main/res/mipmap-mdpi/ic_launcher.png)

## Task
List all currencies you get from the endpoint (one per row). Each row has an input where you can enter any amount of money. When you tap on a currency row it should slide to the top and it's input becomes the first responder. When you’re changing the amount the app must simultaneously update the corresponding value for other currencies.

## Screenshots
<p align="center">
 <img src="/screenshots/01.gif" alt="List of currencies" align="left">
 <img src="/screenshots/02.gif" alt="Change value of currency" align="center">
 <img src="/screenshots/03.gif" alt="Click multiple currencies" align="right">
</p>

## Build variants
This solution consist of 4 build variants:
- mock
- develop
- test
- production

Except mock build every build variant should have different base api url. Currently there is only one used for rest of them. It can be easily changed in `Config.kt` file.

## Problem
I have decided to use different api call when it is different currency on the top of the list. I came across problem that api does not return very small values for currency rates.

For example api call `https://hiring.revolut.codes/api/android/latest?base=IDR` returns a lot of zero values.

## License
```
 Copyright 2020 Wojciech Kryg

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
