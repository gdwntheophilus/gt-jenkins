
def handleBodyInput(body,parameters){
    body.resolbeStrategy = Closure.DELEGATE_FIRST
    body.delegate = parameters
    body()
}

def call(body) {
    def parameters = [:]
    def invokeAnsibleHelper = new InvokeAnsibleHelper(this)
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
                        invokeAnsibleHelper.runAnsiblePlaybook("playbooks/invoke-ansible.yml", "hosts/hosts")
                    }
                }
            }
        }
    }
}