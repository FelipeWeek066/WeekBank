package com.weeklab.weekbank.entities;

import com.weeklab.weekbank.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE tb_users SET deleted = true WHERE id=? ")
@SQLRestriction("deleted = false")
@Table(name = "tb_users")
public class User implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String password;
    private UserRole role;
    int amount;

    private boolean deleted;
    public User(String name, String encryptedPassword, UserRole role) {
        this.name = name;
        this.password = encryptedPassword;
        this.role = role;
    }
    public User(String name, String encryptedPassword) {
        this.name = name;
        this.password = encryptedPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
