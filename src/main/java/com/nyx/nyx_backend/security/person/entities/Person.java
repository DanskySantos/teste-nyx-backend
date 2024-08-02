package com.nyx.nyx_backend.security.person.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nyx.nyx_backend.domain.common.SuperclassEntity;
import com.nyx.nyx_backend.security.user.entities.request.RegisterRequest;
import com.nyx.nyx_backend.shared.utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_person")
public class Person extends SuperclassEntity implements Serializable {

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "birthday", length = 255)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(name = "user_id")
    private Integer userId;

    public static Person createPerson(RegisterRequest registerRequest) {
        Date birthday = null;
        if (registerRequest.getBirthday() != null && !StringUtils.isEmpty(registerRequest.getBirthday())) {
            birthday = Utils.dateToSave(registerRequest.getBirthday());
        }

        return Person.builder()
                .name(registerRequest.getName())
                .birthday(birthday)
                .build();
    }
}
