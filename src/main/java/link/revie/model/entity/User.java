package link.revie.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * USERテーブル用エンティティ
 */
@Setter
@Getter
@Entity
@Table(name = "USER")
public class User implements UserDetails {
    public enum Authority {
        USER,
        ADMIN,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_NAME", unique = true)
    @NotBlank
    private String userName;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "AUTHORITY", nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    @CreatedDate
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED_TIME")
    @LastModifiedDate
    private Date modifiedTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority.toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (authority != user.authority) return false;
        if (createdTime != null ? !createdTime.equals(user.createdTime) : user.createdTime != null) return false;
        return modifiedTime != null ? modifiedTime.equals(user.modifiedTime) : user.modifiedTime == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + authority.hashCode();
        result = 31 * result + createdTime.hashCode();
        result = 31 * result + modifiedTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
