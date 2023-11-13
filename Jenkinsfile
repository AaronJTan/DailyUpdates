pipeline {
    agent {
        label 'build-server'
    }

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
                dir('/home/ubuntu/deploy') {
                    sh 'ansible-playbook deploy.yaml'
                }
            }
        }
    }
}