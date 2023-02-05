package com.sdacodecoolproject.usersmanager.user.dto;

import java.util.Date;
import java.util.List;

public class UserDto {

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
    private boolean isNonLocked;

    public UserDto(Long id, String userIdNumber, String firstName, String lastName, String username, String email,
                   String password, Date lastLoginDate, Date lastLoginDisplay, Date registrationDate, String rolePermissions,
                   List<String> authorities, boolean isActive, boolean isNonLocked) {
        this.id = id;
        this.userIdNumber = userIdNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDisplay = lastLoginDisplay;
        this.registrationDate = registrationDate;
        this.rolePermissions = rolePermissions;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNonLocked = isNonLocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserIdNumber() {
        return userIdNumber;
    }

    public void setUserIdNumber(String userIdNumber) {
        this.userIdNumber = userIdNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDisplay() {
        return lastLoginDisplay;
    }

    public void setLastLoginDisplay(Date lastLoginDisplay) {
        this.lastLoginDisplay = lastLoginDisplay;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(String rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNonLocked() {
        return isNonLocked;
    }

    public void setNonLocked(boolean notLocked) {
        isNonLocked = notLocked;
    }


}
