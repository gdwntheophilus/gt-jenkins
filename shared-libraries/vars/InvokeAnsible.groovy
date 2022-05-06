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
    println( pipeline_params)

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