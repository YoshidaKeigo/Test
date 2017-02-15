package link.revie.model.repository;

import link.revie.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * USERテーブル操作用リポジトリ
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * ユーザー名からUSERのレコードを検索し返却します。
     * useNameはUNIQUE制約を付けている為、取得できるレコードは1つです。
     *
     * @param userName ユーザー名(userId)
     * @return UserのEntity/存在しない場合Null
     */
    User findByUserName(String userName);
}
