package com.example.security.Service;

import com.example.security.Model.Admin;
import com.example.security.dto.adminDto;

public interface AdminService {
    /**
     * @param username
     * @return
     */
    Admin findByUsername(String username);
    Admin save(adminDto adminDto);
}
