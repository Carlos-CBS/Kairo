package com.Kario.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.Kario.Models.Enums.Role;

public class RoleTest {

    @Test
    void shouldHaveCorrectName() {
        assertEquals("USER", Role.USER.name());
        assertEquals("ADMIN", Role.ADMIN.name());
    }
    
}
