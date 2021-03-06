package com.hoteling.hotelApp.config;

import com.hoteling.hotelApp.security.JWTConfigurer;
import com.hoteling.hotelApp.security.TokenProvider;
import com.hoteling.hotelApp.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private Environment env;

    private UserSecurityService userSecurityService;

    private final CorsFilter corsFilter;

    private final TokenProvider tokenProvider;

    public SecurityConfig(Environment env, UserSecurityService userSecurityService, CorsFilter corsFilter,
                          TokenProvider tokenProvider) {
        this.env = env;
        this.userSecurityService = userSecurityService;
        this.corsFilter = corsFilter;
        this.tokenProvider = tokenProvider;
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/image/**",
            "/room/**",
            "/user/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/content/**")
                .antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).csrf().disable().cors().disable().httpBasic().and().authorizeRequests()
                .antMatchers("/api/authenticate")
                .permitAll()
                .antMatchers("/api/**")
                .authenticated()
                // .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() To allow options but it is not working need to
                // filter
                .anyRequest().permitAll()
                .and()
                .apply(securityConfigurerAdapter());;
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

}
