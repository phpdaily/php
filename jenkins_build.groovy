folder('phpdaily') {
    description('This example shows basic folder/job creation')
}

def generateJob(String path, String tagname) {
    freeStyleJob("phpdaily/$tagname") {
        scm {
            git("https://github.com/phpdaily/php.git")
        }

        logRotator {
            numToKeep(10)
        }

        triggers {
            cron('30 0 * * *')
        }

        steps {
            dockerBuildAndPublish {
                buildContext("$path")
                repositoryName('phpdaily/php')
                tag("$tagname")
                forceTag(false)
                registryCredentials('dockerio-registry')
                noCache(true)
            }
            shell("docker rmi --force phpdaily/php:$tagname")
        }
    }
}

def generateJobPushDescription() {
    freeStyleJob("phpdaily/update-description") {
        scm {
            git("https://github.com/phpdaily/php.git")
        }

        logRotator {
            numToKeep(5)
        }

        triggers {
            cron('0 7 * * *')
        }

        wrappers {
            credentialsBinding {
                usernamePassword("DOCKER_USERNAME", "DOCKER_PASSWORD", "dockerio-registry")
            }
        }

        environmentVariables {
            env("DOCKER_IMAGE", "phpdaily/php")
        }

        steps {
            shell("./push-hub-description.sh")
        }
    }
}

def version81 = "8.1.0"
generateJob('8.1-dev/alpine3.12/cli/', 'latest')
generateJob('8.1-dev/alpine3.12/cli/', '8.1-dev')
generateJob('8.1-dev/alpine3.12/cli/', "8.1-dev-alpine3.12")
generateJob('8.1-dev/alpine3.12/cli/', "$version81-dev-cli-alpine3.12")
generateJob('8.1-dev/alpine3.12/fpm/', "$version81-dev-fpm-alpine3.12")
generateJob('8.1-dev/alpine3.13/cli/', "8.1-dev-alpine3.13")
generateJob('8.1-dev/alpine3.13/cli/', "$version81-dev-cli-alpine3.13")
generateJob('8.1-dev/alpine3.13/fpm/', "$version81-dev-fpm-alpine3.13")
generateJob('8.1-dev/alpine3.14/cli/', "8.1-dev-alpine3.14")
generateJob('8.1-dev/alpine3.14/cli/', "$version81-dev-cli-alpine3.14")
generateJob('8.1-dev/alpine3.14/fpm/', "$version81-dev-fpm-alpine3.14")
generateJob('8.1-dev/buster/cli/', "8.1-dev-buster")
generateJob('8.1-dev/buster/apache/', "$version81-dev-apache-buster")
generateJob('8.1-dev/buster/cli/', "$version81-dev-cli-buster")
generateJob('8.1-dev/buster/fpm/', "$version81-dev-fpm-buster")
generateJob('8.1-dev/buster/zts/', "$version81-dev-zts-buster")

def version80 = "8.0.9"
generateJob('8.0-dev/alpine3.12/cli/', '8.0-dev')
generateJob('8.0-dev/alpine3.12/cli/', "8.0-dev-alpine3.12")
generateJob('8.0-dev/alpine3.12/cli/', "$version80-dev-cli-alpine3.12")
generateJob('8.0-dev/alpine3.12/fpm/', "$version80-dev-fpm-alpine3.12")
generateJob('8.0-dev/alpine3.13/cli/', "8.0-dev-alpine3.13")
generateJob('8.0-dev/alpine3.13/cli/', "$version80-dev-cli-alpine3.13")
generateJob('8.0-dev/alpine3.13/fpm/', "$version80-dev-fpm-alpine3.13")
generateJob('8.0-dev/alpine3.14/cli/', "8.0-dev-alpine3.14")
generateJob('8.0-dev/alpine3.14/cli/', "$version80-dev-cli-alpine3.14")
generateJob('8.0-dev/alpine3.14/fpm/', "$version80-dev-fpm-alpine3.14")
generateJob('8.0-dev/buster/cli/', "8.0-dev-buster")
generateJob('8.0-dev/buster/apache/', "$version80-dev-apache-buster")
generateJob('8.0-dev/buster/cli/', "$version80-dev-cli-buster")
generateJob('8.0-dev/buster/fpm/', "$version80-dev-fpm-buster")
generateJob('8.0-dev/buster/zts/', "$version80-dev-zts-buster")

