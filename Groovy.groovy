
      package de.allianz.Groovy

      def lib = library identifier: 'BizDevOps_JSL@develop', retriever: modernSCM(
      [$class: 'GitSCMSource',
        remote: 'https://github.developer.allianz.io/JEQP/BizDevOps-JSL.git',
        credentialsId: 'TOKEN'])

      def jslMaven      = lib.de.allianz.bdo.pipeline.JSLMaven.new()
      
      def jslNexus      = lib.de.allianz.bdo.pipeline.JSLNexus.new()
      def jslSonarqube  = lib.de.allianz.bdo.pipeline.JSLSonarqube.new()

      def build() {
        jslMaven.build()
      }

      def componentTest {
        jslMaven.testunit("component") 
        jslSonarqube.call()
      }

      def integrationTest {
        jslMaven.testunit("integration")
      }

      def uatTest {
        jslMaven.testunit("uat")
      }

      def acceptanceTest {
        jslMaven.testunit("acceptance")
      }

      def publishArtifacts() {
        jslNexus.push()
      }

    