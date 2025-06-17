# Card Management System

Система управления банковскими картами предоставляет REST API для работы с картами и пользователями.

## Основные возможности
* Создание, блокировка, активация и удаление карт.
* Переводы между собственными картами.
* Просмотр баланса и списка карт (пагинация и фильтрация).
* Администрирование пользователей.

## Технологии
* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Liquibase
* JWT
* Swagger
* PostgreSQL/MySQL

## Настройка и запуск
1. Создайте файл `application.properties` в `src/main/resources` и укажите параметры подключения к базе данных и секреты:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/card_db
   spring.datasource.username=YOUR_DB_USER
   spring.datasource.password=YOUR_DB_PASSWORD
   security.jwt.secret-key=YOUR_JWT_SECRET
   security.encryption-card-util.secret-key=YOUR_ENCRYPTION_SECRET
   ```
2. Запустите приложение:
   ```bash
   mvn spring-boot:run
   ```
   или используйте Docker Compose, если он добавлен в проект.

## Полезные ссылки
* Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* Миграции Liquibase: `src/main/resources/db/changelog`

## Замечания
Временные README-файлы в директориях контроллеров или сервисов следует удалить перед релизом.
