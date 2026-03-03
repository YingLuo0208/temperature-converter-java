pipeline {
    agent any

    tools {
        // Make sure 'Maven3' is configured in Jenkins Global Tool Configuration
        maven 'Maven3'
    }

    environment {
        // Add Docker to PATH (Windows specific)
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

        // Docker Hub credentials (configure in Jenkins Credentials)
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'

        // Docker Hub repository
        DOCKERHUB_REPO = 'luoying0208/temperature-converter'

        // Docker image tag
        DOCKER_IMAGE_TAG = 'v1'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/YingLuo0208/temperature-converter-java.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running unit tests...'
                bat 'mvn test'
            }
        }

        stage('Generate JaCoCo Report') {
            steps {
                echo 'Generating code coverage report...'
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Test Results') {
            steps {
                echo 'Publishing test results...'
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                echo 'Publishing code coverage report...'
                jacoco()
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }

    }

    post {
        always {
            echo 'Pipeline execution completed!'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

