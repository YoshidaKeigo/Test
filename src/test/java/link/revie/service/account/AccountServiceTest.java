package link.revie.service.account;

import link.revie.model.entity.User;
import link.revie.model.repository.UserRepository;
import link.revie.support.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import static org.mockito.Mockito.*;

/**
 * アカウント情報を操作するサービスのテスト
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TestUtil testUtil;

    @MockBean
    private UserRepository userRepository;

    /**
     * 取得したUser情報がそのまま返却されていることをテストします。
     */
    @Test
    public void test_001_loadUserByUsername_OK() throws Exception {
        String userName = "load_success_user";
        User expected = this.testUtil.generateExpectedUser(
                1,
                userName,
                "load_success_password",
                User.Authority.ROLE_ADMIN,
                "2017-02-02 12:12:12",
                "2017-02-02 13:12:12"
        );
        when(this.userRepository.findByUserName(userName)).thenReturn(expected);

        UserDetails actual = this.accountService.loadUserByUsername(userName);
        assertThat(expected, equalTo(actual));
    }

    /**
     * userNameを空文字で取得した場合にUsernameNotFoundExceptionになることをテストします。
     */
    @Test(expected = UsernameNotFoundException.class)
    public void test_002_loadUserByUsername_NG_empty_username() throws Exception {
        String userName = "";
        //MEMO これいらない気がするが一応
        User returnUser = this.testUtil.generateExpectedUser(
                1,
                "empty_username",
                "empty_username_password",
                User.Authority.ROLE_ADMIN,
                "2017-02-02 12:12:12",
                "2017-02-02 13:12:12"
        );
        when(this.userRepository.findByUserName(userName)).thenReturn(returnUser);

        this.accountService.loadUserByUsername(userName);
    }

    /**
     * userNameをNullで取得した場合にUsernameNotFoundExceptionになることをテストします。
     */
    @Test(expected = UsernameNotFoundException.class)
    public void test_003_loadUserByUsername_NG_null_username() throws Exception {
        String userName = null;
        //MEMO これいらない気がするが一応
        User returnUser = this.testUtil.generateExpectedUser(
                1,
                "null_username",
                "null_username_password",
                User.Authority.ROLE_ADMIN,
                "2017-02-02 12:12:12",
                "2017-02-02 13:12:12"
        );
        when(this.userRepository.findByUserName(userName)).thenReturn(returnUser);

        this.accountService.loadUserByUsername(userName);
    }

    /**
     * 取得したUser情報がNullの場合にUsernameNotFoundExceptionになることをテストします。
     */
    @Test(expected = UsernameNotFoundException.class)
    public void test_004_loadUserByUsername_NG_null_user() throws Exception {
        String userName = "return_user_null";
        when(this.userRepository.findByUserName(userName)).thenReturn(null);

        this.accountService.loadUserByUsername(userName);
    }
}