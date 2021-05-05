# WarehouseApp

Warehouse App Test - Anamika

App Solution try to show architecture approach of solving problem.


-> App uses Android latest navigation architecture with single activity
-> MVVM Architecture
-> Dependency Injection using Dagger-Hilt
-> Data Binding
-> Paging Library
-> Retrofit
-> Coroutine

-> Best Practice for API key was implemented,
It will not be reflected in GIT
-> So please add before running the project on Android Studio
Please put this under gradle.properties
SUBSCRIPTION_KEY =put the key here


Main Screen: Have 2 Button
-> Scan
-> Search

Search:
-> Type in any keyword on search option provided in toolbar.
-> Searching item will display result in RecyclerView.
-> Android Jetpack Paging Library has been used to show result with pagination
-> Clicking on particular row, navigate user to detail view.
->Data is passed from search row to detail view using Safe Args

Scan:
-> Scan the Barcode using ML Kit Library
https://developers.google.com/ml-kit
-> Search Barcode in API 
-> Fetch result and display data accordingly

Note: I worked with QR code and Barcode scanner a lot while I was working on my research project for almost 1.5 years. That time I used Zxing.

I was sure the there might be something new now and I read about Barcode API detection by Google (Mobile Vision API ).

Now they are part of ML KIT. 
The Mobile Vision API is now a part of ML Kit. 


