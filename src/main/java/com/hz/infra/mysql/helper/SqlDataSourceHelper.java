package com.hz.infra.mysql.helper;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class SqlDataSourceHelper {
    public static DataSource create(String host, String port, String database, String userName, String password) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true" +
                        "&useJDBCCompliantTimezoneShift=true" +
                        "&useLegacyDatetimeCode=false" +
                        "&serverTimezone=UTC",
                host, port, database));
        config.setUsername(userName);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    public static DataSource createFromProperties(final Properties config) {
        return new HikariDataSource(new HikariConfig(config));
    }

    public static DataSource createFromProperties(final File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return createFromProperties(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error while loading config properties file at: " + file.getAbsolutePath(), e);
        }
    }

    public static DataSource createFromProperties(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("",e);
        }
        return createFromProperties(properties);
    }

    public static DataSource createFromProperties(final String configPropertiesFile) {
        return createFromProperties(new File(configPropertiesFile));
    }
}
