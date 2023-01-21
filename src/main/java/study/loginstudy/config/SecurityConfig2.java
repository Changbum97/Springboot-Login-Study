package study.loginstudy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import study.loginstudy.auth.JwtTokenFilter;
import study.loginstudy.domain.UserRole;
import study.loginstudy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token Login에 사용하는 Security Config
 */
/*@Configuration
@EnableWebSecurity*/
@RequiredArgsConstructor
public class SecurityConfig2 {

    private final UserService userService;
    private static String secretKey = "my-secret-key-123123";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                // API
                .antMatchers("/jwt-api-login/info").authenticated()
                .antMatchers("/jwt-api-login/admin/**").hasAuthority(UserRole.ADMIN.name())
                // 화면
                .antMatchers("/jwt-login/info").authenticated()
                .antMatchers("/jwt-login/admin/**").hasAuthority(UserRole.ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                // Security Config와는 달리 익명 클래스 사용
                // 인증 실패
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException, IOException {
                        // jwt-api-login(api)에서 인증에 실패하면 error을 그대로 출력
                        // jwt-login(화면)에서 인증에 실패하면 에러 페이지로 redirect
                        if (!request.getRequestURI().contains("api")) {
                            response.sendRedirect("/jwt-login/authentication-fail");
                        }
                    }
                })
                // 인가 실패
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        if (!request.getRequestURI().contains("api")) {
                            response.sendRedirect("/jwt-login/authorization-fail");
                        }
                    }
                })
                .and().build();
    }
}
