package com.Kario.Services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Kario.Models.User;

@Service
public class UserContext {
    
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Long getCurrentUserId() {
        User usr = getCurrentUser();
        return usr.getId();
    }
}
