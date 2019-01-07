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
   
    // parameters {
    //     string(defaultValue: "install", description: "What Maven goal to call", name: "MAVEN_GOAL")
    // }

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
                // sshagent (credentials: ['jenkins-ssh-key']) {
                //     sh 'printenv'
                //     sh 'ssh-add -l -o StrictHostKeyChecking=no '
                //     git branch: 'master', url: 'git@github.com:Ar0x13/spring-boot-web-app-with-maven-build.git'
                //     checkout scm
                // }       
                checkout scm         
            }
        }

        stage('Build') { 
            agent any

            steps {               
                // sh "mvn clean ${env.MAVEN_GOAL}"
                sh "mvn clean install"
            }            
        }

    }
}
