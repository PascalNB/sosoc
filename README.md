# Service-Oriented Security Operations Center

## Dependencies

Make sure the following tools are installed before building or deploying the project:

- **Java 21** – Required to compile the services using Maven
- **Maven** – Used to build and package the Java services
- **Docker** – For building and running container images
- **Docker Compose** – To run the services without Kubernetes
- **Minikube** – To create and manage a local Kubernetes cluster
- **kubectl** – CLI tool to interact with the Kubernetes cluster

## Build

The folder `services` contains a `pom.xml` that specifies all services as modules.
To build the project, run:

```bash
mvn -f ./services/pom.xml clean package
```

This generates a JAR file for each service.

## Deploy with Minikube

The project consists of a multi-container application, where each service corresponds to a container.
The containers are orchestrated using minikube in a Kubernetes cluster.
To deploy the containers, first start minikube:

```bash
minikube start
```

Next, the container images need to be built and pushed to the local minikube registry.

For Windows users, run the following command:

```bash
minikube docker-env --shell powershell | Invoke-Expression
```

For Linux or Mac users, run:

```bash
eval $(minikube docker-env)
```

Next, `docker-compose.yml` file in `services` specifies the multi-container application.
To build the images, run:

```bash
docker-compose -f ./services/docker-compose.yml build
```

The Kubernetes manifest files need to be applied now to the minikube cluster.
The manifests consist of a namespace, a database directory, and a deployment/statefulset file for each service.

To apply the manifests, run:

```bash
kubectl apply -f ./kubernetes/soa-project-namespace.yml
```

Wait for the namespace to be created.
Note that the alert and notify service depends on a secrets file. This file is generated using a `.env` file.
To generate the secrets file, run:

```bash
kubectl create secret generic alertnotify-mail --namespace=soa-project --from-env-file=services/.env
```

Wait for the secret to be created.
Then, apply the database and service manifests.

```bash

kubectl apply -f ./kubernetes/database/
kubectl apply -f ./kubernetes/services/
``` 

Then, enable the connection to the gateway service. Open a new terminal and run:

```bash
minikube tunnel
```

Now the services are running in the minikube cluster.
The user interface is accessible at `http://localhost:8080`.

Optionally, you can also visualize the Kubernetes cluster using the Kubernetes dashboard.
Open a new terminal and run:

```bash
minikube dashboard
``` 

## Deploy with Docker Compose

If for any reason, the application cannot be hosted in a Kubernetes cluster,
the application can be run using Docker Compose.
To deploy the containers, run:

```bash
docker-compose -f ./services/docker-compose.yml up --build
```

## Functionality Testing

Once all services are running, post the following JSON object to `http://localhost:8080/api/logs`.

```json
{
  "event": "email-received",
  "endpoint": {
    "name": "client-1",
    "ip": "198.51.100.42",
    "host": "example.com"
  },
  "data": {
    "email": {
      "source": "user@malicious.com"
    }
  }
}
```

This action sends the JSON log object to the **Log Collection & Monitoring Service**,
which stores it in its corresponding database.
The functionality that follows is explained in the report.
