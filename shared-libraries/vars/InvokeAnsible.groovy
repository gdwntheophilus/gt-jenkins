import org.helper.InvokeAnsibleHelper;

def handleBodyInput(body,pipeline_params){
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipeline_params
    body()
}

def call(body) {
    def pipeline_params = [:]
    // handleBodyInput(body,pipeline_params)
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipeline_params
    body()
    def invokeAnsibleHelper = new InvokeAnsibleHelper(this)

    //different ways to get the values
    String credentialsId = pipeline_params.get('credentialsId')
    println(credentialsId)
    String credentialsId2 = pipeline_params.credentialsId
    println(credentialsId2)
    String credentialsId3 = pipeline_params.containsKey('credentialsId') ? pipeline_params.get('credentialsId') : 'default'
    println(credentialsId3)



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