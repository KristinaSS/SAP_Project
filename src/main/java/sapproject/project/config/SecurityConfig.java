package sapproject.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sapproject.project.jwtsecurity.CustomUserDetailsService;
import sapproject.project.jwtsecurity.JwtAuthenticationEntryPoint;
import sapproject.project.jwtsecurity.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .and()
                .inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/campaign/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/cart").permitAll()
                .antMatchers("/product/all").permitAll()
                .antMatchers("/account/all").permitAll()
                .antMatchers("/account/get-{id}").hasAuthority("admin")
                .antMatchers("/account/update").permitAll()
                .antMatchers("/account/delete-{accID}").permitAll()
                .antMatchers("/account/create-{accID}").permitAll()
                .antMatchers("/findAccountByEmail").permitAll()
                .antMatchers("/product/filteredByCategory").permitAll()
                .antMatchers("/product/filteredByKeyword").permitAll()
                .antMatchers("/product/get").permitAll()
                .antMatchers("/product/create").permitAll()
                .antMatchers("/product/delete").permitAll()
                .antMatchers("/product/edit").permitAll()
                .antMatchers("/product/outOfStock").permitAll()
                .antMatchers("/cart/getAllByUser").permitAll()
                .antMatchers("/cart/addItem").permitAll()
                .antMatchers("/cart/calculate").permitAll()
                .antMatchers("/cart/calculate-with-shipping").permitAll()
                .antMatchers("/cart/deleteItem").permitAll()
                .antMatchers("/order/makeOrder").permitAll()
                .antMatchers("/orderDetails/**").permitAll()
                .antMatchers("/product/filteredByCampaign").permitAll()
                .antMatchers("/campaign/deleteProductInCampaign").permitAll()
                .antMatchers("/campaign/active").permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
     }

}
