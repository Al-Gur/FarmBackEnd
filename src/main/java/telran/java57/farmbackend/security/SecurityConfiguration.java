package telran.java57.farmbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    final CorsConfig corsConfig = new CorsConfig();

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors().configurationSource(corsConfig.corsConfigurationSource());
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("user/register").permitAll()

                .requestMatchers("user/showall")
                .access(new WebExpressionAuthorizationManager("hasRole('ADMINISTRATOR')"))

                .requestMatchers("/user/info/{login}")
                .access(new WebExpressionAuthorizationManager("hasRole('ADMINISTRATOR') " +
                        "or authentication.name == #login"))

                .requestMatchers(HttpMethod.DELETE, "/user/delete/{login}")
                .access(new WebExpressionAuthorizationManager("hasRole('ADMINISTRATOR') " +
                        "or authentication.name == #login"))

                .requestMatchers("/user/addrole/{login}/{role}")
                .access(new WebExpressionAuthorizationManager("hasRole('ADMINISTRATOR')"))

                .requestMatchers("/user/removerole/{login}/{role}")
                .access(new WebExpressionAuthorizationManager("hasRole('ADMINISTRATOR')"))

                .requestMatchers("products/showall").permitAll()
                .requestMatchers("products/show").permitAll()
                .requestMatchers("products/show/**").permitAll()

                .requestMatchers("products/reset_categories")
                .access(new WebExpressionAuthorizationManager("hasRole('ADMINISTRATOR')"))

                .requestMatchers("/products/cart/{login}")
                .access(new WebExpressionAuthorizationManager("hasRole('ADMINISTRATOR') " +
                        "or authentication.name == #login"))

                .anyRequest().authenticated()
        );
        return httpSecurity.build();
    }
}
