package com.trashguy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Autowired
        CustomSuccessHandler customSuccessHandler;

        @Autowired
        CustomFailureHandler customFailureHandler;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("user")
                    .password("password")
                    .roles("USER");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.httpBasic().disable();

            http.formLogin()
                    .successHandler(customSuccessHandler)
                    .failureHandler(customFailureHandler)
                    .permitAll();

            http.exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint);

            http.authorizeRequests()
                    .anyRequest()
                    .authenticated();

            http.csrf().disable();
        }
    }

    @Controller
    public class TestController {

        @RequestMapping(value = "/test", method = RequestMethod.GET)
        @ResponseBody
        String testMethod() {
            return "You should only be able to see this while authenticated";
        }

    }
}