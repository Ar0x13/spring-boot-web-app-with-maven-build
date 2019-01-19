pipeline {
    agent {
       label 'slave'
    }
    
    tools {
        jdk 'jdk8'
        maven 'mvn3'
    }

    // using the Timestamper plugin we can add timestamps to the console log
    options {
        timestamps()
        timeout(time: 20, unit: 'MINUTES')
    }
    
    // parameters {
    //  string(
        //     name: "MAVEN_PARAMS", 
        //     defaultValue: "clean install", 
        //     description: "What Maven goal to call"
        // )
    // }
   
    stages {   
        stage('test java and maven installation') {
            steps {

                // check java 
                sh 'java -version'
                sh 'which java'

                // check maven 
                sh 'mvn -version'
                sh 'which mvn'
            }
        }

        stage('Checkout repo') {           
            steps {             
                // checkout code from repo
                checkout scm
            }                      
        }

        stage('Build') {           
            steps {          
                dir('my_project') {  
                    // sh "mvn ${env.MAVEN_PARAMS}" 
                    sh "mvn clean install"   
                }                 
            }

            post {
                success {
                    // stash name: "app", includes: "target/*.jar"
                    // Archive the built artifacts
                    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true, fingerprint: true
                }
            }
        }

    }
    
    post {
        success {
            echo 'Build succeeded.'
        }

        unstable {
            echo 'This build returned an unstable status.'
        }

        failure {
            echo 'This build has failed. See logs for details.'
        }
    }
}
