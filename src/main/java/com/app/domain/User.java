package com.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "application_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "application_user_application_role",
            joinColumns = {@JoinColumn(name = "application_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "application_role_id", referencedColumnName = "id")})
    private Set<UserRole> userRoleSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoleSet.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
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
