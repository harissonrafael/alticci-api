#!/bin/bash
echo '########## REMOVING THE DOCKER IMAGE ##########'
docker stop alticci-api
docker rm alticci-api
docker rmi alticci-api_image

echo '########## BUILDING THE PROJECT ##########'
./gradlew build

echo '########## BUILDING THE DOCKER IMAGE ##########'
docker build --tag alticci-api_image .

echo '########## RUNNING THE DOCKER IMAGE ##########'
docker run -p 8080:8080 --name alticci-api alticci-api_image
