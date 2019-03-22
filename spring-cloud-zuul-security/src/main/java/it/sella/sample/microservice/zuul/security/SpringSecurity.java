package it.sella.sample.microservice.zuul.security;


import it.sella.sample.microservice.zuul.security.common.JwtAuthenticationConfig;
import it.sella.sample.microservice.zuul.security.common.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by gbs04154 on 22-02-2019.
 */
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {


        @Autowired
        private JwtAuthenticationConfig authenticationConfig;

        @Bean
        public JwtAuthenticationConfig jwtConfig(){
               return new JwtAuthenticationConfig();
        }

        @Override
        protected void configure(final HttpSecurity httpSecurity) throws Exception {
//            super.configure(http);
                httpSecurity.csrf().disable()
                        .logout().disable()
                        .formLogin().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                                .anonymous()
                        .and()
                                .exceptionHandling().authenticationEntryPoint((req,rsp, e)-> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                        .and()
                                .addFilterAfter(new JwtTokenAuthenticationFilter(authenticationConfig), UsernamePasswordAuthenticationFilter.class)
                        .authorizeRequests()
                                .antMatchers(authenticationConfig.getUrl()).permitAll()
                                .antMatchers("/backend/admin").hasRole("ADMIN")
                                .antMatchers("/backend/user").hasRole("USER")
                                .antMatchers("/backend/guest").permitAll();
        }

}
