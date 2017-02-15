package link.revie.service.account.impl;

import link.revie.model.entity.User;
import link.revie.model.repository.UserRepository;
import link.revie.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * アカウント情報を操作するサービス
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * ユーザー名でユーザー情報を取得します。
     * このメソッドで返却されたユーザー情報を用いて認証処理を行います。
     *
     * @param userName ユーザー名(userId)
     * @return UserのEntity
     * @throws UsernameNotFoundException 指定されたユーザーが存在しない場合スローします。
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(userName)) {
            throw new UsernameNotFoundException("Username is Empty");
        }
        User user = this.userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found for name : " + userName);
        }
        return user;
    }
}
