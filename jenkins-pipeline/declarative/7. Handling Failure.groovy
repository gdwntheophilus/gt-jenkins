pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'echo check'
            }
        }
    }
    //post method
    post {
        always {
            echo "hoooo"
        }
        failure {
            echo "hiiiiiii"
        }
    }
}