pipeline {
    agent any 
    
    tools {
        jdk 'jdk8'
        maven 'mvn3'
    }

    // using the Timestamper plugin we can add timestamps to the console log
    options {
        timestamps()
    }
   
    parameters {
        string(defaultValue: "clean install", description: "What Maven goal to call", name: "MAVEN_PARAMS")
    }

    stages {   
        stage('test java installation') {
            steps {
                sh 'java -version'
                sh 'which java'
            }
        }

        stage('test maven installation') {
            steps {
                sh 'mvn -version'
                sh 'which mvn'
            }
        }  

        stage('Prepare environment') { 
            steps {  
                checkout scm         
            }
        }

        stage('Build') {           
            steps {               
                sh "mvn ${env.MAVEN_PARAMS}"
            }              
        }
        

    }
}
