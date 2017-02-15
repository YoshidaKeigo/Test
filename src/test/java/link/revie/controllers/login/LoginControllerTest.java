package link.revie.controllers.login;

import link.revie.model.entity.User;
import link.revie.service.account.AccountService;
import link.revie.support.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

/**
 * ログインコントローラーのテスト
 * ログイン・ログアウト処理はWebSecurityConfigのテストに書いた方がいいかもだが
 * こちらの方がわかりやすい気がするのでここに書きます。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private TestUtil testUtil;

    @MockBean
    private AccountService accountService;

    /**
     * ログインページが表示されていることをテストします。
     */
    @Test
    public void test_001_login_index() throws Exception {
        this.mvc.perform(get("/Login"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("login"))
                .andExpect(xpath("/html/head/title").string("Login"));
    }

    /**
     * 指定されたユーザー名(userId)で取得したUser情報とパスワードが一致した場合、ログインが成功することをテストします。
     */
    @Test
    public void test_002_login_OK() throws Exception {
        String userName = "login_ok_user";
        String password = "login_ok";
        String encodePassword = new BCryptPasswordEncoder().encode(password);
        User returnUser = this.testUtil.generateExpectedUser(
                1,
                userName,
                encodePassword,
                User.Authority.ADMIN,
                "2017-02-02 12:12:12",
                "2017-02-02 13:12:12"
        );
        when(this.accountService.loadUserByUsername(userName))
                .thenReturn(returnUser);

        this.mvc.perform(formLogin()
                .loginProcessingUrl("/Login/Auth")
                .user("userId", userName)
                .password("password", password)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/Add/Form"))
                .andExpect(authenticated().withUsername(userName));
    }

    /**
     * 指定されたユーザー名(userId)で取得したUser情報とパスワードが一致しなかった場合に
     * (ここではエンコードしないパスワードとの比較)ログインが失敗することをテストします。
     */
    @Test
    public void test_003_login_NG_password_missMatch() throws Exception {
        String userName = "login_exist_user";
        String password = "login_exist_user_password";
        User returnUser = this.testUtil.generateExpectedUser(
                1,
                userName,
                password,
                User.Authority.ADMIN,
                "2017-02-02 12:12:12",
                "2017-02-02 13:12:12"
        );
        when(this.accountService.loadUserByUsername(userName))
                .thenReturn(returnUser);

        this.loginErrorTest(userName, password);
    }

    /**
     * 指定されたユーザー名(userId)が存在しなかった場合にログインが失敗することをテストします。
     */
    @Test
    public void test_004_login_NG_user_notFound() throws Exception {
        String userName = "login_not_exist_user";
        String password = "login_not_exist_user_password";
        when(this.accountService.loadUserByUsername(userName))
                .thenThrow(new UsernameNotFoundException("User not found for name : " + userName));

        this.loginErrorTest(userName, password);
    }

    /**
     * ログイン中にログアウトを実施しログアウトが成功していることをテストします。
     */
    @Test
    @WithMockUser(username = "login_ok_user", authorities = {"ADMIN"})
    public void test_005_logout_OK() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(authenticated());
        this.mvc.perform(get("/Logout"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated());
    }

    /**
     * ログインしていない時にログアウトを実施しトップ画面にリダイレクト認証がされていないことをテストします。
     */
    @Test
    public void test_006_logout_OK() throws Exception {
        this.mvc.perform(get("/Logout"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(unauthenticated());
    }

    /**
     * ログインエラー時の共通テスト
     * テストなのでそれぞれ書いた方が良いし微妙なのですが、ひとまずこれで。
     * BadCredentialsExceptionのテスト部分が本当は、
     * andExpect(request().sessionAttribute("SPRING_SECURITY_LAST_EXCEPTION", isA(BadCredentialsException.class))
     * って感じで書きたいが、isA(BadCredentialsException.class)のとこがnullになってしまっていて実現できていない。
     * 原因がわかったら直す。
     */
    private void loginErrorTest(String userName, String password) throws Exception {
        this.mvc.perform(formLogin()
                .loginProcessingUrl("/Login/Auth")
                .user("userId", userName)
                .password("password", password)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/Login"))
                .andExpect(unauthenticated())
                .andExpect(result -> {
                    String name = "SPRING_SECURITY_LAST_EXCEPTION";
                    Object obj = result.getRequest().getSession().getAttribute(name);
                    if (obj instanceof BadCredentialsException) {
                        AssertionErrors.assertEquals("Session attribute \'" + name + "\'",
                                "User ID、またはPasswordが正しくありません",
                                ((BadCredentialsException) obj).getMessage());
                    } else {
                        AssertionErrors.fail("BadCredentialsExceptionではありませんでした。",
                                "BadCredentialsException",
                                obj);
                    }
                });
    }

}