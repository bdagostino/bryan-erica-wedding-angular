package net.wedding.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("default")
@PropertySource("spring-profile-not-set")
public class DefaultConfiguration {
}
