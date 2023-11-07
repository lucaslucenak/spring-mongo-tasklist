package com.unifacisa.tasklist.models;

import com.unifacisa.tasklist.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class UserModel implements UserDetails {

    @Id
    private String id;

    @NotNull(message = "Field username shouldn't be null")
    @NotEmpty(message = "Field username shouldn't be empty")
    @NotBlank(message = "Field username shouldn't be blank")
    @Indexed(unique = true)
    private String username;

    @NotNull(message = "Field password shouldn't be null")
    @NotEmpty(message = "Field password shouldn't be empty")
    @NotBlank(message = "Field password shouldn't be blank")
    private String password;

    @NotNull(message = "Field email shouldn't be null")
    @NotEmpty(message = "Field email shouldn't be empty")
    @NotBlank(message = "Field email shouldn't be blank")
    @Indexed(unique = true)
    @Email
    private String email;

    @NotNull(message = "Field role shouldn't be null")
    @Enumerated(value = EnumType.STRING)
    private RoleEnum role;

    private List<TaskModel> tasks;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UserModel(UserDetails userDetails) {
        BeanUtils.copyProperties(userDetails, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == RoleEnum.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
}
