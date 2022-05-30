package com.example.ChartTest.Service;

import com.example.ChartTest.DAO.AppUserRepository;
import com.example.ChartTest.DAO.RoleRepository;
import com.example.ChartTest.Entity.AppUser;
import com.example.ChartTest.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public AppUser saveUser(AppUser user) {
        String passwordCrypter= bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordCrypter);

        return userRepository.save(user);
    }

    @Override
    public Role saveRole( Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user= userRepository.findByUsername(username);
        Role role =roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        System.out.println("get Users url"+userRepository.findAll());
        return userRepository.findAll();
    }


}
