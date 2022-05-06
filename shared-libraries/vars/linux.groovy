def call(Closure body) {
    node('jenins-agent-slave-02') {
        body()
    }
}