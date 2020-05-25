# Example app to run SQS locally with Spring Boot

Configurations needed to run the app locally.

```properties
cloud.aws.stack.auto=false
cloud.aws.region.static=us-east-1
```

## Localstack

Run LocalStack with Docker compose

```bash
docker-compose up
```

Create the Queue 

```bash
docker-compose exec localstack bash
awslocal sqs create-queue --queue-name my_queue
```

## Java examples

Send a message, check **[Enqueuer.java](src/main/java/co/pablobastidasv/Enqueuer.java)**.

Receive a message, check **[SqsMessageListener](src/main/java/co/pablobastidasv/SqsMessageListener.java)**.

NOTE: These examples run in local environment with [LocalStack](https://github.com/localstack/localstack),
to run this code in [EKS](https://aws.amazon.com/eks/) environment the configurations 
-[Spring Config](src/main/java/co/pablobastidasv/AmazonLocalConfig.java) and 
[Application properties](src/main/resources/application.yaml)- 
are not needed.
