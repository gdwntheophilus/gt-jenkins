package org.helper

class InvokeAnsibleHelper implements Serializable {
    def steps
    def InvokeAnsibleHelper(steps) {
        this.steps = steps
    }

    String runAnsiblePlaybook(String playbookname, String hostsFileName) {

        steps.retry(3) {
            steps.echo "running ansible playbook: ${playbookname}"
            steps.echo "ansible-playbook -i " + hostsFileName + ' ' + playbookname
        }
        return "ansible-playbook -i " + hostsFileName + ' ' + playbookname
    }
    
}