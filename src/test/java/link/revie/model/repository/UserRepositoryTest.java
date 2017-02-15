package link.revie.model.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import link.revie.model.entity.User;
import link.revie.support.CsvDataSetLoader;
import link.revie.support.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Userテーブル操作用レポジトリのテスト
 * テスト毎にDBの内容を確認したかったので、@Transactionalは付けていません。
 * 必要になったら付けてください。
 */
@RunWith(SpringRunner.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestUtil testUtil;

    /**
     * 存在するユーザーのレコードが取得出来ていることをテストします。
     * userNameはUNIQUE制約があるので、同一名のuserNameが存在するテストは実施しません。
     * (複数レコード取得できないかのテストは実施しません。Userを作成することがあれば、
     * そちらで同一名のuserNameが作成できないことをテストします。)
     */
    @Test
    @DatabaseSetup("/testdata/UserRepository/base/setup/")
    public void test_001_findByUserName_get_success() throws Exception {
        User expected = this.testUtil.generateExpectedUser(
                2,
                "test_user2",
                "hoge",
                User.Authority.ADMIN,
                "2017-02-01 12:12:12",
                "2017-02-01 13:12:12"
        );
        User actual = userRepository.findByUserName("test_user2");
        assertThat(actual, equalTo(expected));
    }

    /**
     * 存在しないユーザーを取得するとnullになることをテストします。
     */
    @Test
    @DatabaseSetup("/testdata/UserRepository/base/setup/")
    public void test_002_findByUserName_get_fail() throws Exception {
        User actual = userRepository.findByUserName("hige");
        assertThat(actual, nullValue());
    }

}