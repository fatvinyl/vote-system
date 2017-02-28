package ru.fatvinyl.votesystem.model;

import java.util.Date;
import java.util.Set;

/**
 * @author Anton Yolgin
 */

public class User extends NamedEntity{

    private String email;

    private String password;

    private Date registered;

    private boolean enabled;

    private Set<Role> roles;

    private Restaurant user_vote;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Restaurant getUser_vote() {
        return user_vote;
    }

    public void setUser_vote(Restaurant user_vote) {
        this.user_vote = user_vote;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
