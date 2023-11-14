pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url:'https://github.com/AaronJTan/DailyUpdates.git'
            }
        }

        stage('Build') {
            parallel {
                stage('Build and Push Frontend Image') {
                    steps {
                        script {
                            def frontendImage = docker.build('aaronjtan/dailyupdates-ui:latest', './dailyupdates-ui')
                            frontendImage.push()
                        }
                    }
                }
                stage('Build and Push Backend Image') {
                    steps {
                        script {
                            def backendImage = docker.build('aaronjtan/dailyupdates:latest', './DailyUpdates')
                            backendImage.push()
                        }
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                dir('~/deploy') {
                    sh 'ansible-playbook deploy.yaml'
                }
            }
        }
    }
}