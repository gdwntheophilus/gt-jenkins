import org.helper.InvokeAnsibleHelper;

def handleBodyInput(body,parameters){
    body.resolbeStrategy = Closure.DELEGATE_FIRST
    println "body.resolveStrategy: ${body.resolveStrategy}"
    body.delegate = parameters
    println("body.delegate: ${body.delegate}")
    body()
    println("body: ${body}")
}

def call(body) {
    def parameters = [:]
    handleBodyInput(body,parameters)
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
                    echo "${parameters}"
                    script {
                        invokeAnsibleHelper.runAnsiblePlaybook("playbooks/invoke-ansible.yml", "hosts/hosts")
                    }
                }
            }
        }
    }
}