import org.helper.InvokeAnsibleHelper;

def handleBodyInput(body,pipeline_params){
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipeline_params
    body()
}

def call(body) {

    def invokeAnsibleHelper = new InvokeAnsibleHelper(this)
    def pipeline_params = [:]
    // handleBodyInput(body,pipeline_params)
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipeline_params
    body()
    String credentialsId2 = pipeline_params.credentialsId
    println(credentialsId)
    println(credentialsId2)

    pipeline {
        agent any 
        options {
            ansiColor('xterm')
            timestamps()
            skipDefaultCheckout true
        }
        stages {
            stage('invokingAnsible') {
                steps {
                    script {
                        println(credentialsId)
                        println(credentialsId2)
                        invokeAnsibleHelper.runAnsiblePlaybook("playbooks/invoke-ansible.yml", "hosts/hosts")
                    }
                }
            }
        }
    }
}