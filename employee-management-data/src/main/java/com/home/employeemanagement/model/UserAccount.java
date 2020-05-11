package com.home.employeemanagement.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(name = "user_accounts")
// Not working need to see why @JsonFilter(value = "userFilter")
public class UserAccount extends RepresentationModel<UserAccount> implements Serializable {

    private static final long serialVersionUID = 1234567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonView(Views.ExternalUser.class)
    private Long userId;

    @JsonView(Views.ExternalUser.class)
    @Column(name = "username")
    private String userName;

    @JsonView(Views.ExternalUser.class)
    private String email;

    @JsonView(Views.ExternalUser.class)
    private boolean enabled = true;

    //@JsonIgnore
    @JsonView(Views.InternalUser.class)
    private String password;

    @JsonView(Views.InternalUser.class)
    private String role;
}
