Weather — Android App written in Kotlin with MVVM architecture. In this project I tried to write clean and readable code.
Have kept the design very minimalistic and light which makes it easy to see only the necessary data.

Status: In development (1.0.1-release) 

## Goals
-   [x] To display weather forecasts for different locations 
-   [x] To save locations to favourites 
-   [x] To support different units
-   [x] To add tests for UI and code
-   [x] To show 7-day forecast 
-   [x] To provide documentation
-   [x] To add animations
-   [x] To support night theme
-   [ ] Display weather forecast based on user location -- Not yet done.

## Libraries & Dependencies
-   [Retrofit](https://github.com/square/retrofit) - Type-safe HTTP client for Android and Java by Square, Inc.
-   [Koin](https://github.com/InsertKoinIO/koin) - Pragmatic lightweight dependency injection framework for Kotlin.
-   [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access.
-   [Gson](https://github.com/google/gson) - Gson is a Java library that can be used to convert Java Objects into their JSON representation.
-   [Material Design Components](https://material.io/develop/android/) - Material CardView, Bottom AppBar

## How to use this project
1.  Create API keys:
    -   [OpenWeatherMap](https://openweathermap.org/api) to fetch weather forecasts
    -   [OpenCage Geocoder](https://opencagedata.com/api) to find locations by name

2.  Edit `local.properties` file. Add your API keys here:

```kotlin
// ...
api_key_open_weather_map = "OPEN_WEATHER_MAP_API_KEY"
api_key_open_cage = "OPEN_CAGE_API_KEY"
```

3.  Rebuild project

Note -- Icons made by [Freepik](https://www.flaticon.com/authors/freepik) from [www.flaticon.com](https://www.flaticon.com/) and modified by me :)


