## Mobiquity Test Challenge 
### **24<sup>th</sup> Febreuary 2019**
## GETTING STARTED	

These instructions will get you a copy of the project up and running on your local machine:

1. Clone the [project repository](https://github.com/mfathy/MobiqTest) from Github.
    ```
    git clone https://github.com/mfathy/MobiqTest.git
    ```
2. Open **Android studio**, Select File | Open... and point to the the project, wait until the project syncs and builds successfully.
3. Run the project using Android studio.

## DISCUSSION 

### Data Sources

There is one level of data persistence: 

*   Network with http caching.

The chosen fetch of data is simple:

*   In every get products request:
    *   Return remote copy.
    *   Return cached copy:
        *   If there is Internet, get the cache that was stored 5 seconds ago.
        *   If there is no Internet, get the cache that was stored 7 days ago.

### Dependency Injection

I've used **dagger** for dependency injection, also I've added different component and modules for test layer.

### Testing

I have included the required Instrumentation, Unit and UI tests with the project:

*   Unit tests for most of the app classes.
*   Integration tests for testing integration between layers components and the layer itself.
*   Ui tests using Espresso:
    *   _For espresso testing I have use normal way of mocking the response using dagger injection >> check espresso tests on [master](https://github.com/mfathy/MobiqTest) branch._
    *   _Another way of testing using [Okhttp mock web server](https://github.com/square/okhttp/tree/master/mockwebserver) to easily mock valid, invalid responses and server errors to show how the UI will acts at those situations >> check espresso tests on [espresso-tests-mockwebserver](https://github.com/mfathy/MobiqTest/tree/espresso-tests-mockwebserver) branch._

To run all test case at once look for the following test suites:

*   **UnitTestSuite**
*   **InstrumentedTestSuite**


### Architecture

Following **[clean architecture](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)** with **MVVM, **which has some of clean architecture principles except layer independence.

![enter image description here](https://raw.githubusercontent.com/mfathy/MobiqTest/master/art/CleanArchitecture.jpg)

![enter image description here](https://raw.githubusercontent.com/mfathy/MobiqTest/master/art/layers.png)

Relation between layers:

*   The UI layer contains views which observes to the presentation layer and listen for data changes.
*   The Presentation layer receives UI layer calls and respond to domain layer interactors and run them.
*   The Domain layer calls data layer.
*   The Data layer manage which source should read from "in our case - only remote", then serve the request back to the domain layer.


#### **MVVM**

The [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture.


*   **Model**: refers either to a domain model, or to the data access layer.
*   **View**: refers to the UI.
*   **View model**: is an abstraction of the view exposing public properties and commands. It has a binder, which automates communication between the view and its bound properties in the view model. 

#### **A typical scenario:**

1. A user opens the app and activity created and starts listening for ViewModel data changes.
2. A call to fetch products in ViewModel is started from the starting activity.
3. ViewModel calls GetProducts use case to start fetching data.
4. GetProducts use case calls data repository asking for data.
5. Data repository determines which data store should read from. "In our case we have remote data store only with http caching to decrease server requests."
6. Remote data store request the data from the server and respond with **Success** otherwise **Error**.

#### **Libraries**

*   [Common Android X libraries](https://developer.android.com/jetpack/androidx) - AndroidX is the open-source project that the Android team uses to develop, test, package, version and release libraries within [Jetpack](https://developer.android.com/jetpack).
*   [Mockito](http://site.mockito.org/) - A mocking framework used to implement unit tests.
*   [Dagger](https://github.com/google/dagger) - for dependency Injection
*   [RxJava](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM. 
*   [Okhttp](http://square.github.io/okhttp/) - An HTTP+HTTP/2 client for Android and Java applications.
*   [Hamcrest](http://hamcrest.org/JavaHamcrest/) -  Junit Matchers
*   [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - A scriptable web server for testing HTTP clients.
*   [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
*   [Gson](https://github.com/google/gson) - a json serialize and deserialize library.
*   [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/) - LiveData, ViewModel.

### **Thank you.**


