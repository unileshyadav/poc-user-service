node {
    checkout scm

    docker.withRegistry('', 'unofel') {
      def customImage = docker.build("unofel/unotech-user:v1")
      customImage.push()
    }
    
              
    sh "wget 'https://storage.googleapis.com/space-cloud/linux/space-cli.zip'"
    sh "unzip space-cli.zip"
    sh "./space-cli login --username 'admin' --key '1234' --url 'http://a11d129a002d046c09249fbc63ccdd78-940691642.ap-south-1.elb.amazonaws.com'"
    sh "./space-cli apply service.yaml"
}