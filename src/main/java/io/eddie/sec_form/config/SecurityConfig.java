package io.eddie.sec_form.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
//                .formLogin(Customizer.withDefaults())
                .formLogin(
                        form -> {
                            form.failureForwardUrl("/login?error=true")
//                                    .successForwardUrl("/")
//                                    .defaultSuccessUrl("/happy")
                                    .usernameParameter("loginId")
                                    .passwordParameter("loginPwd")
                                    .loginProcessingUrl("/signin")
                                    .loginPage("/signin")
                                    .permitAll(); // /login
                        }
                )
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/signup", "/signin")
                                    .anonymous()
                                .anyRequest()
//                                    .denyAll();
                                    .authenticated();
                        }
                )
                .build();
    }

}
