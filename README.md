# S3 API demo

This is a simple Springboot app that can upload and download AWS S3 objects to a single bucket.

To run you'll need to provide:

- The S3 bucket name (uploads files at the root level)
- AWS Access key
- AWS Secret key

Sample run command
```bash
mvnw spring-boot:run "-Dspring-boot.run.arguments=--app.bucket_name=BUCKETNAME --cloud.aws.credentials.accessKey=AWS_ACCESS_KEY --cloud.aws.credentials.secretKey=AWS_SECRET_KEY"
```

A dockerfile is added for container-based deployments. This can also be built+deployed using AWS codebuild/codedeploy/ecs and can be referenced in the `buildspec.yml`, `appspec.yml`, and the `taskdef.json` file.