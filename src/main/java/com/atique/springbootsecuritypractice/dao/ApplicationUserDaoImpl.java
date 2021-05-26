package com.atique.springbootsecuritypractice.dao;

import com.atique.springbootsecuritypractice.model.ApplicationUser;
import com.atique.springbootsecuritypractice.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.atique.springbootsecuritypractice.enums.ApplicationUserRole.*;

@Repository("fake-repo")
public class ApplicationUserDaoImpl implements ApplicationUserDao {

    private final PasswordConfig passwordConfig;

    @Autowired
    public ApplicationUserDaoImpl(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = new ArrayList<>(Arrays.asList(
                new ApplicationUser(
                        "student",
                        passwordConfig.passwordEncoder().encode("password"),
                        STUDENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "admin",
                        passwordConfig.passwordEncoder().encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "adminTrainee",
                        passwordConfig.passwordEncoder().encode("password"),
                        ADMIN_TRAINEE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        ));
        return applicationUsers;
    }
}
