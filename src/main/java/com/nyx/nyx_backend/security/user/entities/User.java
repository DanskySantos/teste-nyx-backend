package com.nyx.nyx_backend.security.user.entities;

import com.nyx.nyx_backend.domain.common.SuperclassEntity;
import com.nyx.nyx_backend.security.person.entities.Person;
import com.nyx.nyx_backend.security.roles.entity.Role;
import com.nyx.nyx_backend.security.user.entities.request.RegisterRequest;
import com.nyx.nyx_backend.security.user.entities.request.UserRequest;
import com.nyx.nyx_backend.shared.utils.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User extends SuperclassEntity implements Serializable, UserDetails {

    @Column(name = "username", length = 255, nullable = false)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "email", length = 255, unique = true, nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "excluded")
    private boolean excluded = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        //returning email for spring security
        return email;
    }

    public String getUsernameForDto() {
        return username;
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

    public static User createUser(RegisterRequest registerRequest) {
        return User.builder()
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .excluded(false)
                .role(Role.USER)
                .build();
    }

    public static User updateUser(User user, UserRequest userRequest) {
        Date birthday = null;
        if (userRequest.getBirthday() != null && StringUtils.hasText(userRequest.getBirthday()))
            birthday = Utils.dateToSave(userRequest.getBirthday());

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.person.setBirthday(birthday);
        user.person.setName(userRequest.getName());

        return user;
    }

    public static User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return user;
    }

    public static User deleteUser(User user) {
        user.setExcluded(true);
        return user;
    }
}
