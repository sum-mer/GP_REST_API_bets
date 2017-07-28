# gp_rest_bets

REST-API with one-resource /bets with one method to add new bet and one method for displaying bets for specified player.

## Prerequisites
- Java 1.8
- Maven

## Description

Bet can be added by POST method (localhost:8080/bets) with playerName, gameName and moneyAmount for this bet.
![print-post](https://user-images.githubusercontent.com/1145530/28715621-28ad2956-7399-11e7-9df8-e6fe936ab94a.JPG)


Bets for specified player are retrieved by GET method (locahost:8080/bets/{playerName}).
![print-get](https://user-images.githubusercontent.com/1145530/28715623-2b40283a-7399-11e7-9e5d-c4b8f913a511.JPG)

## How To
- clone repository from github
git clone https://github.com/sum-mer/GP_REST_API_bets.git

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
