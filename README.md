# Alticci

## Application to calculate Alticci sequence

Alticci-API is a java backend application for calculate the alticci sequence.

The Alticci sequence - a(n) - is defined by:

```
n=0 => a(0) = 0
n=1 => a(1) = 1
n=2 => a(2) = 1
n>2 => a(n) = a(n-3) + a(n-2)
```

Examples - the top 10:

```
0
1
1
1
2
2
3
4
5
7
9
```

## What do you need to know

This tutorial was made with commands on Windows but the commands used can be used on other OS as long as the necessary
modifications are made. To run the application you must have java installed or docker because you will need to run the
command `java -jar` or `docker run` respectively.

## How to run the project using java directly

- Go to `alticci-api` folder;
- run command to build the project:

```sh
./gradlew build
```

- Copy the created `app.jar` jar file to the folder `<alticci-api_folder>/build/libs` and paste it into the folder of
  your choice;
- To run the application, just run the command

```sh
java -jar app.jar
```

## How to run the project using docker

- Go to `alticci-api` folder;
- run command to build, create image and run the project:

```sh
./start_using_docker.sh
```

> Note: To stop and clear docker container and images run the command `./stop_using_docker.sh`

## How to use

If all commands were executed without error, go to the browser and access a
URL <http://localhost:8080/alticci/{index_number}>

- Another possibility is on the browser using swagger page. Go to the browser and access a
  URL `http://localhost:8080/swagger-ui/`. On this page you can calculate the sequence based on the desired index and
  retrieve all cached values

## Features

| Feature | Situation |
| ------ | ------ |
| Create server to calculate alticci sequence | Done |
| Configure Swagger | Done |
| Integration test | Done |
| Create documentation (README.md) | In process |