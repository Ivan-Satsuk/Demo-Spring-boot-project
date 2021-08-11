package com.geekbrains.demoboot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "username")
    String name;

    @Column(name = "password")
    String password;

    @Column(name="enabled")
    Boolean enabled;

    public User() {
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "authorities",
            joinColumns = @JoinColumn(name ="username"),
            inverseJoinColumns = @JoinColumn(name="authority")
    )
    List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
