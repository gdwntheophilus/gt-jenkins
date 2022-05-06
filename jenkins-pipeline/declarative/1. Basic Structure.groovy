pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                // 
                sh 'echo this is build section'
            }
        }
        stage('Test') { 
            steps {
                // 
                sh 'echo this is test section'
            }
        }
        stage('Deploy') { 
            steps {
                // 
                sh 'echo this is deploy'
            }
        }
    }
}