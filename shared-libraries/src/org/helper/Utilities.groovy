package org.helper
class Utilities implements Serializable {
  def steps
  Utilities(steps) {this.steps = steps}
  def mvn(args) {
    steps.sh "echo -o ${args}"
    steps.sh "ls -lrt /"
  }
}