package com.example.springhibernate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import okhttp3.HttpUrl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "\"authorization\"")
@Getter
@Setter
public class Authorization implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @Column(name = "hash_of_pass", nullable = false)
    private String hashOfPass;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE
    })
    @JsonIgnore
    @PrimaryKeyJoinColumn(name = "user_data")
    private UserData userData;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public static HttpUrl.Builder builder() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.getHashOfPass();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
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

    // Constructors, getters, and setters omitted for brevity
}
