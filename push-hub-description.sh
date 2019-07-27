#!/bin/bash

set -e

TOKEN=$(curl -s -X POST \
    -H "Content-Type: application/json" \
    -d '{"username": "'"$DOCKER_USERNAME"'", "password": "'"$DOCKER_PASSWORD"'"}' \
    https://hub.docker.com/v2/users/login/ | jq -r .token)

CODE=$(jq -n --arg msg "$(<README.md)" \
    '{"registry":"registry-1.docker.io","full_description": $msg }' | \
        curl -s -o /dev/null -L -w "%{http_code}" \
           https://cloud.docker.com/v2/repositories/"${DOCKER_IMAGE}"/ \
           -d @- -X PATCH \
           -H "Content-Type: application/json" \
           -H "Authorization: JWT ${TOKEN}")

if [[ "${CODE}" = "200" ]]; then
    printf "Successfully pushed README to Docker Hub\n"
else
    printf "Unable to push README to Docker Hub, response code: %s\n" "${CODE}"
    exit 1
fi
