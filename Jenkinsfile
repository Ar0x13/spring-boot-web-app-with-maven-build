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
                // sh "mvn ${env.MAVEN_PARAMS}" 
                sh "mvn clean install"   
            }

            post {
                success {
                    // stash name: "app", includes: "target/*.jar"
                    // Archive the built artifacts
                    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true, fingerprint: true
                }
            }
        }

        stage('Deploy to prod1') {
            steps { 
                sh 'echo hostname'
                // copy artifcat to production nodes         
                sshPublisher(publishers: [sshPublisherDesc(configName: 'prod1', transfers: [sshTransfer(cleanRemote: false, excludes: '',
                                          execCommand:'pkill -f \'java -jar\' && sleep 6s && cd /home/jenkins && java -jar *-SNAPSHOT.jar &', 
                                          execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false,
                                          patternSeparator: '[, ]+', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')],
                                          usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }

        stage('Deploy to prod2') {
            steps {  
                sh 'echo hostname'
                // copy artifcat to production nodes 
                sshPublisher(publishers: [sshPublisherDesc(configName: 'prod2', transfers: [sshTransfer(cleanRemote: false, excludes: '',
                                          execCommand:'pkill -f \'java -jar\' && sleep 6s && cd /home/jenkins && java -jar *-SNAPSHOT.jar &', 
                                          execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false,
                                          patternSeparator: '[, ]+', remoteDirectorySDF: false, removePrefix: 'target', sourceFiles: 'target/*.jar')],
                                          usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
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
