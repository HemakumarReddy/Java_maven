pipeline {
    
  agent any 

  stages {
    stage('Checkout') {
      steps {
        git credentialsId: '',         
        url: 'https://github.com/HemakumarReddy/Java_maven.git',
        branch: 'main'
      }
    }
    
    stage('Build code') {
      steps{
        bat script: 'mvn compile'
      }      
    }

    stage('Run Test') {
      steps{
        bat script: 'mvn test -Dbrowser=localchrome'
      }      
    }

    stage('Publish Report') {
      steps{
        pushHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir:'',reportFiles:'target/surefire-reports/Extent*.html', reportName:'Pipeline', reportTitles:''])
      }
    } 
       
  }
}
