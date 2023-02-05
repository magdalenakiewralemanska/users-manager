package com.sdacodecoolproject.usersmanager.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_id_number")
    private String userIdNumber;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    private String username;
    private String email;

    private String password;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "last_login_display")
    private Date lastLoginDisplay;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "role_permissions")
    private String rolePermissions;
    private List<String > authorities;

    @Column(name = "active_account")
    private boolean isActive;

    @Column(name = "actual_account_status")
    private boolean isNonLocked;
}
