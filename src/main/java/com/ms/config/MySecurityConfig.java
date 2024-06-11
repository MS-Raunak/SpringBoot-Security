package com.ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig{
    //basic configuration
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        *this configuration occurs with WebSecurityConfigurorAdaptor which was used in older version now its delete from spring security 6
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(Customizer.withDefaults());

         //This is also removed from spring security 6 onwards
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/")
                .permitAll()
                .and()
                .formLogin();
        */

        //latest configuration::provide security for all controllers' handler
        /*return http.authorizeHttpRequests(request -> request.anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();

         */

        //Provide security for specific controller's handlers except home,login,register
        /*return http.authorizeHttpRequests(request -> request.requestMatchers("/home", "login", "register").permitAll().anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
         */
        //Same like above but controller specific configuration is happening
        /* BASIC CONFIGURATION
            return http.authorizeHttpRequests(request -> request

                        .requestMatchers("/home/**") ///home/** means request starting with /home
                        .hasRole("USER")//USER AND ADMIN BOTH CAN ACCESS /home/** request
                        .anyRequest()
                        .authenticated())
                        .httpBasic(Customizer.withDefaults())
                        .build();

         */
        //Form Based Configuration(Defualt form Made by S boot): Mainly Used in Web app
        /* return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/home/**") ///home/** means request starting with /home
                        .hasRole("USER")//USER AND ADMIN BOTH CAN ACCESS /home/** request
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults()) //Default form
                .build();



    }
    */
    //Form-Based Config(Custom Form Validation)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                ).build();
    }

    //Role based configuration
    @Bean
    public InMemoryUserDetailsManager inMemoryConfiguration(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("NORMAL")  //normal username
                .password(passwordEncoder.encode("NORMAL")) // normal user password
                .roles("USER") //role is user
                .build();

        UserDetails admin = User.withUsername("ADMIN") //Admin username
                .password(passwordEncoder.encode("ADMIN")) //Admin user password
                .roles("USER", "ADMIN") // admin play both role, can be both user and admin
                .build();

        return new InMemoryUserDetailsManager(user, admin);

    }

    //Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder(10);

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return passwordEncoder;
    }
}

