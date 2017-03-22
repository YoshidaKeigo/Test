package link.revie.view;

import link.revie.model.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 共通Viewのテストを行います。
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommonViewTest {

    @Autowired
    private MockMvc mvc;

    /**
     * 未ログインの場合、アカウント情報が表示されていないことをテストします。
     */
    @Test
    public void test_001_non_account_parts() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/header/div[@id='header-right']/account-parts/div[@id='account-name']")
                                .doesNotExist());
    }

    /**
     * admin権限ログイン済の場合、アカウント情報が表示されていることをテストします。
     */
    @Test
    @WithMockUser(username = "admin_user", authorities = {"ROLE_ADMIN"})
    public void test_002_show_account_parts() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/header/div[@id='header-right']/account-parts/div[@id='account-name']")
                                .string("admin_user"));
    }

    /**
     * user権限ログイン済の場合、アカウント情報が表示されていることをテストします。
     * (今のところ必要ないが、使う時に備えて)
     */
    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    public void test_003_show_account_parts() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/header/div[@id='header-right']/account-parts/div[@id='account-name']")
                                .string("user"));
    }

    /**
     * 未ログインの場合、ログアウトボタンが表示されていないことをテストします。
     */
    @Test
    public void test_004_non_logout_button() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/menu-separator/a[@href='/Logout']")
                                .doesNotExist());
    }

    /**
     * admin権限ログイン済の場合、ログアウトボタンが表示されていることをテストします。
     */
    @Test
    @WithMockUser(username = "admin_user", authorities = {"ROLE_ADMIN"})
    public void test_005_show_logout_button() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/menu-separator/a[@href='/Logout']")
                                .exists());
    }

    /**
     * user権限ログイン済の場合、ログアウトボタンが表示されていることをテストします。
     * (今のところ必要ないが、使う時に備えて)
     */
    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    public void test_006_show_logout_button() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/menu-separator/a[@href='/Logout']")
                                .exists());
    }

    /**
     * 未ログインの場合、記事作成ボタンが表示されていないことをテストします。
     */
    @Test
    public void test_007_non_add_article_button() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/a[@href='/Add/Form']")
                                .doesNotExist());
    }

    /**
     * admin権限ログイン済の場合、記事作成ボタンが表示されていることをテストします。
     */
    @Test
    @WithMockUser(username = "admin_user", authorities = {"ROLE_ADMIN"})
    public void test_008_show_add_article_button() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/a[@href='/Add/Form']")
                                .exists());
    }

    /**
     * user権限ログイン済の場合、記事作成ボタンが表示されていないことをテストします。
     * (今のところ必要ないが、使う時に備えて)
     */
    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    public void test_009_non_add_article_button() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/a[@href='/Add/Form']")
                                .doesNotExist());
    }

    /**
     * 未ログインの場合、記事編集ボタンが表示されていないことをテストします。
     */
    @Test
    public void test_010_non_update_article_button() throws Exception {
        this.mvc.perform(get("/").flashAttr("article", new Article()))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/a[@href='/Add/Form/']")
                                .doesNotExist());
    }

    /**
     * admin権限ログイン済の場合、記事編集ボタンが表示されていることをテストします。
     */
    @Test
    @WithMockUser(username = "admin_user", authorities = {"ROLE_ADMIN"})
    public void test_011_show_update_article_button() throws Exception {
        this.mvc.perform(get("/").flashAttr("article", new Article()))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/a[@href='/Update/Form/']")
                                .exists());
    }

    /**
     * user権限ログイン済の場合、記事編集ボタンが表示されていないことをテストします。
     * (今のところ必要ないが、使う時に備えて)
     */
    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    public void test_012_non_update_article_button() throws Exception {
        this.mvc.perform(get("/").flashAttr("article", new Article()))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/a[@href='/Add/Form/']")
                                .doesNotExist());
    }

    /**
     * 未ログインの場合、記事削除ボタンが表示されていないことをテストします。
     */
    @Test
    public void test_013_non_delete_article_button() throws Exception {
        this.mvc.perform(get("/").flashAttr("article", new Article()))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/menu-button[@rewrite-target='delete']")
                                .doesNotExist());
    }

    /**
     * admin権限ログイン済の場合、記事削除ボタンが表示されていることをテストします。
     */
    @Test
    @WithMockUser(username = "admin_user", authorities = {"ROLE_ADMIN"})
    public void test_014_show_delete_article_button() throws Exception {
        this.mvc.perform(get("/").flashAttr("article", new Article()))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/menu-button[@rewrite-target='delete']")
                                .exists());
    }

    /**
     * user権限ログイン済の場合、記事編集ボタンが表示されていないことをテストします。
     * (今のところ必要ないが、使う時に備えて)
     */
    @Test
    @WithMockUser(username = "user", authorities = {"ROLE_USER"})
    public void test_015_non_delete_article_button() throws Exception {
        this.mvc.perform(get("/").flashAttr("article", new Article()))
                .andExpect(
                        xpath("/html/body/div[1]/modal-content[@key='menu']/menu-button[@rewrite-target='delete']")
                                .doesNotExist());
    }
}
