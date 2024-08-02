package com.nyx.nyx_backend.security.user.entities.request;

import com.nyx.nyx_backend.security.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Integer id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String role;
    private String newPassword;
    private String updateDate;
    private String creationDate;
    private String birthday;
    private String phone;
    private Boolean enabled;
    private Boolean accountNonLocked;
    private Boolean accountNonExpired;

    public static User mapRequestToEntity(UserRequest userRequest) {
        User usuario = new User();
        usuario.setId(userRequest.getId());
        usuario.setUsername(userRequest.getUsername());
        usuario.setPassword(userRequest.getPassword());
        usuario.setEmail(userRequest.getEmail());

        return usuario;
    }

}
