import org.helper.InvokeAnsibleHelper;

def handleBodyInput(body,pipeline_params){
    body.resolbeStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipeline_params
    body()
}

def call(body) {
    def pipeline_params = [:]
    handleBodyInput(body,pipeline_params)
    def invokeAnsibleHelper = new InvokeAnsibleHelper(this)
    String credentialsId = pipeline_params.get('credentialsId')

    String credentialsId2 = pipeline_params.credentialsId

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
                    echo "${credentialsId}"
                    echo "${credentialsId2}"
                    script {
                        invokeAnsibleHelper.runAnsiblePlaybook("playbooks/invoke-ansible.yml", "hosts/hosts")
                    }
                }
            }
        }
    }
}