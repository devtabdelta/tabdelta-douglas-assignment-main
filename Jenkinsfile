pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    parameters {
      string defaultValue: 'testng.xml', name: 'xml_file'
    }
    stages {
        stage('Run Tests') {
            steps {
                script {
                    def xmlFile = params.xml_file
                    sh "mvn clean test -DsuiteFile=${xmlFile} -Dheadless=true -e"
                }
            }
        }
    }
    post {
      always {
        junit '**/target/surefire-reports/TEST-*.xml'
      }
    }
}
