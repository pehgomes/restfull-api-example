# schedule-people-service


> `schedule-people-service` manage birthday schedules

### Endpoints

| Method   | URI                                                                                             | Description                       | Response Status            |
|----------|-------------------------------------------------------------------------------------------------|-----------------------------------|----------------------------|
| `POST`   | [<u>`/v1/schedules/birthdays`](docs/payload/new-schedule.json)                                  | create a schedule.                | `201`, `202`, `422`, `500` |
| `PUT`    | [<u>`/v1/schedules/birthdays/{birthdayId}`](docs/payload/new-schedule.json)                     | update existing schedule by id.   | `204`, `422`               |
| `DELETE` | [<u>`/v1/schedules/birthdays/{birthdayId}`](docs/payload/new-schedule.json)                     | delete existing schedule by id.   | `200`, `422`               |
| `GET`    | [<u>`/v1/schedules/birthdays/by-birthday`](docs/payload/get-schedules.json)                     | returns schedules by birthday     | `200`, `500`               |
| `GET`    | [<u>`/v1/schedules/birthdays/by-name`](docs/payload/get-schedules.json)                         | returns schedules by name         | `200`, `500`               |
| `GET`    | [<u>`/v1/schedules/birthdays/sorted-by-name`](docs/payload/get-schedules-sorted-by-name.json)   | returns schedules sorted by name  | `200`, `500`               |
| `GET`    | [<u>`/v1/schedules/birthdays/sorted-by-month`](docs/payload/get-schedules-sorted-by-month.json) | returns schedules sorted by month | `200`, `500`               |
### Constraints

| Name               | Description      |
|--------------------|------------------|
| `RuntimeException` | CustomerNotFound |

## Project architecture and organization
### Frameworks and technologies

* Java
* Spring Boot (WebFlux, Data JPA, Actuator)
* Flyway
* Gradle (Kotlin DSL)
* Swagger, Springfox

## How build and run

```bash
./gradlew clean build

./gradlew composeUp

java -jar -Dspring.profiles.active=local build/libs/app.jar
```
## Contact information

* Pedro Gomes (pedrogomesup2@gmail.com)
