#!/bin/bash
echo '########## REMOVING THE DOCKER IMAGE ##########'
docker stop alticci-api
docker rm alticci-api
docker rmi alticci-api_image
