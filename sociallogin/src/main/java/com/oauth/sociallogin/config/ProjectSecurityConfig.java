package com.oauth.sociallogin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
/*
 This entire class cnan be skipped if we add following property to application.properties

 */
@Configuration
public class ProjectSecurityConfig {
    @Value("${github.client.secret}")
    private String gitClientSecret;

    @Value("${facebook.client.secret}")
    private String facebookClientId;

    @Value("${facebook.client.secret}")
    private String facebookClientSecret;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests)-> requests.requestMatchers("/secure").authenticated()
        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                //whenever we specify oauth2 login we need to specify which oauth server is used whether its social login or custom auth server
                //we can specifu that in separate bean i.e. CLientRegistrationRepository
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();

    }
    /*
// In ClientRegistrationRepo store all auth server related info
    @Bean
    ClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration github=githubClientRegistration();
       // ClientRegistration facebook=githubClientRegistration();

        return new InMemoryClientRegistrationRepository(github);
    }


    private ClientRegistration githubClientRegistration(){
        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23lifdJ6MZTdUc3j0S").clientSecret(gitClientSecret).build();
    }

    private ClientRegistration facebookClientRegistration(){
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook").clientId(facebookClientId).clientSecret(facebookClientSecret).build();
    }*/
}
