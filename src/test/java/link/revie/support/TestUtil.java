package link.revie.support;

import link.revie.model.entity.User;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * テストで使う便利メソッドを作ります。
 * 構成的に問題だったら直す
 */
@Component
public class TestUtil {

    /**
     * Expect用Userを作成します。
     * 日付はyyyy-MM-dd HH:mm:ssの文字列を指定してください。
     *
     * @param id           ID
     * @param userName     ユーザー名(userId)
     * @param password     パスワード
     * @param authority    権限
     * @param createdTime  作成日時
     * @param modifiedTime 更新日時
     * @return UserのEntity
     * @throws ParseException 日付がDate型にパース出来ない場合にスローします。
     */
    public User generateExpectedUser(Integer id, String userName, String password, User.Authority authority, String createdTime, String modifiedTime) throws ParseException {
        User expected = new User();
        expected.setId(id);
        expected.setUserName(userName);
        expected.setPassword(password);
        expected.setAuthority(authority);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        expected.setCreatedTime(new Timestamp(format.parse(createdTime).getTime()));
        expected.setModifiedTime(new Timestamp(format.parse(modifiedTime).getTime()));
        return expected;
    }
}
