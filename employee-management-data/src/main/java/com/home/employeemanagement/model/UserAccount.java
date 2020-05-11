package com.home.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user_accounts")
public class UserAccount extends RepresentationModel<UserAccount> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String userName;

    private String email;
    private boolean enabled = true;
    @JsonIgnore
    private String password;
}
