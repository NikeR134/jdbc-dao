package ru.netology.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String productQuery;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.productQuery = read("find-products.sql");
    }

    public List<String> getProductName(String name) {
        return jdbcTemplate.queryForList(productQuery, Map.of("name", name), String.class);
    }

    private static String read(String scriptFileName) {
        try (InputStream inputStream = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to read SQL script " + scriptFileName, exception);
        }
    }
}
