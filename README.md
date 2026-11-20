# JDBC DAO

Spring Boot-приложение использует `NamedParameterJdbcTemplate` для поиска заказов по имени покупателя.

Запустите PostgreSQL командой `docker compose up -d`, затем приложение командой `mvn spring-boot:run`.

Пример запроса: `GET http://localhost:8080/products/fetch-product?name=Ivan`
