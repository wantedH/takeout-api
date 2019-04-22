package ecjtu.zjf.takeoutapi.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 商户信息
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Saler implements Serializable,UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_SALER"));
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

    private static final long serialVersionUID = 1L;

    private int id;

    private String username;

    private String password;

    private String name;

    private String location;

    private String tel;

    private String avatar;
}
