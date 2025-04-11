package nl.utwente.sosoc.userinterface.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfiguration {

    @Value("${services.logmonitor}")
    private String logmonitorUrl;
    @Value("${services.identitymanagement}")
    private String identitymanagementUrl;
    @Value("${services.playbookmanagement}")
    private String playbookmanagementUrl;
    @Value("${services.threatintelligence}")
    private String threatintelligenceUrl;

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/api/rules/**")
                .filters(f -> f.stripPrefix(1))
                .uri(logmonitorUrl))
            .route(r -> r.path("/api/logs/**")
                .filters(f -> f.stripPrefix(1))
                .uri(logmonitorUrl))
            .route(r -> r.path("/api/users/**")
                .filters(f -> f.stripPrefix(1))
                .uri(identitymanagementUrl))
            .route(r -> r.path("/api/playbooks/**")
                .filters(f -> f.stripPrefix(1))
                .uri(playbookmanagementUrl))
            .route(r -> r.path("/api/iocs/**")
                .filters(f -> f.stripPrefix(1))
                .uri(threatintelligenceUrl))
            .build();
    }

}
