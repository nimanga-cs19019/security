package com.example.security.Service.Impl;
import com.example.security.Model.Admin;
import com.example.security.Repository.AdminRepository;
import com.example.security.Repository.RoleRepository;
import com.example.security.Service.AdminService;
import com.example.security.dto.adminDto;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



import java.lang.annotation.*;
import java.util.Arrays;
import java.util.Collections;

@Service

public class AdminServiceImpl implements AdminService {

    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminRepository adminRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public Admin save(adminDto adminDto) {
        Admin admin=new Admin();
        admin.setFirstname(adminDto.getFirstname());
        admin.setLastname(adminDto.getLastname());
        admin.setUsername(admin.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRoles(Collections.singletonList(roleRepository.findByName("ADMIN")));
        return adminRepository.save(admin);
    }
}
