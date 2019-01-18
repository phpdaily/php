#!/usr/bin/env bash

COLOR_BLUE='\033[0;36m'
COLOR_RED='\033[1;31m'
COLOR_YELLOW='\033[1;33m'
COLOR_NONE='\033[0m'

build_image() {
    if [[ -z "$1" || -z "$2" ]]; then
        echo -e "${COLOR_RED}ERROR: missing argument${COLOR_NONE}"
        return
    fi

    local BASE_TAG="phpdaily/php"
    local DOCKERFILE_PATH="$1"
    local VERSION_TAG="$2"
    local IMAGE_TAG="$BASE_TAG:$VERSION_TAG"

    mkdir -p logs

    echo -e "==> Build ${COLOR_YELLOW}$IMAGE_TAG${COLOR_NONE} from ${COLOR_BLUE}$DOCKERFILE_PATH${COLOR_NONE}"

    docker build --no-cache -t "$IMAGE_TAG" $DOCKERFILE_PATH > "logs/$VERSION_TAG" 2> "logs/$VERSION_TAG"

    local EXIT_CODE=$?
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

VERSION_74="7.4.0"
build_image "7.4-dev/alpine3.8/cli/" "latest" 
build_image "7.4-dev/alpine3.8/cli/" "7.4-dev" 
build_image "7.4-dev/alpine3.8/cli/" "$VERSION_74-dev-cli-alpine" 
build_image "7.4-dev/alpine3.8/fpm/" "$VERSION_74-dev-fpm-alpine" 
build_image "7.4-dev/alpine3.8/zts/" "$VERSION_74-dev-zts-alpine" 
build_image "7.4-dev/stretch/apache/" "$VERSION_74-dev-apache-stretch"
build_image "7.4-dev/stretch/cli/" "$VERSION_74-dev-cli-stretch"
build_image "7.4-dev/stretch/fpm/" "$VERSION_74-dev-fpm-stretch"
build_image "7.4-dev/stretch/zts/" "$VERSION_74-dev-zts-stretch"

VERSION_73="7.3.2"
build_image "7.3-dev/alpine3.8/cli/" "7.3-dev"
build_image "7.3-dev/alpine3.8/cli/" "$VERSION_73-dev-cli-alpine"
build_image "7.3-dev/alpine3.8/fpm/" "$VERSION_73-dev-fpm-alpine"
build_image "7.3-dev/alpine3.8/zts/" "$VERSION_73-dev-zts-alpine"
build_image "7.3-dev/stretch/apache/" "$VERSION_73-dev-apache-stretch"
build_image "7.3-dev/stretch/cli/" "$VERSION_73-dev-cli-stretch"
build_image "7.3-dev/stretch/fpm/" "$VERSION_73-dev-fpm-stretch"
build_image "7.3-dev/stretch/zts/" "$VERSION_73-dev-zts-stretch"

VERSION_72="7.2.15"
build_image "7.2-dev/alpine3.8/cli/" "7.2-dev"
build_image "7.2-dev/alpine3.8/cli/" "$VERSION_72-dev-cli-alpine"
build_image "7.2-dev/alpine3.8/fpm/" "$VERSION_72-dev-fpm-alpine"
build_image "7.2-dev/alpine3.8/zts/" "$VERSION_72-dev-zts-alpine"
build_image "7.2-dev/stretch/apache/" "$VERSION_72-dev-apache-stretch"
build_image "7.2-dev/stretch/cli/" "$VERSION_72-dev-cli-stretch"
build_image "7.2-dev/stretch/fpm/" "$VERSION_72-dev-fpm-stretch"
build_image "7.2-dev/stretch/zts/" "$VERSION_72-dev-zts-stretch"

VERSION_71="7.1.27"
build_image "7.1-dev/alpine3.8/cli/" "7.1-dev"
build_image "7.1-dev/alpine3.8/cli/" "$VERSION_71-dev-cli-alpine"
build_image "7.1-dev/alpine3.8/fpm/" "$VERSION_71-dev-fpm-alpine"
build_image "7.1-dev/alpine3.8/zts/" "$VERSION_71-dev-zts-alpine"
build_image "7.1-dev/stretch/apache/" "$VERSION_71-dev-apache-stretch"
build_image "7.1-dev/stretch/cli/" "$VERSION_71-dev-cli-stretch"
build_image "7.1-dev/stretch/fpm/" "$VERSION_71-dev-fpm-stretch"
build_image "7.1-dev/stretch/zts/" "$VERSION_71-dev-zts-stretch"
build_image "7.1-dev/jessie/apache/" "$VERSION_71-dev-apache-jessie"
build_image "7.1-dev/jessie/cli/" "$VERSION_71-dev-cli-jessie"
build_image "7.1-dev/jessie/fpm/" "$VERSION_71-dev-fpm-jessie"
build_image "7.1-dev/jessie/zts/" "$VERSION_71-dev-zts-jessie"