package backendminhagab.example.MinhaGab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final CustomUserDetailService userDetailsService;
    private final SecurityFilter securityFilter;

    public SecurityConfig(CustomUserDetailService userDetailsService, SecurityFilter securityFilter) {
        this.userDetailsService = userDetailsService;
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");

        http
                .cors() // Ativando o suporte a CORS
                .and()
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("FINANCEIRO")
                        .requestMatchers("/clinica/**").hasRole("CLINICA")
                        .requestMatchers(HttpMethod.GET, "/comentarios/todos").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.POST, "/comentarios/criar").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.GET, "/comentarios/usuario/{userId}").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.GET, "/comentarios/{id}").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.PUT, "/comentarios/{id}").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.DELETE, "/comentarios/deletar/{id}").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.PUT, "/comentarios/responder").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.GET, "/gab_requests").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.POST, "/gab_requests/create").hasAnyRole("FINANCEIRO", "CLINICA")
                        .requestMatchers(HttpMethod.DELETE, "/gab_requests/delete/{id}").hasAnyRole("FINANCEIRO", "CLINICA")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        logger.info("Security filter chain configured");

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5173", "http://localhost:5173")); // Origens permitidas
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        config.setAllowedHeaders(Arrays.asList("*")); // Cabeçalhos permitidos
        config.setAllowCredentials(true); // Permitir cookies

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplicar CORS em todas as rotas

        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Creating PasswordEncoder bean");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        logger.info("Creating AuthenticationManager bean");
        return authenticationConfiguration.getAuthenticationManager();
    }
}
