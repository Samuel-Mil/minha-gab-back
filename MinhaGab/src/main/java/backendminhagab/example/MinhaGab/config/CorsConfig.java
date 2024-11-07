package backendminhagab.example.MinhaGab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas as rotas
                .allowedOrigins("http://127.0.0.1:5173", "http://localhost:5173") // Certifique-se de incluir todas as possíveis origens
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Cabeçalhos permitidos
                .allowCredentials(true); // Permitir cookies
}
}