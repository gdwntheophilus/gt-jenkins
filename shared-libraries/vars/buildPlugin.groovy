def call(Map config) {
    node {
        echo "https://github.com/jenkinsci/${config.name}-plugin.git"
        echo 'mvn install'
    }
}