package io.eddie.sec_form.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
//                .formLogin(
//                        form -> {
//                            form.failureForwardUrl("/login?error=true")
////                                    .successForwardUrl("/")
////                                    .defaultSuccessUrl("/happy", true)
//                                    .usernameParameter("loginId")
//                                    .passwordParameter("loginPwd")
//                                    .loginProcessingUrl("/signin")
//                                    .loginPage("/signin")
//                                    .permitAll(); // /login
//                        }
//                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
//                .logout( logout -> {
//                    logout.logoutUrl("/signout")
//                            .logoutSuccessUrl("/")
//                            .clearAuthentication(true)
//                            .invalidateHttpSession(true)
//                            .deleteCookies("JSESSIONID");
//                })
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/signup", "/signin")
                                    .anonymous()
                                .requestMatchers("/user/**")
//                                    .hasRole("MEMBER")
//                                    .hasAnyRole("MEMBER", "MANAGER", "ADMIN")
                                    .hasAnyAuthority("MEMBER", "MANAGER", "ADMIN")
                                .requestMatchers("/manager/**")
//                                    .hasRole("MANAGER") // MANAGER, ROLE_MANAGER
//                                    .hasAnyRole("MANAGER", "ADMIN") // ROLE_MANAGER, ROLE_ADMIN
                                    .hasAnyAuthority("MANAGER", "ADMIN")
                                .requestMatchers("/admin/**")
//                                    .hasRole("ADMIN")
                                    .hasAuthority("ADMIN")
                                .anyRequest()
//                                    .denyAll();
                                    .authenticated();
                        }
                )
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        String targetPwd = "1234";
//        String encoded = passwordEncoder().encode(targetPwd);
//
//        log.info("encoded = {}", encoded);
//
//        manager.createUser(
//                User.withUsername("user")
//                        .password(encoded)
////                        .roles("ADMIN") // ROLE_ADMIN
//                        .authorities("ADMIN")
//                        .build()
//        );
//
//        return manager;
//
//    }


}