def version74 = "7.4.22"
generateJob('7.4-dev/alpine3.12/cli/', '7.4-dev')
generateJob('7.4-dev/alpine3.12/cli/', "7.4-dev-alpine3.12")
generateJob('7.4-dev/alpine3.12/cli/', "$version74-dev-cli-alpine3.12")
generateJob('7.4-dev/alpine3.12/fpm/', "$version74-dev-fpm-alpine3.12")
generateJob('7.4-dev/alpine3.12/zts/', "$version74-dev-zts-alpine3.12")
generateJob('7.4-dev/alpine3.13/cli/', "7.4-dev-alpine3.13")
generateJob('7.4-dev/alpine3.13/cli/', "$version74-dev-cli-alpine3.13")
generateJob('7.4-dev/alpine3.13/fpm/', "$version74-dev-fpm-alpine3.13")
generateJob('7.4-dev/alpine3.13/zts/', "$version74-dev-zts-alpine3.13")
generateJob('7.4-dev/alpine3.14/cli/', "7.4-dev-alpine3.14")
generateJob('7.4-dev/alpine3.14/cli/', "$version74-dev-cli-alpine3.14")
generateJob('7.4-dev/alpine3.14/fpm/', "$version74-dev-fpm-alpine3.14")
generateJob('7.4-dev/alpine3.14/zts/', "$version74-dev-zts-alpine3.14")
generateJob('7.4-dev/buster/cli/', "7.4-dev-buster")
generateJob('7.4-dev/buster/apache/', "$version74-dev-apache-buster")
generateJob('7.4-dev/buster/cli/', "$version74-dev-cli-buster")
generateJob('7.4-dev/buster/fpm/', "$version74-dev-fpm-buster")
generateJob('7.4-dev/buster/zts/', "$version74-dev-zts-buster")

def version73 = "7.3.30"
generateJob('7.3-dev/alpine3.12/cli/', '7.3-dev')
generateJob('7.3-dev/alpine3.12/cli/', "7.3-dev-alpine3.12")
generateJob('7.3-dev/alpine3.12/cli/', "$version73-dev-cli-alpine3.12")
generateJob('7.3-dev/alpine3.12/fpm/', "$version73-dev-fpm-alpine3.12")
generateJob('7.3-dev/alpine3.12/zts/', "$version73-dev-zts-alpine3.12")
generateJob('7.3-dev/alpine3.13/cli/', "7.3-dev-alpine3.13")
generateJob('7.3-dev/alpine3.13/cli/', "$version73-dev-cli-alpine3.13")
generateJob('7.3-dev/alpine3.13/fpm/', "$version73-dev-fpm-alpine3.13")
generateJob('7.3-dev/alpine3.13/zts/', "$version73-dev-zts-alpine3.13")
generateJob('7.3-dev/alpine3.14/cli/', "7.3-dev-alpine3.14")
generateJob('7.3-dev/alpine3.14/cli/', "$version73-dev-cli-alpine3.14")
generateJob('7.3-dev/alpine3.14/fpm/', "$version73-dev-fpm-alpine3.14")
generateJob('7.3-dev/alpine3.14/zts/', "$version73-dev-zts-alpine3.14")
generateJob('7.3-dev/stretch/apache/', "$version73-dev-apache-stretch")
generateJob('7.3-dev/stretch/cli/', "7.3-dev-stretch")
generateJob('7.3-dev/stretch/cli/', "$version73-dev-cli-stretch")
generateJob('7.3-dev/stretch/fpm/', "$version73-dev-fpm-stretch")
generateJob('7.3-dev/stretch/zts/', "$version73-dev-zts-stretch")
generateJob('7.3-dev/buster/apache/', "$version73-dev-apache-buster")
generateJob('7.3-dev/buster/cli/', "7.3-dev-buster")
generateJob('7.3-dev/buster/cli/', "$version73-dev-cli-buster")
generateJob('7.3-dev/buster/fpm/', "$version73-dev-fpm-buster")
generateJob('7.3-dev/buster/zts/', "$version73-dev-zts-buster")

generateJobPushDescription()
