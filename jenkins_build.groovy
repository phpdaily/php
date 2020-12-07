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
                additionalBuildArgs("--rm --build-arg PEAR_URL=https://github.com/pear/pearweb_phars/raw/master/install-pear-nozlib.phar")
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

def version80 = "8.0.1"
generateJob('8.0-dev/alpine3.9/cli/', 'latest')
generateJob('8.0-dev/alpine3.9/cli/', '8.0-dev')
generateJob('8.0-dev/alpine3.9/cli/', "$version80-dev-cli-alpine")
generateJob('8.0-dev/alpine3.9/fpm/', "$version80-dev-fpm-alpine")
generateJob('8.0-dev/alpine3.9/zts/', "$version80-dev-zts-alpine")
generateJob('8.0-dev/alpine3.10/cli/', "$version80-dev-cli-alpine3.10")
generateJob('8.0-dev/alpine3.10/fpm/', "$version80-dev-fpm-alpine3.10")
generateJob('8.0-dev/alpine3.10/zts/', "$version80-dev-zts-alpine3.10")
generateJob('8.0-dev/stretch/apache/', "$version80-dev-apache-stretch")
generateJob('8.0-dev/stretch/cli/', "$version80-dev-cli-stretch")
generateJob('8.0-dev/stretch/fpm/', "$version80-dev-fpm-stretch")
generateJob('8.0-dev/stretch/zts/', "$version80-dev-zts-stretch")
generateJob('8.0-dev/buster/apache/', "$version80-dev-apache-buster")
generateJob('8.0-dev/buster/cli/', "$version80-dev-cli-buster")
generateJob('8.0-dev/buster/fpm/', "$version80-dev-fpm-buster")
generateJob('8.0-dev/buster/zts/', "$version80-dev-zts-buster")

def version74 = "7.4.14"
generateJob('7.4-dev/alpine3.9/cli/', '7.4-dev')
generateJob('7.4-dev/alpine3.9/cli/', "$version74-dev-cli-alpine")
generateJob('7.4-dev/alpine3.9/fpm/', "$version74-dev-fpm-alpine")
generateJob('7.4-dev/alpine3.9/zts/', "$version74-dev-zts-alpine")
generateJob('7.4-dev/alpine3.10/cli/', "$version74-dev-cli-alpine3.10")
generateJob('7.4-dev/alpine3.10/fpm/', "$version74-dev-fpm-alpine3.10")
generateJob('7.4-dev/alpine3.10/zts/', "$version74-dev-zts-alpine3.10")
generateJob('7.4-dev/stretch/apache/', "$version74-dev-apache-stretch")
generateJob('7.4-dev/stretch/cli/', "$version74-dev-cli-stretch")
generateJob('7.4-dev/stretch/fpm/', "$version74-dev-fpm-stretch")
generateJob('7.4-dev/stretch/zts/', "$version74-dev-zts-stretch")
generateJob('7.4-dev/buster/apache/', "$version74-dev-apache-buster")
generateJob('7.4-dev/buster/cli/', "$version74-dev-cli-buster")
generateJob('7.4-dev/buster/fpm/', "$version74-dev-fpm-buster")
generateJob('7.4-dev/buster/zts/', "$version74-dev-zts-buster")

def version73 = "7.3.26"
generateJob('7.3-dev/alpine3.9/cli/', '7.3-dev')
generateJob('7.3-dev/alpine3.8/cli/', "$version73-dev-cli-alpine3.8")
generateJob('7.3-dev/alpine3.8/fpm/', "$version73-dev-fpm-alpine3.8")
generateJob('7.3-dev/alpine3.8/zts/', "$version73-dev-zts-alpine3.8")
generateJob('7.3-dev/alpine3.9/cli/', "$version73-dev-cli-alpine")
generateJob('7.3-dev/alpine3.9/fpm/', "$version73-dev-fpm-alpine")
generateJob('7.3-dev/alpine3.9/zts/', "$version73-dev-zts-alpine")
generateJob('7.3-dev/alpine3.10/cli/', "$version73-dev-cli-alpine3.10")
generateJob('7.3-dev/alpine3.10/fpm/', "$version73-dev-fpm-alpine3.10")
generateJob('7.3-dev/alpine3.10/zts/', "$version73-dev-zts-alpine3.10")
generateJob('7.3-dev/stretch/apache/', "$version73-dev-apache-stretch")
generateJob('7.3-dev/stretch/cli/', "$version73-dev-cli-stretch")
generateJob('7.3-dev/stretch/fpm/', "$version73-dev-fpm-stretch")
generateJob('7.3-dev/stretch/zts/', "$version73-dev-zts-stretch")
generateJob('7.3-dev/buster/apache/', "$version73-dev-apache-buster")
generateJob('7.3-dev/buster/cli/', "$version73-dev-cli-buster")
generateJob('7.3-dev/buster/fpm/', "$version73-dev-fpm-buster")
generateJob('7.3-dev/buster/zts/', "$version73-dev-zts-buster")

generateJobPushDescription()
