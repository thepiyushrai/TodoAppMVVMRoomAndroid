# TodoAppMVVMRoomAndroid
This Repo is about how to use Room Database with MVVM Architecture with the help of a simple application.

What exactly is MVVM?
A Model-View-ViewModel architecture is the MVVM architecture.
It enables the separation of user interface logic from business (or back-end) logic.
Its goal is to keep UI code simple and devoid of app logic, making it easier to manage and test.
MVVM is composed of the following layers:

Model:

The model represents the Application's data and business logic. It is made up of business logic - local and remote data sources, model classes, and a repository.

View:

The view is made up of UI code (Activity, Fragment), and XML. The view's role in this pattern is to observe (or subscribe to) a ViewModel observable to obtain data for updating UI elements.

ViewModel:

The ViewModel is a class that provides data to the user interface and survives configuration changes. A ViewModel serves as a hub of communication between the Repository and the UI.
One of the most important implementation strategies for this layer is to decouple it from the View, which means that the ViewModel should be unaware of the view with which it is interacting.

Room Database
The Room persistence library acts as an abstraction layer over SQLite, allowing for fluent database access while leveraging SQLite's full power. Room, in particular, offers the following advantages:

SQL queries are validated at compile time.
Annotations that reduce repetitive and error-prone boilerplate code.
Database migration paths have been simplified.
