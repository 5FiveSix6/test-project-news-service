package yuriy.spring.news_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yuriy.spring.news_service.entity.Status;
import yuriy.spring.news_service.service.status_updater.NewsStatusUpdateStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class NewsServiceApplicationConfiguration {

    private final List<NewsStatusUpdateStrategy> statusUpdateStrategies;

    @Bean
    public Map<Status, NewsStatusUpdateStrategy> statusUpdateStrategies() {
        Map<Status, NewsStatusUpdateStrategy> strategyMap = new HashMap<>();
        statusUpdateStrategies.forEach(strategy ->
                strategyMap.put(strategy.getStatus(), strategy));
        return strategyMap;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("News Service API")
                        .description("Test Spring Boot Application for \"Центр цифрового развития\"")
                        .version("1.0"));
    }
}
