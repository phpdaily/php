#!/bin/bash

set -euo pipefail

docker run -v $WORKSPACE:/workspace \
  -e DOCKERHUB_USERNAME="$DOCKER_USERNAME" \
  -e DOCKERHUB_PASSWORD="$DOCKER_PASSWORD" \
  -e DOCKERHUB_REPOSITORY="${DOCKER_IMAGE}" \
  -e README_FILEPATH="/workspace/README.md" \
  peterevans/dockerhub-description:2.1
