#!/usr/bin/env bash

BUILD_VERSION=$1

COLOR_BLUE='\033[0;36m'
COLOR_RED='\033[1;31m'
COLOR_YELLOW='\033[1;33m'
COLOR_NONE='\033[0m'

show_spinner() {
  local -r pid="${1}"
  local -r delay='0.75'
  local spinstr='\|/-'
  local temp

  while ps a | awk '{print $1}' | grep -q "${pid}"; do
    temp="${spinstr#?}"
    printf " [%c]  " "${spinstr}"

    spinstr=${temp}${spinstr%"${temp}"}
    sleep "${delay}"

    printf "\b\b\b\b\b\b"
  done
  printf "    \b\b\b\b"
}

build_image() {
    if [[ -z "$1" || -z "$2" ]]; then
        echo -e "${COLOR_RED}ERROR: missing argument${COLOR_NONE}"
        return
    fi

    local BASE_TAG="phpdaily/php"
    local DOCKERFILE_PATH="$1"
    local VERSION_TAG="$2"
    local IMAGE_TAG="$BASE_TAG:$VERSION_TAG"
    local BUILD_ARGS="--rm --build-arg PEAR_URL=https://github.com/pear/pearweb_phars/raw/master/install-pear-nozlib.phar"

    mkdir -p logs

    echo -e "==> Build ${COLOR_YELLOW}$IMAGE_TAG${COLOR_NONE} from ${COLOR_BLUE}$DOCKERFILE_PATH${COLOR_NONE}"

    if [[ -n "$BUILD_VERSION" && "$BUILD_VERSION" != "$VERSION_TAG" ]]; then
        echo -e "    * Skipping $VERSION_TAG"
        echo ""
        return
    fi

    docker build --no-cache $BUILD_ARGS -t "$IMAGE_TAG" $DOCKERFILE_PATH > "logs/$VERSION_TAG" 2> "logs/$VERSION_TAG"
    local EXIT_CODE=$?
    # show_spinner "$!"

    if [[ $EXIT_CODE != 0 ]]; then
        echo -e "${COLOR_RED}    /!\\ ERROR during construction /!\\ ${COLOR_NONE}"
        echo ""
    fi

    docker push "$IMAGE_TAG" > "logs/$VERSION_TAG" 2> "logs/$VERSION_TAG" &

    local EXIT_CODE=$?
    if [[ $EXIT_CODE != 0 ]]; then
        echo -e "${COLOR_RED}    /!\\ ERROR during push /!\\ ${COLOR_NONE}"
        echo ""
    fi
}

VERSION_80="8.0.0"
build_image "8.0-dev/alpine3.9/cli/" "8.0-dev"
build_image "8.0-dev/alpine3.9/cli/" "$VERSION_80-dev-cli-alpine"
build_image "8.0-dev/alpine3.9/fpm/" "$VERSION_80-dev-fpm-alpine"
build_image "8.0-dev/alpine3.9/zts/" "$VERSION_80-dev-zts-alpine"
build_image "8.0-dev/alpine3.10/cli/" "$VERSION_80-dev-cli-alpine3.10"
build_image "8.0-dev/alpine3.10/fpm/" "$VERSION_80-dev-fpm-alpine3.10"
build_image "8.0-dev/alpine3.10/zts/" "$VERSION_80-dev-zts-alpine3.10"
build_image "8.0-dev/stretch/apache/" "$VERSION_80-dev-apache-stretch"
build_image "8.0-dev/stretch/cli/" "$VERSION_80-dev-cli-stretch"
build_image "8.0-dev/stretch/fpm/" "$VERSION_80-dev-fpm-stretch"
build_image "8.0-dev/stretch/zts/" "$VERSION_80-dev-zts-stretch"
build_image "8.0-dev/buster/apache/" "$VERSION_80-dev-apache-buster"
build_image "8.0-dev/buster/cli/" "$VERSION_80-dev-cli-buster"
build_image "8.0-dev/buster/fpm/" "$VERSION_80-dev-fpm-buster"
build_image "8.0-dev/buster/zts/" "$VERSION_80-dev-zts-buster"

