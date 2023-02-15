# Java-locity

This is a Java Spring Boot application that serves as a travel itinerary planner. It allows users to create, update, and view travel itineraries for their trips, along with user profile picture uploads. The application also integrates with the Trip Advisor and Mapbox APIs for additional trip planning features.

### Features

* User authentication and authorization using Spring Security.
* Integration with the Trip Advisor API for retrieving information about travel destinations, hotels, and attractions.
* Integration with the Mapbox API for displaying selected start point on a map.
* Create, update, delete and view travel itineraries, including trip dates, destinations, and activities.

# Getting Started

### Prerequisites
To run this application, you will need dependencies for the following:

* Java 17
* Maven
* Spring Boot
* Spring Security
* lombok
* mysql-connector-java
* thymeleaf
* thymeleaf-extras-springsecurity6

### Installation
Clone the repository:
* https://github.com/JavaCohortTeam3/Java-locity.git

Set up the necessary environment variables.
* travleKey = <your-tripadvisor-api-key>
* mapKey = <your-mapbox-api-key>


# Usage
### Navagation
To navigate to any portion of the site you can use the navbar buttons. The navbar buttons are as follows and are dynamically displayed based on the user's authentication status
* Javalocity Logo - Home Page
* Plan a Trip - Create Itinerary Page
* Profile - User Profile Page
* Log Out - Log Out of the Application
* Log In - Log In to the Application
* Register - Register a New User
* Account Info - User Account Info Page
* The A-team - About Us Page

### Creating a User
To use the application, you must create a user account. Navigate to the registration page through the navbar and enter your username, email, and password.

### Logging In
Once you have created a user account, you can log in with your username and password.

### Updating User Profile Picture
To update your user profile picture, navigate to the user account info page and click the "add profile picture" button. From there, you can upload a new profile picture.

### Creating a Travel Itinerary
To create a new travel itinerary, navigate to the "Create Itinerary" page through the "Plan a Trip" button on the navbar. Enter the trip details, including the trip destination in the mapbox search bar and the  start and end dates. Then click the "submit button". From here you can add hotels, restaurants and attractions to your itinerary.Once finished you click the "finalize trip" to save your itinerary.

### Viewing a Travel Itinerary
To view a travel itinerary, navigate to the "Profile" button listed in the navbar. Click the "View Trip" button on the itinerary you want to view.

### Updating a Travel Itinerary
To update a travel itinerary, navigate to the "Profile" . Click on the itinerary you want to update, then click the "Add to Trip" button. Update the itinerary details as needed. To remove the trip click the "Delete Trip" button on the itinerary you wish to remove.