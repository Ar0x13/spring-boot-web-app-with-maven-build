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
    //     string(name: "MAVEN_PARAMS", defaultValue: "clean install", description: "What Maven goal to call")
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
            steps {  
                checkout scm         
            }
        }

        stage('Build') {           
            steps {               
                // sh "mvn ${env.MAVEN_PARAMS}"
                sh "mvn clean install"
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, onlyIfSuccessful: true
            }              
        }
        
        stage('Deploy to PROD') {
            agent {
                label 'prod'
            }
            when {
                branch 'master'
            }
            steps {
                step([  $class: 'CopyArtifact',
                    filter: '**/target/*.jar',
                    fingerprintArtifacts: true,
                    projectName: '${JOB_NAME}',
                    selector: [$class: 'SpecificBuildSelector', buildNumber: '${BUILD_NUMBER}'],
                    target: '/home/jenkins'
                ])
                sh 'nohup java -jar /home/jenkins/target/*.jar &'
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
