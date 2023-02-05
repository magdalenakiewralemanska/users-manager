package com.sdacodecoolproject.usersmanager.user.dto;

import com.sdacodecoolproject.usersmanager.user.model.User;

import java.util.Date;
import java.util.List;

public class UserBuilder {

    private Long id;
    private String userIdNumber;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Date lastLoginDate;
    private Date lastLoginDisplay;
    private Date registrationDate;
    private String rolePermissions;
    private List<String > authorities;
    private boolean isActive;
    private boolean isNotLocked;

    public UserBuilder withId(final Long id){
        this.id = id;
        return this;
    }
    public UserBuilder withUserIdNumber(final String userIdNumber){
        this.userIdNumber = userIdNumber;
        return this;
    }

    public UserBuilder withFirstName(final String firstName){
        this.firstName = firstName;
        return this;
    }
    public UserBuilder withLastName(final String lastName){
        this.lastName = lastName;
        return this;
    }
    public UserBuilder withUsername(final String username){
        this.username = username;
        return this;
    }
    public UserBuilder withEmail(final String email){
        this.email = email;
        return this;
    }
    public UserBuilder withPassword(final String password){
        this.password = password;
        return this;
    }
    public UserBuilder withLastLoginDate(final Date lastLoginDate){
        this.lastLoginDate = lastLoginDate;
        return this;
    }
    public UserBuilder withLastLoginDisplay(final Date lastLoginDisplay){
        this.lastLoginDisplay = lastLoginDisplay;
        return this;
    }
    public UserBuilder withRegistrationDate(final Date registrationDate){
        this.registrationDate = registrationDate;
        return this;
    }
    public UserBuilder withRolePermissions(final String rolePermissions){
        this.rolePermissions = rolePermissions;
        return this;
    }
    public UserBuilder withAuthorities(List<String> authorities){
        this.authorities = authorities;
        return this;
    }
    public UserBuilder withIsActive(final boolean isActive){
        this.isActive = isActive;
        return this;
    }
    public UserBuilder withIsNonLocked(final boolean isNonLocked){
        this.isNotLocked = isNonLocked;
        return this;
    }

    public User build() {
        return new User(id, userIdNumber, firstName, lastName, username, email, password, lastLoginDate, lastLoginDisplay,
                registrationDate, rolePermissions, authorities, isActive, isNotLocked);
    }

}
