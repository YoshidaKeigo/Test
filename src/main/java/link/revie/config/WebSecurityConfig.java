package link.revie.config;

import link.revie.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * セキュリティ設定を無視する設定
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/css/**",
                "/custom-tag/**",
                "/font/**",
                "/img/**",
                "/js/**",
                "/web-lib/**"
        );
    }

    /**
     * 認証設定
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認証していないユーザーを含む全てのユーザーがアクセスできるパスの設定
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/Login",
                        "/Detail/**",
                        "/Category/**",
                        "/Search/**",
                        "/MovieType/**"
                )
                .permitAll().anyRequest().authenticated();

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/Login/Auth")                          // 認証処理のパス
                .loginPage("/Login")                                        // ログインフォームのパス
                .failureUrl("/Login")                                       // 認証失敗時の遷移先
                .defaultSuccessUrl("/")                                     // 認証成功時の遷移先
                .usernameParameter("userId").passwordParameter("password")  // ユーザー名、パスワードのパラメータ名
                .and();

        // ログアウト設定
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/Logout")) // ログアウト処理のパス
                .logoutSuccessUrl("/");                                     // 成功時の遷移先
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        private final AccountService accountService;

        @Autowired
        public AuthenticationConfiguration(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // 認証するユーザーを設定する
            auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
        }
    }
}