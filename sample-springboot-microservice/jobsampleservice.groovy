import helper.JobHelper

def jobHelperbuild = new JobHelper(this)

jobHelperbuild.buildJob('sample-springboot-microservice/properties.groovy')
jobHelperbuild.testJob('sample-springboot-microservice/properties.groovy')