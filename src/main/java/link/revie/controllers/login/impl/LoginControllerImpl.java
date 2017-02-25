package link.revie.controllers.login.impl;

import link.revie.controllers.login.LoginController;
import org.springframework.stereotype.Controller;

/**
 * ログイン関係のコントローラー
 * URLマッピングはinterfaceで定義
 *
 * 実際のログイン処理等はWebSecurityConfigで実施
 * @see link.revie.config.WebSecurityConfig
 */
@Controller
public class LoginControllerImpl implements LoginController {

    /**
     * ログインページを表示します。
     * @return ログインページ(login.html)
     */
    @Override
    public String login() {
        return "login";
    }
}
