pipeline {
    agent any
    options {
        ansiColor('xterm')
    }
    stages {
        stage('git clone'){
            
            steps{
                dir('/jenkins'){
                    sh 'ls -lrt'
                }
            }
        }
    }
}