VERSION_74="7.4.13"
build_image "7.4-dev/alpine3.9/cli/" "latest"
build_image "7.4-dev/alpine3.9/cli/" "7.4-dev"
build_image "7.4-dev/alpine3.8/cli/" "$VERSION_74-dev-cli-alpine3.8"
build_image "7.4-dev/alpine3.8/fpm/" "$VERSION_74-dev-fpm-alpine3.8"
build_image "7.4-dev/alpine3.8/zts/" "$VERSION_74-dev-zts-alpine3.8"
build_image "7.4-dev/alpine3.9/cli/" "$VERSION_74-dev-cli-alpine"
build_image "7.4-dev/alpine3.9/fpm/" "$VERSION_74-dev-fpm-alpine"
build_image "7.4-dev/alpine3.9/zts/" "$VERSION_74-dev-zts-alpine"
build_image "7.4-dev/stretch/apache/" "$VERSION_74-dev-apache-stretch"
build_image "7.4-dev/stretch/cli/" "$VERSION_74-dev-cli-stretch"
build_image "7.4-dev/stretch/fpm/" "$VERSION_74-dev-fpm-stretch"
build_image "7.4-dev/stretch/zts/" "$VERSION_74-dev-zts-stretch"
build_image "7.4-dev/buster/apache/" "$VERSION_74-dev-apache-buster"
build_image "7.4-dev/buster/cli/" "$VERSION_74-dev-cli-buster"
build_image "7.4-dev/buster/fpm/" "$VERSION_74-dev-fpm-buster"
build_image "7.4-dev/buster/zts/" "$VERSION_74-dev-zts-buster"

VERSION_73="7.3.25"
build_image "7.3-dev/alpine3.9/cli/" "7.3-dev"
build_image "7.3-dev/alpine3.8/cli/" "$VERSION_73-dev-cli-alpine3.8"
build_image "7.3-dev/alpine3.8/fpm/" "$VERSION_73-dev-fpm-alpine3.8"
build_image "7.3-dev/alpine3.8/zts/" "$VERSION_73-dev-zts-alpine3.8"
build_image "7.3-dev/alpine3.9/cli/" "$VERSION_73-dev-cli-alpine"
build_image "7.3-dev/alpine3.9/fpm/" "$VERSION_73-dev-fpm-alpine"
build_image "7.3-dev/alpine3.9/zts/" "$VERSION_73-dev-zts-alpine"
build_image "7.3-dev/alpine3.10/cli/" "$VERSION_73-dev-cli-alpine3.10"
build_image "7.3-dev/alpine3.10/fpm/" "$VERSION_73-dev-fpm-alpine3.10"
build_image "7.3-dev/alpine3.10/zts/" "$VERSION_73-dev-zts-alpine3.10"
build_image "7.3-dev/stretch/apache/" "$VERSION_73-dev-apache-stretch"
build_image "7.3-dev/stretch/cli/" "$VERSION_73-dev-cli-stretch"
build_image "7.3-dev/stretch/fpm/" "$VERSION_73-dev-fpm-stretch"
build_image "7.3-dev/stretch/zts/" "$VERSION_73-dev-zts-stretch"

VERSION_72="7.2.35"
build_image "7.2-dev/alpine3.9/cli/" "7.2-dev"
build_image "7.2-dev/alpine3.8/cli/" "$VERSION_72-dev-cli-alpine3.8"
build_image "7.2-dev/alpine3.8/fpm/" "$VERSION_72-dev-fpm-alpine3.8"
build_image "7.2-dev/alpine3.8/zts/" "$VERSION_72-dev-zts-alpine3.8"
build_image "7.2-dev/alpine3.9/cli/" "$VERSION_72-dev-cli-alpine"
build_image "7.2-dev/alpine3.9/fpm/" "$VERSION_72-dev-fpm-alpine"
build_image "7.2-dev/alpine3.9/zts/" "$VERSION_72-dev-zts-alpine"
build_image "7.2-dev/alpine3.10/cli/" "$VERSION_72-dev-cli-alpine3.10"
build_image "7.2-dev/alpine3.10/fpm/" "$VERSION_72-dev-fpm-alpine3.10"
build_image "7.2-dev/alpine3.10/zts/" "$VERSION_72-dev-zts-alpine3.10"
build_image "7.2-dev/stretch/apache/" "$VERSION_72-dev-apache-stretch"
build_image "7.2-dev/stretch/cli/" "$VERSION_72-dev-cli-stretch"
build_image "7.2-dev/stretch/fpm/" "$VERSION_72-dev-fpm-stretch"
build_image "7.2-dev/stretch/zts/" "$VERSION_72-dev-zts-stretch"
build_image "7.2-dev/buster/apache/" "$VERSION_72-dev-apache-buster"
build_image "7.2-dev/buster/cli/" "$VERSION_72-dev-cli-buster"
build_image "7.2-dev/buster/fpm/" "$VERSION_72-dev-fpm-buster"
build_image "7.2-dev/buster/zts/" "$VERSION_72-dev-zts-buster"

# cleanup
docker system prune --force
docker volume prune --force
