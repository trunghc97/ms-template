package com.example.trunghc.entity;

import com.example.trunghc.dto.request.common.RegisterRequest;
import com.example.trunghc.dto.request.user.CreateNewUserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {

    private String userId;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private String role;

    public User(RegisterRequest request) {
        userId = request.getUserId();
        name = request.getUserName();
        email = request.getEmail();
        role = "USER";
    }

    public User(CreateNewUserRequest request) {
        userId = request.getUserId();
        name = request.getUserName();
        email = request.getEmail();
        role = request.getRole();
    }

}
