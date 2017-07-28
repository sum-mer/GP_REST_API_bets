# GP REST API BETS

REST-API with one-resource /bets with two methods.
Method to add new bet and method for displaying player's bets.

## Prerequisites
- Java 1.8
- Maven

## Description

Bet can be added by POST method (localhost:8080/bets) with playerName, gameName and moneyAmount for this bet.
![print-post](https://user-images.githubusercontent.com/1145530/28720664-25cac030-73ae-11e7-8a29-295f3ed2b353.JPG)


Bets for specified player are retrieved by GET method (locahost:8080/bets/{playerName}).
![print-get](https://user-images.githubusercontent.com/1145530/28720660-22b7c370-73ae-11e7-96cd-d85f2cfe21b7.JPG)

## How To
- clone repository from github:
git clone -b rest_api https://github.com/sum-mer/GP_REST_API_bets.git

run from IDE:

 - mvn clean compile

 - run com.gp.bets.Application.main()

run from JAR:

 - mvn clean package

 - java -jar bets-1.0.jar
 
 use:
 - run http/rest client to make HTTP requests and view responses


## Tests
5 unit tests can be run with:

mvn test
