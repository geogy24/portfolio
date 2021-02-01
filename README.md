# Portfolio & Profile

_This project is a test_. Hours on developing 8. 

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

If you want to run this project on console, you need to set up the environment variables, which are

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

If you want to run the project throughout Intellij IDEA, you must set up the environment variables
following this [link](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html)

### Documentation

You can check how to do a request and what parameters are required in our documentation,
running the server and make a request to http://localhost:8080/swagger-ui.html.

**Check the postman collection on the repository**

**Note**
If you need the documentation on JSON format you can get it, make a request to
http://localhost:8080/v2/api-docs

## Run tests️

We encourage that you run the test by an IDE as Intellj IDEA to check the process.

On new versions of this IDE must be exists a problem running test with gradle, all you
have to do is follow this [link](https://stackoverflow.com/a/55417140) and select the class
or classes you want running the test; right click and run.

## Things to improve

Here are a few things to improve on this project:

- Tables on the database has not constraints like not null, we must be check that.
- Tables on the database use email string field as primary key, this could be replaced
  by an integer autoincrement field and make this email field unique, this could be generate
  a better performance.
- The models have not constraints because we follow the database table structure, if we do
  the first thing on this list, we can have a better way to check on the model the right 
  constraints to the model.

### Follow next coding rules

_Follow next [coding rules](https://google.github.io/styleguide/javaguide.html), to mantain clean code_

## Built with

* [Spring Boot](https://spring.io/guides/gs/spring-boot/)
* [Gradle](https://gradle.org/)

## Authors

* **Jorge Díaz** - *Initial work* - [@geogy24](https://github.com/geogy24)

## License

This project is under [MIT](https://opensource.org/licenses/MIT) license
