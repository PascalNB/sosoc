# Service-Oriented Security Operations Center

## Building

The folder `services` contains a `pom.xml` that specifies all services as modules.
To build the project, run:

```bash
mvn -f ./services/pom.xml clean package
```

This generates a JAR file for each service.

## Deployment

The `docker-compose.yml` file in `services` specifies the multi-container application.
It includes all services, ActiveMQ and PostgreSQL.
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
The functionality that follows is explained in the midterm report.
