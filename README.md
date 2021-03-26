# S3 API demo

This is a simple AWS S3 app that can upload and download objects to a single bucket.

To run you'll need to provide:

- The S3 bucket name (uploads files at the root level)
- AWS Access key
- AWS Secret key

Sample run command
```bash
mvnw spring-boot:run "-Dspring-boot.run.arguments=--app.bucket_name=BUCKETNAME --cloud.aws.credentials.accessKey=AWS_ACCESS_KEY --cloud.aws.credentials.secretKey=AWS_SECRET_KEY"
```

A dockerfile is added for container-based deployments. This can also be built using AWS codebuild and can be referenced in `buildspec.yml`