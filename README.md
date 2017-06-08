# 1. Frameworki
## Backend
* [Spring Boot v1.5.3](http://projects.spring.io/spring-boot/)
* [Kotlin v1.1.2](https://kotlinlang.org/)
* [Gradle](https://gradle.org/)

## Frontend
* [AngularJS 1.6](https://angularjs.org/)
* [Npm](https://www.npmjs.com/)
* [Bower](https://bower.io/)
* [Gulp](http://gulpjs.com/)
* [Jade](https://www.npmjs.com/package/jade)

# 2. Uruchomienie projektu
## Baza danych
W procesie developmentu używana jest baza H2. Na heroku pracuje baza PostgreSQL. Na produkcji uruchomiona jest baza SQL Server.

## Backend
Backend uruchamiamy przy użyciu taska gradlowego `bootRun` (`./gradlew bootRun`).
Domyślnie wybrany jest port 8080. 

## Frontend
Do uruchomienia frontendu potrzebujemy npm. Instalujemy globalnie gulpa poprzez polecenie 
`npm install -g gulp`. Następnie pobieramy zależności wywołując `npm install`. 
Następnie uruchamiamy gulp'a, który stworzy nam pliki `.html` z plików `.jade` oraz wstrzyknie
skrypty `.js` oraz pliki `.css` do `index.html`. Uruchamiamy gulpa poprzez polecenie `gulp`.
Podczas developmentu, aby gulp obserwował i reagował na nasze zmiany w plikach uruchamiamy task
`dev` (`gulp dev`) oraz wyłączamy cache w naszej przeglądarce.

# 3. Środowiska
## Produkcyjne
Środowisko produkcyjne jest dostępne pod adresem [prod](https://ticket-booking-prod.herokuapp.com).
Do działania środowiska produkcyjnego potrzebna jest uruchomiona baza SQL Server.
## Testowe
Środowisko testowe jest dostępne pod adresem [testowe](https://ticket-booking.herokuapp.com)

    
