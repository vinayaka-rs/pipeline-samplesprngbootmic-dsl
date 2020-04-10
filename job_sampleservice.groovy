job('Spring-boot-build') {
    scm {
        git {
            remote {
                url("https://github.com/vinayaka-rs/sample-spring-boot-microservice.git")
                credentials("github_id")
            }
            extensions {
                wipeOutWorkspace()
            }
            branch('master')
        }
    }

    steps {
        gradle {
            fromRootBuildScriptDir(true)
            // Sets the executable flag on the wrapper script file before invoking the file.
            makeExecutable(true)
            // Specifies the Gradle tasks to be invoked.
            tasks('clean build')
        }
        //gradle('clean build')
    }

    publishers {
        mailer('vinayaka_rs@outlook.com', true, true) 
        extendedEmail { 
            recipientList('vinayaka_rs@outlook.com') 
            defaultSubject('$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!') 
            defaultContent('$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS') 
            contentType('text/html') 
            triggers { 
                failure { 
                    replyToList('$PROJECT_DEFAULT_REPLYTO') 
                    contentType('text/html') 
                    content('$PROJECT_DEFAULT_CONTENT') 
                    subject('$PROJECT_DEFAULT_SUBJECT') 
                    sendTo { 
                    recipientList() 
                    } 
                } 
                success { 
                    replyToList('$PROJECT_DEFAULT_REPLYTO') 
                    contentType('text/html') 
                    content('$PROJECT_DEFAULT_CONTENT') 
                    subject('$PROJECT_DEFAULT_SUBJECT') 
                    sendTo { 
                        recipientList() 
                    } 
                } 
            }
        }
        
        downstream('Spring-boot-test', 'SUCCESS')
    }

}

job('Spring-boot-test') {
    scm {
        git {
            remote {
                url("https://github.com/vinayaka-rs/sample-spring-boot-microservice.git")
                credentials("github_id")
            }
            extensions {
                wipeOutWorkspace()
            }
            branch('master')
        }
    }
    steps {
        gradle {
            fromRootBuildScriptDir(true)
            // Sets the executable flag on the wrapper script file before invoking the file.
            makeExecutable(true)
            // Specifies the Gradle tasks to be invoked.
            tasks('clean build test')
        }
        //gradle('clean test')
    }

    publishers {
        downstream('Spring-build-publish', 'SUCCESS')
    }
}

job('Spring-build-publish') {
    scm {
        git {
            remote {
                url("https://github.com/vinayaka-rs/sample-spring-boot-microservice.git")
                credentials("github_id")
            }
            extensions {
                wipeOutWorkspace()
            }
            branch('master')
        }
    }
    steps {
        gradle {
            fromRootBuildScriptDir(true)
            // Sets the executable flag on the wrapper script file before invoking the file.
            makeExecutable(true)
            // Specifies the Gradle tasks to be invoked.
            tasks('clean build')
        }
        //gradle('clean build')
    }

    publishers {
        archiveArtifacts {
            // Archives artifacts only if the build is successful.
            onlyIfSuccessful(true)
            // Specifies the files to archive.
            pattern("build/**/**/*")
        }
    }
}

