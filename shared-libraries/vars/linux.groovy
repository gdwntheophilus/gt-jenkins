def call(Closure body) {
    node('linux') {
        body()
    }
}