nestedView('Spring Boot Microservice') {
    views {
        listView('Spring Boot Service Jobs') {
            jobs {
                regex(/Spring-.*/)
            }
            columns {
                status()
                weather()
                name()
                lastSuccess()
                lastFailure()
                lastDuration()
                buildButton()
            }
        }
        deliveryPipelineView("Spring Boot Service Build") {
            pipelineInstances(1)
            enableManualTriggers()
            showChangeLog()
            pipelines {
                component("Build/Test", "Spring-boot-build")
            }
        }
    }
}