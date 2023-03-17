# Connect to MongoDB Atlas using ECS

## Step 1

Create a terraform.tfvars similar to this

```bash
security_groups    = ["sg-********************"]
subnets            = ["subnet-*******************"]
execution_role_arn = "arn:aws:iam::123456789:role/ecsTaskExecutionRole"
container_image    = "123456789.dkr.ecr.eu-west-2.amazonaws.com/mongodb-aws-ecs-springboot:latest"
MONGODB_URI        = "mongodb+srv://cluster0.abcde.mongodb.net/?authMechanism=MONGODB-AWS"
```

## Step 2

Build JAVA app and docker container

```bash
./gradlew bootBuildImage
```

## Step 3

Push image to ECR (Note: you can use docker hub as well)

```bash
aws ecr get-login-password --region eu-west-2 | docker login --username AWS 123456789.dkr.ecr.eu-west-2.amazonaws.com --password-stdin
docker push 123456789.dkr.ecr.eu-west-2.amazonaws.com/mongodb-aws-ecs-springboot:latest
```

## Step 4

Run terraform to build resources

```bash
cd terraform
terraform init
terraform plan
terraform apply
```

## Check results

Login to Cloud Watch and check the logs for the _mongodb-aws-ecs_ log group. You should see a document printed.

## TODO

There are a few steps that are missing here:

- Create a MongoDB Cluster
- Add a database user with the _execution_role_arn_ specified above that can read any database (or at least the movies collections in the m_flix database)
