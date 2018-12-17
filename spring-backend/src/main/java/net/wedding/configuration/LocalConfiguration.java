package net.wedding.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@Profile(value = "local")
public class LocalConfiguration {

    @Bean
    public DataSource localDataSource() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        final DataSource hsqlDataSource = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("local-schema.sql")
                .addScript("local-data.sql")
                .build();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setDataSource(hsqlDataSource);

        return new HikariDataSource(hikariConfig);
    }
}
