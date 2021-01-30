# Portfolio & Profile

_This project is a test_

## Starting

_You can get a copy of this project, cloning this repository._

```
git clone https://github.com/geogy24/portfolio.git
```

### Pre-requirements

_This are the thecnologies involved to the project_

- Java 11.x version
- MySQL 8.x version

### Installation

- _To install MySQL server on Ubuntu 20.04 desktop, follow next [guide](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04-es)_
- _To install Java 11 on Ubuntu 20.04, follow next [guide](https://www.digitalocean.com/community/tutorials/como-instalar-java-con-apt-en-ubuntu-18-04-es)_

## Running


If you want to run this project on console, you need to set the environment variables, which are

```
export MYSQL_DB_HOST=
export MYSQL_DB_PORT=
export MYSQL_DB_NAME=
export MYSQL_DB_USERNAME=
export MYSQL_DB_PASSWORD=
export TWITTER_ACCESS_TOKEN=
export TWITTER_ACCESS_TOKEN_SECRET=
export TWITTER_CONSUMER_SECRET=
export TWITTER_CONSUMER_KEY=
```

Next, run with gradle

```
./gradlew bootRun
```

The below command initialize a Tomcat server at 8080 port.

### Documentation

You can check how to do a request and what parameters are required in our documentation,
running the server and make a request to http://localhost:8080/swagger-ui.html.

**Note**
If you need the documentation on JSON format you can get it, make a request to
http://localhost:8080/v2/api-docs

## Run tests️


### Follow next coding rules

_Follow next [coding rules](https://google.github.io/styleguide/javaguide.html), to mantain clean code_

## Built with

* [Spring Boot](https://spring.io/guides/gs/spring-boot/)
* [Gradle](https://gradle.org/)

## Authors

* **Jorge Díaz** - *Initial work* - [@geogy24](https://github.com/geogy24)

## License

This project is under [MIT](https://opensource.org/licenses/MIT) license
