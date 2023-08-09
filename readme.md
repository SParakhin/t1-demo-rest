1. Для запуска контейнера PostgreSQL поднять файл docker-compose.yaml
2. Описание openAPI доступно на http://localhost:8080/v3/api-docs Swagger-UI доступен теперь
только для версии Spring Boot 3.0 и выше. В требованиях использовать версию 2.***.
3. При необходимости для создания базы и пользователя запустить sql скрипт create-db-and-user.sql
в каталоге resources/db/changelog/create-db-and-user.sql
4. Аудит изменений профиля пользователя ведется с помощью библиотеки Envers в таблицах users_aud и
revinfo.
5. Для отправки тестовых хапросов через Postman можно использовать примеры тестовых json в папке
test\json.