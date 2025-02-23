pipeline {
    agent any

    environment {
        BUILD_NUMBER_ENV = "${env.BUILD_NUMBER}"
        TEXT_SUCCESS_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Success"
        TEXT_FAILURE_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Failure"
    }

    stages {
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn clean install'
                    bat 'mvn clean verify sonar:sonar'
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }
    }

    post {
        success {
            script{
                 withCredentials([string(credentialsId: 'token-bot-tele', variable: 'TOKEN'), string(credentialsId: 'id-tele', variable: 'CHAT_ID')]) {
                    bat ''' curl -s -X POST https://api.telegram.org/bot"%TOKEN%"/sendMessage -d chat_id="%CHAT_ID%" -d text="%TEXT_SUCCESS_BUILD%" '''
                 }
            }
        }
        failure {
            script{
                withCredentials([string(credentialsId: 'token-bot-tele', variable: 'TOKEN'), string(credentialsId: 'id-tele', variable: 'CHAT_ID')]) {
                    bat ''' curl -s -X POST https://api.telegram.org/bot"%TOKEN%"/sendMessage -d chat_id="%CHAT_ID%" -d text="%TEXT_FAILURE_BUILD%" '''
                }
            }
        }
    }
}