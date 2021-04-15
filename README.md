# Test Mercado Libre
This application was developed on native Android using Kotlin. The architecture that was proposed for a scalable and easily integrated solution was MVVM, since the ViewModel is in charge of orchestrating all the calls to the api asynchronously using Kotlin Coroutines.In the data layer, the classes were modeled using the Parcelable pattern to be able to handle the JSON objects of the API responses. Each activity has its ViewModel which is Observed through the Observer pattern which is responsible for updating the data in the View when there are changes in the data that arrive from the API. Version control was used with Git.

# What the app does

- Home
- Search field
- Viewing search results
- Detail of a product

# Example
<div align="center">
<img src="https://github.com/jachacon36/Test_Mercado_Libre/blob/master/screenshots/example_gif.gif?raw=true" alt="example_gif.gif" width="400px">
</div>

# Screenshots

<div align="center">
    <img src="https://raw.githubusercontent.com/jachacon36/Test_Mercado_Libre/master/screenshots/Screenshot_1.png" width="200px"</img> 
     <img src="https://raw.githubusercontent.com/jachacon36/Test_Mercado_Libre/master/screenshots/Screenshot_2.png" width="200px"</img> 
     <img src="https://raw.githubusercontent.com/jachacon36/Test_Mercado_Libre/master/screenshots/Screenshot_3.png" width="200px"</img> 
     <img src="https://raw.githubusercontent.com/jachacon36/Test_Mercado_Libre/master/screenshots/Screenshot_4.png" width="200px"</img> 
</div>

# Project requirements

- Android Studio v4 or higher
- Clone the project
- Synchronize with the gradle

# Application requirements

- Android version (Jelly Bean	4.1 or higher)

# API

https://developers.mercadolibre.com.ar/es_ar/items-y-busquedas
