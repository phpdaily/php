folder('phpdaily') {
    description('This example shows basic folder/job creation')
}

def generateJob(String path, String tagname) {
    freeStyleJob("phpdaily/$tagname") {
        scm {
            git("git@github.com:jdecool/docker-php.git")
        }

        logRotator {
            numToKeep(10)
        }

        triggers {
            scm('@daily')
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
        }
    }
}

def version74 = "7.4.0"
generateJob('7.4-dev/alpine3.8/cli/', 'latest')
generateJob('7.4-dev/alpine3.8/cli/', '7.4-dev')
generateJob('7.4-dev/alpine3.8/cli/', "$version74-dev-cli-alpine")
generateJob('7.4-dev/alpine3.8/fpm/', "$version74-dev-fpm-alpine")
generateJob('7.4-dev/alpine3.8/zts/', "$version74-dev-zts-alpine")
generateJob('7.4-dev/stretch/apache/', "$version74-dev-apache-stretch")
generateJob('7.4-dev/stretch/cli/', "$version74-dev-cli-stretch")
generateJob('7.4-dev/stretch/fpm/', "$version74-dev-fpm-stretch")
generateJob('7.4-dev/stretch/zts/', "$version74-dev-zts-stretch")

def version73 = "7.3.2"
generateJob('7.3-dev/alpine3.8/cli/', '7.3-dev')
generateJob('7.3-dev/alpine3.8/cli/', "$version73-dev-cli-alpine")
generateJob('7.3-dev/alpine3.8/fpm/', "$version73-dev-fpm-alpine")
generateJob('7.3-dev/alpine3.8/zts/', "$version73-dev-zts-alpine")
generateJob('7.3-dev/stretch/apache/', "$version73-dev-apache-stretch")
generateJob('7.3-dev/stretch/cli/', "$version73-dev-cli-stretch")
generateJob('7.3-dev/stretch/fpm/', "$version73-dev-fpm-stretch")
generateJob('7.3-dev/stretch/zts/', "$version73-dev-zts-stretch")

def version72 = "7.2.15"
generateJob('7.2-dev/alpine3.8/cli/', '7.2-dev')
generateJob('7.2-dev/alpine3.8/cli/', "$version72-dev-cli-alpine")
generateJob('7.2-dev/alpine3.8/fpm/', "$version72-dev-fpm-alpine")
generateJob('7.2-dev/alpine3.8/zts/', "$version72-dev-zts-alpine")
generateJob('7.2-dev/stretch/apache/', "$version72-dev-apache-stretch")
generateJob('7.2-dev/stretch/cli/', "$version72-dev-cli-stretch")
generateJob('7.2-dev/stretch/fpm/', "$version72-dev-fpm-stretch")
generateJob('7.2-dev/stretch/zts/', "$version72-dev-zts-stretch")

def version71 = "7.1.27"
generateJob('7.1-dev/alpine3.8/cli/', '7.1-dev')
generateJob('7.1-dev/alpine3.8/cli/', "$version71-dev-cli-alpine")
generateJob('7.1-dev/alpine3.8/fpm/', "$version71-dev-fpm-alpine")
generateJob('7.1-dev/alpine3.8/zts/', "$version71-dev-zts-alpine")
generateJob('7.1-dev/stretch/apache/', "$version71-dev-apache-stretch")
generateJob('7.1-dev/stretch/cli/', "$version71-dev-cli-stretch")
generateJob('7.1-dev/stretch/fpm/', "$version71-dev-fpm-stretch")
generateJob('7.1-dev/stretch/zts/', "$version71-dev-zts-stretch")
generateJob('7.1-dev/jessie/apache/', "$version71-dev-apache-jessie")
generateJob('7.1-dev/jessie/cli/', "$version71-dev-cli-jessie")
generateJob('7.1-dev/jessie/fpm/', "$version71-dev-fpm-jessie")
generateJob('7.1-dev/jessie/zts/', "$version71-dev-zts-jessie")
