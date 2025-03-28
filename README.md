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

Once all services are running, post the following JSON object to `http://localhost:8080/logs`.
Ensure that the timestamp is the current time in RFC 3339 and note that the correct time zone has to be set.
For example:
```
2025-03-28T18:03:30.553+01:00
```

You can use https://it-tools.tech/date-converter to quickly obtain the current time in the correct format.

```json
{
  "timestamp": "<timestamp>",
  "event": "email-received",
  "endpoint": {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
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
