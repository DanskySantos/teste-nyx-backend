package com.nyx.nyx_backend.security.user.entities.response;

import com.nyx.nyx_backend.security.person.entities.Person;
import com.nyx.nyx_backend.security.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String role;
    private Person person;
    private String updateDate;
    private String creationDate;
    private String birthday;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Boolean accountNonExpired;
    private Boolean excluded;

    public static UserResponse mapEntityToResponse(User user) {

        return UserResponse.builder()
                .username(user.getUsernameForDto())
                .id(user.getId())
                .name(user.getPerson().getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .birthday(user.getPerson().getBirthday() != null ? user.getPerson().getBirthday().toString() : null)
                .role(user.getRole().name())
                .excluded(user.isExcluded())
                .build();
    }
}
