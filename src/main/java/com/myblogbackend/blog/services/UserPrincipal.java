package com.myblogbackend.blog.services;

import java.util.*;
import java.util.stream.Collectors;

import com.myblogbackend.blog.models.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
 
  	private Long id;
 
    private String name;
 
    private String email;
 
    @JsonIgnore
    private String password;
    
    private Map<String, Object> attributes;
 
    public UserPrincipal(Long id, String name, 
              String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
 
    public static UserPrincipal build(UserEntity userEntity) {
        return new UserPrincipal(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPassword()
        );
    }
 
    public Long getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }
 
    public String getEmail() {
        return email;
    }
    
    @Override
	public String getUsername() {
		return email;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
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
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}