package com.example.ChartTest.Service;

import com.example.ChartTest.Entity.AppUser;
import com.example.ChartTest.Entity.Role;

import java.util.List;

public interface AccountService {
     AppUser saveUser(AppUser user);
     Role saveRole(Role role);
     void addRoleToUser(String username ,String roleName);
     AppUser getUser(String username);
     List<AppUser>getUsers();




}
