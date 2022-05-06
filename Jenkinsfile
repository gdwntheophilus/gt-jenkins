pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'echo "Hello Build"'
        sleep 10
        ansiColor(colorMapName: 'xterm') {
          sh 'echo color changed'
        }

      }
    }

  }
}