package com.atique.springbootsecuritypractice.dao;

import com.atique.springbootsecuritypractice.model.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
