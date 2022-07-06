package com.company.dto.profile;

import com.company.entity.AttachEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String jwtToken;
    private LocalDateTime createdDate;
    private String tempEmail;
    private AttachEntity photo;
}
