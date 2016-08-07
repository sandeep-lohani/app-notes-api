# app-notes-api
CRUD notes

#Introduction
This is a web project to retrieve weather data for a particular city using information from Open Weather Map.

+ Created a website which will return current weather data from OpenWeatherMap.org, based on a city chosen by the user i.e. 
o	London
o	Hong Kong

+ the user input their choice of city via a standard HTML <form>& receive results showing: 
+today’s date
+the city name
+overall description of the weather (e.g. "Light rain", "Clear sky", etc.)
+temperature in Fahrenheit and Celsius
+sunrise and sunset times in 12 hour format (e.g. 9:35am; 11:47pm)

#Implementation details
	+ the backend of the application uses core Java SE8, EE with spring boot
	+ the project compiles on a machine with Maven 3.3 and Java SE8 
	+ the website runs on 8080 port of ‘localhost’, via embedded application server (e.g. Jetty) in spring boot
Set-up
	+ Maven build project using command mvn:install which compiles the application, creates a jar(app-weather-api.jar) and runs the Junit tests.
	+ Run java command to run this spring boot jar java –jar app-weather-api.jar from /target folder
	+ Logs are generated at /var/log/application/weather-api

#Usage
##Dependencies
Included dependencies for spring boot, swagger, Jackson and junit 
##How to Run
	+ Hit URL on browser - http://localhost:8080/local/ 
	+ Input - Enter valid city name in the input box provided i.e. London etc.
	+ Result - The weather data is displayed in tabular format
##Alternate Way to Run
	+ Hit swagger URL on browser - http://localhost:8080/local/swagger-ui.html
	+ Input - Enter valid city name in request parameter i.e. London etc.
	+ Result - The weather data is displayed in response
##Known Issue
	+ Even if a non existing city is entered weather owm api returns some fuzzy values
#To do
	+ UI can be further enhanced to do more validation and proper error response
	+ More exception handling can be put in place

