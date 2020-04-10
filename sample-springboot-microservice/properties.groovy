agent_node_name = "any"

git {
    service {
        url = "https://github.com/vinayaka-rs/spring-boot-gradle.git"
        branch = "master"
    }

    user {
        cred = "605abf89-a797-4b53-ba81-427f3b29a12d"
    }
}

job {
    pipelines {
        build_job_name = 'Spring-boot-build'
        test_job_name = 'Spring-boot-test'
        publish_job_name = 'Spring-build-publish'
    }
 
}

email {
    user {
        mail = 'vinayaka_rs@outlook.com'
    }
}
