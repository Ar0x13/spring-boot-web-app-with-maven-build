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

    environment {
        //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
    }
    
    parameters {
        string(defaultValue: "install", description: "What Maven goal to call", name: "MAVEN_GOAL")
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
            agent any
            steps {
                git branch: 'master', url: 'git@github.com:Ar0x13/spring-boot-web-app-with-maven-build.git'
                checkout scm
                
            }
        }

        stage('Build') { 
            agent any

            steps {               
                sh "mvn clean ${env.MAVEN_GOAL}"
            }            
        }

    }
}
